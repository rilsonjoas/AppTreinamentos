package negocio;
import dados.RepositorioTreinosArray;
import excecoes.TreinoNaoEncontradoException;

import java.util.ArrayList;
import java.util.UUID;

public class ControladorTreino {

    private RepositorioTreinosArray repositorio;
    private  Treino treino;


    private UUID id;
    private String nome;
    private String tipoDeTreino;
    private int duracao;
    private int nivelDeDificuldade;
    ArrayList<Exercicio> exercicios ;

    private double caloriasQueimadas;
    private double progresso;
    private boolean concluido;

    public ControladorTreino(Treino treino) throws TreinoNaoEncontradoException {
        repositorio = RepositorioTreinosArray.getInstanciaUnica();
        this.treino = repositorio.buscar(treino.getId());
        exercicios = treino.getExercicios();

    }

    public void removerExercicio(Exercicio exercicio) {
        if (exercicios.remove(exercicio)) {
            calcularCaloriasQueimadas(); // Recalcula as calorias queimadas
            atualizarProgresso(); // Atualiza o progresso do treino
        }
    }

    // Método para adicionar um exercício ao treino
    public void adicionarExercicio(Exercicio exercicio) {
        if (exercicio != null && !exercicios.contains(exercicio)) {
            exercicios.add(exercicio);
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

}
