package beansTipoDeProjeto;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import beans.AbstractBean;
import beans.EnderecoPaginas;

import entities.TipoDeProjeto;

import impl.TipoDeProjetoServiceImpl;

import service.ServiceDacException;
import service.TipoDeProjetoService;

@Named
@ViewScoped
public class TipoDeProjetoEdit extends AbstractBean {
	private static final long serialVersionUID = -7779155604704232174L;
	@Inject
	private TipoDeProjetoService tipoDeProjetoService = new TipoDeProjetoServiceImpl();

	private TipoDeProjeto tipoDeProjeto;

	public void init() {
		try {
			if (tipoDeProjeto == null) {
				// Criando nova mesa
				tipoDeProjeto = new TipoDeProjeto();
			} else {
				// Editando um produto já existente
				if (isEdicaoDeTipoDeProjeto()) {
					tipoDeProjetoService.update(tipoDeProjeto);
				} else {
					tipoDeProjetoService.save(tipoDeProjeto);
				}
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public String saveTipoDeProjeto() {
		try {
			if (isEdicaoDeTipoDeProjeto()) {
				tipoDeProjetoService.update(tipoDeProjeto);
			} else {
				tipoDeProjetoService.save(tipoDeProjeto);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Projeto '" + tipoDeProjeto.getTipo() + "' salvo");

		return EnderecoPaginas.PAGINA_PRINCIPAL_TIPO_DE_PROJETO;
	}

	public boolean isEdicaoDeTipoDeProjeto() {
		return tipoDeProjeto != null && tipoDeProjeto.getId() != null;
	}

	public TipoDeProjetoService getTipoDeProjetoService() {
		return tipoDeProjetoService;
	}

	public void setTipoDeProjetoService(TipoDeProjetoService tipoDeProjetoService) {
		this.tipoDeProjetoService = tipoDeProjetoService;
	}

	public TipoDeProjeto getTipoDeProjeto() {
		return tipoDeProjeto;
	}

	public void setTipoDeProjeto(TipoDeProjeto tipoDeProjeto) {
		this.tipoDeProjeto = tipoDeProjeto;
	}

}
