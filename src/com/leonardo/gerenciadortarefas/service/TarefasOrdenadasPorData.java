package com.leonardo.gerenciadortarefas.service;

import com.leonardo.gerenciadortarefas.model.Tarefa;

import java.util.Comparator;

public class TarefasOrdenadasPorData implements Comparator<Tarefa> {
    @Override
    public int compare(Tarefa t1, Tarefa t2) {
        return t1.getDataDeVencimento().compareTo(t2.getDataDeVencimento());
    }
}
