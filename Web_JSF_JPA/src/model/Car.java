package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * The persistent class for the car database table.
 * 
 */
@Entity
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CAR_ID")
	private int carId;

	private int kilometrage;

	private String model;

	private String name;

	private int price;


	public Car() {
	}

	public int getCarId() {
		return this.carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public int getKilometrage() {
		return this.kilometrage;
	}

	public void setKilometrage(int kilometrage) {
		this.kilometrage = kilometrage;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj.getClass() != getClass()) {
			return false;
		}

		Car car = (Car) obj;
		if (!model.equals(car.model)) {
			return false;
		}

		if (!name.equals(car.name)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return model.hashCode() ^ name.hashCode();
	}
	
}
