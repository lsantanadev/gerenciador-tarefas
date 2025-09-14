package com.leonardo.gerenciadortarefas.service;

import com.leonardo.gerenciadortarefas.exception.TarefaNaoEncontradaException;
import com.leonardo.gerenciadortarefas.model.StatusTarefa;
import com.leonardo.gerenciadortarefas.model.Tarefa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeTarefas {
    private List<Tarefa> tarefas;
    private Long proximoID;

    public GerenciadorDeTarefas() {
        this.tarefas = new ArrayList<>();
        this.proximoID = 1L;
    }

    public void adicionarTarefa(Tarefa tarefa) {
        if (tarefa.getTitulo() == null || tarefa.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("O título da tarefa não pode ser vazio");
        }

        if (tarefa.getDataDeVencimento().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data de vencimento não pode ser uma data que ja passou");
        }
        tarefa.setId(proximoID++);
        tarefas.add(tarefa);
    }

    public void removerTarefa(Long id) throws TarefaNaoEncontradaException {
        boolean remove = tarefas.removeIf(tarefa -> tarefa.getId().equals(id));
        if (!remove) {
            throw new TarefaNaoEncontradaException("Tarefa com ID " + id + " não encontrado");
        }
    }

    public void atualizarTarefa(Long id, Tarefa novaTarefa) throws TarefaNaoEncontradaException {
        Tarefa tarefaExistente = buscarTarefaPorId(id);

        tarefaExistente.setTitulo(novaTarefa.getTitulo());
        tarefaExistente.setDescricao(novaTarefa.getDescricao());
        tarefaExistente.setDataDeVencimento(novaTarefa.getDataDeVencimento());

        throw new TarefaNaoEncontradaException("Tarefa com ID " + id + " não encontrada");
    }

    public void marcarComoConcluida(Long id) throws TarefaNaoEncontradaException {
        Tarefa tarefaExistente = buscarTarefaPorId(id);
        tarefaExistente.setStatus(StatusTarefa.CONCLUIDA);

        throw new TarefaNaoEncontradaException("Tarefa com ID " + id + " não encontrada");
    }

    public List<Tarefa> getTarefasPendentes() {
        List<Tarefa> tarefasPendentes = new ArrayList<>();

        for (Tarefa tarefa : tarefas) {
            if (tarefa.getStatus() == (StatusTarefa.PENDENTE)) {
                tarefasPendentes.add(tarefa);
            }
        }

        return tarefasPendentes;
    }

    public List<Tarefa> getTarefasConcluida() {
        List<Tarefa> tarefasConcluidas = new ArrayList<>();

        for (Tarefa tarefa : tarefas) {
            if (tarefa.getStatus() == (StatusTarefa.CONCLUIDA)) {
                tarefasConcluidas.add(tarefa);
            }
        }

        return tarefasConcluidas;
    }

    public Tarefa buscarTarefaPorId(Long id) throws TarefaNaoEncontradaException {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId().equals(id)) {
                return tarefa;
            }
        }

        throw new TarefaNaoEncontradaException("Tarefa com ID " + id + " não encontrada");
    }

    public void iniciarTarefa(Long id) throws TarefaNaoEncontradaException {
        Tarefa tarefa = buscarTarefaPorId(id);

        if (tarefa.getStatus() == StatusTarefa.PENDENTE) {
            tarefa.setStatus(StatusTarefa.EM_ANDAMENTO);
        } else {
            throw new IllegalArgumentException("Apenas tarefas com status PENDENTE podem ser iniciadas");
        }
    }
}
