package dados;

import negocio.Treino;
import excecoes.TreinoNaoEncontradoException;

import java.util.UUID;

public class RepositorioTreinos {
    private static final int MAX_TREINOS = 10;
    private Treino[] treinos;
    private int totalTreinos;

    public RepositorioTreinos() {
        this.treinos = new Treino[MAX_TREINOS];
        this.totalTreinos = 0;
    }

    public void adicionar(Treino treino) {
        if (treino == null || treino.getId() == null) {
            throw new IllegalArgumentException("Treino inválido.");
        }
        if (totalTreinos >= MAX_TREINOS) {
            throw new IllegalStateException("Repositório de treinos está cheio.");
        }
        treinos[totalTreinos++] = treino;
    }

    public void remover(UUID id) throws TreinoNaoEncontradoException {
        int index = -1;
        for (int i = 0; i < totalTreinos; i++) {
            if (treinos[i].getId().equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new TreinoNaoEncontradoException("Treino não encontrado.");
        }

        treinos[index] = treinos[totalTreinos - 1];
        treinos[totalTreinos - 1] = null;
        totalTreinos--;
    }

    public Treino buscar(UUID id) throws TreinoNaoEncontradoException {
        for (int i = 0; i < totalTreinos; i++) {
            if (treinos[i].getId().equals(id)) {
                return treinos[i];
            }
        }
        throw new TreinoNaoEncontradoException("Treino não encontrado.");
    }

    public void atualizar(Treino treino) throws TreinoNaoEncontradoException {
        if (treino == null || treino.getId() == null) {
            throw new IllegalArgumentException("Treino inválido.");
        }
        for (int i = 0; i < totalTreinos; i++) {
            if (treinos[i].getId().equals(treino.getId())) {
                treinos[i] = treino;
                return;
            }
        }
        throw new TreinoNaoEncontradoException("Treino não encontrado.");
    }
}