package br.com.leonardo.mercadolivre.model;

public class EnviarEmail {

    public static void enviarEmailNovaPergunta(Usuario vendedor, Usuario comprador,Produto produto){

        System.out.println("---------------------------------Email---------------------------------------");
        System.out.println("De: " + comprador.getLogin().toString());
        System.out.println("Para: " + vendedor.getLogin().toString());
        System.out.println("_________________________________Text:_______________________________________");
        System.out.println("Olá, " + vendedor.getLogin().toString()+ ". O usuário " + comprador.getLogin().toString()
                + " cadastrou uma nova pergunta para o produto: " + produto.getNome());
    }


    public static void enviarEmailNovaCompra(Usuario vendedor, Usuario comprador,Produto produto, Integer quantidade){

        System.out.println("---------------------------------Email---------------------------------------");
        System.out.println("De: " + comprador.getLogin().toString());
        System.out.println("Para: " + vendedor.getLogin().toString());
        System.out.println("_________________________________Text:_______________________________________");
        System.out.println("Olá, " + vendedor.getLogin().toString()+ ". O usuário " + comprador.getLogin().toString()
                + " iniciou a compra de "+ quantidade +" unidades do produto: " + produto.getNome());

        System.out.println("______________________________________________________________________________");
        System.out.println("Caso a compra seja finalizada restarão," + produto.getQuantidade() + " unidades do produto");

    }
}


