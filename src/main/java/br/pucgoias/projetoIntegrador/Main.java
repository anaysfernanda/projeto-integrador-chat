package br.pucgoias.projetoIntegrador;

import java.util.ArrayList;

public class Main {
    private static LivrariaFacade init() {
        ArrayList<Transacao> transacoes = new ArrayList<>();
        ArrayList<Livro> livros = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();

        LivrariaFacade livraria = new LivrariaFacade(transacoes, livros, clientes);

        livraria.adicionarLivro(new Livro("13339", "O Senhor dos Anéis", "J. R. R. Tolkien", 50.0f));
        livraria.adicionarLivro(new Livro("13666", "O Hobbit", "J. R. R. Tolkien", 30.0f));
        livraria.adicionarLivro(new Livro("12345", "O Silmarillion", "J. R. R. Tolkien", 40.0f));
        livraria.adicionarLivro(new Livro("43256", "Anjos e Demônios", "Dan Brown", 30.0f));
        livraria.adicionarLivro(new Livro("65436", "Fortaleza Digital", "Dan Brown", 25.0f));
        livraria.adicionarLivro(new Livro("12342", "O Código da Vinci", "Dan Brown", 35.0f));

        livraria.cadastrarCliente(new Cliente("João", "12345678910"));
        livraria.cadastrarCliente(new Cliente("Maria", "12345678911"));
        livraria.cadastrarCliente(new Cliente("José", "12345678912"));
        livraria.cadastrarCliente(new Cliente("Pedro", "12345678913"));
        livraria.cadastrarCliente(new Cliente("Ana", "12345678914"));

        return livraria;

    }

    public static void main(String[] args) {

        LivrariaCLI livrariaCLI = new LivrariaCLI(init());

        livrariaCLI.menuInicial();
        System.exit(0);

    }


}

