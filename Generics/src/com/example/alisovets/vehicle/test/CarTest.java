package com.example.alisovets.vehicle.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.example.alisovets.human.Firefighter;
import com.example.alisovets.human.Human;
import com.example.alisovets.human.Policeman;
import com.example.alisovets.vehicle.Car;
import com.example.alisovets.vehicle.NotEnoughSeatsException;
import com.example.alisovets.vehicle.PassengerNotFoundException;

public class CarTest {
	private static int DEFAULT_MAX_SIZE = 5;
	Car<Human> car;

	@Before
	public void initList() {
		car = new Car<Human>(DEFAULT_MAX_SIZE);
	}

	@Test
	public void testCreate() {
		assertNotNull(car);
	}

	@Test
	public void testCreatePolice() {
		Car<Policeman> policeCar = new Car<Policeman>(DEFAULT_MAX_SIZE);
		assertNotNull(policeCar);
	}

	@Test
	public void testCreateFirefighter() {
		Car<Firefighter> firefighterCar = new Car<Firefighter>(DEFAULT_MAX_SIZE);
		assertNotNull(firefighterCar);
	}

	@Test
	public void testGetMaxSize() {
		assertEquals(DEFAULT_MAX_SIZE, car.getMaxSize());
	}

	@Test
	public void testMaxSize0() {
		assertEquals(0, car.getSize());
	}

	@Test
	public void testLoadHuman1() {
		Human human = new Human();
		assertEquals(0, car.getSize());
		car.load(human);
		assertEquals(1, car.getSize());
	}

	@Test
	public void testLoadHuman2() {
		Human human = new Human();
		assertEquals(0, car.getSize());
		car.load(human);
		car.load(human);
		assertEquals(2, car.getSize());
	}

	@Test
	public void testLoadHumanMax() {
		Human human = new Human();
		assertEquals(0, car.getSize());

		for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
			car.load(human);
			assertEquals(i + 1, car.getSize());
		}
		assertEquals(DEFAULT_MAX_SIZE, car.getMaxSize());
	}

	@Test(expected = NotEnoughSeatsException.class)
	public void testLoadHumanTooMany() {
		Human human = new Human();
		assertEquals(0, car.getSize());
		try {
			for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
				car.load(human);
				assertEquals(i + 1, car.getSize());
			}
		} catch (NotEnoughSeatsException e) {
			throw new RuntimeException(e);
		}
		assertEquals(DEFAULT_MAX_SIZE, car.getMaxSize());
		car.load(human);
	}

	@Test
	public void testLoadHumanNull() {
		assertEquals(0, car.getSize());
		car.load(null);
		assertEquals(1, car.getSize());
	}

	@Test
	public void testLoadPolicemanMax() {
		Policeman policeman = new Policeman();
		assertEquals(0, car.getSize());

		for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
			car.load(policeman);
			assertEquals(i + 1, car.getSize());
		}
		assertEquals(DEFAULT_MAX_SIZE, car.getMaxSize());
	}

	@Test
	public void testLoadFirefighterMax() {
		Firefighter firefighter = new Firefighter();
		assertEquals(0, car.getSize());

		for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
			car.load(firefighter);
			assertEquals(i + 1, car.getSize());
		}
		assertEquals(DEFAULT_MAX_SIZE, car.getMaxSize());
	}

	@Test(expected = PassengerNotFoundException.class)
	public void testUnloadHumanWhenNotExist() {
		Human human = new Human();
		assertEquals(0, car.getSize());
		car.unload(human);
	}

	@Test
	public void testUnloadHuman() {
		Human human = new Human();
		car.load(human);
		assertEquals(1, car.getSize());
		car.unload(human);
		assertEquals(0, car.getSize());
	}
	
	
	@Test
	public void testUnload() {
		Human human1 = new Human();
		Human human2 = new Human();
		Firefighter firefighter = new Firefighter();
		Policeman policeman = new Policeman();
		
		car.load(human1);
		car.load(firefighter);
		car.load(policeman);
		car.load(human2);
		assertEquals(4, car.getSize());
		
		car.unload(human1);
		assertEquals(3, car.getSize());
		car.unload(human2);
		assertEquals(2, car.getSize());
		car.unload(firefighter);
		assertEquals(1, car.getSize());
		car.unload(policeman);
		assertEquals(0, car.getSize());
		
	}

}
