package br.com.imobiliaria;

import br.com.imobiliaria.dao.ClienteDAO;
import br.com.imobiliaria.dao.ImovelDAO;
import br.com.imobiliaria.dao.ContratoDAO;
import br.com.imobiliaria.model.Cliente;
import br.com.imobiliaria.model.Imovel;
import br.com.imobiliaria.model.Contrato;
import br.com.imobiliaria.util.DbUtil;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initDb();

        ClienteDAO clienteDAO = new ClienteDAO();
        ImovelDAO imovelDAO = new ImovelDAO();
        ContratoDAO contratoDAO = new ContratoDAO();

        while (true) {
            System.out.println("\n===== SISTEMA IMOBILIÁRIA =====");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Cadastrar imóvel");
            System.out.println("3. Cadastrar contrato");
            System.out.println("4. Listar imóveis disponíveis");
            System.out.println("5. Listar contratos ativos");
            System.out.println("6. Listar clientes com mais contratos");
            System.out.println("7. Listar contratos expirando em 30 dias");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            int opcao = Integer.parseInt(sc.nextLine());
            switch (opcao) {
                case 1 -> clienteDAO.cadastrarCliente(sc);
                case 2 -> imovelDAO.cadastrarImovel(sc);
                case 3 -> contratoDAO.cadastrarContrato(sc);
                case 4 -> imovelDAO.listarDisponiveis();
                case 5 -> contratoDAO.listarAtivos();
                case 6 -> contratoDAO.listarClientesComMaisContratos();
                case 7 -> contratoDAO.listarContratosExpirando30Dias();
                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void initDb() {
        try (Connection conn = DbUtil.getConnection(); Statement stmt = conn.createStatement()) {
            String sql = new String(Main.class.getResourceAsStream("/schema.sql").readAllBytes());
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
