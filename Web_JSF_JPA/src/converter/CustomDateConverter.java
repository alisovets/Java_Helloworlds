package converter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.ValidatorException;

@FacesConverter("CustomDateConverter")
public class CustomDateConverter extends DateTimeConverter {

	public CustomDateConverter() {
		setPattern("dd.MM.yyyy");
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		Date date = (Date)super.getAsObject(context, component, value);
		System.out.println("!!!!!!!  date = " + date);
		if(date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		if (year < 100){
			calendar.set(Calendar.YEAR, year + 2000);
		}
		return calendar.getTime();
	}

}
