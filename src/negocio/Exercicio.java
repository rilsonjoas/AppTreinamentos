package negocio;

import java.util.*;

public class Exercicio {
    private UUID id;
    private String nome;
    private String descricao;
    private ArrayList<String> musculosTrabalhados;
    private TipoExercicio tipo;
    private int tempo; // em segundos
    private double caloriasQueimadasPorMinuto;
    private boolean concluido;
    private double caloriasQueimadas;

    // Enum para tipos de exercício
    public enum TipoExercicio {
        FORCA,
        CARDIO,
        FLEXIBILIDADE,
        EQUILIBRIO,
        AQUATICO,
        OUTRO
    }

    // Construtor padrão
    public Exercicio() {
        this.id = UUID.randomUUID();
        this.musculosTrabalhados = new ArrayList<>();
        this.concluido = false;
    }

    // Construtor com parâmetros
    public Exercicio(String nome, String descricao, TipoExercicio tipo,
                     int tempo, double caloriasQueimadasPorMinuto) {
        this();
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.tempo = tempo;
        this.caloriasQueimadasPorMinuto = caloriasQueimadasPorMinuto;
    }

    // Getters
    public UUID getId(){
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

    // Métodos para manipular músculos trabalhados
    public void adicionarMusculoTrabalhado(String musculo) {
        if (musculo != null &&
                !musculosTrabalhados.contains(musculo)) {
            musculosTrabalhados.add(musculo);
        }
    }

    public void removerMusculoTrabalhado(String musculo) {
        musculosTrabalhados.remove(musculo);
    }

    public double calcularCaloriasQueimadas() {
        // Calcula calorias queimadas baseado no tempo e calorias por minuto
        caloriasQueimadas = (tempo / 60.0) * caloriasQueimadasPorMinuto;
        return caloriasQueimadas;
    }

    public double getCaloriasQueimadas() {
        return calcularCaloriasQueimadas();
    }

    // Método para marcar o exercício como concluído
    public void concluir() {
        this.concluido = true;
    }

    // Método para verificar se o exercício está concluído
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