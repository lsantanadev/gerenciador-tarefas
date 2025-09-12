package com.leonardo.gerenciadortarefas.main;

import com.leonardo.gerenciadortarefas.model.Tarefa;
import com.leonardo.gerenciadortarefas.service.GerenciadorDeTarefas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataVencimento;
        short opcao = 0;

        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();
        do {
            System.out.println("==================================\n" +
                               "= SISTEMA GERENCIADOR DE TAREFAS =\n" +
                               "==================================");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Listar Tarefas Pendentes");
            System.out.println("3. Listar Tarefas Concluidas");
            System.out.println("4. Marcar Tarefa como Concluida");
            System.out.println("5. Iniciar Tarefa");
            System.out.println("6. Atualizar Tarefa");
            System.out.println("7. Remover Tarefa");
            System.out.println("8. Sair");
            System.out.print("Escolha uma Opção: ");
            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    try {
                        System.out.print("Titulo: ");
                        String titulo = sc.nextLine();

                        System.out.print("Descrição: ");
                        String descricao = sc.nextLine();

                        System.out.print("Data de vencimento (dd/MM/yyyy): ");
                        String data = sc.nextLine();
                        dataVencimento = LocalDate.parse(data, formatter);

                        Tarefa tarefa = new Tarefa(titulo, descricao, dataVencimento);
                        gerenciador.adicionarTarefa(tarefa);
                        System.out.println("Tarefa adicionada com sucesso!");
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                case 2:


            }
        } while (opcao != 8);
    }
}
