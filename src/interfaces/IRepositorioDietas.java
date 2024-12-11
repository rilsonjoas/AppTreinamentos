package interfaces;

import excecoes.DietaNaoEncontradaException;
import negocio.Dieta;

import java.util.UUID;

public interface IRepositorioDietas {

    void adicionar(Dieta dieta);

    void atualizar(Dieta dieta) throws DietaNaoEncontradaException;

    void remover(UUID id) throws DietaNaoEncontradaException;

    Dieta buscar(UUID id) throws DietaNaoEncontradaException;
}