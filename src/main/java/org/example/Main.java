package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean opcMenu = false;
        while (!opcMenu){
            System.out.println(" Cadastrar Cliente - 1\n");
            System.out.println(" Realizar Pedido - 2 \n");
            System.out.println(" Alterar Daados do Cliente - 3 \n");
            System.out.println(" Sair - 4 \n");
            int opcao = sc.nextInt();

            switch (opcao){
                case 1:
                    cadastrarCliente(clientes);
                    break;

                case 2:
                    realizarPedidos(clientes);
                    break;

                case 3:
                    alterarClientes(clientes);
                    break;

                case 4:
                    return;
            }
        }

    }

    public static List<Cliente> cadastrarCliente (List<Cliente> clientes){

        Scanner sc = new Scanner(System.in);
        boolean sair = false;
        do{
            int sairCadastro = 0;

            System.out.println("Digite o nome e a idade: ");
            String nome = sc.next();
            int idade = sc.nextInt();
            System.out.println("Digite o endereco rua numero: ");
            Endereco endereco = new Endereco(sc.next(), sc.nextInt());
            sc.reset();

            clientes.add(new Cliente(nome, idade, endereco));

            System.out.println("Cadastrar outra pessoa - 1");
            System.out.println("Sair - 2");
            sairCadastro = sc.nextInt();

            if(sairCadastro == 2){
                sair = true;
            }

        }while(!sair);
        return clientes;
    }

    public static List<Cliente> alterarClientes (List<Cliente> clientes){
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o Nome do Cliente que deseja Alterar: ");
        String nome = sc.nextLine();

        Cliente cliente = null;

        for( Cliente clienteValor: clientes) {
            if (clienteValor.getNome().equals(nome)){
                cliente = clienteValor;
                break;
            }
        }
        if (cliente == null){
            System.out.println("Cliente Nao Encontrado");
            return clientes;

        }

        System.out.println("O que deseja Alterar");
        System.out.println(" Nome - 1\n");
        System.out.println(" Idade - 2 \n");
        System.out.println(" Endereco - 3 \n");
        System.out.println(" Sair - 4 \n");
        sc.reset();

        int opcao = sc.nextInt();

        switch (opcao){
            case 1:
                System.out.println(" Digite o Nome \n");
                cliente.setNome(sc.nextLine());
                sc.reset();
                break;

            case 2:
                System.out.println(" Digite a Idade \n");
                cliente.setIdade(sc.nextInt());
                sc.reset();
                break;

            case 3:
                System.out.println(" Digite a Rua \n");
                cliente.getEndereco().setRua(sc.nextLine());
                sc.reset();
                System.out.println(" Digite a Numero \n");
                cliente.getEndereco().setNumero(sc.nextInt());
                sc.reset();
                break;

            case 4:
                break;
        }

        return clientes;

    }

    public static List<Cliente> realizarPedidos (List<Cliente> clientes){
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        System.out.println("Digite o Nome do Cliente: ");
        String nome = sc.nextLine();

        Cliente cliente = null;

        for( Cliente clienteValor: clientes) {
            if (clienteValor.getNome().equals(nome)){
                cliente = clienteValor;
                break;
            }
        }
        if (cliente == null){
            System.out.println("Cliente Nao Encontrado");
            return clientes;

        }

        sc.reset();

        for (int i=0; opcao != 2 ;i++){
            Scanner scan = new Scanner(System.in);
            System.out.println("Digite o nome do produto " + i);
            Pedido pedido = new Pedido(sc.nextLine());
            cliente.getPedidos().add(pedido);
            sc.reset();
            System.out.println("Adicionar mais produtos - 1");
            System.out.println("Sair - 2");
            opcao = scan.nextInt();
            scan.reset();
        }
        for (Cliente cliente1: clientes){
            if (cliente1.getNome().equals(cliente.getNome())){
                cliente = cliente1;
                break;
            }
        }

        return clientes;
    }



}