package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entities.Group;
import entities.Usuario;
import service.ServiceDacException;
import service.UsuarioService;

@Named
@ViewScoped
public class CriarUsuarioColaborador extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8084039557753245768L;

	private Usuario usuario;

	private List<Usuario> usuarios;

	@Inject
	private UsuarioService usuarioService;

	public void init() {
		// Inicializar dados do usuário
		usuario = new Usuario();
		usuario.setGroup(Group.COLABORADOR);

		try {
			usuarios = usuarioService.getAll();
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public String saveUsuario() {
		try {
			// Criação de novo usuário
			usuarioService.save(usuario);
			reportarMensagemDeSucesso("Usuário '" + usuario.getNome() + "' criado com sucesso!");
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		return EnderecoPaginas.PAGINA_PRINCIPAL;
	}

	public void checarDisponibilidadeLogin() {
		try {
			usuarioService.validarLogin(usuario);
			reportarMensagemDeSucesso(String.format("Login '%s' is available.", usuario.getLogin()));
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
