package br.com.imobiliaria.dao;

import br.com.imobiliaria.model.Cliente;
import br.com.imobiliaria.util.DbUtil;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteDAO {

    public void cadastrarCliente(Scanner sc) {
        try {
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("CPF (somente números): ");
            String cpf = sc.nextLine();
            System.out.print("Telefone (somente números): ");
            String tel = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();

            if (!cpf.matches("\\d+")) {
                System.out.println("⚠️ CPF inválido, use apenas números!");
                return;
            }
            if (!tel.matches("\\d+")) {
                System.out.println("⚠️ Telefone inválido, use apenas números!");
                return;
            }

            try (Connection conn = DbUtil.getConnection();
                 PreparedStatement check = conn.prepareStatement("SELECT 1 FROM cliente WHERE cpf=?")) {
                check.setString(1, cpf);
                ResultSet rs = check.executeQuery();
                if (rs.next()) {
                    System.out.println("⚠️ CPF já cadastrado!");
                    return;
                }
            }

            try (Connection conn = DbUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO cliente(nome, cpf, telefone, email) VALUES (?,?,?,?)")) {
                stmt.setString(1, nome);
                stmt.setString(2, cpf);
                stmt.setString(3, tel);
                stmt.setString(4, email);
                stmt.executeUpdate();
                System.out.println("✅ Cliente cadastrado com sucesso!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> listarTodos() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM cliente")) {
            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("email")
                ));
            }
        }
        return clientes;
    }
}
