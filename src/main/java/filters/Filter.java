package filters;

import java.io.Serializable;

import service.ServiceDacException;

public interface Filter extends Serializable {

	default public void validate() throws ServiceDacException {
		// Não fazer validação alguma por padrão.
	}

}
