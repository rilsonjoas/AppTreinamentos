package dados;

import negocio.Usuario;
import excecoes.UsuarioNaoEncontradoException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RepositorioUsuarios {
    private Map<UUID, Usuario> usuarios;

    public RepositorioUsuarios() {
        this.usuarios = new HashMap<>();
    }

    public void adicionar(Usuario usuario) {
        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("Usuário inválido.");
        }
        usuarios.put(usuario.getId(), usuario);
    }

    public void remover(UUID id) throws UsuarioNaoEncontradoException {
        if (!usuarios.containsKey(id)) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado.");
        }
        usuarios.remove(id);
    }

    public Usuario buscar(UUID id) throws UsuarioNaoEncontradoException {
        Usuario usuario = usuarios.get(id);
        if (usuario == null) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado.");
        }
        return usuario;
    }

    public void atualizar(Usuario usuario) throws UsuarioNaoEncontradoException {
        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("Usuário inválido.");
        }
        if (!usuarios.containsKey(usuario.getId())) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado.");
        }
        usuarios.put(usuario.getId(), usuario);
    }
}

