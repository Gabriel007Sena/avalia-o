package model;

public class Livro {
    private int idlivro;
    private String titulo;
    private String autor;
    private String ano;

    public Livro(int idlivro, String titulo, String autor, String ano) {
        this.idlivro = idlivro;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    // Getters e Setters
    public int getId() {
        return idlivro;
    }

    public void setId(int idlivro) {
        this.idlivro = idlivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "idlivro=" + idlivro +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", ano='" + ano + '\'' +
                '}';
    }
}
