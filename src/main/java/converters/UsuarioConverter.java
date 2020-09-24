package converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import entities.Usuario;
import impl.UsuarioServiceImpl;
import service.ServiceDacException;
import service.UsuarioService;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter<Usuario> {

	private UsuarioService usuario = new UsuarioServiceImpl();

	@Override
	public Usuario getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			int id = Integer.parseInt(value);
			return usuario.getByID(id);
		} catch (ServiceDacException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Usuario value) {

		if (!(value instanceof Usuario)) {
			return null;
		}

		Integer id = ((Usuario) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
