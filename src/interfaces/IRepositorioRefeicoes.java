package interfaces;

import excecoes.RefeicaoNaoEncontradaException;
import negocio.Refeicao;

import java.util.UUID;

public interface IRepositorioRefeicoes {

    public void adicionar(Refeicao refeicao);

    public void atualizar(Refeicao refeicao) throws RefeicaoNaoEncontradaException;

    public void remover(UUID id) throws RefeicaoNaoEncontradaException;

    public Refeicao buscar(UUID id) throws RefeicaoNaoEncontradaException;
}