package dados;

import negocio.Meta;
import excecoes.MetaNaoEncontradaException;

import java.util.UUID;

public class RepositorioMetas {
    private static final int MAX_METAS = 10; // Define um tamanho máximo
    private Meta[] metas;
    private int totalMetas;

    public RepositorioMetas() {
        this.metas = new Meta[MAX_METAS];
        this.totalMetas = 0;
    }

    public void adicionar(Meta meta) {
        if (meta == null || meta.getId() == null) {
            throw new IllegalArgumentException("Meta inválida.");
        }
        if (totalMetas >= MAX_METAS) {
            throw new IllegalStateException("Repositório de metas está cheio.");
        }
        metas[totalMetas++] = meta;
    }

    public void remover(UUID id) throws MetaNaoEncontradaException {
        int index = -1;
        for (int i = 0; i < totalMetas; i++) {
            if (metas[i].getId().equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new MetaNaoEncontradaException("Meta não encontrada.");
        }

        metas[index] = metas[totalMetas - 1];
        metas[totalMetas - 1] = null;
        totalMetas--;
    }

    public Meta buscar(UUID id) throws MetaNaoEncontradaException {
        for (int i = 0; i < totalMetas; i++) {
            if (metas[i].getId().equals(id)) {
                return metas[i];
            }
        }
        throw new MetaNaoEncontradaException("Meta não encontrada.");
    }

    public void atualizar(Meta meta) throws MetaNaoEncontradaException {
        if (meta == null || meta.getId() == null) {
            throw new IllegalArgumentException("Meta inválida.");
        }
        for (int i = 0; i < totalMetas; i++) {
            if (metas[i].getId().equals(meta.getId())) {
                metas[i] = meta;
                return;
            }
        }
        throw new MetaNaoEncontradaException("Meta não encontrada.");
    }
}