package com.example.alisovets.vehicle.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.example.alisovets.human.Firefighter;
import com.example.alisovets.vehicle.FirefighterCar;
import com.example.alisovets.vehicle.NotEnoughSeatsException;
import com.example.alisovets.vehicle.PassengerNotFoundException;

public class FirefighterCarTest {
	private static int DEFAULT_MAX_SIZE = 5;
	FirefighterCar<Firefighter> firefighterCar;

	@Before
	public void initList() {
		firefighterCar = new FirefighterCar<Firefighter>(DEFAULT_MAX_SIZE);
	}

	@Test
	public void testCreate() {
		assertNotNull(firefighterCar);
	}


	@Test
	public void testGetMaxSize() {
		assertEquals(DEFAULT_MAX_SIZE, firefighterCar.getMaxSize());
	}

	@Test
	public void testMaxSize0() {
		assertEquals(0, firefighterCar.getSize());
	}

	@Test
	public void testLoadFirefighter1() {
		Firefighter firefighter = new Firefighter();
		assertEquals(0, firefighterCar.getSize());
		firefighterCar.load(firefighter);
		assertEquals(1, firefighterCar.getSize());
	}

	@Test
	public void testLoadFirefighter2() {
		Firefighter firefighter = new Firefighter();
		assertEquals(0, firefighterCar.getSize());
		firefighterCar.load(firefighter);
		firefighterCar.load(firefighter);
		assertEquals(2, firefighterCar.getSize());
	}

	@Test
	public void testLoadFirefighterMax() {
		Firefighter firefighter = new Firefighter();
		assertEquals(0, firefighterCar.getSize());

		for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
			firefighterCar.load(firefighter);
			assertEquals(i + 1, firefighterCar.getSize());
		}
		assertEquals(DEFAULT_MAX_SIZE, firefighterCar.getMaxSize());
	}

	@Test(expected = NotEnoughSeatsException.class)
	public void testLoadFirefighterTooMany() {
		Firefighter firefighter = new Firefighter();
		assertEquals(0, firefighterCar.getSize());
		try {
			for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
				firefighterCar.load(firefighter);
				assertEquals(i + 1, firefighterCar.getSize());
			}
		} catch (NotEnoughSeatsException e) {
			throw new RuntimeException(e);
		}
		assertEquals(DEFAULT_MAX_SIZE, firefighterCar.getMaxSize());
		firefighterCar.load(firefighter);
	}

	@Test
	public void testLoadFirefighterNull() {
		assertEquals(0, firefighterCar.getSize());
		firefighterCar.load(null);
		assertEquals(1, firefighterCar.getSize());
	}

	@Test(expected = PassengerNotFoundException.class)
	public void testUnloadFirefighterWhenNotExist() {
		Firefighter firefighter = new Firefighter();
		assertEquals(0, firefighterCar.getSize());
		firefighterCar.unload(firefighter);
	}

	@Test
	public void testUnloadFirefighter() {
		Firefighter firefighter = new Firefighter();
		firefighterCar.load(firefighter);
		assertEquals(1, firefighterCar.getSize());
		firefighterCar.unload(firefighter);
		assertEquals(0, firefighterCar.getSize());
	}

	@Test
	public void testUnload() {
		Firefighter firefighter1 = new Firefighter();
		Firefighter firefighter2 = new Firefighter();
		Firefighter firefighter3 = new Firefighter();

		firefighterCar.load(firefighter1);
		firefighterCar.load(firefighter2);
		firefighterCar.load(firefighter3);
		assertEquals(3, firefighterCar.getSize());

		firefighterCar.unload(firefighter1);
		assertEquals(2, firefighterCar.getSize());
		firefighterCar.unload(firefighter2);
		assertEquals(1, firefighterCar.getSize());
		firefighterCar.unload(firefighter3);
		assertEquals(0, firefighterCar.getSize());

	}
}
