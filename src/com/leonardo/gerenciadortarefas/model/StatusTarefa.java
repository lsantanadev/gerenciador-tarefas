package com.leonardo.gerenciadortarefas.model;

public enum StatusTarefa {
    PENDENTE("Pendente"),
    EM_ANDAMENTO("Em Andamento"),
    CONCLUIDA("Concluida");

    private String nomeStatus;

    StatusTarefa(String nomeStatus) {
        this.nomeStatus = nomeStatus;
    }

    public String getNomeStatus() {
        return nomeStatus;
    }

    public void setNomeStatus(String nomeStatus) {
        this.nomeStatus = nomeStatus;
    }
}
