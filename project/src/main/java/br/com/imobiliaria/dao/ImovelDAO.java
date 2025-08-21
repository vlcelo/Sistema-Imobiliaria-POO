package br.com.imobiliaria.dao;

import br.com.imobiliaria.model.Imovel;
import br.com.imobiliaria.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImovelDAO {

    // Cadastro com validação de endereço duplicado
    public void cadastrarImovel(Scanner sc) {
        try {
            System.out.print("Endereço: ");
            String endereco = sc.nextLine();
            System.out.print("Cidade: ");
            String cidade = sc.nextLine();
            System.out.print("Tipo: ");
            String tipo = sc.nextLine();
            System.out.print("Quartos: ");
            int quartos = Integer.parseInt(sc.nextLine());
            System.out.print("Banheiros: ");
            int banheiros = Integer.parseInt(sc.nextLine());
            System.out.print("Área (m2): ");
            double area = Double.parseDouble(sc.nextLine());
            System.out.print("Valor aluguel: ");
            double valor = Double.parseDouble(sc.nextLine());

            try (Connection conn = DbUtil.getConnection();
                 PreparedStatement check = conn.prepareStatement("SELECT 1 FROM imovel WHERE endereco=?")) {
                check.setString(1, endereco);
                ResultSet rs = check.executeQuery();
                if (rs.next()) {
                    System.out.println("⚠️ Endereço já cadastrado!");
                    return;
                }
            }

            try (Connection conn = DbUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO imovel(endereco, cidade, tipo, quartos, banheiros, area_m2, valor_aluguel, status) " +
                                 "VALUES (?,?,?,?,?,?,?, 'DISPONIVEL')")) {
                stmt.setString(1, endereco);
                stmt.setString(2, cidade);
                stmt.setString(3, tipo);
                stmt.setInt(4, quartos);
                stmt.setInt(5, banheiros);
                stmt.setDouble(6, area);
                stmt.setDouble(7, valor);
                stmt.executeUpdate();
                System.out.println("✅ Imóvel cadastrado com sucesso!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Listar todos os imóveis
    public List<Imovel> listarTodos() throws SQLException {
        List<Imovel> imoveis = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM imovel")) {
            while (rs.next()) {
                imoveis.add(new Imovel(
                        rs.getInt("id"),
                        rs.getString("endereco"),
                        rs.getString("cidade"),
                        rs.getString("tipo"),
                        rs.getInt("quartos"),
                        rs.getInt("banheiros"),
                        rs.getDouble("area_m2"),
                        rs.getDouble("valor_aluguel"),
                        rs.getString("status")
                ));
            }
        }
        return imoveis;
    }

    // Listar apenas imóveis disponíveis
    public void listarDisponiveis() {
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM imovel WHERE status='DISPONIVEL'")) {

            System.out.println("===== IMÓVEIS DISPONÍVEIS =====");
            while (rs.next()) {
                System.out.printf("ID: %d | %s - %s | %s | %d quartos | %d banheiros | %.2f m² | R$ %.2f | %s%n",
                        rs.getInt("id"),
                        rs.getString("endereco"),
                        rs.getString("cidade"),
                        rs.getString("tipo"),
                        rs.getInt("quartos"),
                        rs.getInt("banheiros"),
                        rs.getDouble("area_m2"),
                        rs.getDouble("valor_aluguel"),
                        rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
