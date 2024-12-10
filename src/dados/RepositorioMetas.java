package dados;

import negocio.Meta;
import excecoes.MetaNaoEncontradaException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RepositorioMetas {
    private Map<UUID, Meta> metas;

    public RepositorioMetas() {
        this.metas = new HashMap<>();
    }

    public void adicionar(Meta meta) {
        if (meta == null || meta.getId() == null) {
            throw new IllegalArgumentException("Meta inválida.");
        }
        metas.put(meta.getId(), meta);
    }

    public void remover(UUID id) throws MetaNaoEncontradaException {
        if (!metas.containsKey(id)) {
            throw new MetaNaoEncontradaException("Meta não encontrada.");
        }
        metas.remove(id);
    }

    public Meta buscar(UUID id) throws MetaNaoEncontradaException {
        Meta meta = metas.get(id);
        if (meta == null) {
            throw new MetaNaoEncontradaException("Meta não encontrada.");
        }
        return meta;
    }

    public void atualizar(Meta meta) throws MetaNaoEncontradaException {
        if (meta == null || meta.getId() == null) {
            throw new IllegalArgumentException("Meta inválida.");
        }
        if (!metas.containsKey(meta.getId())) {
            throw new MetaNaoEncontradaException("Meta não encontrada.");
        }
        metas.put(meta.getId(), meta);
    }
}