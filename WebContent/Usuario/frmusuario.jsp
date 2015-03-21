<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Cadastro de Usuários</title>
</head>
<body>

<c:import url="/includes/menu.jsp"></c:import><br />
<h1> Cadastro de Usuários</h1>
<form action="usuariocontroller.do" method="post">
 <input type="hidden" name="id" value="${requestScope.usuario.id }"/> <br />
 Nome: <input type="text" name="nome" value="${requestScope.usuario.nome}"/> <br />
 Login: <input type="text" name="user" value="${requestScope.usuario.user }"/> <br />
 Senha: <input type="text" name="senha" value="${requestScope.usuario.senha }"/> <br />
 <input type="submit" value="Gravar" />

</form>

</body>
</html>