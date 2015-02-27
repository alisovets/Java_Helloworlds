package backingbean;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "localeChanger")
@SessionScoped
public class LocaleChanger {

	private Locale locale = FacesContext.getCurrentInstance().getViewRoot()
			.getLocale();

	public Locale getLocale() {
		return locale;
	}

	public String enAction() {
		locale = Locale.ENGLISH;
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(locale);
		return null;
	}

	public String ruAction() {
		locale = new Locale("ru");
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(locale);
		return null;
	}

	public String ukAction() {
		locale = new Locale("uk");
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(locale);
		return null;
	}

}
