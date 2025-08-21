package br.com.imobiliaria.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
    private static final String URL = "jdbc:sqlite:imobiliaria.db";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            throw new RuntimeException("Erro na conexão com o banco", e);
        }
    }
}
