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


    public static void enviarEmailNovaCompra(Usuario vendedor, Usuario comprador,Produto produto){

        System.out.println("---------------------------------Email---------------------------------------");
        System.out.println("De: " + comprador.getLogin().toString());
        System.out.println("Para: " + vendedor.getLogin().toString());
        System.out.println("_________________________________Text:_______________________________________");
        System.out.println("Olá, " + vendedor.getLogin().toString()+ ". O usuário " + comprador.getLogin().toString()
                + " iniciou a compra de "+ produto.getQuantidade() +" unidades do produto: " + produto.getNome());
    }
}


