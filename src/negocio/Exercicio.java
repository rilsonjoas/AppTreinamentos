package negocio;

import java.util.*;

public class Exercicio {
    private static Exercicio instance; // Instância única da classe (Singleton)
    private UUID id;
    private String nome;
    private String descricao;
    private ArrayList<String> musculosTrabalhados;
    private TipoExercicio tipo;
    private int tempo;
    private double caloriasQueimadasPorMinuto;
    private boolean concluido;
    private double caloriasQueimadas;


    // Enum para representar os tipos de exercício (pode ser revisado)
    public enum TipoExercicio {
        FORCA,
        CARDIO,
        FLEXIBILIDADE,
        EQUILIBRIO,
        AQUATICO,
        OUTRO
    }

    // Construtor privado para o padrão Singleton
    private Exercicio() {
        this.id = UUID.randomUUID(); // Gera um ID único
        this.musculosTrabalhados = new ArrayList<>(); // Inicializa a lista de músculos trabalhados
        this.concluido = false; // Define o exercício como não concluído inicialmente

    }

    // Método para obter a instância única do exercício no padrão Singleton
    public static Exercicio getInstance() {
        if (instance == null) {
            instance = new Exercicio();
        }
        return instance;
    }

    // Método para inicializar o exercício com parâmetros
    public void inicializar(String nome, String descricao, TipoExercicio tipo,
                            int tempo, double caloriasQueimadasPorMinuto) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.tempo = tempo;
        this.caloriasQueimadasPorMinuto = caloriasQueimadasPorMinuto;
    }


    // Getters
    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }


    public ArrayList<String> getMusculosTrabalhados() {
        return musculosTrabalhados;
    }

    public TipoExercicio getTipo() {
        return tipo;
    }

    public double getCaloriasQueimadas() {
        return calcularCaloriasQueimadas();
    }

    public int getTempo() {
        return tempo;
    }

    public double getCaloriasQueimadasPorMinuto() {
        return caloriasQueimadasPorMinuto;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public void setTipo(TipoExercicio tipo) {
        this.tipo = tipo;
    }


    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public void setCaloriasQueimadasPorMinuto(double caloriasQueimadasPorMinuto) {
        this.caloriasQueimadasPorMinuto = caloriasQueimadasPorMinuto;
    }

    // OUTROS MÉTODOS
    // Adiciona um músculo à lista de músculos trabalhados, verificando se já existe
    public void adicionarMusculoTrabalhado(String musculo) {
        if (musculo != null && !musculosTrabalhados.contains(musculo)) {
            musculosTrabalhados.add(musculo);
        }
    }

    // Remove um músculo da lista de músculos trabalhados
    public void removerMusculoTrabalhado(String musculo) {
        musculosTrabalhados.remove(musculo);
    }

    // Calcula o total de calorias queimadas durante o exercício
    public double calcularCaloriasQueimadas() {
        caloriasQueimadas = (tempo / 60.0) * caloriasQueimadasPorMinuto;
        return caloriasQueimadas;
    }

    // Marca o exercício como concluído
    public void concluir() {
        this.concluido = true;
    }

    // Retorna se o exercício está concluído
    public boolean isConcluido() {
        return concluido;
    }

    @Override
    public String toString() {
        return "negocio.Exercicio{" +
                "nome='" + nome + '\'' +
                ", tipo=" + tipo +
                ", tempo=" + tempo + " segundos" +
                ", calorias=" + String.format("%.2f", caloriasQueimadas) +
                ", concluido=" + concluido +
                '}';
    }
}