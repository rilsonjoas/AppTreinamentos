package dados;

import excecoes.TreinoNaoEncontradoException;
import interfaces.IRepositorioTreinos;
import negocio.Treino;

import java.util.UUID;

public class RepositorioTreinosArray implements IRepositorioTreinos {

    private Treino[] treinos;
    private int proximoIndice;

    private static RepositorioTreinosArray instanciaUnica;


    public RepositorioTreinosArray() {
        treinos = new Treino[10];
        proximoIndice = 0;
    }

    public static RepositorioTreinosArray getInstanciaUnica() {
        if (instanciaUnica == null) {
            instanciaUnica = new RepositorioTreinosArray();
        }
        return instanciaUnica;
    }

    private int procurarIndice(UUID id) {
        int i = 0;
        boolean achou = false;
        while ((!achou) && (i < this.proximoIndice)) {
            if (id.equals(this.treinos[i].getId())) {
                achou = true;
            } else {
                i = i + 1;
            }
        }
        return i;
    }

    @Override
    public void adicionar(Treino treino) {
        if(proximoIndice > treinos.length - 1) {
            int novoTam = proximoIndice + 10;
            Treino[] arrayTemporario = new Treino[novoTam];

            for (int i = 0; i < proximoIndice; i++) {
                arrayTemporario[i] = treinos[i];
            }
            treinos = arrayTemporario;
        }

        treinos[proximoIndice] = treino;
        proximoIndice++;

    }

    @Override
    public void atualizar(Treino treino) throws TreinoNaoEncontradoException {
        if(treino != null) {
            int indice = this.procurarIndice(treino.getId());
            if (indice != proximoIndice) {
                treinos[indice] = treino;
            }

        }else{
            throw new IllegalArgumentException("Treino inválido.");
        }

    }

    @Override
    public void remover(UUID id) throws TreinoNaoEncontradoException {
        int i = this.procurarIndice(id);
        if (i != this.proximoIndice) {
            this.treinos[i] = this.treinos[this.proximoIndice - 1];
            this.treinos[this.proximoIndice - 1] = null;
            this.proximoIndice = this.proximoIndice - 1;
        } else {
            throw  new TreinoNaoEncontradoException("Treino Não encontrado");
        }
    }

    @Override
    public Treino buscar(UUID id) throws TreinoNaoEncontradoException {
        int indice = procurarIndice(id);
        if ( indice < proximoIndice) {
            return treinos[indice];

        }else{
            throw new TreinoNaoEncontradoException("Treino Não encontrado");
        }

    }
}