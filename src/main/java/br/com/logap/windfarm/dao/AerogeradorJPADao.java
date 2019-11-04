package br.com.logap.windfarm.dao;

import br.com.logap.windfarm.model.Aerogerador;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AerogeradorJPADao extends EntidadeJPADao<Aerogerador> implements AerogeradorDAO {

    @Inject
    public AerogeradorJPADao(EntityManager entityManager) {
        super(entityManager, Aerogerador.class);
    }
}
