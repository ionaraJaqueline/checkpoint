package beans;

public final class EnderecoPaginas {

	public EnderecoPaginas() {
		throw new UnsupportedOperationException("Esta classe não deve ser instanciada!");
	}

	private static final String REDIRECT_TRUE = "?faces-redirect=true";

	public static final String PAGINA_PRINCIPAL = "/paginas/protegido/index.xhtml" + REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_USUARIO = "/paginas/protegido/usuario/index.xhtml" + REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_PROJETO = "/paginas/protegido/projeto/index.xhtml" + REDIRECT_TRUE;

	public static final String PAGINA_PRINCIPAL_TIPO_DE_PROJETO = "/paginas/protegido/tipoDeProjeto/index.xhtml" + REDIRECT_TRUE;
	
}
