package dados;

import negocio.Treino;
import excecoes.TreinoNaoEncontradoException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RepositorioTreinos {
    private Map<UUID, Treino> treinos;

    public RepositorioTreinos() {
        this.treinos = new HashMap<>();
    }

    public void adicionar(Treino treino) {
        if (treino == null || treino.getId() == null) {
            throw new IllegalArgumentException("Treino inválido.");
        }
        treinos.put(treino.getId(), treino);
    }

    public void remover(UUID id) throws TreinoNaoEncontradoException {
        if (!treinos.containsKey(id)) {
            throw new TreinoNaoEncontradoException("Treino não encontrado.");
        }
        treinos.remove(id);
    }

    public Treino buscar(UUID id) throws TreinoNaoEncontradoException {
        Treino treino = treinos.get(id);
        if (treino == null) {
            throw new TreinoNaoEncontradoException("Treino não encontrado.");
        }
        return treino;
    }

    public void atualizar(Treino treino) throws TreinoNaoEncontradoException {
        if (treino == null || treino.getId() == null) {
            throw new IllegalArgumentException("Treino inválido.");
        }
        if (!treinos.containsKey(treino.getId())) {
            throw new TreinoNaoEncontradoException("Treino não encontrado.");
        }
        treinos.put(treino.getId(), treino);
    }
}