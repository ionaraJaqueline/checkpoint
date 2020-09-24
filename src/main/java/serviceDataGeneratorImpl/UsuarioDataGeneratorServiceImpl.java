/*package serviceDataGeneratorImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import entities.Group;
import entities.Usuario;
import filters.UsuarioFilter;
import service.ServiceDacException;
import service.UsuarioService;
import serviceDataGenerator.UsuarioDataGeneratorService;
import util.DateUtil;

public class UsuarioDataGeneratorServiceImpl implements Serializable, UsuarioDataGeneratorService {

	/**
	 * 
	 
	private static final long serialVersionUID = 3371124651147613246L;
	
	private static final String SENHA_FUNCIONARIO = "123456";
	
	private static final String SENHA_ADMIN = "1234567";
	
	private static final String HASH_SENHA_FUNCIONARIO = "jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=";
	
	private static final String HASH_SENHA_ADMIN = "i7DPbrmxfQ99IrRW8SElfcElTh8BZlNwR2OD6ndt9BQ=";
	
	@Inject
	private UsuarioService usuarioService;
	
	
	@Override
	public void generateData() throws ServiceDacException {
		List<Usuario> usuariosAdicionar = new ArrayList<Usuario>();
		List<Usuario> usuariosAtualizarSenha = new ArrayList<Usuario>();
		Usuario admin = getUsuarioAdmin();
		Usuario funcionario = getUsuarioFuncionario();
		UsuarioFilter usuarioFilter = new UsuarioFilter();

		// Funcionario
		usuarioFilter.setLogin(funcionario.getLogin());
		List<Usuario> usuarios = usuarioService.findBy(usuarioFilter);
		if (usuarios.isEmpty()) {
			usuariosAdicionar.add(funcionario);
		} else {
			funcionario = usuarios.get(0);
			if (!usuarioTemSenha(funcionario, HASH_SENHA_FUNCIONARIO)) {
				funcionario.setPassword(SENHA_FUNCIONARIO);
				usuariosAtualizarSenha.add(funcionario);
			}
		}

		// Admin
		usuarioFilter.setLogin(admin.getLogin());
		usuarios = usuarioService.findBy(usuarioFilter);
		if (usuarios.isEmpty()) {
			usuariosAdicionar.add(admin);
		} else {
			admin = usuarios.get(0);
			if (!usuarioTemSenha(admin, HASH_SENHA_ADMIN)) {
				admin.setPassword(SENHA_ADMIN);
				usuariosAtualizarSenha.add(admin);
			}
		}
		
		// Add
		for (Usuario usuario : usuariosAdicionar) {
			usuarioService.save(usuario);
		}
		
		// Update
		for (Usuario usuario : usuariosAtualizarSenha) {
			usuarioService.update(usuario, true);
		}
		
	}

	private boolean usuarioTemSenha(Usuario usuario, String hashEsperadoSenha) {
		return hashEsperadoSenha.equals(usuario.getPassword());
	}

	private Usuario getUsuarioAdmin() {
		Usuario usuario = new Usuario();

		usuario.setLogin("admin");
		usuario.setPassword(SENHA_ADMIN);
		usuario.setGroup(Group.ADMIN);

		usuario.setNome("Ionara");
		usuario.setSobreNome("Farias");
		usuario.setDataDeAniversario(DateUtil.getStartOfDay(new Date()));
		
		
		usuario.getSalario();

		return usuario;
	}

	private Usuario getUsuarioFuncionario() {
		Usuario usuario = new Usuario();

		usuario.setLogin("pedro");
		usuario.setPassword(SENHA_FUNCIONARIO);
		usuario.setGroup(Group.FUNCIONARIO);

		usuario.setNome("Pedro");
		usuario.setSobreNome("Tal");
		usuario.setDataDeAniversario(DateUtil.getStartOfDay(new Date()));
		
		return usuario;
	}
	
}*/
