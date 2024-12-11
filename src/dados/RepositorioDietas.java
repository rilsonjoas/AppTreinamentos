package dados;

import negocio.Dieta;
import excecoes.DietaNaoEncontradaException;

import java.util.UUID;

public class RepositorioDietas {
    private static final int MAX_DIETAS = 10;
    private Dieta[] dietas;
    private int totalDietas;

    public RepositorioDietas() {
        this.dietas = new Dieta[MAX_DIETAS];
        this.totalDietas = 0;
    }

    public void adicionar(Dieta dieta) {
        if (dieta == null || dieta.getId() == null) {
            throw new IllegalArgumentException("Dieta inválida.");
        }
        if (totalDietas >= MAX_DIETAS) {
            throw new IllegalStateException("Repositório de dietas está cheio.");
        }
        dietas[totalDietas++] = dieta;
    }

    public void remover(UUID id) throws DietaNaoEncontradaException {
        int index = -1;
        for (int i = 0; i < totalDietas; i++) {
            if (dietas[i].getId().equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new DietaNaoEncontradaException("Dieta não encontrada.");
        }

        dietas[index] = dietas[totalDietas - 1];
        dietas[totalDietas - 1] = null;
        totalDietas--;
    }

    public Dieta buscar(UUID id) throws DietaNaoEncontradaException {
        for (int i = 0; i < totalDietas; i++) {
            if (dietas[i].getId().equals(id)) {
                return dietas[i];
            }
        }
        throw new DietaNaoEncontradaException("Dieta não encontrada.");
    }

    public void atualizar(Dieta dieta) throws DietaNaoEncontradaException {
        if (dieta == null || dieta.getId() == null) {
            throw new IllegalArgumentException("Dieta inválida.");
        }
        for (int i = 0; i < totalDietas; i++) {
            if (dietas[i].getId().equals(dieta.getId())) {
                dietas[i] = dieta;
                return;
            }
        }
        throw new DietaNaoEncontradaException("Dieta não encontrada.");
    }
}