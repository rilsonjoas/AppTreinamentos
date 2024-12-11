package negocio;

import java.util.*;

public class Meta {
    private static Meta instance; // Instância única da classe (Singleton)
    private UUID id;
    private String descricao;
    private Tipo tipo;
    private double valorAlvo;
    private double progressoAtual;
    private Date dataCriacao;
    private Date dataConclusao;

    // Enum para representar os tipos de meta (pode mudar)
    public enum Tipo {
        PESO, MEDIDAS, OUTROS;
    }

    // Construtor privado para o padrão Singleton
    private Meta() {
        this.id = UUID.randomUUID(); // Gera um ID único
        this.dataCriacao = new Date(); // Define a data de criação como a data atual
    }

    // Método para obter a instância única da Meta no padrão Singleton
    public static Meta getInstance() {
        if (instance == null) {
            instance = new Meta();
        }
        return instance;
    }

    // Método para inicializar a meta com parâmetros
    public void inicializar(String descricao, Tipo tipo, double valorAlvo, double progressoAtual) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.valorAlvo = valorAlvo;
        this.progressoAtual = progressoAtual;
    }


    // Getters
    public UUID getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public double getValorAlvo() {
        return valorAlvo;
    }

    public double getProgressoAtual() {
        return progressoAtual;
    }

    //Setters

    public void setValorAlvo(double valorAlvo) {
        this.valorAlvo = valorAlvo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setProgressoAtual(double progressoAtual) {
        this.progressoAtual = progressoAtual;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    // OUTROS MÉTODOS
    // Verifica se a meta foi concluída
    public boolean isConcluida() {
        return dataConclusao != null;
    }

    // Calcula o progresso da meta em porcentagem
    public double getProgresso() {
        if (valorAlvo == 0) {
            return 0;
        }
        return (progressoAtual / valorAlvo) * 100;
    }

    // Define a data de conclusão para a data atual, marcando a meta como concluída
    public void concluirMeta() {
        this.dataConclusao = new Date();
    }
}