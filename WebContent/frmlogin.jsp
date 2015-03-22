<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:import url="/includes/css.jsp"></c:import>
<c:import url="/includes/js.jsp"></c:import>

<title>Sistema de Automação de Locadora de Filmes</title>
</head>
<body>
 <div class="container" style="margin-top:40px">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<div class="panel panel-default">	
				<div class="panel-heading">
					<h3 class="panel-title" style="text-align: center;">Automação para Locadora de Filmes</h3>
				</div>
		<div class="panel-body">
		<form class="form-signin" action="autcontroller.do" method="post">
			<h3 class="form-signin-heading" style="text-align: center;">Logar no Sistema</h3>
			<div style="margin-bottom: 25px" class="input-group">
				<span class="input-group-addon">
					<i class="glyphicon glyphicon-user"></i>
				</span>  
				<input name="user" class="form-control" type="text" autofocus="" required="" placeholder="Digite o Login">
			</div>
			<div style="margin-bottom: 25px" class="input-group">
				<span class="input-group-addon">
					<i class="glyphicon glyphicon-lock"></i>
				</span>  
				<input name="senha" class="form-control" type="password" required="" placeholder="Digite o Senha">
			</div>			
			<button class="btn btn-lg btn-primary btn-block" type="submit"> Autenticar</button>
			<span class="text-danger">${msg}</span>
			
		</form>
	</div>
	</div>
	</div>
	</div>
	</div>
</body>
</html>