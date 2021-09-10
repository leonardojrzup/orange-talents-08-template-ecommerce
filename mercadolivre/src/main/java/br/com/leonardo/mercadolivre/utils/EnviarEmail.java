package br.com.leonardo.mercadolivre.utils;

import br.com.leonardo.mercadolivre.model.Produto;
import br.com.leonardo.mercadolivre.model.Usuario;

public class EnviarEmail {

    public static void enviarEmailNovaPergunta(Usuario vendedor, Usuario comprador, Produto produto) {

        System.out.println("---------------------------------Email---------------------------------------");
        System.out.println("De: " + comprador.getLogin().toString());
        System.out.println("Para: " + vendedor.getLogin().toString());
        System.out.println("_________________________________Text:_______________________________________");
        System.out.println("Olá, " + vendedor.getLogin().toString() + ". O usuário " + comprador.getLogin().toString()
                + " cadastrou uma nova pergunta para o produto: " + produto.getNome());
    }


    public static void enviarEmailNovaCompra(Usuario vendedor, Usuario comprador, Produto produto, Integer quantidade) {

        System.out.println("---------------------------------Email---------------------------------------");
        System.out.println("De: mercado.livre@ficticio.com.br ");
        System.out.println("Para: " + vendedor.getLogin().toString());
        System.out.println("_________________________________Text:_______________________________________");
        System.out.println("Olá, " + vendedor.getLogin().toString() + ". O usuário " + comprador.getLogin().toString()
                + " iniciou a compra de " + quantidade + " unidades do produto: " + produto.getNome());

        System.out.println("______________________________________________________________________________");
        System.out.println("Caso a compra seja finalizada restarão," + produto.getQuantidade() + " unidades do produto");

    }


    public static void enviarEmailCompraSucesso(Usuario comprador, Produto produto, Integer quantidade) {
        System.out.println("---------------------------------Email---------------------------------------");
        System.out.println("De: mercado.livre@ficticio.com.br ");
        System.out.println("Para: " + comprador.getLogin().toString());
        System.out.println("_________________________________Text:_______________________________________");
        System.out.println("Olá, " + comprador.getLogin().toString() + ". A compra de  " + quantidade
                + " itens do produto " + produto.getNome() + "Foi finalizada e está a caminho");

        System.out.println("______________________________________________________________________________");
        System.out.println("O prazo de entrega é de 90 dias");


    }

    public static void enviarEmailCompraFalhou(Usuario comprador, Produto produto, Integer quantidade, String caminho, Long idCompra) {
        System.out.println("---------------------------------Email---------------------------------------");
        System.out.println("De: mercado.livre@ficticio.com.br ");
        System.out.println("Para: " + comprador.getLogin().toString());
        System.out.println("_________________________________Text:_______________________________________");
        System.out.println("Olá, " + comprador.getLogin().toString() + ". A compra de  " + quantidade
                + " itens do produto " + produto.getNome() + "Falhou. Por gentileza tentar realizar o pagamento novamente");
        System.out.println("______________________________________________________________________________");
        System.out.println("Para realizar uma nova tentativa de pagamento, clique aqui ou acesse o link http://localhost:8080/" + caminho + idCompra);


    }


}



