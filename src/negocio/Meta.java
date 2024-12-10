package negocio;

import java.util.*;

public class Meta {
    private UUID id;
    private String descricao;
    private Tipo tipo;
    private double valorAlvo;
    private double progressoAtual;
    private Date dataCriacao;
    private Date dataConclusao; // Pode ser null

    public enum Tipo {
        PESO, MEDIDAS, OUTROS;
    }

    // Construtor
    public Meta(String descricao, Tipo tipo, double valorAlvo, double progressoAtual, Date dataCriacao) {
        this.id = UUID.randomUUID();
        this.descricao = descricao;
        this.tipo = tipo;
        this.valorAlvo = valorAlvo;
        this.progressoAtual = progressoAtual;
        this.dataCriacao = dataCriacao;
        this.dataConclusao = null;
    }

    // Getters e Setters
    public UUID getId(){
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public double getValorAlvo() {
        return valorAlvo;
    }

    public void setValorAlvo(double valorAlvo) {
        this.valorAlvo = valorAlvo;
    }

    public double getProgressoAtual() {
        return progressoAtual;
    }

    public void setProgressoAtual(double progressoAtual) {
        this.progressoAtual = progressoAtual;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    //OUTROS MÉTODOS
    // Método para verificar se a meta foi concluída
    public boolean isConcluida() {
        return dataConclusao != null;
    }

    // Método para obter o progresso atual como porcentagem
    public double getProgresso() {
        if (valorAlvo == 0) {
            return 0;
        }
        return (progressoAtual / valorAlvo) * 100;
    }

    // Método para marcar a meta como concluída
    public void concluirMeta() {
        this.dataConclusao = new Date(); // Define a data atual como a conclusão
    }
}
