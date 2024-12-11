package dados;

import negocio.Refeicao;
import excecoes.RefeicaoNaoEncontradaException;

import java.util.UUID;

public class RepositorioRefeicoes {
    private static final int MAX_REFEICOES = 100; // Define um tamanho máximo
    private Refeicao[] refeicoes;
    private int totalRefeicoes;

    public RepositorioRefeicoes() {
        this.refeicoes = new Refeicao[MAX_REFEICOES];
        this.totalRefeicoes = 0;
    }

    public void adicionar(Refeicao refeicao) {
        if (refeicao == null || refeicao.getId() == null) {
            throw new IllegalArgumentException("Refeição inválida.");
        }
        if (totalRefeicoes >= MAX_REFEICOES) {
            throw new IllegalStateException("Repositório de refeições está cheio.");
        }
        refeicoes[totalRefeicoes++] = refeicao;
    }

    public void remover(UUID id) throws RefeicaoNaoEncontradaException {
        int index = -1;
        for (int i = 0; i < totalRefeicoes; i++) {
            if (refeicoes[i].getId().equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new RefeicaoNaoEncontradaException("Refeição não encontrada.");
        }

        refeicoes[index] = refeicoes[totalRefeicoes - 1];
        refeicoes[totalRefeicoes - 1] = null;
        totalRefeicoes--;
    }

    public Refeicao buscar(UUID id) throws RefeicaoNaoEncontradaException {
        for (int i = 0; i < totalRefeicoes; i++) {
            if (refeicoes[i].getId().equals(id)) {
                return refeicoes[i];
            }
        }
        throw new RefeicaoNaoEncontradaException("Refeição não encontrada.");
    }

    public void atualizar(Refeicao refeicao) throws RefeicaoNaoEncontradaException {
        if (refeicao == null || refeicao.getId() == null) {
            throw new IllegalArgumentException("Refeição inválida.");
        }
        for (int i = 0; i < totalRefeicoes; i++) {
            if (refeicoes[i].getId().equals(refeicao.getId())) {
                refeicoes[i] = refeicao;
                return;
            }
        }
        throw new RefeicaoNaoEncontradaException("Refeição não encontrada.");
    }
}