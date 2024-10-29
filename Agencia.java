package dev.etech.agencia;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import java.util.ArrayList;
import java.util.Scanner;

public class Agencia {
    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    private static CPFValidator cpfValidator = new CPFValidator();
    private static CNPJValidator cnpjValidator = new CNPJValidator();
    private static ArrayList<Conta> listaContas;

    public static void main(String[] args) {
        listaContas = new ArrayList<>();
        operacoes();
    }

    public static void operacoes() {
        System.out.println("|********************************|");
        System.out.println("|**Ola! Somos o MiniBanco, é um prazer ver você por aqui!**|");
        System.out.println("|********************************|");
        System.out.println("|********Selecione uma das opções abaixo para começar!********|");
        System.out.println("|********************************|");
        System.out.println("|   1 - Criar conta pessoa física     |");
        System.out.println("|   2 - Criar conta pessoa jurídica   |");
        System.out.println("|   3 - Depositar                     |");
        System.out.println("|   4 - Sacar                         |");
        System.out.println("|   5 - Transferir                    |");
        System.out.println("|   6 - Consultar saldo               |");
        System.out.println("|   7 - Listar                        |");
        System.out.println("|   8 - Sair                          |");

        int opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                criarContaPF();
                break;
            case 2:
                criarContaPJ();
                break;
            case 3:
                depositar();
                break;
            case 4:
                sacar();
                break;
            case 5:
                transferir();
                break;
        //  case 6:
        //      consultarSaldo();
        //      break;
            case 7:
                listar();
                break;
            case 8:
                System.out.println("Até logo");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida!");
                operacoes();
                break;
        }
    }



    private static void criarContaPF() {
        boolean cpfValido = false;
        System.out.println("Sistema de criação de conta para pessoa física!");
        System.out.println("Preencha abaixo para criar uma conta no MiniBanco!");
        System.out.println("\nNome: ");
        String nome = sc.next();
        String cpf = "";
        while (!cpfValido) {
            System.out.println("\nCPF: ");
            cpf = sc.next();
            cpfValido = cpfValidator.invalidMessagesFor(cpf).isEmpty();
            if (!cpfValido)
                System.out.println("O CPF digitado é inválido! Por favor, tente novamente!");
        }
        System.out.println("\nEmail: ");
        String email = sc.next();
        PessoaFisica pessoaFisica = new PessoaFisica(nome, cpf, email);
        Conta conta = new Conta(pessoaFisica);
        listaContas.add(conta);
        System.out.println("Conta criada com sucesso!");
        System.out.println("O ID da sua conta é: " + conta.getConta());
        operacoes();
    }

    public static void criarContaPJ() {
        boolean cnpjValido = false;
        System.out.println("Sistema de criação de conta para pessoa jurídica!");
        System.out.println("Preencha abaixo para criar uma conta no MiniBanco!");
        System.out.println("\nNome: ");
        String nome = sc.next();
        String cnpj = "";
        while (!cnpjValido) {
            System.out.println("\nCNPJ: ");
            cnpj = sc.next();
            cnpjValido = cnpjValidator.invalidMessagesFor(cnpj).isEmpty();
            if (!cnpjValido)
                System.out.println("O CNPJ digitado é inválido! Por favor, tente novamente!");
        }
        System.out.println("\nEmail: ");
        String email = sc.next();
        PessoaJuridica pessoaJuridica = new PessoaJuridica(nome, cnpj, email);
        Conta conta = new Conta(pessoaJuridica);
        listaContas.add(conta);
        System.out.println("Conta criada com sucesso!");
        System.out.println("O ID da sua conta é: " + conta.getConta());
        operacoes();
    }

    public static void depositar() {
        System.out.println("Sistema de depósito.");
        System.out.println("Insira o ID da conta que deseja depositar");
        int idConta = sc.nextInt();
        Conta conta = validaExistenciaConta(idConta);
        if (conta != null) {
            System.out.println("Insira o valor do depósito");
            Double valorDeposito = sc.nextDouble();
            conta.depositar(valorDeposito);
        } else {
            System.out.println("Conta não encontrada!"); // Mensagem correta caso a conta não seja encontrada
        }
        operacoes(); // Continua para o menu de operações
    }

    public static Conta validaExistenciaConta(int idConta) {
        Conta contaValida = null;
        if (listaContas.size() > 0) {
            for (Conta conta : listaContas) {
                if (conta.getConta() == idConta) {
                    contaValida = conta;
                }
            }
        } else {
            System.out.println("Nenhuma conta foi criada ainda.");
        }
        return contaValida;
    }

    public static void sacar() {
        System.out.println("Sistema de saque.");
        System.out.println("Insira o ID da conta que deseja sacar");
        int idConta = sc.nextInt();
        Conta conta = validaExistenciaConta(idConta);
        if (conta != null) {
            System.out.println("Insira o valor do saque");
            Double valorSaque = sc.nextDouble();
            conta.sacar(valorSaque);
        } else {
            System.out.println("Conta não encontrada!");
        }
        operacoes(); // Continua para o menu de operações
    }

    public static void transferir() {
        System.out.println("Sistema de transferencia.");
        System.out.println("Insira o ID da sua conta origem: ");
        int idContaOrigem = sc.nextInt();
        Conta contaOrigem = validaExistenciaConta(idContaOrigem);

        if (contaOrigem != null) {
            System.out.println("Insira o ID da conta de destino: ");
            int idContaDestino = sc.nextInt();
            Conta contaDestino = validaExistenciaConta(idContaDestino);
            if (contaDestino != null) ;
            System.out.println("Qual valor da transferencia: ");
            Double valorTransferencia = sc.nextDouble();

            contaOrigem.transferir(contaDestino, valorTransferencia);

        } else {
            System.out.println("Conta de destino inexistente!");
        }
        operacoes();
    }
    public static void consultaSaldo(){
        System.out.println("Sistema de consulta saldo.");
        System.out.println("Insira o ID da conta que deseja consultar");
        int idConta = sc.nextInt();
        Conta conta = validaExistenciaConta(idConta);
        if(conta != null){
            }else  {
            System.out.println("Conta não encontrada!");
        }
        operacoes();
    }
    public static void listar(){
        if(listaContas.size() > 0){
            for (Conta conta : listaContas){
               System.out.println(conta);
}
       }else {
          System.out.println("Não há contas cadastradas!");

}

       operacoes();
}

}



    