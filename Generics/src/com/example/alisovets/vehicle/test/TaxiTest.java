package com.example.alisovets.vehicle.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.example.alisovets.human.Firefighter;
import com.example.alisovets.human.Human;
import com.example.alisovets.human.Policeman;
import com.example.alisovets.vehicle.NotEnoughSeatsException;
import com.example.alisovets.vehicle.PassengerNotFoundException;
import com.example.alisovets.vehicle.Taxi;

public class TaxiTest {
	private static int DEFAULT_MAX_SIZE = 4;
	Taxi<Human> taxi;

	@Before
	public void initList() {
		taxi = new Taxi<Human>(DEFAULT_MAX_SIZE);
	}

	@Test
	public void testCreate() {
		assertNotNull(taxi);
	}

	@Test
	public void testCreatePolice() {
		Taxi<Policeman> taxi = new Taxi<Policeman>(DEFAULT_MAX_SIZE);
		assertNotNull(taxi);
	}

	@Test
	public void testCreateFirefighter() {
		Taxi<Firefighter> taxi = new Taxi<Firefighter>(DEFAULT_MAX_SIZE);
		assertNotNull(taxi);
	}

	@Test
	public void testGetMaxSize() {
		assertEquals(DEFAULT_MAX_SIZE, taxi.getMaxSize());
	}

	@Test
	public void testMaxSize0() {
		assertEquals(0, taxi.getSize());
	}

	@Test
	public void testLoadHuman1() {
		Human human = new Human();
		assertEquals(0, taxi.getSize());
		taxi.load(human);
		assertEquals(1, taxi.getSize());
	}

	@Test
	public void testLoadHuman2() {
		Human human = new Human();
		assertEquals(0, taxi.getSize());
		taxi.load(human);
		taxi.load(human);
		assertEquals(2, taxi.getSize());
	}

	@Test
	public void testLoadHumanMax() {
		Human human = new Human();
		assertEquals(0, taxi.getSize());

		for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
			taxi.load(human);
			assertEquals(i + 1, taxi.getSize());
		}
		assertEquals(DEFAULT_MAX_SIZE, taxi.getMaxSize());
	}

	@Test(expected = NotEnoughSeatsException.class)
	public void testLoadHumanTooMany() {
		Human human = new Human();
		assertEquals(0, taxi.getSize());
		try {
			for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
				taxi.load(human);
				assertEquals(i + 1, taxi.getSize());
			}
		} catch (NotEnoughSeatsException e) {
			throw new RuntimeException(e);
		}
		assertEquals(DEFAULT_MAX_SIZE, taxi.getMaxSize());
		taxi.load(human);
	}

	@Test
	public void testLoadHumanNull() {
		assertEquals(0, taxi.getSize());
		taxi.load(null);
		assertEquals(1, taxi.getSize());
	}

	@Test
	public void testLoadPolicemanMax() {
		Policeman policeman = new Policeman();
		assertEquals(0, taxi.getSize());

		for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
			taxi.load(policeman);
			assertEquals(i + 1, taxi.getSize());
		}
		assertEquals(DEFAULT_MAX_SIZE, taxi.getMaxSize());
	}

	@Test
	public void testLoadFirefighterMax() {
		Firefighter firefighter = new Firefighter();
		assertEquals(0, taxi.getSize());

		for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
			taxi.load(firefighter);
			assertEquals(i + 1, taxi.getSize());
		}
		assertEquals(DEFAULT_MAX_SIZE, taxi.getMaxSize());
	}

	@Test(expected = PassengerNotFoundException.class)
	public void testUnloadHumanWhenNotExist() {
		Human human = new Human();
		assertEquals(0, taxi.getSize());
		taxi.unload(human);
	}

	@Test
	public void testUnloadHuman() {
		Human human = new Human();
		taxi.load(human);
		assertEquals(1, taxi.getSize());
		taxi.unload(human);
		assertEquals(0, taxi.getSize());
	}

	@Test
	public void testUnload() {
		Human human1 = new Human();
		Human human2 = new Human();
		Firefighter firefighter = new Firefighter();
		Policeman policeman = new Policeman();

		taxi.load(human1);
		taxi.load(firefighter);
		taxi.load(policeman);
		taxi.load(human2);
		assertEquals(4, taxi.getSize());

		taxi.unload(human1);
		assertEquals(3, taxi.getSize());
		taxi.unload(human2);
		assertEquals(2, taxi.getSize());
		taxi.unload(firefighter);
		assertEquals(1, taxi.getSize());
		taxi.unload(policeman);
		assertEquals(0, taxi.getSize());

	}

}
