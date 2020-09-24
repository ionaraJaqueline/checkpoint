package filters;

import java.util.Date;


import entities.Group;
import service.ServiceDacException;

public class UsuarioFilter {
	private static final long serialVersionUID = 1L;

	private String nome;

	private String sobreNome;

	private Date inicioDataDeAniversario;

	private Date fimDataDeAniversario;

	private Group group;

	private String login;

	public UsuarioFilter() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public Date getInicioDataDeAniversario() {
		return inicioDataDeAniversario;
	}

	public void setInicioDataDeAniversario(Date inicioDataDeAniversario) {
		this.inicioDataDeAniversario = inicioDataDeAniversario;
	}

	public Date getFimDataDeAniversario() {
		return fimDataDeAniversario;
	}

	public void setFimDataDeAniversario(Date fimDataDeAniversario) {
		this.fimDataDeAniversario = fimDataDeAniversario;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	

	public boolean isEmpty() {
		if (this.nome != null && !this.nome.trim().isEmpty()) {
			return false;
		}
		if (this.sobreNome != null && !this.sobreNome.trim().isEmpty()) {
			return false;
		}
		if (this.inicioDataDeAniversario != null) {
			return false;
		}
		if (this.fimDataDeAniversario != null) {
			return false;
		}
		if (this.group != null) {
			return false;
		}
		if (this.login != null && !this.login.trim().isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "UsuarioFilter [nome=" + nome + ", sobreNome=" + sobreNome + ", inicioDataDeAniversario="
				+ inicioDataDeAniversario + ", fimDataDeAniversario=" + fimDataDeAniversario + ", group=" + group
				+ ", login=" + login + "]";
	}

	public void validate() throws ServiceDacException {
		if (this.inicioDataDeAniversario != null && this.fimDataDeAniversario != null) {
			if (this.inicioDataDeAniversario.after(this.fimDataDeAniversario)) {
				throw new ServiceDacException("'Birthday range begin' should be before 'birthday range end'!");
			}
		}
	}
}
