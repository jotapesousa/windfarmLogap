package br.com.logap.windfarm.bean;

import br.com.logap.windfarm.dao.UsuarioDAO;
import br.com.logap.windfarm.model.Usuario;
import br.com.logap.windfarm.util.SessionUtils;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {


    private UsuarioDAO usuarioDAO;

    private Usuario usuario;
    private String login;
    private String senha;

    @Inject
    public LoginBean (UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }


    public String logar() {
        usuario = usuarioDAO.buscarPorLogin(login);

        if (usuario != null && usuario.getSenha().equals(senha)) {

            SessionUtils.setParam("usuarioLogado", usuario);

            return "/index?faces-redirect=true";
        } else {
            usuario = null;

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Usuário ou senha inválidos", ""));
            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        usuario = null;
        return "/login?faces-redirect=true";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
