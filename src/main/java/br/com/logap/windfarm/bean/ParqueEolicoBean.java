package br.com.logap.windfarm.bean;

import br.com.logap.windfarm.model.ComplexoEolico;
import br.com.logap.windfarm.model.ParqueEolico;
import br.com.logap.windfarm.service.ComplexoEolicoService;
import br.com.logap.windfarm.service.Exceptions.NomeExistenteException;
import br.com.logap.windfarm.service.ParqueEolicoService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
//import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class ParqueEolicoBean implements Serializable {

    private ParqueEolicoService parqueEolicoService;

    private ComplexoEolicoService complexoEolicoService;


    private ParqueEolico parqueEolico;
    private List<ParqueEolico> parques;
    private String termoPesquisa;


    @Inject
    public ParqueEolicoBean(ParqueEolicoService parqueEolicoService, ComplexoEolicoService complexoEolicoService) {
        this.parqueEolicoService = parqueEolicoService;
        this.complexoEolicoService = complexoEolicoService;
        this.parqueEolico = new ParqueEolico();
    }


    public void iniciar() {
        parques = parqueEolicoService.iniciar();
    }

    public void buscar() {
        parqueEolico = parqueEolicoService.buscarPorId(parqueEolico.getId());
    }

    public void pesquisar() { this.parques = this.parqueEolicoService.pesquisar(termoPesquisa); }

    public String salvar() {
        try {
            parqueEolico = parqueEolicoService.salvar(parqueEolico);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parque Eólico salvo com sucesso!"));

            return "/parqueEolico/pesquisa?faces-redirect=true";
        } catch (NomeExistenteException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(),""));
            return "";
        }
    }

    public String remover(Integer id) {
        parqueEolicoService.remover(id);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parque Eólico removido com sucesso!"));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        return "/parqueEolico/pesquisa?faces-redirect=true";
    }

    public List<ComplexoEolico> listarComplexos() { return complexoEolicoService.listar(); }


    public ParqueEolico getParqueEolico() { return parqueEolico; }

    public List<ParqueEolico> getParques() { return parques; }

    public String getTermoPesquisa() { return termoPesquisa; }

    public void setTermoPesquisa(String termoPesquisa) { this.termoPesquisa = termoPesquisa; }
}
