package br.pucgoias.projetoIntegrador;

import java.util.Date;

public class LivrariaCLI {

    private final LivrariaFacade livrariaFacade;
    private final String mensagemMenuPrincipal = "Olá, seja bem vindo a livraria!\n";
    private final String mensagemRetorno = "Deseja retornar ao menu inicial?\n1 - Sim\n2 - Não\n";
    private final String mensagemMenuAcao = "O que deseja fazer hoje?\n";
    private final String mensagemMenuBuscarLivro = "Digite o termo de busca:\n";
    private final String mensagemMenuInicialCliente = "Primeiramente, digite o seu nome:\n";
    private final String mensagemMenuCadastrarCliente = "Digite o seu CPF:\n";
    private CarrinhoDeCompras carrinhoDeCompras;

    LivrariaCLI(LivrariaFacade livrariaFacade) {
        this.livrariaFacade = livrariaFacade;
    }

    public void confirmarSaida() {
        System.out.println(mensagemRetorno);
        int opcao = Integer.parseInt(System.console().readLine());
        if (opcao == 1) {
            if (carrinhoDeCompras == null) {
                menuInicial();
            } else {
                menuOpcoes();
            }
        } else {
            System.exit(0);
        }
    }

    public void menuInicial() {
        System.out.println(mensagemMenuPrincipal);
        atenderCliente();
    }

    private void atenderCliente() {
        System.out.println(mensagemMenuInicialCliente);
        String nome = System.console().readLine();
        this.carrinhoDeCompras = new CarrinhoDeCompras(new Cliente(nome));
        System.out.println("Olá, " + this.carrinhoDeCompras.getCliente().getNome() + "!");
        menuOpcoes();
    }

    public void menuOpcoes() {
        System.out.println(mensagemMenuAcao);
        System.out.println("1 - Buscar livro\n" +
                "2 - Listar pedidos\n" +
                "3 - Ver carrinho\n" +
                "4 - Fechar pedido\n" +
                "0 - Sair\n");
        int opcao = Integer.parseInt(System.console().readLine());


        switch (opcao) {
            case 1:
                menuBuscarLivro();
                break;
            case 2:
                listarPedidos();
                break;
            case 3:
                verCarrinho();
                break;
            case 4:
                fecharPedido();
                break;
            case 0:
                confirmarSaida();
                break;
            default:
                System.out.println("Opção inválida!");
                menuOpcoes();
                break;
        }

    }

    public void menuBuscarLivro() {
        System.out.println(mensagemMenuBuscarLivro);
        String termo = System.console().readLine();
        Livro livro = livrariaFacade.buscarLivro(termo);

        if (livro == null) {
            System.out.println("Livro não encontrado!");
            confirmarSaida();
        } else {
            System.out.println("Livro encontrado!");
            menuAdicionarAoCarrinho(livro);
        }
    }

    public void menuAdicionarAoCarrinho(Livro livro) {
        System.out.println("Deseja adicionar " + livro.getTitulo() + " ao carrinho?\n1 - Sim\n2 - Não\n");
        int opcao = Integer.parseInt(System.console().readLine());
        switch (opcao) {
            case 1:
                adicionarAoCarrinho(livro);
                break;
            case 2:
                menuOpcoes();
                break;
            default:
                System.out.println("Opção inválida!");
                menuAdicionarAoCarrinho(livro);
                break;
        }
    }

    public void adicionarAoCarrinho(Livro livro) {
        System.out.println("Digite a quantidade:\n");
        int quantidade = Integer.parseInt(System.console().readLine());
        this.carrinhoDeCompras.adicionarItem(livro, quantidade);
        System.out.println("Livro adicionado ao carrinho com sucesso!");
        menuOpcoes();
    }


    private void cadastrarCliente() {
        Cliente clienteAtual = this.carrinhoDeCompras.getCliente();

        if (clienteAtual.getCpf() == null) {

            System.out.println("Cliente não cadastrado!");
            System.out.println(mensagemMenuCadastrarCliente);
            String cpf = System.console().readLine();
            clienteAtual.setCpf(cpf);
        }

        livrariaFacade.cadastrarCliente(clienteAtual);
        System.out.println("Cliente cadastrado com sucesso!");
        fecharPedido();
    }


    private void fecharPedido() {
        Cliente cliente = this.carrinhoDeCompras.getCliente();

        if (cliente.getCpf() == null || this.livrariaFacade.buscarClienteCadastro(cliente) == null) {
            cadastrarCliente();
        }

        if (this.carrinhoDeCompras.estaVazio()) {
            System.out.println("Carrinho vazio!");
            menuOpcoes();
        }

        livrariaFacade.registarTransacao(new Transacao(this.carrinhoDeCompras, new Date()));
        System.out.println("Pedido realizado com sucesso!");
        this.carrinhoDeCompras = new CarrinhoDeCompras(this.carrinhoDeCompras.getCliente());
        confirmarSaida();

    }

    private void listarPedidos() {
        Cliente clienteAtual = this.carrinhoDeCompras.getCliente();

        if (clienteAtual.getCpf() == null) {
            System.out.println("Entre o seu CPF para ver os pedidos");
            String cpf = System.console().readLine();
            clienteAtual.setCpf(cpf);
        }

        String pedidos = this.livrariaFacade.listaTransacoesPorCliente(clienteAtual);

        System.out.println(pedidos);
        menuOpcoes();
    }

    private void verCarrinho() {
        System.out.println(this.carrinhoDeCompras.toString());
        menuOpcoes();
    }


}
