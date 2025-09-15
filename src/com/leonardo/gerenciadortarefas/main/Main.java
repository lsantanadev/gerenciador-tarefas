package com.leonardo.gerenciadortarefas.main;

import com.leonardo.gerenciadortarefas.exception.TarefaNaoEncontradaException;
import com.leonardo.gerenciadortarefas.model.Tarefa;
import com.leonardo.gerenciadortarefas.service.GerenciadorDeTarefas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int escolha = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataVencimento;

        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();
        do {
            System.out.println("""
                    ==================================
                    = SISTEMA GERENCIADOR DE TAREFAS =
                    ==================================""");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Listar Tarefas Pendentes");
            System.out.println("3. Listar Tarefas Concluidas");
            System.out.println("4. Marcar Tarefa como Concluida");
            System.out.println("5. Iniciar Tarefa");
            System.out.println("6. Atualizar Tarefa");
            System.out.println("7. Remover Tarefa");
            System.out.println("8. Sair");
            System.out.print("Escolha uma Opção: ");
            escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1: // Adicionar Tarefa
                    System.out.print("Titulo: ");
                    String titulo = sc.nextLine();

                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();

                    System.out.print("Data de vencimento (dd/MM/yyyy): ");
                    String data = sc.nextLine();

                    try {
                        dataVencimento = LocalDate.parse(data, formatter);
                        Tarefa tarefa = new Tarefa(titulo, descricao, dataVencimento);
                        gerenciador.adicionarTarefa(tarefa);
                        System.out.println("Tarefa adicionada com sucesso!");
                    } catch (DateTimeParseException e) {
                        System.err.println("ERRO: Formato de data inválido. Por favor, use o formato dd/MM/yyyy.");
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                case 2: // Listar tarefas pendentes
                    List<Tarefa> pendentes = gerenciador.getTarefasPendentes();

                    if (pendentes.isEmpty()) {
                        System.out.println("Não há tarefas pendentes para mostrar");
                    } else {
                        System.out.println("===Tarefas Pendentes===");
                        for (Tarefa pendente : pendentes) {
                            System.out.println(pendente);
                        }
                        System.out.println("=======================");
                    }
                    break;

                case 3: // Listar tarefas concluidas
                    List<Tarefa> concluidas = gerenciador.getTarefasConcluida();

                    if (concluidas.isEmpty()) {
                        System.out.println("Não há tarefas concluídas para mostrar");
                    } else {
                        System.out.println("===Tarefas Concluídas===");
                        for (Tarefa concluida : concluidas) {
                            System.out.println(concluida);
                        }
                        System.out.println("========================");
                    }
                    break;

                case 4: // Marcar tarefa como concluida
                    System.out.println("Digite o ID da tarefa para concluir: ");
                    Long idConcluir = sc.nextLong();
                    sc.nextLine();

                    try {
                        gerenciador.marcarComoConcluida(idConcluir);
                    } catch (TarefaNaoEncontradaException e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                case 5: // Iniciar uma tarefa
                    System.out.println("Digite o ID da tarefa para iniciar: ");
                    Long idIniciar = sc.nextLong();
                    sc.nextLine();

                    try {
                        gerenciador.iniciarTarefa(idIniciar);
                    } catch (TarefaNaoEncontradaException e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                case 6: // Atualizar tarefa
                    System.out.println("Digite o ID da tarefa que quer atualizar os dados: ");
                    Long idAtualizar = sc.nextLong();
                    sc.nextLine();

                    System.out.println("Novo título: ");
                    String novoTitulo = sc.nextLine();

                    System.out.println("Nova Descrição: ");
                    String novaDescricao = sc.nextLine();


                    System.out.println("Nova Data: ");
                    String novaData = sc.nextLine();

                    try {
                        dataVencimento = LocalDate.parse(novaData, formatter);

                        Tarefa tarefaAtualizada = new Tarefa(novoTitulo, novaDescricao, dataVencimento);
                        gerenciador.atualizarTarefa(idAtualizar, tarefaAtualizada);

                        System.out.println("Tarefa atualizada ID: " + tarefaAtualizada.getId());
                    } catch (TarefaNaoEncontradaException e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                case 7: // Remover tarefa
                    System.out.println("Digite o ID da tarefa para excluir: ");
                    Long idRemove = sc.nextLong();
                    sc.nextLine();

                    try {
                        gerenciador.removerTarefa(idRemove);
                    } catch (TarefaNaoEncontradaException e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                case 8: // sair
                    System.out.println("Saindo......");
                    break;

                default:
                    System.out.println("Valor inválido... Escolha entre 1 e 8");
                    break;
            }
        } while (escolha != 8);
    }
}
