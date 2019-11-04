package br.com.logap.windfarm.service;

import br.com.logap.windfarm.dao.ComplexoEolicoDAO;
import br.com.logap.windfarm.dao.ParqueEolicoDAO;
import br.com.logap.windfarm.model.ComplexoEolico;
import br.com.logap.windfarm.model.ParqueEolico;
import br.com.logap.windfarm.service.Exceptions.NomeExistenteException;
import br.com.logap.windfarm.service.Exceptions.ValorNuloException;
import br.com.logap.windfarm.util.Transacional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class ParqueEolicoService implements Serializable {

    @Inject
    private ParqueEolicoDAO parqueEolicoDAO;

    @Inject
    private ComplexoEolicoDAO complexoEolicoDAO;


    public List<ParqueEolico> iniciar() {
        return parqueEolicoDAO.todos();
    }

    public ParqueEolico buscarPorId(Integer id) {
        return parqueEolicoDAO.buscarPorId(id);
    }

    public List<ParqueEolico> pesquisar(String termoPesquisa) {
        return parqueEolicoDAO.pesquisar(termoPesquisa);
    }

    public List<ParqueEolico> listar() {
        return parqueEolicoDAO.todos();
    }

    @Transacional
    public ParqueEolico salvar(ParqueEolico parqueEolico) throws NomeExistenteException{
        validarNome(parqueEolico);
        return parqueEolicoDAO.salvar(parqueEolico);
    }

    @Transacional
    public void remover(Integer id) {
        ParqueEolico parqueBanco = parqueEolicoDAO.buscarPorId(id);
        parqueEolicoDAO.remover(parqueBanco);
    }

    private void validarNome(ParqueEolico parque) throws NomeExistenteException {
        ParqueEolico parqueBanco = parqueEolicoDAO.buscarPorNome(parque.getNome());

        if (parqueBanco != null) {
            if (parque.getId() == null || parqueBanco.getId() != parque.getId())
                throw new NomeExistenteException();
        }
    }
}
