package converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


import entities.TipoDeProjeto;

import impl.TipoDeProjetoServiceImpl;
import service.ServiceDacException;
import service.TipoDeProjetoService;

@FacesConverter(forClass = TipoDeProjeto.class)
public class TipoDeProjetoConverter implements Converter<TipoDeProjeto> {

	private TipoDeProjetoService tipoDeProjeto = new TipoDeProjetoServiceImpl();

	@Override
	public TipoDeProjeto getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			int id = Integer.parseInt(value);
			return tipoDeProjeto.getByID(id);
		} catch (ServiceDacException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, TipoDeProjeto value) {
		if (!(value instanceof TipoDeProjeto)) {
			return null;
		}

		Integer id = ((TipoDeProjeto) value).getId();
		return (id != null) ? id.toString() : null;
	}
}
