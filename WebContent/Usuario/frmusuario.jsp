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
<c:import url="/includes/menu.jsp"></c:import>
	<div class="container">
	<form  role="form" action="usuariocontroller.do" method="post">		
		<h1> Cadastro de Usuários</h1>
			<div  class="form-group">
				<input type="hidden" name="id" value="${requestScope.usuario.id }"/>				
					<label for="nome"> Nome: </label>
				  	<input type="text" id="nome" class="form-control" name="nome" placeholder="Digite seu Nome" value="${requestScope.usuario.nome}"/> 
			</div>
			<div  class="form-group">			
			 	<label for="user">Login: </label> 	
			 	<input type="text" id="user" class="form-control" name="user" placeholder="Digite seu Login" value="${requestScope.usuario.user }"/> 
			</div>
			<div  class="form-group">			
			 	<label for="senha">Senha:</label>
			 	<input type="password" id="senha" class="form-control" name="senha" placeholder="Digite Sua Senha" value="${requestScope.usuario.senha }"/>
			</div>
			<button type="submit" class="btn btn-success">Gravar</button>
	</form>
	</div>

</body>
</html>