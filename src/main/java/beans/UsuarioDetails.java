package beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import entities.Usuario;



@Named
@RequestScoped
public class UsuarioDetails extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5032113942694888619L;
	
	private Usuario usuarioDetalhes;

	@PostConstruct
	public void init() {
		this.usuarioDetalhes = getUsuarioLogado();
	}

	public Usuario getUsuarioDetalhes() {
		return usuarioDetalhes;
	}

	public void setUsuarioDetalhes(Usuario usuarioDetalhes) {
		this.usuarioDetalhes = usuarioDetalhes;
	}

}
