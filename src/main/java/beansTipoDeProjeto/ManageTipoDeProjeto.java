package beansTipoDeProjeto;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import beans.AbstractBean;
import beans.EnderecoPaginas;

import entities.TipoDeProjeto;

import filters.TipoDeProjetoFilter;

import impl.TipoDeProjetoServiceImpl;
import service.ServiceDacException;
import service.TipoDeProjetoService;

@Named
@ViewScoped
public class ManageTipoDeProjeto extends AbstractBean {

	private static final long serialVersionUID = 1L;
	@Inject
	private TipoDeProjetoService tipoDeProjetoService = new TipoDeProjetoServiceImpl();

	private List<TipoDeProjeto> tiposDeProjetos;

	private TipoDeProjetoFilter tipoDeProjetoFilter;

	public TipoDeProjetoService getTipoDeProjetoService() {
		return tipoDeProjetoService;
	}

	public List<TipoDeProjeto> getTiposDeProjetos() {
		return tiposDeProjetos;
	}

	public void setTiposDeProjetos(List<TipoDeProjeto> tiposDeProjetos) {
		this.tiposDeProjetos = tiposDeProjetos;
	}

	public TipoDeProjetoFilter getTipoDeProjetoFilter() {
		return tipoDeProjetoFilter;
	}

	public void setTipoDeProjetoFilter(TipoDeProjetoFilter tipoDeProjetoFilter) {
		this.tipoDeProjetoFilter = tipoDeProjetoFilter;
	}

	public void setTipoDeProjetoService(TipoDeProjetoService tipoDeProjetoService) {
		this.tipoDeProjetoService = tipoDeProjetoService;
	}

	@PostConstruct
	public void init() {
		limpar();
		filtrar();
	}

	public String filtrar() {
		try {
			tiposDeProjetos = tipoDeProjetoService.findBy(getTipoDeProjetoFilter());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.tipoDeProjetoFilter = new TipoDeProjetoFilter();
		return null;
	}

	public String delete(TipoDeProjeto tipoDeProjeto) {
		try {
			tipoDeProjetoService.delete(tipoDeProjeto);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Tipo de Projeto '" + tipoDeProjeto.getTipo() + "' deleted");

		return EnderecoPaginas.PAGINA_PRINCIPAL_TIPO_DE_PROJETO;
	}

}
