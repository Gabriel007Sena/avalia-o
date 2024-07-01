import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;

import DAO.EmprestimoDAO;
import DAO.LivroDAO;
import DAO.UsuarioDAO;
import Conexao.ConectaDB;
import model.Livro;
import model.Usuario;
import model.Emprestimo;

public class MainApp {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LivroDAO livroDAO = new LivroDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

        // Testando a conexão
        ConectaDB conectaDB = new ConectaDB();
        try (Connection connection = conectaDB.getConexao()) {
            if (connection != null) {
                System.out.println("Conexão estabelecida com sucesso!");
            } else {
                System.out.println("Falha na conexão!");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Inserir Livro");
            System.out.println("2. Buscar Livro por ID");
            System.out.println("3. Listar Livros");
            System.out.println("4. Remover Livro por ID");
            System.out.println("5. Inserir Usuário");
            System.out.println("6. Buscar Usuário por ID");
            System.out.println("7. Listar Usuários");
            System.out.println("8. Remover Usuário por ID");
            System.out.println("9. Realizar Empréstimo");
            System.out.println("10. Realizar Devolução");
            System.out.println("11. Listar Empréstimos");
            System.out.println("12. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    scanner.nextLine();  // Consome a nova linha deixada pelo nextInt()
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o autor do livro: ");
                    String autor = scanner.nextLine();
                    System.out.print("Digite o ano do livro: ");
                    String ano = scanner.nextLine();
                    Livro livro = new Livro(0, titulo, autor, ano);
                    livroDAO.inserirLivro(livro);
                    break;

                case 2:
                    System.out.print("Digite o ID do livro: ");
                    int idLivro = lerInteiro(scanner);
                    Livro livroEncontrado = livroDAO.buscarLivroPorId(idLivro);
                    System.out.println(livroEncontrado != null ? livroEncontrado : "Livro não encontrado.");
                    break;
                case 3:
                    List<Livro> livros = livroDAO.listarLivros();
                    if (livros.isEmpty()) {
                        System.out.println("Nenhum livro encontrado.");
                    } else {
                        livros.forEach(System.out::println);
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID do livro: ");
                    int idLivroRemover = lerInteiro(scanner);
                    livroDAO.removerLivroPorId(idLivroRemover);
                    break;
                case 5:
                    scanner.nextLine();  // Consome a nova linha deixada pelo nextInt()
                    System.out.print("Digite o nome do usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o email do usuário: ");
                    String email = scanner.nextLine();
                    Usuario usuario = new Usuario(0, nome, email);
                    usuarioDAO.inserirUsuario(usuario);
                    break;
                case 6:
                    System.out.print("Digite o ID do usuário: ");
                    int idUsuario = lerInteiro(scanner);
                    Usuario usuarioEncontrado = usuarioDAO.buscarUsuarioPorId(idUsuario);
                    System.out.println(usuarioEncontrado != null ? usuarioEncontrado : "Usuário não encontrado.");
                    break;
                case 7:
                    List<Usuario> usuarios = usuarioDAO.listarUsuarios();
                    if (usuarios.isEmpty()) {
                        System.out.println("Nenhum usuário encontrado.");
                    } else {
                        usuarios.forEach(System.out::println);
                    }
                    break;
                case 8:
                    System.out.print("Digite o ID do usuário: ");
                    int idUsuarioRemover = lerInteiro(scanner);
                    usuarioDAO.removerUsuarioPorId(idUsuarioRemover);
                    break;
                case 9:
                    int idLivroEmprestimo = EmprestimoDAO.lerIdLivroDoUsuario(scanner);
                    int idUsuarioEmprestimo = EmprestimoDAO.lerIdUsuario(scanner);
                    emprestimoDAO.realizarEmprestimo(idLivroEmprestimo, idUsuarioEmprestimo);
                    break;
                case 10:
                    int idEmprestimoDevolucao = EmprestimoDAO.lerIdEmprestimo(scanner);
                    emprestimoDAO.realizarDevolucao(idEmprestimoDevolucao);
                    break;
                case 11:
                    List<Emprestimo> emprestimos = emprestimoDAO.listarEmprestimos();
                    if (emprestimos.isEmpty()) {
                        System.out.println("Nenhum empréstimo encontrado.");
                    } else {
                        emprestimos.forEach(System.out::println);
                    }
                    break;
                case 12:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static int lerInteiro(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                scanner.next(); // descarta a entrada inválida
            }
        }
    }
}
