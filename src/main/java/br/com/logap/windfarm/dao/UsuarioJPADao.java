package br.com.logap.windfarm.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.logap.windfarm.model.Usuario;

import java.io.Serializable;
import java.util.Optional;

public class UsuarioJPADao extends EntidadeJPADao<Usuario> implements UsuarioDAO, Serializable {

    @Deprecated
    public UsuarioJPADao() {this(null);}

    @Inject
    public UsuarioJPADao(EntityManager entityManager) {
        super(entityManager, Usuario.class);
    }

    @Override
    public Usuario buscarPorLogin(String login) {
        Query query = this.manager.createQuery("SELECT u FROM Usuario u WHERE u.login = :login");
        query.setParameter("login",login);

        Optional<Usuario> usuario = query.setMaxResults(1).getResultList().stream().findFirst();
        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            return null;
        }
    }
}
