package interfaces;

import excecoes.ExercicioNaoEncontradoException;
import negocio.Exercicio;

import java.util.UUID;

public interface IRepositorioExercicios {

    void adicionar(Exercicio exercicio);

    void atualizar(Exercicio exercicio) throws ExercicioNaoEncontradoException;

    void remover(UUID id) throws ExercicioNaoEncontradoException;

    Exercicio buscar(UUID id) throws ExercicioNaoEncontradoException;
}