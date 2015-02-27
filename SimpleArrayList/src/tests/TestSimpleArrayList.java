package tests;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Iterator;

import questions.*;
    
public class TestSimpleArrayList {
	
	@Test
    public void getTest() {
		SimpleArrayList sal = new SimpleArrayList();
		sal.add(0, "S");
		sal.add(1, "A");
		sal.add(2, "S");
        assertEquals("A", sal.get(1));
    }
	
	@Test
    public void addTest() {
		SimpleArrayList sal = new SimpleArrayList();
		sal.add(0, "A");
		sal.add(0, "H");
		sal.add(0, "S");
		sal.add(0, "A");
		sal.add(0, "S");
        assertEquals("H", sal.get(3));
    }
	
	@Test
    public void setTest() {
		SimpleArrayList sal = new SimpleArrayList();
		sal.add(0, "S");
		sal.add(1, "A");
		sal.add(2, "S");
		sal.set(1, "X");
        assertEquals("X", sal.get(1));
    }
	
	@Test
    public void iteratorNextTest() {
		SimpleArrayList sal = new SimpleArrayList();
		sal.add(0, "A");
		sal.add(0, "H");
		sal.add(0, "S");
		sal.add(0, "A");
		sal.add(0, "S");
		
		String str = ""; 
		for(Iterator iterator = sal.iterator(); iterator.hasNext();){
			str += iterator.next();
		}
        assertEquals("SASHA", str);
    }
	
	@Test
    public void iteratorRemoveTest() {
		SimpleArrayList sal = new SimpleArrayList();
		sal.add(0, "A");
		sal.add(0, "H");
		sal.add(0, "S");
		sal.add(0, "A");
		sal.add(0, "S");
		
		for(Iterator iterator = sal.iterator(); iterator.hasNext();){
			String s = (String)iterator.next();			
			if(s.equals("H")){
				iterator.remove();
			}
		}
		String str = ""; 
		for(Iterator iterator = sal.iterator(); iterator.hasNext();){
			str += iterator.next();
		}
        assertEquals("SASA", str);
    }
	
	
	
}
