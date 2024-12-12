package negocio;

import java.util.*;

public class Refeicao {
    private UUID id;
    private String nome;
    private String descricao;
    private int calorias;
    private Map<String, Double> macronutrientes;

    // Construtor
    public Refeicao() {
        this.id = UUID.randomUUID(); // Gera um ID único
    }

    // Construtor com parâmetros
    public Refeicao(String nome, String descricao, Map<String, Double> macronutrientes) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.descricao = descricao;
        this.macronutrientes = macronutrientes;
        this.calorias = calcularCalorias();
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

    public int getCalorias() {
        return calorias;
    }

    public Map<String, Double> getMacronutrientes() {
        return macronutrientes;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setMacronutrientes(Map<String, Double> macronutrientes) {
        this.macronutrientes = macronutrientes;
        this.calorias = calcularCalorias(); // Recalcula as calorias após atualizar os macronutrientes
    }

    // OUTROS MÉTODOS
    // Método para calcular o total de calorias da refeição com base nos macronutrientes
    public int calcularCalorias() {
        double totalCalorias = 0;

        //Se macronutrientes não for nulo
        if (macronutrientes != null) {
            //Interação por todos os macronutrientes da nutrição
            for (Map.Entry<String, Double> entry : macronutrientes.entrySet()) {
                String macro = entry.getKey();
                Double quantidade = entry.getValue();
                if (macro.equals("Proteínas") || macro.equals("Carboidratos")) {
                    totalCalorias += quantidade;
                } else if (macro.equals("Gorduras")) {
                    totalCalorias += quantidade;
                }
            }
        }

        //Conversão do totalCalorias para um valor inteiro
        return (int) Math.round(totalCalorias);
    }

    // Retorna uma cópia dos macronutrientes para evitar modificações diretas em Refeição
    public Map<String, Double> calcularMacronutrientes() {
        return new HashMap<>(macronutrientes);
    }
}