package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDB {

    private static final String URL = "jdbc:mysql://localhost:3306/test"; // Ajuste a URL do banco de dados
    private static final String USER = "root"; // Ajuste o usuário do banco de dados
    private static final String PASSWORD = "root"; // Ajuste a senha do banco de dados

    public Connection getConexao() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
