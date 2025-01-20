package negocio;

import java.util.*;

public class Treino {
    private UUID id;
    private String nome;
    private String tipoDeTreino;
    private int duracao;
    private int nivelDeDificuldade;
    private Usuario usuario;
    private ArrayList<Exercicio> exercicios;
    private double caloriasQueimadas;
    private double progresso;
    private boolean concluido;

    // Construtor
    public Treino() {
        this.id = UUID.randomUUID(); // Gera um ID único
        this.exercicios = new ArrayList<>();
        this.concluido = false; // Define o treino como não concluído inicialmente
        this.caloriasQueimadas = 0.0;
        this.progresso = 0.0;
    }

    // Contrutor com parâmetros
    public Treino(String nome, String tipoDeTreino, int duracao, int nivelDeDificuldade, Usuario usuario) {
        this.nome = nome;
        this.tipoDeTreino = tipoDeTreino;
        this.duracao = duracao;
        this.nivelDeDificuldade = nivelDeDificuldade;
        this.usuario = usuario;
        this.id = UUID.randomUUID();
        this.exercicios = new ArrayList<>();
        this.concluido = false;
        this.caloriasQueimadas = 0.0;
        this.progresso = 0.0;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipoDeTreino() {
        return tipoDeTreino;
    }

    public int getDuracao() {
        return duracao;
    }

    public int getNivelDeDificuldade() {
        return nivelDeDificuldade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ArrayList<Exercicio> getExercicios() {
        return exercicios;
    }

    public double getCaloriasQueimadas() {
        return caloriasQueimadas;
    }

    public double getProgresso() {
        return progresso;
    }

    public boolean isConcluido() {
        return concluido;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipoDeTreino(String tipoDeTreino) {
        this.tipoDeTreino = tipoDeTreino;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public void setNivelDeDificuldade(int nivelDeDificuldade) {
        this.nivelDeDificuldade = nivelDeDificuldade;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // OUTROS MÉTODOS



    // Método para atualizar o progresso do treino


    @Override
    public String toString() {
        return "negocio.Treino{" +
                "nome='" + nome + '\'' +
                ", tipoDeTreino='" + tipoDeTreino + '\'' +
                ", duracao=" + duracao +
                ", nivelDeDificuldade=" + nivelDeDificuldade +
                ", progresso=" + progresso +
                ", concluido=" + concluido +
                '}';
    }
}