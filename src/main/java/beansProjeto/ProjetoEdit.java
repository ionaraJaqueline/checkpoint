package beansProjeto;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import beans.AbstractBean;
import beans.EnderecoPaginas;

import entities.Projeto;
import entities.TipoDeProjeto;
import entities.Usuario;
import exception.DacRuntimeException;
import impl.ProjetoServiceImpl;
import impl.TipoDeProjetoServiceImpl;
import impl.UsuarioServiceImpl;
import service.ProjetoService;
import service.ServiceDacException;
import service.TipoDeProjetoService;
import service.UsuarioService;

@Named
@ViewScoped
public class ProjetoEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7779155604704232174L;
	@Inject
	private ProjetoService projetoService = new ProjetoServiceImpl();
	@Inject
	private UsuarioService usuarioService = new UsuarioServiceImpl();
	@Inject
	private TipoDeProjetoService tipoDeProjetoService = new TipoDeProjetoServiceImpl();
	
	private List<TipoDeProjeto> tiposDeProjetos;
	
	private List<Usuario> usuarios;

	private Projeto projeto;

	@PostConstruct
	public void postConstruct() {
		inicializarUsuarios();
		inicializarTiposDeProjetos();

	}

	private void inicializarUsuarios() {
		try {
			setUsuarios(usuarioService.getAll());
		} catch (ServiceDacException e) {
			throw new DacRuntimeException("Ocorreu algum problema ao tentar recuperar os usuarios.", e);
		}
	}
	
	private void inicializarTiposDeProjetos() {
		try {
			setTiposDeProjetos(tipoDeProjetoService.getAll());
		} catch (ServiceDacException e) {
			throw new DacRuntimeException("Ocorreu algum problema ao tentar recuperar os tipos de projetos.", e);
		}
	}
	

	public void init() {
		try {
			if (projeto == null) {
				// Criando novo projeto
				projeto = new Projeto();
			} else {
				// Editando um projeto já existente
				if (isEdicaoDeProjeto()) {
					projetoService.update(projeto);
				} else {
					projetoService.save(projeto);
				}
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public String saveProjeto() {
		try {
			if (isEdicaoDeProjeto()) {
				projetoService.update(projeto);
			} else {
				projetoService.save(projeto);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Projeto '" + projeto.getNome() + "' salvo");

		return EnderecoPaginas.PAGINA_PRINCIPAL_PROJETO;
	}

	public boolean isEdicaoDeProjeto() {
		return projeto != null && projeto.getId() != null;
	}

	public ProjetoService getProjetoService() {
		return projetoService;
	}

	public void setProjetoService(ProjetoService projetoService) {
		this.projetoService = projetoService;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<TipoDeProjeto> getTiposDeProjetos() {
		return tiposDeProjetos;
	}

	public void setTiposDeProjetos(List<TipoDeProjeto> tiposDeProjetos) {
		this.tiposDeProjetos = tiposDeProjetos;
	}

}
