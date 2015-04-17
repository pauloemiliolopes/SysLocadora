<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movimentaçoes Financeiras</title>
</head>
<body>
<c:import url="/includes/menu.jsp"></c:import><br />
<div class="container">
	<form  role="form" action="caixacontroller.do" method="post">
		<h1>Lançamento de Caixa</h1>
		<div  class="form-group">
	 		<input type="hidden" name="id" value="${requestScope.caixa.id }"/> <br />
	 		<label for="nome">Descriçao :</label> 
	 		<input id="nome" type="text" size="40" class="form-control" name="descricao" value="${requestScope.caixa.descricao}"/> <br />
	 	</div>
	 	<div class="form-group">
	 	<label for="nome">Valor :</label> 
	 	<input id="nome" type="text" size="40" class="form-control" name="valor" value="${requestScope.caixa.valorpagamento}"/> <br />
	 	</div>
		<div  class="form-group">
			<c:if test="${caixa.tipo == 'R' || caixa.tipo == null}">
			<label for="nome">Tipo de Movimento :</label>
			<br/>
			<br/>
			   <input type="radio" name="tipo" value="R" checked="checked">
				<label >Entrada </label>
				<input type="radio" name="tipo" value="P">
				<label >Saida </label>
			</c:if>
			<c:if test="${caixa.tipo == 'P'}">
			<label for="nome">Tipo de Movimento :</label>
			<br/>
			<br/>
			<input type="radio" name="tipo" value="R" >
				<label >Entrada </label>
				<input type="radio" name="tipo" value="P" checked="checked" >
				<label >Saida </label>
			</c:if> 
		</div>
		<br/>
		<br/>
			<br/>
		<button class="btn btn-success">Gravar</button>
	</form>
</div>
</body>
</html>