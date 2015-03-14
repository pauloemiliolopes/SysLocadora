<%@ page import="br.com.sistemalocadora.Model.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Generos </title>
</head>
<body>

<table border='1'>
<tr bgcolor='EAEAEA'> 
<th>Codigo</th> 
<th>Nome</th>
<th>Status</th>
<th>Ação</th>

</tr>
<%
List<Genero> lista = (List<Genero> ) request.getAttribute("lista"); 

for (Genero genero : lista) {  %>

<tr> 
<td><%=genero.getId()%></td> 
<td><%=genero.getNome()%></td>
<td><%=genero.getStatus()%></td>
<td>
|
<a href="generocontroller.do?acao=alt&id=<%=genero.getId()%>">Alterar</a>
|
<a href="generocontroller.do?acao=exc&id=<%=genero.getId()%>">Excluir</a>
|
</td>

</tr>
	
<% 	
}
%>

</table>

</body>
</html>