import java.util.*;

public class Refeicao {
    private UUID id;
    private String nome;
    private String descricao;
    private int calorias;
    private Map<String, Double> macronutrientes; // Exemplo: proteínas, carboidratos, gorduras

    // Construtor
    public Refeicao(String nome, String descricao, Map<String, Double> macronutrientes) {
        this.id = UUID.randomUUID(); // Gera um ID único automaticamente
        this.nome = nome;
        this.descricao = descricao;
        this.macronutrientes = macronutrientes;
        this.calorias = calcularCalorias(); // Calcula calorias com base nos macronutrientes fornecidos
    }

    // Método para calcular calorias baseado nos macronutrientes
    public int calcularCalorias() {
        double totalCalorias = 0;
        if (macronutrientes != null) {
            // Assumindo os valores padrão para calorias por grama:
            // Proteínas: 4 kcal, Carboidratos: 4 kcal, Gorduras: 9 kcal
            totalCalorias += macronutrientes.getOrDefault("Proteínas", 0.0) * 4;
            totalCalorias += macronutrientes.getOrDefault("Carboidratos", 0.0) * 4;
            totalCalorias += macronutrientes.getOrDefault("Gorduras", 0.0) * 9;
        }
        return (int) Math.round(totalCalorias); // Retorna calorias arredondadas
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setMacronutrientes(Map<String, Double> macronutrientes) {
        this.macronutrientes = macronutrientes;
        this.calorias = calcularCalorias(); // Recalcula calorias ao alterar macronutrientes
    }

    public Map<String, Double> getMacronutrientes() {
        return macronutrientes;
    }

    //Outros métodos
    public Map<String, Double> calcularMacronutrientes() {
        return new HashMap<>(macronutrientes); // Retorna uma cópia dos macronutrientes
    }
}
