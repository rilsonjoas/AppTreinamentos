package dados;

import negocio.Exercicio;
import excecoes.ExercicioNaoEncontradoException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RepositorioExercicios {
    private Map<UUID, Exercicio> exercicios;

    public RepositorioExercicios() {
        this.exercicios = new HashMap<>();
    }

    public void adicionar(Exercicio exercicio) {
        if (exercicio == null || exercicio.getId() == null) {
            throw new IllegalArgumentException("Exercício inválido.");
        }
        exercicios.put(exercicio.getId(), exercicio);
    }

    public void remover(UUID id) throws ExercicioNaoEncontradoException {
        if (!exercicios.containsKey(id)) {
            throw new ExercicioNaoEncontradoException("Exercício não encontrado.");
        }
        exercicios.remove(id);
    }

    public Exercicio buscar(UUID id) throws ExercicioNaoEncontradoException {
        Exercicio exercicio = exercicios.get(id);
        if (exercicio == null) {
            throw new ExercicioNaoEncontradoException("Exercício não encontrado.");
        }
        return exercicio;
    }

    public void atualizar(Exercicio exercicio) throws ExercicioNaoEncontradoException {
        if (exercicio == null || exercicio.getId() == null) {
            throw new IllegalArgumentException("Exercício inválido.");
        }
        if (!exercicios.containsKey(exercicio.getId())) {
            throw new ExercicioNaoEncontradoException("Exercício não encontrado.");
        }
        exercicios.put(exercicio.getId(), exercicio);
    }
}