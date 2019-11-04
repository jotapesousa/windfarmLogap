package br.com.logap.windfarm.service;

import br.com.logap.windfarm.dao.AerogeradorDAO;
import br.com.logap.windfarm.dao.ParqueEolicoDAO;
import br.com.logap.windfarm.model.Aerogerador;
import br.com.logap.windfarm.model.ParqueEolico;
import br.com.logap.windfarm.service.Exceptions.NomeExistenteException;
import br.com.logap.windfarm.util.Transacional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class AerogeradorService implements Serializable {

    @Inject
    private AerogeradorDAO aerogeradorDAO;


    public List<Aerogerador> iniciar() {
        return aerogeradorDAO.todos();
    }

    public Aerogerador buscarPorId(Integer id) {
        return aerogeradorDAO.buscarPorId(id);
    }

    public List<Aerogerador> pesquisar(String termoPesquisa) {
        return aerogeradorDAO.pesquisar(termoPesquisa);
    }

    @Transacional
    public Aerogerador salvar(Aerogerador aerogerador) throws NomeExistenteException{
        validarNome(aerogerador);
        return aerogeradorDAO.salvar(aerogerador);
    }

    @Transacional
    public void remover(Integer id) {
        Aerogerador aerogerador = aerogeradorDAO.buscarPorId(id);
        aerogeradorDAO.remover(aerogerador);
    }

    private void validarNome(Aerogerador aerogerador) throws NomeExistenteException{
        Aerogerador aerogeradorBanco = aerogeradorDAO.buscarPorNome(aerogerador.getNome());

        if (aerogeradorBanco != null) {
            if (aerogerador.getId() == null || aerogeradorBanco.getId() != aerogerador.getId())
                throw new NomeExistenteException();
        }
    }
}
