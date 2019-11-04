package br.com.logap.windfarm.dao;

import br.com.logap.windfarm.model.Usuario;

import java.util.Optional;

import br.com.logap.windfarm.dao.EntidadeDAO;

public interface UsuarioDAO extends EntidadeDAO<Usuario> {

    Usuario buscarPorLogin(String login);
}
