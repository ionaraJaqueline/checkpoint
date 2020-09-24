package converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import entities.Projeto;

import impl.ProjetoServiceImpl;

import service.ProjetoService;
import service.ServiceDacException;

@FacesConverter(forClass = Projeto.class)
public class ProjetoConverter implements Converter<Projeto> {

	private ProjetoService projeto = new ProjetoServiceImpl();

	@Override
	public Projeto getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			int id = Integer.parseInt(value);
			return projeto.getByID(id);
		} catch (ServiceDacException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Projeto value) {
		if (!(value instanceof Projeto)) {
			return null;
		}

		Integer id = ((Projeto) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
