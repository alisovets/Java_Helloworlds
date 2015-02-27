package model;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * 
 * class specifying transfer object representing the issue entity
 *
 */
public class Issue {
	public enum Periodicity {
		DAILY, WEEKLY, MONTHLY;

		public String toLocaleString(Locale locale) {

			if ("Ukrainian".equals(locale.getDisplayLanguage())) {
				switch (this) {
				case DAILY:
					return "щоденно";
				case WEEKLY:
					return "щотижня";
				case MONTHLY:
					return "щомісяця";
				}
			} else if ("Russian".equals(locale.getDisplayLanguage())) {
				switch (this) {
				case DAILY:
					return "ежедневно";
				case WEEKLY:
					return "еженедельно";
				case MONTHLY:
					return "ежемесячно";
				}
			}
			return toString().toLowerCase();
		}
	};

	private int id;
	private String name;
	private Double price;
	private Periodicity periodicity;
	private String publisher;
	private boolean actual;
	private int adminId;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public boolean isActual() {
		return actual;
	}

	public void setActual(boolean actual) {
		this.actual = actual;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Periodicity getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(Periodicity periodicity) {
		this.periodicity = periodicity;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLocalePrice(Locale locale) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		return formatter.format(price);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Issue)) {
			return false;
		}

		Issue issue = (Issue) obj;
		if (id != issue.id) {
			return false;
		}

		if (adminId != issue.adminId) {
			return false;
		}

		if (price != issue.price) {
			return false;
		}

		if (actual != issue.actual) {
			return false;
		}

		if (name != null) {
			if (!name.equals(issue.name)) {
				return false;
			}
		} else {
			if (issue.name != null) {
				return false;
			}
		}

		if (publisher != null) {
			if (!publisher.equals(issue.publisher)) {
				return false;
			}
		} else {
			if (issue.publisher != null) {
				return false;
			}
		}

		if (periodicity != issue.periodicity) {
			return false;
		}

		return true;
	}
	
	@Override
	public int hashCode() {
		int hash = id;
		hash = hash * 17 + adminId;
		hash = hash * 17 +  price.intValue(); 
		hash = hash * 17 + (actual ? 1 : 0);
		hash = hash * 17 + (name == null ? 0 : name.hashCode());
		hash = hash * 17 + (publisher == null ? 0 : publisher.hashCode());
		hash = hash * 17 + periodicity.hashCode();
		return hash;
	}

}
