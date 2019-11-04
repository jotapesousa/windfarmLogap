package br.com.logap.windfarm.bean;

import br.com.logap.windfarm.model.Aerogerador;
import br.com.logap.windfarm.model.ParqueEolico;
import br.com.logap.windfarm.service.AerogeradorService;
import br.com.logap.windfarm.service.Exceptions.NomeExistenteException;
import br.com.logap.windfarm.service.ParqueEolicoService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class AerogeradorBean implements Serializable {

    private AerogeradorService aerogeradorService;

    private ParqueEolicoService parqueEolicoService;

    private Aerogerador aerogerador;
    private List<Aerogerador> aerogeradores;
    private String termoPesquisa;

    @Inject
    public AerogeradorBean(AerogeradorService aerogeradorService, ParqueEolicoService parqueEolicoService) {
        this.aerogeradorService = aerogeradorService;
        this.parqueEolicoService = parqueEolicoService;
        aerogerador = new Aerogerador();
    }


    public void iniciar() {
        aerogeradores = aerogeradorService.iniciar();
    }

    public void buscar() {
        aerogerador = aerogeradorService.buscarPorId(aerogerador.getId());
    }

    public List<Aerogerador> pesquisar() {
        return aerogeradorService.pesquisar(termoPesquisa);
    }

    public String salvar(){
        try {
            aerogerador = aerogeradorService.salvar(aerogerador);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aerogerador salvo com sucesso!"));

            return "/aerogerador/pesquisa?faces-redirect=true";
        } catch (NomeExistenteException e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
            return "";
        }
    }

    public String remover(Integer id) {
        aerogeradorService.remover(id);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aerogerador removido com sucesso!"));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        return "/aerogerador/pesquisa?faces-redirect=true";
    }

    public List<ParqueEolico> listarParques() {
        return parqueEolicoService.listar();
    }


    public Aerogerador getAerogerador() { return aerogerador; }

    public List<Aerogerador> getAerogeradores() {
        return aerogeradores;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }
}
