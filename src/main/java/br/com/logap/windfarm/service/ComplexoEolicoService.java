package br.com.logap.windfarm.service;

import br.com.logap.windfarm.dao.ComplexoEolicoDAO;
import br.com.logap.windfarm.model.ComplexoEolico;
import br.com.logap.windfarm.service.Exceptions.NomeExistenteException;
import br.com.logap.windfarm.util.Transacional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class ComplexoEolicoService implements Serializable {

    @Inject
    private ComplexoEolicoDAO complexoEolicoDAO;


    public List<ComplexoEolico> iniciar() {
        return (List<ComplexoEolico>) complexoEolicoDAO.todos();
    }

    public ComplexoEolico buscarPorId(Integer id) {
        return complexoEolicoDAO.buscarPorId(id);
    }

    public List<ComplexoEolico> pesquisar(String termoPesquisa) {
        return complexoEolicoDAO.pesquisar(termoPesquisa);
    }

    public List<ComplexoEolico> listar() {
        List<ComplexoEolico> complexos = complexoEolicoDAO.todos();
        return complexos;
    }

    @Transacional
    public ComplexoEolico salvar(ComplexoEolico complexoEolico) throws NomeExistenteException {
        validarNome(complexoEolico);
        return complexoEolicoDAO.salvar(complexoEolico);
    }

    @Transacional
    public void remover(Integer id) {
        ComplexoEolico complexoEolico = complexoEolicoDAO.buscarPorId(id);
        complexoEolicoDAO.remover(complexoEolico);
    }


    private void validarNome(ComplexoEolico complexo) throws NomeExistenteException {
        ComplexoEolico complexoBanco = complexoEolicoDAO.buscarPorNome(complexo.getNome());
        if (complexoBanco != null) {
            if (complexo.getId() == null || complexoBanco.getId() != complexo.getId())
                throw new NomeExistenteException("Nome de Complexo Eólico já existente. Insira outro nome.");
        }
    }
}
