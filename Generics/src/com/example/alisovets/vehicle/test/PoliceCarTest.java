package com.example.alisovets.vehicle.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.example.alisovets.human.Policeman;
import com.example.alisovets.vehicle.NotEnoughSeatsException;
import com.example.alisovets.vehicle.PassengerNotFoundException;
import com.example.alisovets.vehicle.PoliceCar;

public class PoliceCarTest {

	private static int DEFAULT_MAX_SIZE = 5;
	PoliceCar<Policeman> policeCar;

	@Before
	public void initList() {
		policeCar = new PoliceCar<Policeman>(DEFAULT_MAX_SIZE);
	}

	@Test
	public void testCreate() {
		assertNotNull(policeCar);
	}

	@Test
	public void testGetMaxSize() {
		assertEquals(DEFAULT_MAX_SIZE, policeCar.getMaxSize());
	}

	@Test
	public void testMaxSize0() {
		assertEquals(0, policeCar.getSize());
	}

	@Test
	public void testLoadPoliceman1() {
		Policeman policeman = new Policeman();
		assertEquals(0, policeCar.getSize());
		policeCar.load(policeman);
		assertEquals(1, policeCar.getSize());
	}

	@Test
	public void testLoadPoliceman2() {
		Policeman policeman = new Policeman();
		assertEquals(0, policeCar.getSize());
		policeCar.load(policeman);
		policeCar.load(policeman);
		assertEquals(2, policeCar.getSize());
	}

	@Test
	public void testLoadPolicemanMax() {
		Policeman policeman = new Policeman();
		assertEquals(0, policeCar.getSize());

		for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
			policeCar.load(policeman);
			assertEquals(i + 1, policeCar.getSize());
		}
		assertEquals(DEFAULT_MAX_SIZE, policeCar.getMaxSize());
	}

	@Test(expected = NotEnoughSeatsException.class)
	public void testLoadPolicemanTooMany() {
		Policeman policeman = new Policeman();
		assertEquals(0, policeCar.getSize());
		try {
			for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
				policeCar.load(policeman);
				assertEquals(i + 1, policeCar.getSize());
			}
		} catch (NotEnoughSeatsException e) {
			throw new RuntimeException(e);
		}
		assertEquals(DEFAULT_MAX_SIZE, policeCar.getMaxSize());
		policeCar.load(policeman);
	}

	@Test
	public void testLoadPolicemanNull() {
		assertEquals(0, policeCar.getSize());
		policeCar.load(null);
		assertEquals(1, policeCar.getSize());
	}

	@Test(expected = PassengerNotFoundException.class)
	public void testUnloadPolicemanWhenNotExist() {
		Policeman policeman = new Policeman();
		assertEquals(0, policeCar.getSize());
		policeCar.unload(policeman);
	}

	@Test
	public void testUnloadPoliceman() {
		Policeman policeman = new Policeman();
		policeCar.load(policeman);
		assertEquals(1, policeCar.getSize());
		policeCar.unload(policeman);
		assertEquals(0, policeCar.getSize());
	}

	@Test
	public void testUnload() {
		Policeman policeman1 = new Policeman();
		Policeman policeman2 = new Policeman();
		Policeman policeman3 = new Policeman();

		policeCar.load(policeman1);
		policeCar.load(policeman2);
		policeCar.load(policeman3);
		assertEquals(3, policeCar.getSize());

		policeCar.unload(policeman1);
		assertEquals(2, policeCar.getSize());
		policeCar.unload(policeman2);
		assertEquals(1, policeCar.getSize());
		policeCar.unload(policeman3);
		assertEquals(0, policeCar.getSize());

	}

}
