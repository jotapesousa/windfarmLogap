package br.com.logap.windfarm.dao;

import br.com.logap.windfarm.model.ParqueEolico;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ParqueEolicoJPADao extends EntidadeJPADao<ParqueEolico> implements ParqueEolicoDAO {

    @Deprecated
    public ParqueEolicoJPADao() { this(null); }

    @Inject
    public ParqueEolicoJPADao(EntityManager entityManager) {
        super(entityManager, ParqueEolico.class);
    }

}
