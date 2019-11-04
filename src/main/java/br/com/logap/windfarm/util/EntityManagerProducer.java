package br.com.logap.windfarm.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer {

    @Inject
    private PersistenceProperties properties;

    @ApplicationScoped
    @Produces
    public EntityManagerFactory criarFactory() {
        return Persistence.createEntityManagerFactory("windfarmPU", properties.get());
    }

    public void fecharFactory(@Disposes EntityManagerFactory factory) {
        factory.close();
    }

    @Produces
    @RequestScoped
    public EntityManager criarManager(EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

    public void fecharManager(@Disposes EntityManager manager) {
        manager.close();
    }
}