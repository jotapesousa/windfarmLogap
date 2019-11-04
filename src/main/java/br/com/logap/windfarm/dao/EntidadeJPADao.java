package br.com.logap.windfarm.dao;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.logap.windfarm.model.Aerogerador;
import br.com.logap.windfarm.model.Entidade;

public abstract class EntidadeJPADao<T extends Entidade> implements EntidadeDAO<T> {

    protected EntityManager manager;
    protected Class<T> tClass;

    public EntidadeJPADao(EntityManager entityManager, Class<T> tClass) {
        this.manager = entityManager;
        this.tClass = tClass;
    }

    @Override
    public T buscarPorId(Integer id) { return manager.find(tClass, id); }

    @Override
    public T salvar(T entidade) {
        if(entidade.getId() != null) {
            this.manager.merge(entidade);
        } else {
            this.manager.persist(entidade);
        }
        return entidade;
    }

    @Override
    public void remover(T entidade) { manager.remove(entidade); }

    @Override
    public List<T> todos() {
        return manager.createQuery("SELECT t FROM "+tClass.getSimpleName()+" t").getResultList();
    }

    @Override
    public T buscarPorNome(String nome) {
        Query query =  manager.createQuery("SELECT t FROM "+tClass.getSimpleName()+" t WHERE t.nome = :nome")
                .setParameter("nome", nome);
        Object obj = query.setMaxResults(1).getResultList().stream().findFirst().orElse(null);
        return (T) obj;
    }

    @Override
    public List<T> pesquisar(String termoPesquisa) {
        return manager.createQuery("SELECT t FROM "+tClass.getSimpleName()+" t WHERE t.nome LIKE :termo")
                .setParameter("termo", "%"+termoPesquisa+"%").getResultList();
    }
}
