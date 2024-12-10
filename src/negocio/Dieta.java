package negocio;

import java.util.*;

public class Dieta {
    private UUID id;
    private String nome;
    private Objetivo objetivo;
    private int caloriasDiarias;
    private Map<String, Double> macronutrientes;
    private List<Refeicao> refeicoes;
    private Usuario usuario;

    public enum Objetivo {
        PERDA_DE_PESO, GANHO_DE_MASSA, MANUTENCAO;
    }

    public Dieta(String nome, Objetivo objetivo, int caloriasDiarias, Usuario usuario) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.objetivo = objetivo;
        this.caloriasDiarias = caloriasDiarias;
        this.macronutrientes = new HashMap<>();
        this.refeicoes = new ArrayList<>();
        this.usuario = usuario;
    }

    // Getters e Setters
    public UUID getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public int getCaloriasDiarias() {
        return caloriasDiarias;
    }

    public void setCaloriasDiarias(int caloriasDiarias) {
        this.caloriasDiarias = caloriasDiarias;
    }

    public Map<String, Double> getMacronutrientes() {
        return macronutrientes;
    }

    public void setMacronutrientes(Map<String, Double> macronutrientes) {
        this.macronutrientes = macronutrientes;
    }

    public List<Refeicao> getRefeicoes() {
        return refeicoes;
    }

    public void setRefeicoes(List<Refeicao> refeicoes) {
        this.refeicoes = refeicoes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    // Adiciona uma refeição à lista de refeições
    public void adicionarRefeicao(Refeicao refeicao) {
        refeicoes.add(refeicao);
    }

    // Remove uma refeição da lista de refeições
    public void removerRefeicao(Refeicao refeicao) {
        refeicoes.remove(refeicao);
    }

    // Calcula a soma de macronutrientes com base nas refeições
    public Map<String, Double> calcularMacronutrientes() {
        //Calcular;
        return macronutrientes;
    }

    // Calcula o total de calorias consumidas
    public int calcularCaloriasTotais() {
        int totalCalorias = 0;
        for (Refeicao refeicao : refeicoes) {
            totalCalorias += refeicao.getCalorias();
        }
        return totalCalorias;
    }

    // Calcula o progresso com base no objetivo
    public double getProgresso() {
        int caloriasConsumidas = calcularCaloriasTotais();
        return (double) caloriasConsumidas / caloriasDiarias * 100;
    }

    // Verifica se o objetivo foi concluído
    public boolean isConcluido() {
        if (objetivo == Objetivo.PERDA_DE_PESO) {
            return calcularCaloriasTotais() <= caloriasDiarias;
        } else if (objetivo == Objetivo.GANHO_DE_MASSA) {
            return calcularCaloriasTotais() >= caloriasDiarias;
        }
        return false;
    }
}
