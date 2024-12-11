package dados;

import negocio.Usuario;
import excecoes.UsuarioNaoEncontradoException;

import java.util.UUID;

public class RepositorioUsuarios {
    private static final int MAX_USUARIOS = 10;
    private Usuario[] usuarios;
    private int totalUsuarios;

    public RepositorioUsuarios() {
        this.usuarios = new Usuario[MAX_USUARIOS];
        this.totalUsuarios = 0;
    }

    public void adicionar(Usuario usuario) {
        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("Usuário inválido.");
        }
        if (totalUsuarios >= MAX_USUARIOS) {
            throw new IllegalStateException("Repositório de usuários está cheio.");
        }
        usuarios[totalUsuarios++] = usuario;
    }

    public void remover(UUID id) throws UsuarioNaoEncontradoException {
        for (int i = 0; i < totalUsuarios; i++) {
            if (usuarios[i].getId().equals(id)) {
                usuarios[i] = usuarios[totalUsuarios - 1];
                usuarios[totalUsuarios - 1] = null;
                totalUsuarios--;
                return;
            }
        }
        throw new UsuarioNaoEncontradoException("Usuário não encontrado.");
    }

    public Usuario buscar(UUID id) throws UsuarioNaoEncontradoException {
        for (int i = 0; i < totalUsuarios; i++) {
            if (usuarios[i].getId().equals(id)) {
                return usuarios[i];
            }
        }
        throw new UsuarioNaoEncontradoException("Usuário não encontrado.");
    }

    public void atualizar(Usuario usuario) throws UsuarioNaoEncontradoException {
        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("Usuário inválido.");
        }
        for (int i = 0; i < totalUsuarios; i++) {
            if (usuarios[i].getId().equals(usuario.getId())) {
                usuarios[i] = usuario;
                return;
            }
        }
        throw new UsuarioNaoEncontradoException("Usuário não encontrado.");
    }
}
