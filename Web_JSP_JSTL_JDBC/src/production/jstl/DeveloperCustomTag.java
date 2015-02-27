package jstl;


import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class DeveloperCustomTag  extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	private String name;
    
    public void setName(String name) {
        this.name = name;
    }	
    
	@Override
    public int doStartTag() throws JspException {
        try {
        	pageContext.getOut().print("<h6>");
        	pageContext.getOut().print("This page was developed by ");
            pageContext.getOut().print(name);
            pageContext.getOut().print("</h6>");
        } catch(IOException ioException) {
            throw new JspException("Error: " + ioException.getMessage());
        }       
        return SKIP_BODY;
    }
}


