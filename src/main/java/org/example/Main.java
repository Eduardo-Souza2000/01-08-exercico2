package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean opcMenu = false;
        while (!opcMenu){
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Realizar Pedido");
            System.out.println("3 - Alterar Dados do Cliente");
            System.out.println("4 - Consultar pedidos");
            System.out.println("5 - Consultar clientes");
            System.out.println("6 - Consultar quantidade de pedidos em atendimento");
            System.out.println("7 - Consultar quantidade de pedidos encerrados");
            System.out.println("8 - Salvar em arquivo");
            System.out.println("0 - Sair");
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
                    consultarPedidos(clientes);
                    break;
                case 5:
                    consultarClientes(clientes);
                    break;
                case 6:
                    totalDePedidosEmAtendimento(clientes);
                    break;
                case 7:
                    totalDePedidosEncerrados(clientes);
                    break;
                case 8:
                    salvarEmArquivo(clientes);
                    break;
                case 0:
                    return;
            }
        }
    }
    public static void salvarEmArquivo(List<Cliente> clientes){
        try{
            FileWriter fileWriter = new FileWriter("dados.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(Cliente cliente : clientes){
                printWriter.println(cliente.getNome() + " ");
                printWriter.println("Endereco:\nRua: "
                        + cliente.getEndereco().getRua() + "\n"
                        + "Numero: " + cliente.getEndereco().getNumero());
                System.out.println(cliente.getNome());
            }
            printWriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void totalDePedidosEncerrados(List<Cliente> clientes){
        int totalEncerrado = 0;
        for(Cliente cliente : clientes){
            for(Pedido pedido : cliente.getPedidos()){
                if(!pedido.isEmAtendimento()){
                    totalEncerrado++;
                }
            }
        }
        System.out.println("Total de pedidos encerrados: " + totalEncerrado);
    }
    public static void totalDePedidosEmAtendimento(List<Cliente> clientes){
        int totalEmAtendimento = 0;
        for(Cliente cliente : clientes){
            for(Pedido pedido : cliente.getPedidos()){
                if(pedido.isEmAtendimento()){
                    totalEmAtendimento++;
                }
            }
        }
        System.out.println("Total de pedidos em atendimento: " + totalEmAtendimento);
    }
    public static void consultarClientes(List<Cliente> clientes){
        int i = 0;
        for(Cliente cliente: clientes){
            System.out.println("Cliente " + (i  + 1) + " " + cliente.getNome());
            i++;
        }
    }
    public static void consultarPedidos(List<Cliente> clientes){
        int totalDePedidos = 0;
        for (Cliente cliente : clientes) {
            int i = 0;
            System.out.println("Cliente: " + cliente.getNome());
            for(Pedido pedido: cliente.getPedidos()){
                System.out.println("Pedido " + i + 1 + " " + pedido.getProdutos());
                i++;
                totalDePedidos++;
            }
            System.out.println("\nTotal de pedidos: " + totalDePedidos);
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

            System.out.println("1 - Cadastrar outra pessoa");
            System.out.println("2 - Sair");
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
        System.out.println("1 - Nome");
        System.out.println("2 - Idade");
        System.out.println("3 - Endereco");
        System.out.println("4 - Sair");
        sc.reset();

        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao){
            case 1:
                System.out.println(" Digite o Nome");
                cliente.setNome(sc.nextLine());
                sc.reset();
                break;

            case 2:
                System.out.println(" Digite a Idade");
                cliente.setIdade(sc.nextInt());
                sc.reset();
                break;

            case 3:
                System.out.println(" Digite a Rua");
                cliente.getEndereco().setRua(sc.nextLine());
                sc.reset();
                System.out.println(" Digite a Numero");
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
            System.out.println("Digite o nome do produto " + i + 1);
            Pedido pedido = new Pedido(sc.nextLine());
            cliente.getPedidos().add(pedido);
            sc.reset();
            System.out.println("1 - Adicionar mais produtos");
            System.out.println("2 - Sair");
            opcao = scan.nextInt();
            scan.reset();
        }

        for(int i = 0; i < clientes.size(); i++){
            if (clientes.get(i).getNome().equals(cliente.getNome())){
                clientes.remove(i);
                clientes.add(cliente);
                break;
            }
        }

        return clientes;
    }
}