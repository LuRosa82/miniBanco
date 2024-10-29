package dev.etech.agencia;


public class Pessoa {
    private String nome;
    private String email;


    public Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;

    }

    protected String getNome() {
        return nome;
    }

    protected void setNome(String nome) {
        this.nome = nome;
    }


    protected String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }


}






