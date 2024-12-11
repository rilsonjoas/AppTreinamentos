package interfaces;

import excecoes.TreinoNaoEncontradoException;
import negocio.Treino;

import java.util.UUID;

public interface IRepositorioTreinos {

    public void adicionar(Treino treino);

    public void atualizar(Treino treino)throws TreinoNaoEncontradoException;

    public void remover(UUID id)throws TreinoNaoEncontradoException;

    public Treino buscar(UUID id)throws TreinoNaoEncontradoException;
}