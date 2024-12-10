package dados;

import negocio.Dieta;
import excecoes.DietaNaoEncontradaException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RepositorioDietas {
    private Map<UUID, Dieta> dietas;

    public RepositorioDietas() {
        this.dietas = new HashMap<>();
    }

    public void adicionar(Dieta dieta) {
        if (dieta == null || dieta.getId() == null) {
            throw new IllegalArgumentException("Dieta inválida.");
        }
        dietas.put(dieta.getId(), dieta);
    }

    public void remover(UUID id) throws DietaNaoEncontradaException {
        if (!dietas.containsKey(id)) {
            throw new DietaNaoEncontradaException("Dieta não encontrada.");
        }
        dietas.remove(id);
    }

    public Dieta buscar(UUID id) throws DietaNaoEncontradaException {
        Dieta dieta = dietas.get(id);
        if (dieta == null) {
            throw new DietaNaoEncontradaException("Dieta não encontrada.");
        }
        return dieta;
    }

    public void atualizar(Dieta dieta) throws DietaNaoEncontradaException {
        if (dieta == null || dieta.getId() == null) {
            throw new IllegalArgumentException("Dieta inválida.");
        }
        if (!dietas.containsKey(dieta.getId())) {
            throw new DietaNaoEncontradaException("Dieta não encontrada.");
        }
        dietas.put(dieta.getId(), dieta);
    }
}