package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Conexao.ConectaDB;
import model.Emprestimo;
import model.Usuario;

public class EmprestimoDAO {
    private ConectaDB conexao;

    public EmprestimoDAO() {
        this.conexao = new ConectaDB();
    }

    public void realizarEmprestimo(int idlivro, int idusuario) {
        String sqlInsert = "INSERT INTO emprestimos (idlivro, idusuario, devolvido) VALUES (?, ?, false)";
        String sqlSelect = "SELECT titulo FROM livros WHERE idlivro = ?";
        String nomeLivro = "";

        try (Connection connection = conexao.getConexao();
             PreparedStatement pstInsert = connection.prepareStatement(sqlInsert);
             PreparedStatement pstSelect = connection.prepareStatement(sqlSelect)) {

            // Realiza o empréstimo
            pstInsert.setInt(1, idlivro);
            pstInsert.setInt(2, idusuario);
            pstInsert.executeUpdate();
            System.out.println("Empréstimo realizado com sucesso.");

            // Obtém o nome do livro emprestado
            pstSelect.setInt(1, idlivro);
            ResultSet rs = pstSelect.executeQuery();
            if (rs.next()) {
                nomeLivro = rs.getString("titulo");
            }

            // Obtém o nome do usuário para quem foi emprestado
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.buscarUsuarioPorId(idusuario);
            String nomeUsuario = usuario != null ? usuario.getNome() : "Usuário não encontrado";

            // Mostra informações
            System.out.println("Livro emprestado: " + nomeLivro);
            System.out.println("Emprestado para: " + nomeUsuario);

        } catch (SQLException e) {
            System.out.println("Erro ao realizar empréstimo: " + e.getMessage());
        }
    }

    public void realizarDevolucao(int idemprestimos) {
        String sql = "UPDATE emprestimos SET devolvido = true WHERE idemprestimos = ?";
        try (Connection connection = conexao.getConexao();
             PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setInt(1, idemprestimos);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Devolução realizada com sucesso.");
            } else {
                System.out.println("Empréstimo não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao realizar devolução: " + e.getMessage());
        }
    }

    public List<Emprestimo> listarEmprestimos() {
        List<Emprestimo> emprestimos = new LinkedList<>();
        String sql = "SELECT * FROM emprestimos";
        try (Connection connection = conexao.getConexao();
             PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo(rs.getInt("idemprestimos"),
                        rs.getInt("idlivro"),
                        rs.getInt("idusuario"),
                        rs.getBoolean("devolvido"));
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar empréstimos: " + e.getMessage());
        }
        return emprestimos;
    }

    public static int lerIdLivroDoUsuario(Scanner scanner) {
        System.out.print("Digite o ID do livro que deseja emprestar: ");
        return scanner.nextInt();
    }

    public static int lerIdUsuario(Scanner scanner) {
        System.out.print("Digite o ID do usuário que está realizando o empréstimo: ");
        return scanner.nextInt();
    }

    public static int lerIdEmprestimo(Scanner scanner) {
        System.out.print("Digite o ID do empréstimo que deseja devolver: ");
        return scanner.nextInt();
    }
}
