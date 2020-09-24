/*package dataGenerator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Eager;

import exception.DacRuntimeException;
import service.ServiceDacException;
import serviceDataGenerator.UsuarioDataGeneratorService;



@Named
@Eager // Thanks, Omnifaces!!!
@ApplicationScoped
public class InitDatabaseIfNeeded {

	@Inject
	private UsuarioDataGeneratorService usuarioDataGeneratorService;
	
	
	
	@PostConstruct
	public void postConstruct() {
		
		try {
			
			usuarioDataGeneratorService.generateData();
		} catch (ServiceDacException e) {
			throw new DacRuntimeException("Ocorreu algum erro ao tentar inicializar a base de dados.", e);
		}
	}
	
}

*/