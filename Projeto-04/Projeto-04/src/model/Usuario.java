package model;
public class Usuario {
    private int idusuario;
    private String nome;
    private String email;

    public Usuario(int idusuario, String nome, String email) {
        this.idusuario = idusuario;
        this.nome = nome;
        this.email = email;
    }

    // Getters e Setters
    public int getId() {
        return idusuario;
    }

    public void setId(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Usuario{" +
                "idusuario=" + idusuario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

