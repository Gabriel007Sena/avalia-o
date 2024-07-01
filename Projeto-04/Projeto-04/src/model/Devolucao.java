package model;
public class Devolucao {
    private int idemprestimo;
    private boolean concluido;

    public Devolucao(int idemprestimo, boolean concluido) {
        this.idemprestimo = idemprestimo;
        this.concluido = concluido;
    }

    // Getters e Setters
    public int getIdEmprestimo() {
        return idemprestimo;
    }

    public void setIdEmprestimo(int idemprestimo) {
        this.idemprestimo = idemprestimo;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }
    
    public String toString() {
        return "Devolucao{" +
                "idemprestimo=" + idemprestimo +
                ", concluido=" + concluido +
                '}';
    }
}
