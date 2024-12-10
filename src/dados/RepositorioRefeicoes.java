package dados;

import negocio.Refeicao;
import excecoes.RefeicaoNaoEncontradaException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RepositorioRefeicoes {
    private Map<UUID, Refeicao> refeicoes;

    public RepositorioRefeicoes() {
        this.refeicoes = new HashMap<>();
    }

    public void adicionar(Refeicao refeicao) {
        if (refeicao == null || refeicao.getId() == null) {
            throw new IllegalArgumentException("Refeição inválida.");
        }
        refeicoes.put(refeicao.getId(), refeicao);
    }

    public void remover(UUID id) throws RefeicaoNaoEncontradaException {
        if (!refeicoes.containsKey(id)) {
            throw new RefeicaoNaoEncontradaException("Refeição não encontrada.");
        }
        refeicoes.remove(id);
    }

    public Refeicao buscar(UUID id) throws RefeicaoNaoEncontradaException {
        Refeicao refeicao = refeicoes.get(id);
        if (refeicao == null) {
            throw new RefeicaoNaoEncontradaException("Refeição não encontrada.");
        }
        return refeicao;
    }

    public void atualizar(Refeicao refeicao) throws RefeicaoNaoEncontradaException {
        if (refeicao == null || refeicao.getId() == null) {
            throw new IllegalArgumentException("Refeição inválida.");
        }
        if (!refeicoes.containsKey(refeicao.getId())) {
            throw new RefeicaoNaoEncontradaException("Refeição não encontrada.");
        }
        refeicoes.put(refeicao.getId(), refeicao);
    }
}