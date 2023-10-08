package br.pucgoias.projetoIntegrador;

public class ItemCarrinho {

    private final Livro livro;
    private int quantidade;

    public ItemCarrinho(Livro livro, int quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
    }

    public Livro getLivro() {
        return this.livro;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new Error("Quantidade inválida");
        }

        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return livro.getTitulo() + " - " + livro.getAutor() + " - " + livro.getPreco() + " - " + quantidade + " - " + getValorTotal() + "\n";
    }

    public double getValorTotal() {
        return this.livro.getPreco() * this.quantidade;
    }
}
