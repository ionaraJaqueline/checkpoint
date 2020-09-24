package beans;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

import entities.Group;


import entities.Usuario;
import filters.UsuarioFilter;
import service.ServiceDacException;
import service.UsuarioService;

public abstract class AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7887186144461468149L;

	@Inject
	private UsuarioService usuarioService;
	public Group[] getGroups() {
		return Group.values();
	}

	
	
	

	protected void reportarMensagemDeErro(String detalhe) {
		reportarMensagem(true, detalhe, false);
	}

	protected void reportarMensagemDeSucesso(String detalhe) {
		reportarMensagem(false, detalhe, true);
	}

	private void reportarMensagem(boolean isErro, String detalhe, boolean keepMessages) {
		String sumario = "Sucesso!";
		Severity severity = FacesMessage.SEVERITY_INFO;
		if (isErro) {
			sumario = "Error!";
			severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();
		}

		FacesMessage msg = new FacesMessage(severity, sumario + " " + detalhe, null);

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(keepMessages);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public boolean isUsuarioInRole(String role) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		return externalContext.isUserInRole(role);
	}

	public String getUsuarioLogin() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal usuarioPrincipal = externalContext.getUserPrincipal();
		if (usuarioPrincipal == null) {
			return "";
		}

		return usuarioPrincipal.getName();
	}

	public Usuario getUsuarioLogado() {
		UsuarioFilter filter = new UsuarioFilter();
		filter.setLogin(getUsuarioLogin());
		List<Usuario> usuarios = null;
		try {
			usuarios = usuarioService.findBy(filter);
		} catch (ServiceDacException e) {
			e.printStackTrace();
			reportarMensagemDeErro("Erro ao recuperar o usuário logado!");
		}

		if (!usuarios.isEmpty()) {
			return usuarios.get(0);
		}

		return null;
	}
}