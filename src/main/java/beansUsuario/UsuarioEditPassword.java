package beansUsuario;


import javax.faces.view.ViewScoped;
import javax.inject.Named;

import beans.AbstractBean;
import beans.EnderecoPaginas;
import entities.Usuario;
import impl.UsuarioServiceImpl;
import service.ServiceDacException;
import service.UsuarioService;

@Named
@ViewScoped
public class UsuarioEditPassword extends AbstractBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4244039822180927305L;

	private Usuario usuario;

	private String confirmacaoPasswordAtual;

	private String newPassword;

	private UsuarioService usuarioService = new UsuarioServiceImpl();

	public String changePassword() {

		try {
			// Confirmar que senha atual equivale a armazenada
			if (!senhaAtualConfere()) {
				reportarMensagemDeErro("Your current password is missing or incorrect!");
				return null; // Permanecer na mesma página
			}

			usuario.setPassword(getNewPassword());
			usuarioService.update(usuario, true);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Password sucessfully changed for user '" + usuario.getNome() + "'");

		return EnderecoPaginas.PAGINA_PRINCIPAL_USUARIO;
	}

	private boolean senhaAtualConfere() throws ServiceDacException {
		return usuarioService.senhaConfere(this.usuario, getConfirmacaoPasswordAtual());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmacaoPasswordAtual() {
		return confirmacaoPasswordAtual;
	}

	public void setConfirmacaoPasswordAtual(String confirmacaoPasswordAtual) {
		this.confirmacaoPasswordAtual = confirmacaoPasswordAtual;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
