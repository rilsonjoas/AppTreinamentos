package interfaces;

import excecoes.MetaNaoEncontradaException;
import negocio.Meta;

import java.util.UUID;

public interface IRepositorioMetas {

    void adicionar(Meta meta);

    void atualizar(Meta meta) throws MetaNaoEncontradaException;

    void remover(UUID id) throws MetaNaoEncontradaException;

    Meta buscar(UUID id) throws MetaNaoEncontradaException;
}