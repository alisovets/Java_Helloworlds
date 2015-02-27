package backingbean;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

public class ClientBeanTest {
	private ClientBean clientBean; 
	
	@Before
	public void init() {
		clientBean = new ClientBean();
	}

	@Test
	public void ClientBean_Created_ObjectExists() {
		ClientBean clientBean = new ClientBean();
		assertNotNull(clientBean);
	}
	
	@Test
	public void create_Sucsess_IdIsFilled(){
		//TODO: it
		
	}
	
	
}
