package exception;

public class DacRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DacRuntimeException(String mensagem) {
		super(mensagem);
	}

	public DacRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
