package model;
public class Emprestimo {
    private int idemprestimo;
    private int idLivro;
    private int idUsuario;
    private boolean devolvido;

    public Emprestimo(int idemprestimo, int idLivro, int idUsuario, boolean devolvido) {
        this.idemprestimo = idemprestimo;
        this.idLivro = idLivro;
        this.idUsuario = idUsuario;
        this.devolvido = devolvido;
    }

    // Getters e Setters
    public int getId() {
        return idemprestimo;
    }

    public void setId(int idemprestimo) {
        this.idemprestimo = idemprestimo;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
    public String toString() {
        return "Emprestimo{" +
                "idemprestimo=" + idemprestimo +
                ", idLivro=" + idLivro +
                ", idUsuario=" + idUsuario +
                ", devolvido=" + devolvido +
                '}';
    }
}
