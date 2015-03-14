<%@page import="br.com.sistemalocadora.Model.Genero"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Generos</title>
</head>
<body>
<%
Genero genero = (Genero) request.getAttribute("genero");
%>

<form action="generocontroller.do" method="post">
 Codigo: <input type="text" name="id" value="<%=genero.getId()%>"/> <br />
 Descriçao: <input type="text" name="nome" value="<%=genero.getNome()%>"/> <br />
 Status: <input type="text" name="status" value="<%=genero.getStatus()%>"/> <br />
 <input type="submit" value="Gravar" />

</form>

</body>
</html>