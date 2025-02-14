package dados;

import excecoes.MetaNaoEncontradaException;
import interfaces.IRepositorioMetas;
import negocio.Meta;

import java.util.UUID;

public class RepositorioMetasArray implements IRepositorioMetas {

    private Meta[] metas;
    private int proximoIndice;

    private static RepositorioMetasArray instanciaUnica;

    private RepositorioMetasArray() {
        metas = new Meta[10];
        proximoIndice = 0;
    }

    public static RepositorioMetasArray getInstanciaUnica() {
        if (instanciaUnica == null) {
            instanciaUnica = new RepositorioMetasArray();
        }
        return instanciaUnica;
    }

    private int procurarIndice(UUID id) {
        int i = 0;
        boolean achou = false;
        while ((!achou) && (i < this.proximoIndice)) {
            if (id.equals(this.metas[i].getId())) {
                achou = true;
            } else {
                i++;
            }
        }
        return achou ? i : proximoIndice;
    }


    @Override
    public void adicionar(Meta meta) {
        if (proximoIndice >= metas.length) {
            int novoTam = metas.length + 10;
            Meta[] arrayTemporario = new Meta[novoTam];
            System.arraycopy(metas, 0, arrayTemporario, 0, metas.length);
            metas = arrayTemporario;
        }
        metas[proximoIndice] = meta;
        proximoIndice++;
    }

    @Override
    public void atualizar(Meta meta) throws MetaNaoEncontradaException {
        if (meta != null) {
            int indice = this.procurarIndice(meta.getId());
            if (indice != proximoIndice) {
                metas[indice] = meta;
            } else {
                throw new MetaNaoEncontradaException("Meta não encontrada para atualizar.");
            }
        } else {
            throw new IllegalArgumentException("Meta inválida.");
        }
    }


    @Override
    public void remover(UUID id) throws MetaNaoEncontradaException {
        int i = this.procurarIndice(id);
        if (i < proximoIndice) {
            metas[i] = metas[proximoIndice - 1];
            metas[proximoIndice - 1] = null;
            proximoIndice--;
        } else {
            throw new MetaNaoEncontradaException("Meta não encontrada para remover.");
        }
    }


    @Override
    public Meta buscar(UUID id) throws MetaNaoEncontradaException {
        int indice = procurarIndice(id);
        if (indice < proximoIndice) {
            return metas[indice];
        } else {
            throw new MetaNaoEncontradaException("Meta não encontrada.");
        }
    }
}