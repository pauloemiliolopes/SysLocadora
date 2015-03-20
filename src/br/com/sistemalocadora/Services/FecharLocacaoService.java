package br.com.sistemalocadora.Services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/fechar")
public class FecharLocacaoService extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter saida = response.getWriter();
		response.setContentType("application/json");	

		Map<String, String[]> parameterMap = request.getParameterMap();
		
		saida.println(GsonResponse.JsonBilder(parameterMap));

		saida.flush();
	}

}
