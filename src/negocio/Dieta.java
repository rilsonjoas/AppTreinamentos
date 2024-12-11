package negocio;

import java.util.*;

public class Dieta {
    private static Dieta instance; // Instância única da classe (Singleton)
    private UUID id;
    private String nome;
    private Objetivo objetivo;
    private int caloriasDiarias;
    private Map<String, Double> macronutrientes;
    private ArrayList<Refeicao> refeicoes;
    private Usuario usuario;

    // Enum para representar os objetivos da dieta (deve ser atualizado futuramente)
    public enum Objetivo {
        PERDA_DE_PESO, GANHO_DE_MASSA, MANUTENCAO;
    }

    // Construtor privado para o padrão Singleton
    private Dieta() {
        this.id = UUID.randomUUID(); // Gera um ID único
        this.macronutrientes = new HashMap<>();
        this.refeicoes = new ArrayList<>();
    }

    // Método para obter a instância única do treino no padrão Singleton
    public static Dieta getInstance() {
        if (instance == null) {
            instance = new Dieta();
        }
        return instance;
    }

    // Método para inicializar a dieta com parâmetros
    public void inicializar(String nome, Objetivo objetivo, int caloriasDiarias, Usuario usuario) {
        this.nome = nome;
        this.objetivo = objetivo;
        this.caloriasDiarias = caloriasDiarias;
        this.usuario = usuario;
    }


    // Getters
    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public int getCaloriasDiarias() {
        return caloriasDiarias;
    }

    public Map<String, Double> getMacronutrientes() {
        return macronutrientes;
    }

    public List<Refeicao> getRefeicoes() {
        return refeicoes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    //Setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public void setCaloriasDiarias(int caloriasDiarias) {
        this.caloriasDiarias = caloriasDiarias;
    }

    public void setMacronutrientes(Map<String, Double> macronutrientes) {
        this.macronutrientes = macronutrientes;
    }

    public void setRefeicoes(ArrayList<Refeicao> refeicoes) {
        this.refeicoes = refeicoes;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // OUTROS MÉTODOS
    // Adiciona uma refeição à lista de refeições
    public void adicionarRefeicao(Refeicao refeicao) {
        if (refeicao != null && !refeicoes.contains(refeicao)) {
            refeicoes.add(refeicao);
        }
    }

    // Remove uma refeição da lista de refeições
    public void removerRefeicao(Refeicao refeicao) {
        refeicoes.remove(refeicao);
    }

    // Calcula o total de macronutrientes da dieta com base nas refeições que a compõem
    public Map<String, Double> calcularMacronutrientes() {
        Map<String, Double> totalMacronutrientes = new HashMap<>();
        for (Refeicao refeicao : refeicoes) {
            Map<String, Double> macrosRefeicao = refeicao.getMacronutrientes();

           //Soma os valores dos macronutrientes de cada refeição e armazena o total em um mapa
            for (Map.Entry<String, Double> entry : macrosRefeicao.entrySet()) {
                totalMacronutrientes.put(entry.getKey(), totalMacronutrientes.getOrDefault(entry.getKey(), 0.0) + entry.getValue());
            }
        }

        return totalMacronutrientes;
    }


    // Calcula o total de calorias da dieta com base nas refeições
    public int calcularCaloriasTotais() {
        int totalCalorias = 0;
        for (Refeicao refeicao : refeicoes) {
            totalCalorias += refeicao.getCalorias();
        }
        return totalCalorias;
    }


    // Calcula o progresso da dieta em relação à meta de calorias diárias
    public double getProgresso() {
        //Se para evitar divisão por zero:
        if (caloriasDiarias == 0) {
            return 0;
        }
        return (double) calcularCaloriasTotais() / caloriasDiarias * 100;
    }

    // Verifica se a dieta foi concluída com base no objetivo e nas calorias consumidas
    public boolean isConcluido() {
        if (objetivo == Objetivo.PERDA_DE_PESO) {
            return calcularCaloriasTotais() <= caloriasDiarias;
        } else if (objetivo == Objetivo.GANHO_DE_MASSA) {
            return calcularCaloriasTotais() >= caloriasDiarias;
        }
        return false;
    }
}