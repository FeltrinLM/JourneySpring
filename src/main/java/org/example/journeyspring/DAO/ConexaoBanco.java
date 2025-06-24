package org.example.journeyspring.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static final String URL = "jdbc:postgresql://localhost:5432/POOW1";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "1234";

    public static Connection getConexao() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver PostgreSQL não encontrado.");
            e.printStackTrace();
            throw new RuntimeException("Driver do PostgreSQL não foi carregado.", e);
        } catch (SQLException e) {
            System.err.println("Erro ao obter conexão com o banco: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Falha ao conectar no PostgreSQL.", e);
        }
    }

}
