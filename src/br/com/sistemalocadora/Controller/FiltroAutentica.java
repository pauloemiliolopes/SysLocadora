package br.com.sistemalocadora.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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

import br.com.sistemalocadora.JDBC.Conexao;

/**
 * Servlet Filter implementation class FiltroAutentica
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD }, urlPatterns = { "/*" })
public class FiltroAutentica implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		String url = httpServletRequest.getRequestURI();

		HttpSession sessao = ((HttpServletRequest) request).getSession();

		if (sessao.getAttribute("usuLogado") != null || urlfree(url)) {

			chain.doFilter(request, response);
			
		} else {

			((HttpServletResponse) response).sendRedirect("frmlogin.jsp");
		}
	}

	private boolean urlfree(String url) {
		if (url.lastIndexOf("api") != -1 
				|| url.lastIndexOf("bootstrap") != -1
				|| url.lastIndexOf("scripts") != -1
				|| url.lastIndexOf("css") != -1
				|| url.lastIndexOf("frmlogin.jsp") != -1
				|| url.lastIndexOf("autcontroller.do") != -1) {
			return true;
		}
		return false;
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
