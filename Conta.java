package dev.etech.agencia;

import dev.etech.utils.Utils;

public class Conta {
    private static int contadorConta = 1;
    private int conta;
    private Pessoa titular;
    private String documento;
    private Double saldo = 0.0;

    public Conta(PessoaFisica titular) {
        this.conta = contadorConta;
        this.titular = titular;
        this.documento = titular.getCpf();
        contadorConta += 1;
    }

    public Conta(PessoaJuridica titular) {
        this.conta = contadorConta;
        this.titular = titular;
        this.documento = titular.getCnpj();
        contadorConta += 1;
    }

    public static int getContadorConta() {
        return contadorConta;
    }

    public static void setContadorConta(int contadorConta) {
        Conta.contadorConta = contadorConta;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public Pessoa getTitular() {
        return titular;
    }

    public void setTitular(Pessoa titular) {
        this.titular = titular;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void depositar(Double valor) {
        if (valor > 0) {
            setSaldo(getSaldo() + valor);
            System.out.println("Depósito no valor de " + Utils.valorDoubleString(valor) + " foi realizado com sucesso!");
            System.out.println("Saldo em conta atualizado: " + Utils.valorDoubleString(getSaldo()));
        } else {
            System.out.println("O valor do depósito deve ser maior que 0.0!");
        }
    }

    public void sacar(Double valor) {
        if (valor > 0 && getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            System.out.println("Saque no valor de " + Utils.valorDoubleString(valor) + " foi realizado com sucesso!");
            System.out.println("Saldo disponível atualizado: " + Utils.valorDoubleString(getSaldo()));
        } else {
            System.out.println("Você não possui saldo suficiente! Saldo disponível: " + Utils.valorDoubleString(getSaldo()));
        }
    }

    public String consultaSaldo() {
        return Utils.valorDoubleString(getSaldo());

    }

    public void transferir(Conta contaDestino, Double valor){
        if(valor <= 0){
            System.out.println("Valor não pode ser menor ou igual a 0.0");

        }else if(this.getSaldo() >= valor){
            this.setSaldo(this.getSaldo() - valor);
            contaDestino.setSaldo(contaDestino.getSaldo() + valor);
            System.out.println("Transferencia no valor de " +Utils.valorDoubleString(valor)+ "realizada com sucesso!");

        }else{
            System.out.println("Voce não possui saldo suficiente! Saldo disponivel: " + consultaSaldo());
        }

        }
    @Override
    public String toString() {
        return "Dados{" +
                "idConta=" + conta +
                ", Nome do titular=" + titular +
                ", documento='" + titular + '\'' +
                ", saldo=" + Utils.valorDoubleString(saldo) +
                '}';
    }


    }

