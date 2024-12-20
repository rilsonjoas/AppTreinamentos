package dados;

import excecoes.ExercicioNaoEncontradoException;
import interfaces.IRepositorioExercicios;
import negocio.Exercicio;

import java.util.UUID;

public class RepositorioExerciciosArray implements IRepositorioExercicios {

    private Exercicio[] exercicios;
    private int proximoIndice;

    private static RepositorioExerciciosArray instanciaUnica;

    private RepositorioExerciciosArray() {
        exercicios = new Exercicio[10];
        proximoIndice = 0;
    }

    public static RepositorioExerciciosArray getInstanciaUnica() {
        if (instanciaUnica == null) {
            instanciaUnica = new RepositorioExerciciosArray();
        }
        return instanciaUnica;
    }

    private int procurarIndice(UUID id) {
        int i = 0;
        boolean achou = false;
        while ((!achou) && (i < this.proximoIndice)) {
            if (id.equals(this.exercicios[i].getId())) {
                achou = true;
            } else {
                i++;
            }
        }
        return achou ? i : proximoIndice;
    }


    @Override
    public void adicionar(Exercicio exercicio) {
        if (proximoIndice >= exercicios.length) {
            int novoTam = exercicios.length + 10;
            Exercicio[] arrayTemporario = new Exercicio[novoTam];
            System.arraycopy(exercicios, 0, arrayTemporario, 0, exercicios.length);
            exercicios = arrayTemporario;
        }
        exercicios[proximoIndice] = exercicio;
        proximoIndice++;
    }

    @Override
    public void atualizar(Exercicio exercicio) throws ExercicioNaoEncontradoException {
        if (exercicio != null) {
            int indice = this.procurarIndice(exercicio.getId());
            if (indice != proximoIndice) {
                exercicios[indice] = exercicio;
            } else {
                throw new ExercicioNaoEncontradoException("Exercício não encontrado para atualizar.");
            }
        } else {
            throw new IllegalArgumentException("Exercício inválido.");
        }
    }

    @Override
    public void remover(UUID id) throws ExercicioNaoEncontradoException {
        int i = this.procurarIndice(id);
        if (i < proximoIndice) {
            exercicios[i] = exercicios[proximoIndice - 1];
            exercicios[proximoIndice - 1] = null;
            proximoIndice--;
        } else {
            throw new ExercicioNaoEncontradoException("Exercício não encontrado para remover.");
        }
    }


    @Override
    public Exercicio buscar(UUID id) throws ExercicioNaoEncontradoException {
        int indice = procurarIndice(id);
        if (indice < proximoIndice) {
            return exercicios[indice];
        } else {
            throw new ExercicioNaoEncontradoException("Exercício não encontrado para buscar.");
        }
    }
}