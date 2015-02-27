package questions;

import java.util.Iterator;
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;


/**
 * SimpleList implements some methods of List interface
 *
 *
 *
 */
public class SimpleArrayList
{
    //array to store elements of the list
    Object[] data;
    int size;
    
    public SimpleArrayList(){
    	data = new Object[10];
    	size = 0; 
    	
    }

    /**
     * Specified by: the same method of java.util.List
     * @param index
     * @return
     */
    public Object get(int index){
    	if((index >= size)||(index < 0))
    		throw new IndexOutOfBoundsException();
    	return data[index];
    }

    /**
     * Specified by: the same method of java.util.List
     * @param index
     * @param obj
     */
    public Object set(int index, Object obj){
    	if((index >= size)||(index < 0))
    		throw new IndexOutOfBoundsException();
    	Object retObj = data[index]; 
    	data[index] = obj;
    	return retObj;
    }

    /**
     * Specified by: the same method of java.util.List
     * @param index
     * @param element
     */
    public void add(int index, Object element){
    	if((index > size)||(index < 0))
    		throw new IndexOutOfBoundsException();
    	
        if(size == data.length - 1){
        		expand();        	
        }
        
        for(int i = size; i >= index; i--){
        	data[i + 1] = data[i];
        }
        
        data[index] = element;
        size++;
    }

    /**
     * Specified by: the same method of java.util.List
     * @return
     */
    
    public Iterator iterator(){
       return new Iterator(){
    	   int pos = -1;
    	   boolean isRemoved = false;
    	   
    	   public boolean hasNext(){
    		   if(pos < size - 1)
    			   return true;
    		   return false;
    	   }
    	   
    	   public Object next(){
    		   if(pos >= size - 1)
    			   throw new NoSuchElementException();
    		   isRemoved = false;
    		   return data[++pos];
    	   }
    	   
    	   public void remove(){
    		   if(pos == -1)
    			   throw new IllegalStateException();
    		   isRemoved = true;
    		   size--;
    		   for(int i = pos; i < size; i++){
    			   data[i] = data[i+1];
    		   }
    	   }
        
       };
    }
    

    /**
     * Specified by: the same method of java.util.List
     * @return
     */
    
    public int hashCode(){
    	 int hashCode = 1;
    	  Iterator i = this.iterator();
    	  while (i.hasNext()) {
    	      Object obj = i.next();
    	      hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
    	  }
    	  return hashCode;
    }
    

    /**
     * Specified by: the same method of java.util.List
     * @return
     */
    public boolean equals(Object obj)
    {
    	if(!(obj instanceof SimpleArrayList))
    		return false;
    
    	if(size != ((SimpleArrayList)obj).size){
    	   return false;
    	}
    	for(int i = 0; i < size; i++){
    	   if(data[i] == null){
    		   if(((SimpleArrayList)obj).data[i] != null)
    			   return false;
    	   }
    	   else{
    		   if(!data[i].equals(((SimpleArrayList)obj).data[i]))
    			   return false;
    	   }
    	}
    	return true;  
    }

    /**
     * Specified by: the same method of java.util.List
     * @return
     */
    public int indexOf(Object o){
       for(int i = 0; i < size; i++){
    	   if(o == null){
    		   if((data[i] == null))
    			   return i;
    	   }
    	   else if(o.equals(data[i]))
    		   return i;    	   
       }
       return -1;
    }
    
    public int size(){
    	return size;
    }
    
    private void expand(){
    	Object[] newData = new Object[data.length * 2];
    	for(int i = 0; i < data.length; i++ ){
    		newData[i] = data[i];
    	}
    	data = newData;
    }
}
