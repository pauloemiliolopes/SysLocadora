<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sistema de Automação de Locadora de Filmes</title>
</head>
<body>

<form action="autcontroller.do" method="post">
 Login: <input type="text" name="user" /><br />
 Senha:<input type="text" name="senha" /><br />
 <input type="submit" value="Autenticar" />
 <label>${msg}</label>

</body>
</html>