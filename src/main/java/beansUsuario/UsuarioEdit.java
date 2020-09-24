package beansUsuario;


import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import beans.AbstractBean;
import beans.EnderecoPaginas;
import entities.Group;
import entities.Usuario;
import impl.UsuarioServiceImpl;
import service.ServiceDacException;
import service.UsuarioService;

@Named
@ViewScoped
public class UsuarioEdit extends AbstractBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7779155604704232174L;
	@Inject
	private UsuarioService usuarioService = new UsuarioServiceImpl();

	private Usuario usuario;

	public void init() {
		try {
			if (usuario == null) {
				// Criando novo usuário
				usuario = new Usuario();
			} else {
				// Editando usuário já existente
				if (isEdicaoDeUsuario()) {
					usuarioService.update(usuario, false);
				} else {
					usuarioService.save(usuario);
				}
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public String saveUsuario() {
		try {
			if (isEdicaoDeUsuario()) {
				usuarioService.update(usuario, false);
			} else {
				usuarioService.save(usuario);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("User '" + usuario.getNome() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL_USUARIO;
	}

	public boolean isEdicaoDeUsuario() {
		return usuario != null && usuario.getId() != null;
	}

	public boolean isAdmin() {
		return usuario != null && usuario.getGroup() == Group.ADMIN;
	}

	public boolean isCoordenador() {
		return usuario != null && usuario.getGroup() == Group.COORDENADOR;
	}

	public boolean isColaborador() {
		return usuario != null && usuario.getGroup() == Group.COLABORADOR;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public void checarDisponibilidadeLogin() {
		try {
			usuarioService.validarLogin(usuario);
			reportarMensagemDeSucesso(String.format("Login '%s' is available.", usuario.getLogin()));
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

}
