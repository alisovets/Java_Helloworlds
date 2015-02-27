package tests;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import questions.ReverseList;
import java.util.Iterator;
import java.util.ArrayList;

public class TestReverseList {
	
	@Test
    public void iteratorRemoveTest() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(0, "A");
		list.add(1, "H");
		list.add(2, "S");
		list.add(3, "A");
		list.add(4, "S");
		
		ReverseList.reverseList(list);
		
		String str = ""; 
		for(Iterator<String> iterator = list.iterator(); iterator.hasNext();){
			str += iterator.next();
		}
        assertEquals("SASHA", str);
    }

}
