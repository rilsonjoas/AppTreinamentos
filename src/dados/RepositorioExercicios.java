package dados;

import negocio.Exercicio;
import excecoes.ExercicioNaoEncontradoException;

import java.util.UUID;

public class RepositorioExercicios {
    private static final int MAX_EXERCICIOS = 10;
    private Exercicio[] exercicios;
    private int totalExercicios;

    public RepositorioExercicios() {
        this.exercicios = new Exercicio[MAX_EXERCICIOS];
        this.totalExercicios = 0;
    }

    public void adicionar(Exercicio exercicio) {
        if (exercicio == null || exercicio.getId() == null) {
            throw new IllegalArgumentException("Exercício inválido.");
        }
        if (totalExercicios >= MAX_EXERCICIOS) {
            throw new IllegalStateException("Repositório de exercícios está cheio.");
        }
        exercicios[totalExercicios++] = exercicio;
    }

    public void remover(UUID id) throws ExercicioNaoEncontradoException {
        int index = -1;
        for (int i = 0; i < totalExercicios; i++) {
            if (exercicios[i].getId().equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new ExercicioNaoEncontradoException("Exercício não encontrado.");
        }

        exercicios[index] = exercicios[totalExercicios - 1];
        exercicios[totalExercicios - 1] = null;
        totalExercicios--;
    }

    public Exercicio buscar(UUID id) throws ExercicioNaoEncontradoException {
        for (int i = 0; i < totalExercicios; i++) {
            if (exercicios[i].getId().equals(id)) {
                return exercicios[i];
            }
        }
        throw new ExercicioNaoEncontradoException("Exercício não encontrado.");
    }

    public void atualizar(Exercicio exercicio) throws ExercicioNaoEncontradoException {
        if (exercicio == null || exercicio.getId() == null) {
            throw new IllegalArgumentException("Exercício inválido.");
        }
        for (int i = 0; i < totalExercicios; i++) {
            if (exercicios[i].getId().equals(exercicio.getId())) {
                exercicios[i] = exercicio;
                return;
            }
        }
        throw new ExercicioNaoEncontradoException("Exercício não encontrado.");
    }
}