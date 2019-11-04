package br.com.logap.windfarm.bean;

import br.com.logap.windfarm.model.ComplexoEolico;
import br.com.logap.windfarm.service.ComplexoEolicoService;
import br.com.logap.windfarm.service.Exceptions.NomeExistenteException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class ComplexoEolicoBean implements Serializable {

    private ComplexoEolicoService complexoEolicoService;

    private ComplexoEolico complexoEolico;
    private List<ComplexoEolico> complexos;
    private String termoPesquisa;


    @Inject
    public ComplexoEolicoBean(ComplexoEolicoService complexoEolicoService) {
        this.complexoEolicoService = complexoEolicoService;
        this.complexoEolico = new ComplexoEolico();
    }


    public void iniciar() {
        complexos = complexoEolicoService.iniciar();
    }

    public void buscar() {
        complexoEolico = complexoEolicoService.buscarPorId(complexoEolico.getId());
    }

    public void pesquisar() {
        this.complexos = this.complexoEolicoService.pesquisar(termoPesquisa);
    }

    public void listar() {
        complexos = complexoEolicoService.listar();
    }

    public String salvar() {
        try {
            complexoEolico = complexoEolicoService.salvar(complexoEolico);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Complexo Eólico salvo com sucesso!"));

            return "/complexoEolico/pesquisa?faces-redirect=true";
        } catch (NomeExistenteException e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),""));
            return "";
        }
    }

    public String remover(Integer id) {
        complexoEolicoService.remover(id);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Complexo Eólico removido com sucesso!"));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        return "/complexoEolico/pesquisa?faces-redirect=true";
    }


    public ComplexoEolico getComplexoEolico() { return complexoEolico; }

    public List<ComplexoEolico> getComplexos() { return complexos; }

    public String getTermoPesquisa() { return termoPesquisa; }

    public void setTermoPesquisa(String termoPesquisa) { this.termoPesquisa = termoPesquisa; }
}
