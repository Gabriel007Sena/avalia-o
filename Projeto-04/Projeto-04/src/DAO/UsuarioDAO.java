package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Conexao.ConectaDB;
import model.Usuario;

public class UsuarioDAO {
    private ConectaDB conexao;

    public UsuarioDAO() {
        this.conexao = new ConectaDB();
    }

    public void inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
        try (Connection connection = conexao.getConexao();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.executeUpdate();
            System.out.println("Usuário inserido com sucesso: " + usuario);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    public Usuario buscarUsuarioPorId(int id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE idusuario = ?";
        try (Connection connection = conexao.getConexao();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getInt("idusuario"),
                        rs.getString("nome"),
                        rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new LinkedList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection connection = conexao.getConexao();
             PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("idusuario"),
                        rs.getString("nome"),
                        rs.getString("email"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    public void removerUsuarioPorId(int id) {
        String sql = "DELETE FROM usuarios WHERE idusuario = ?";
        try (Connection connection = conexao.getConexao();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário removido com sucesso.");
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover usuário: " + e.getMessage());
        }
    }
}
