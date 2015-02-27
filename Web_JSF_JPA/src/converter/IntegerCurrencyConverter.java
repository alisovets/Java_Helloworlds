package converter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.ValidatorException;

@FacesConverter(value="IntegerCurrencyConverter", forClass=Integer.class)
public class IntegerCurrencyConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		final String MESSAGE = "illegal currency format";
		Locale locale = Locale.getDefault();
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		double result;
		
		try {
			result = formatter.parse(value).doubleValue();
		} catch (ParseException e) {
			try{
			result = Double.valueOf(value);
			}
			catch(NumberFormatException nfe){
				FacesMessage msg = 
						new FacesMessage(MESSAGE);
				
				throw new ConverterException(msg);
			}
		}
		
		return (int) (result * 100);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		int summ = (Integer)value;
		
		Locale locale = Locale.getDefault();
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		return formatter.format(summ / 100.0);
	}

}
