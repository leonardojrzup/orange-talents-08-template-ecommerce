package br.com.leonardo.mercadolivre.model;

class EnviarEmail {

    public void enviarEmailNovaPergunta(Usuario vendedor, Usuario comprador,Produto produto){

        System.out.println("---------------------------------Email---------------------------------------");
        System.out.println("De: " + comprador.getLogin().toString());
        System.out.println("Para: " + vendedor.getLogin().toString());
        System.out.println("_________________________________Text:_______________________________________");
        System.out.println("Boa Tarde/Noite, " + vendedor.getLogin().toString()+ ". O usuário " + comprador.getLogin().toString()
                + " cadastrou uma nova pergunta para o produto" + produto.getNome());
    }

}


