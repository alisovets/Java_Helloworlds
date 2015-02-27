package com.example.alisovets.vehicle.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.example.alisovets.human.Firefighter;
import com.example.alisovets.human.Human;
import com.example.alisovets.human.Policeman;
import com.example.alisovets.vehicle.Bus;
import com.example.alisovets.vehicle.NotEnoughSeatsException;
import com.example.alisovets.vehicle.PassengerNotFoundException;

public class BusTest {
	private static int DEFAULT_MAX_SIZE = 40;
	Bus<Human> bus;

	@Before
	public void initList() {
		bus = new Bus<Human>(DEFAULT_MAX_SIZE);
	}

	@Test
	public void testCreate() {
		assertNotNull(bus);
	}

	@Test
	public void testCreatePolice() {
		Bus<Policeman> bus = new Bus<Policeman>(DEFAULT_MAX_SIZE);
		assertNotNull(bus);
	}

	@Test
	public void testCreateFirefighter() {
		Bus<Firefighter> bus = new Bus<Firefighter>(DEFAULT_MAX_SIZE);
		assertNotNull(bus);
	}

	@Test
	public void testGetMaxSize() {
		assertEquals(DEFAULT_MAX_SIZE, bus.getMaxSize());
	}

	@Test
	public void testMaxSize0() {
		assertEquals(0, bus.getSize());
	}

	@Test
	public void testLoadHuman1() {
		Human human = new Human();
		assertEquals(0, bus.getSize());
		bus.load(human);
		assertEquals(1, bus.getSize());
	}

	@Test
	public void testLoadHuman2() {
		Human human = new Human();
		assertEquals(0, bus.getSize());
		bus.load(human);
		bus.load(human);
		assertEquals(2, bus.getSize());
	}

	@Test
	public void testLoadHumanMax() {
		Human human = new Human();
		assertEquals(0, bus.getSize());

		for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
			bus.load(human);
			assertEquals(i + 1, bus.getSize());
		}
		assertEquals(DEFAULT_MAX_SIZE, bus.getMaxSize());
	}

	@Test(expected = NotEnoughSeatsException.class)
	public void testLoadHumanTooMany() {
		Human human = new Human();
		assertEquals(0, bus.getSize());
		try {
			for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
				bus.load(human);
				assertEquals(i + 1, bus.getSize());
			}
		} catch (NotEnoughSeatsException e) {
			throw new RuntimeException(e);
		}
		assertEquals(DEFAULT_MAX_SIZE, bus.getMaxSize());
		bus.load(human);
	}

	@Test
	public void testLoadHumanNull() {
		assertEquals(0, bus.getSize());
		bus.load(null);
		assertEquals(1, bus.getSize());
	}

	@Test
	public void testLoadPolicemanMax() {
		Policeman policeman = new Policeman();
		assertEquals(0, bus.getSize());

		for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
			bus.load(policeman);
			assertEquals(i + 1, bus.getSize());
		}
		assertEquals(DEFAULT_MAX_SIZE, bus.getMaxSize());
	}

	@Test
	public void testLoadFirefighterMax() {
		Firefighter firefighter = new Firefighter();
		assertEquals(0, bus.getSize());

		for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
			bus.load(firefighter);
			assertEquals(i + 1, bus.getSize());
		}
		assertEquals(DEFAULT_MAX_SIZE, bus.getMaxSize());
	}

	@Test(expected = PassengerNotFoundException.class)
	public void testUnloadHumanWhenNotExist() {
		Human human = new Human();
		assertEquals(0, bus.getSize());
		bus.unload(human);
	}

	@Test
	public void testUnloadHuman() {
		Human human = new Human();
		bus.load(human);
		assertEquals(1, bus.getSize());
		bus.unload(human);
		assertEquals(0, bus.getSize());
	}

	@Test
	public void testUnload() {
		Human human1 = new Human();
		Human human2 = new Human();
		Firefighter firefighter = new Firefighter();
		Policeman policeman = new Policeman();

		bus.load(human1);
		bus.load(firefighter);
		bus.load(policeman);
		bus.load(human2);
		assertEquals(4, bus.getSize());

		bus.unload(human1);
		assertEquals(3, bus.getSize());
		bus.unload(human2);
		assertEquals(2, bus.getSize());
		bus.unload(firefighter);
		assertEquals(1, bus.getSize());
		bus.unload(policeman);
		assertEquals(0, bus.getSize());

	}

}
