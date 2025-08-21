package br.com.imobiliaria.dao;

import br.com.imobiliaria.util.DbUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class ContratoDAO {

    // Cadastrar contrato
    public void cadastrarContrato(Scanner sc) {
        try {
            System.out.print("ID Cliente: ");
            int idCliente = Integer.parseInt(sc.nextLine());
            System.out.print("ID Imóvel: ");
            int idImovel = Integer.parseInt(sc.nextLine());
            System.out.print("Valor mensal: ");
            double valor = Double.parseDouble(sc.nextLine());
            System.out.print("Data início (AAAA-MM-DD): ");
            String dataInicio = sc.nextLine();
            System.out.print("Data fim (AAAA-MM-DD): ");
            String dataFim = sc.nextLine();

            // Verifica se imóvel já está alugado
            try (Connection conn = DbUtil.getConnection();
                 PreparedStatement check = conn.prepareStatement(
                         "SELECT 1 FROM contrato WHERE imovel_id=? AND status='ATIVO'")) {
                check.setInt(1, idImovel);
                ResultSet rs = check.executeQuery();
                if (rs.next()) {
                    System.out.println("⚠️ Imóvel já alugado!");
                    return;
                }
            }

            // Inserção
            try (Connection conn = DbUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO contrato(cliente_id, imovel_id, valor_mensal, data_inicio, data_fim, status) " +
                                 "VALUES (?, ?, ?, ?, ?, 'ATIVO')")) {
                stmt.setInt(1, idCliente);
                stmt.setInt(2, idImovel);
                stmt.setDouble(3, valor);
                stmt.setString(4, dataInicio);
                stmt.setString(5, dataFim);
                stmt.executeUpdate();
                System.out.println("✅ Contrato cadastrado com sucesso!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Listar contratos ativos
    public void listarAtivos() {
        String sql = "SELECT * FROM contrato WHERE status='ATIVO'";
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("===== CONTRATOS ATIVOS =====");
            while (rs.next()) {
                System.out.printf("ID: %d | Cliente ID: %d | Imóvel ID: %d | R$ %.2f/mês | %s a %s | %s%n",
                        rs.getInt("id"),
                        rs.getInt("cliente_id"),
                        rs.getInt("imovel_id"),
                        rs.getDouble("valor_mensal"),
                        rs.getString("data_inicio"),
                        rs.getString("data_fim"),
                        rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Contratos expirando nos próximos 30 dias
    public void listarContratosExpirando30Dias() {
        LocalDate hoje = LocalDate.now();
        LocalDate limite = hoje.plusDays(30);
        String sql = "SELECT * FROM contrato WHERE status='ATIVO' AND data_fim BETWEEN ? AND ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, hoje.toString());
            stmt.setString(2, limite.toString());
            ResultSet rs = stmt.executeQuery();

            System.out.println("===== CONTRATOS EXPIRANDO NOS PRÓXIMOS 30 DIAS =====");
            while (rs.next()) {
                System.out.printf("ID: %d | Cliente ID: %d | Imóvel ID: %d | Fim: %s%n",
                        rs.getInt("id"),
                        rs.getInt("cliente_id"),
                        rs.getInt("imovel_id"),
                        rs.getString("data_fim"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Clientes com mais contratos
    public void listarClientesComMaisContratos() {
        String sql = "SELECT cliente_id, COUNT(*) AS total FROM contrato GROUP BY cliente_id ORDER BY total DESC LIMIT 5";

        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("===== CLIENTES COM MAIS CONTRATOS =====");
            while (rs.next()) {
                System.out.printf("Cliente ID: %d | Total de contratos: %d%n",
                        rs.getInt("cliente_id"),
                        rs.getInt("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
