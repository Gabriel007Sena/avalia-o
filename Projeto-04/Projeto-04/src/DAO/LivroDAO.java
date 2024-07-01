package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Conexao.ConectaDB;
import model.Livro;

public class LivroDAO {
    private ConectaDB conexao;

    public LivroDAO() {
        this.conexao = new ConectaDB();
    }

    public void inserirLivro(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, ano) VALUES (?, ?, ?)";
        try (Connection connection = conexao.getConexao();
             PreparedStatement pst = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, livro.getTitulo());
            pst.setString(2, livro.getAutor());
            pst.setString(3, livro.getAno());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        livro.setId(generatedKeys.getInt(1));
                    }
                }
                System.out.println("Livro inserido com sucesso: " + livro);
            } else {
                System.out.println("Falha ao inserir o livro: " + livro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir livro: " + e.getMessage());
        }
    }

    public Livro buscarLivroPorId(int id) {
        Livro livro = null;
        String sql = "SELECT * FROM livros WHERE idlivro = ?";
        try (Connection connection = conexao.getConexao();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    livro = new Livro(rs.getInt("idlivro"),
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getString("ano"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }
        return livro;
    }

    public List<Livro> listarLivros() {
        List<Livro> livros = new LinkedList<>();
        String sql = "SELECT * FROM livros";
        try (Connection connection = conexao.getConexao();
             PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Livro livro = new Livro(rs.getInt("idlivro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("ano"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }
        return livros;
    }

    public void removerLivroPorId(int id) {
        String sql = "DELETE FROM livros WHERE idlivro = ?";
        try (Connection connection = conexao.getConexao();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livro removido com sucesso.");
            } else {
                System.out.println("Livro não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover livro: " + e.getMessage());
        }
    }
}
