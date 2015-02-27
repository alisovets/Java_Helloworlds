package questions;

import java.util.List;


/**
 * Test task
 */

public class ReverseList
{
    /**
     * reverse the list given as the param
     * !!! don't change the signature of the method
     * @param list to be reversed
     */
    public static void reverseList(List list)
    {
    	int size = list.size();
    	Object tmpObj;
    	for(int i = 0; i < size / 2; i++){
    		tmpObj = list.get(size - 1 - i);
    		list.set(size - 1 - i, list.get(i));
    		list.set(i, tmpObj);	
    	}
    }

}
