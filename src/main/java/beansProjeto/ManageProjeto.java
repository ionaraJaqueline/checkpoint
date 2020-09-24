package beansProjeto;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import beans.AbstractBean;
import beans.EnderecoPaginas;

import entities.Projeto;

import filters.ProjetoFilter;
import impl.ProjetoServiceImpl;
import service.ProjetoService;
import service.ServiceDacException;

@Named
@ViewScoped
public class ManageProjeto extends AbstractBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProjetoService projetoService = new ProjetoServiceImpl();
	private List<Projeto> projeto;
	private ProjetoFilter projetoFilter;

	public List<Projeto> getProjeto() {
		return projeto;
	}

	public ProjetoFilter getProjetoFilter() {
		return projetoFilter;
	}

	public void setProjetoFilter(ProjetoFilter projetoFilter) {
		this.projetoFilter = projetoFilter;
	}

	@PostConstruct
	public void init() {
		limpar();
		filtrar();
	}

	public String filtrar() {
		try {
			projeto = projetoService.findBy(getProjetoFilter());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.projetoFilter = new ProjetoFilter();
		return null;
	}

	public String delete(Projeto projeto) {
		try {
			projetoService.delete(projeto);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Projeto '" + projeto.getNome() + "' deletado");

		return EnderecoPaginas.PAGINA_PRINCIPAL_PROJETO;
	}

}
