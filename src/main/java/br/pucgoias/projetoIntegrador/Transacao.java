package br.pucgoias.projetoIntegrador;

import java.util.Date;

public class Transacao {


    private final CarrinhoDeCompras carrinhoDeCompras;
    private final Date data;

    public Transacao(CarrinhoDeCompras carrinhoDeCompras, Date data) {
        this.carrinhoDeCompras = carrinhoDeCompras;
        this.data = data;
    }

    public CarrinhoDeCompras getCarrinhoDeCompras() {
        return this.carrinhoDeCompras;
    }

    public Date getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "Data: " + this.data + "\n" + this.carrinhoDeCompras.toString() + "\n";
    }
}
