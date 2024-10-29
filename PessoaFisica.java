package dev.etech.agencia;

import br.com.caelum.stella.format.CPFFormatter;

public class PessoaFisica extends Pessoa {

    private String cpf;
    private CPFFormatter cpfFormatter = new CPFFormatter();

    public PessoaFisica(String nome, String cpf, String email) {
        super(nome, email);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "PessoaFisica{" +
               "cpf='" + cpfFormatter.format(cpf) + '\'' +
               ']';
    }
}