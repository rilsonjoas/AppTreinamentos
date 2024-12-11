package negocio;

import java.util.*;

public class Treino {
    private static Treino instance; // Instância única para o padrão Singleton
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

    // Construtor privado para o padrão Singleton
    private Treino() {
        this.id = UUID.randomUUID(); // Gera um ID único
        this.exercicios = new ArrayList<>();
        this.concluido = false; // Define o treino como não concluído inicialmente
        this.caloriasQueimadas = 0.0;
        this.progresso = 0.0;
    }

    // Método para obter a instância única do treino no padrão Singleton
    public static Treino getInstance() {
        if (instance == null) {
            instance = new Treino();
        }
        return instance;
    }

    // Método para inicializar o treino com parâmetros
    public void inicializar(String nome, String tipoDeTreino, int duracao, int nivelDeDificuldade, Usuario usuario) {
        this.nome = nome;
        this.tipoDeTreino = tipoDeTreino;
        this.duracao = duracao;
        this.nivelDeDificuldade = nivelDeDificuldade;
        this.usuario = usuario;
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
    // Método para adicionar um exercício ao treino
    public void adicionarExercicio(Exercicio exercicio) {
        if (exercicio != null && !exercicios.contains(exercicio)) {
            exercicios.add(exercicio);
            calcularCaloriasQueimadas(); // Recalcula as calorias queimadas
            atualizarProgresso(); // Atualiza o progresso do treino
        }
    }

    // Método para remover um exercício do treino
    public void removerExercicio(Exercicio exercicio) {
        if (exercicios.remove(exercicio)) {
            calcularCaloriasQueimadas(); // Recalcula as calorias queimadas
            atualizarProgresso(); // Atualiza o progresso do treino
        }
    }

    // Método para calcular as calorias queimadas no treino
    public double calcularCaloriasQueimadas() {
        caloriasQueimadas = 0; // Inicializa as calorias queimadas
        for (Exercicio exercicio : exercicios) {
            caloriasQueimadas += exercicio.calcularCaloriasQueimadas();
        }
        return caloriasQueimadas;
    }

    // Método para atualizar o progresso do treino
    public void atualizarProgresso() {

       //Verificação se o execício não começou, se sim, o progresso é zero e ele não está concluído.
        if (exercicios.isEmpty()) {
            progresso = 0.0;
            concluido = false;
            return;
        }

        // Conta a quantidade de exercícios concluídos
        long exerciciosConcluidos = 0;
        for (Exercicio exercicio : exercicios) {
            if (exercicio.isConcluido()) {
                exerciciosConcluidos++;
            }
        }

        // Calcula o progresso com base nos exercícios concluídos
        // Calcula o progresso com base nos exercícios concluídos
        progresso = (exerciciosConcluidos / (double) exercicios.size()) * 100.0;
        concluido = progresso == 100.0; // Treino como concluído se o progresso for 100%
    }

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