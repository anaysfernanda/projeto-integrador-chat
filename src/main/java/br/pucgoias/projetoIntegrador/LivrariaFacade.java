package br.pucgoias.projetoIntegrador;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LivrariaFacade {

    private final ArrayList<Transacao> transacoes;
    private final ArrayList<Livro> livros;
    private final ArrayList<Cliente> clientesCadastrados;

    public LivrariaFacade(
            ArrayList<Transacao> transacoes,
            ArrayList<Livro> livros,
            ArrayList<Cliente> clientes
    ) {
        this.transacoes = transacoes;
        this.livros = livros;
        this.clientesCadastrados = clientes;
    }

    public void cadastrarCliente(Cliente cliente) {
        this.clientesCadastrados.add(cliente);
    }

    public void adicionarLivro(Livro livro) {
        this.livros.add(livro);
    }

    public Livro buscarLivro(String termo) {
        return livros.stream().filter(livro -> livro.getTitulo().equals(termo) || livro.getAutor().equals(termo))
                .findFirst().orElse(null);
    }

    public Cliente buscarClienteCadastro(Cliente cliente) {
        for (Cliente clienteCadastro : this.clientesCadastrados) {
            if (clienteCadastro.equals(cliente)) {
                return clienteCadastro;
            }
        }
        return null;
    }

    public void registarTransacao(Transacao transacao) {
        this.transacoes.add(transacao);
    }

    public String listaTransacoesPorCliente(Cliente cliente) {
        ArrayList<Transacao> transacoesCliente = (ArrayList<Transacao>) transacoes.stream()
                .filter(transacao -> transacao.getCarrinhoDeCompras().getCliente().equals(cliente))
                .collect(Collectors.toList());

        if (transacoesCliente.isEmpty()) {
            return "Cliente ainda nao fez pedido\n";
        }

        StringBuilder transacoesClienteString = new StringBuilder();
        for (Transacao transacao : transacoesCliente) {
            transacoesClienteString.append(transacao.toString());
        }

        return transacoesClienteString.toString();
    }

}
