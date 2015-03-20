package br.com.sistemalocadora.Controller;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltroAutentica
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD
		}
					, urlPatterns = { "/*" })
public class FiltroAutentica implements Filter {

   
    public FiltroAutentica() {
      
    }

	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		String url = httpServletRequest.getRequestURI();
		
		HttpSession sessao = ((HttpServletRequest) request).getSession();
		
		if(sessao.getAttribute("usuLogado") != null
				|| url.lastIndexOf("frmlogin.jsp") > -1 
				|| url.lastIndexOf("autcontroller.do") > -1
				|| url.lastIndexOf("api") > -1){
			
			chain.doFilter(request, response);
		
		}else{
			
		((HttpServletResponse) response).sendRedirect("frmlogin.jsp"); 	
			
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
