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
	private Map<String, String[]> parameterMap;
	private String string;
	private PrintWriter saida;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		saida = response.getWriter();

		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");
		response.setStatus(201);
		
		parameterMap = request.getParameterMap();
		string = parameterMap.keySet().parallelStream().findFirst().get();
			
		saida.println(string);
		saida.flush();
		

	}

}
