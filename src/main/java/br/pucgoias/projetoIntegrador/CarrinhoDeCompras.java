package br.pucgoias.projetoIntegrador;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class CarrinhoDeCompras {

    private final String idCarrinhoDeCompras;
    private final Cliente cliente;
    private final ArrayList<ItemCarrinho> itensCarrinho = new ArrayList<>();

    public CarrinhoDeCompras(Cliente cliente) {

        this.idCarrinhoDeCompras = UUID.randomUUID().toString();
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public String getId() {
        return this.idCarrinhoDeCompras;
    }

    public void adicionarItem(Livro livro, int quantidade) {
        if (!itensCarrinho.isEmpty()) {
            Optional<ItemCarrinho> itemExistente = itensCarrinho.stream()
                    .filter(item -> item.getLivro().equals(livro))
                    .findFirst();

            if (itemExistente.isPresent()) {
                ItemCarrinho item = itemExistente.get();
                item.setQuantidade(item.getQuantidade() + quantidade);
                return;
            }
        }

        itensCarrinho.add(new ItemCarrinho(livro, quantidade));
    }

    @Override
    public String toString() {
        return "Carrinho de compras do cliente " + cliente.getNome() + "\n" + listarCarrinho();
    }

    public boolean estaVazio() {
        return itensCarrinho.isEmpty();
    }

    public String listarCarrinho() {
        if (itensCarrinho.isEmpty()) {
            return "Carrinho vazio";
        }

        int valorTotal = 0;
        StringBuilder itens = new StringBuilder();

        for (ItemCarrinho item : itensCarrinho) {
            itens.append(item.toString());
            valorTotal += item.getValorTotal();
        }

        return itens + "Valor total: " + valorTotal;
    }

    public void finalizarCompra() {


    }


}
