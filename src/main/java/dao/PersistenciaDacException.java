package dao;

import exception.DacException;

public class PersistenciaDacException extends DacException {

	private static final long serialVersionUID = 1780243892137781599L;

	public PersistenciaDacException(String mensagem) {
		super(mensagem);
	}

	public PersistenciaDacException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}