package filters;

import java.util.Date;

import service.ServiceDacException;

public class ProjetoFilter {
	private static final long serialVersionUID = 1L;
	private String nome;
	private String descricao;
	private Integer cargaHoraria;
	private Date DataInicial;
	private Date DataFinal;

	public ProjetoFilter() {

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
		return DataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		DataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return DataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		DataFinal = dataFinal;
	}

	public boolean isEmpty() {
		if (this.nome != null && !this.nome.trim().isEmpty()) {
			return false;
		}
		if (this.descricao != null && !this.descricao.trim().isEmpty()) {
			return false;
		}
		if (this.cargaHoraria != null) {
			return false;
		}

		if (this.DataInicial != null) {
			return false;
		}
		if (this.DataInicial != null) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "ProjetoFilter [nome=" + nome + ", descricao=" + descricao + ", cargaHoraria=" + cargaHoraria
				+ ", DataInicial=" + DataInicial + ", DataFinal=" + DataFinal + "]";
	}

	public void validate() throws ServiceDacException {
		if (this.DataInicial != null && this.DataFinal != null) {
			if (this.DataInicial.after(this.DataFinal)) {
				throw new ServiceDacException("'\r\n"
						+ "O início do intervalo da  Data do Projeto \"deve ser antes\" da Data final do Projeto'!");
			}
		}
	}

}
