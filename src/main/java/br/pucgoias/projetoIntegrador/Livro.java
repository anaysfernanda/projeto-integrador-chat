package br.pucgoias.projetoIntegrador;

import java.util.Objects;

public class Livro {
    private final String ISBN;
    private final String titulo;
    private final String autor;
    private final float preco;

    public Livro(String ISBN, String titulo, String autor, float preco) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public float getPreco() {
        return preco;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Livro livro = (Livro) object;

        return this.getISBN().equals(livro.getISBN());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getISBN());
    }
}