package entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TB_USUARIO", uniqueConstraints = {
		@UniqueConstraint(name = "uk__tb_usuario__login", columnNames = { "login" }) })
public class Usuario implements Identificavel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	private String nome;

	private String sobreNome;

	private String cpf;

//	// atributo apenas do funcionário
//	private Float salario;

	@Temporal(TemporalType.DATE)
	private Date dataDeAniversario;

	// XXX Renomeando coluna, pois "group" é uma palavra reservada do PostgreSQL:
	// https://www.postgresql.org/docs/9.6/static/sql-keywords-appendix.html
	@Column(name = "usuario_group")
	@Enumerated(EnumType.STRING)
	private Group group;

	private String login;

	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Codigo_Endereco")
	private Endereco endereco;

	public static String usuarioTeste;

	public Usuario() {

		endereco = new Endereco();

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setSobreNome(String sobrenome) {
		this.sobreNome = sobrenome;
	}

	public Date getDataDeAniversario() {
		return dataDeAniversario;
	}

	public void setDataDeAniversario(Date dataDeAniversario) {
		this.dataDeAniversario = dataDeAniversario;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

//	public Float getSalario() {
//		return salario;
//	}
//
//	public void setSalario(Float salario) {
//		this.salario = salario;
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataDeAniversario == null) ? 0 : dataDeAniversario.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
//		result = prime * result + Float.floatToIntBits(salario);
		result = prime * result + ((sobreNome == null) ? 0 : sobreNome.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;

		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataDeAniversario == null) {
			if (other.dataDeAniversario != null)
				return false;
		} else if (!dataDeAniversario.equals(other.dataDeAniversario))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (group != other.group)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
//		if (Float.floatToIntBits(salario) != Float.floatToIntBits(other.salario))
//			return false;
		if (sobreNome == null) {
			if (other.sobreNome != null)
				return false;
		} else if (!sobreNome.equals(other.sobreNome))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	private Date removeTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", sobreNome=" + sobreNome + ", cpf=" + cpf
				+ ", dataDeAniversario=" + dataDeAniversario + ", group=" + group + ", login=" + login + ", password="
				+ password + ", endereco=" + endereco + "]";
	}

	@Override
	public Usuario clone() {
		Usuario clone = new Usuario();
		clone.setId(id);
		clone.setNome(nome);
		clone.setSobreNome(sobreNome);
		clone.setDataDeAniversario(dataDeAniversario);
		clone.setGroup(group);
		clone.setLogin(login);
		clone.setPassword(password);

		clone.setEndereco(endereco);
		clone.setCpf(cpf);
//		clone.setSalario(salario);
		return clone;
	}

	/**
	 * Anula os campos específicos não necessários de acordo com a escolha do grupo.
	 */
//	public void limparCamposEspecificos() {
//		if (group == null) {
//			return;
//		}
//		if (Group.COLABORADOR == group) {
//			salario = null;
//		} else if (Group.COORDENADOR == group) {
//			dataDeAniversario = null;
//			
//		} else if (Group.ADMIN == group) {
//			dataDeAniversario = null;
//			salario = null;
//		}
//	}

}