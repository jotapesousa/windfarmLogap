package br.com.logap.windfarm.dao;

import br.com.logap.windfarm.model.ComplexoEolico;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ComplexoEolicoJPADao extends EntidadeJPADao<ComplexoEolico> implements ComplexoEolicoDAO {

    @Deprecated
    protected ComplexoEolicoJPADao(){this(null);}

    @Inject
    public ComplexoEolicoJPADao(EntityManager entityManager) {
        super(entityManager, ComplexoEolico.class);
    }
}
