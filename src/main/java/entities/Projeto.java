package entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Projeto {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String nome;
	private String descricao;

	private Integer cargaHoraria;

	@Temporal(TemporalType.DATE)
	private Date dataInicial;
	@Temporal(TemporalType.DATE)
	private Date dataFinal;

	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	private TipoDeProjeto tipoDeProjeto;

	public Projeto() {

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoDeProjeto getTipoDeProjeto() {
		return tipoDeProjeto;
	}

	public void setTipoDeProjeto(TipoDeProjeto tipoDeProjeto) {
		this.tipoDeProjeto = tipoDeProjeto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((tipoDeProjeto == null) ? 0 : tipoDeProjeto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (tipoDeProjeto == null) {
			if (other.tipoDeProjeto != null)
				return false;
		} else if (!tipoDeProjeto.equals(other.tipoDeProjeto))
			return false;

		return true;
	}

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
		return "Projeto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", cargaHoraria=" + cargaHoraria
				+ ", dataInicial=" + dataInicial + ", dataFinal=" + dataFinal + ", usuario=" + usuario
				+ ", tipoDeProjeto=" + tipoDeProjeto + "]";
	}

	@Override
	public Projeto clone() {
		Projeto clone = new Projeto();
		clone.setId(id);
		clone.setNome(nome);
		clone.setDescricao(descricao);
		clone.setCargaHoraria(cargaHoraria);
		clone.setDataInicial(dataInicial);
		clone.setDataFinal(dataFinal);

		return clone;
	}

}
