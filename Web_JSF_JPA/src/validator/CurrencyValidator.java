package validator;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("CurrencyValidator")
public class CurrencyValidator  implements Validator{
	private static final String MESSAGE = "illegal currency format"; 
		 
		public void validate(FacesContext context, UIComponent component,
				Object value) throws ValidatorException {
			
			Locale locale = Locale.getDefault();
			NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
			
			try {
				formatter.parse(value.toString()).doubleValue();
			} catch (ParseException e) {
				try{
					Double.valueOf(value.toString());
				}
				catch(NumberFormatException nfe){
					FacesMessage msg = 
							new FacesMessage(MESSAGE);
					
					throw new ValidatorException(msg);
				}
			}
		}
		
}
