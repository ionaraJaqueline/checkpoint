package beansUsuario;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import entities.Usuario;
@Named
@ViewScoped
public class UsuarioEditOwnPassword extends UsuarioEditPassword {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5860793285634698186L;

	public void init() {
		Usuario usuarioLogado = getUsuarioLogado();
		setUsuario(usuarioLogado);
	}
}