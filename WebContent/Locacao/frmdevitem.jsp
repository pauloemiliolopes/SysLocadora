<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Devolvendo Filme</title>
</head>
<body>

<c:import url="/includes/menu.jsp"></c:import>

<form action="itenscontroller.do">
<input type="hidden" name="acao" value="financeiro">
<input type="hidden" name="id" value="${itenslocacao.id }">

<div class="table-responsive">
<table class="table">
		<tr bgcolor='EAEAEA' class="success">
			<tr>
			<td >Codigo :
			${itenslocacao.id}</td>
			</tr>
			<tr>
			<td>Nome do Filme :
			${itenslocacao.filme.nome}</td>
			</tr>
			<tr>
			<td>Total do Item :
			${itenslocacao.filme.preco * itenslocacao.qtd}</td>
			</tr>
			<tr>
			<td>Data Prevista para Devolução :
			 <fmt:formatDate value="${itenslocacao.dataprevdevolucao.time}" pattern="dd/MM/YYYY" />
			</td>
			</tr>			
		
			<tr>
			<td>Data Devolução :
			<fmt:formatDate value="${itenslocacao.datadevolucao.time}" pattern="dd/MM/YYYY" />
			</td>			
		    </tr>
			<tr>			
			<td><center><label>Forma de Pagamento :</label> <br/><br/>
			<input type="radio" name="fpgto" value="DINHEIRO" checked="checked">
				<label >Dinheiro</label>
				<input type="radio" name="fpgto" value="CARTAO">
				<label >Cartão</label></center></td>
			</tr>

	</table>
	</div>
	
	<span class="text-danger">${requestScope.msg}</span><br/><br/>
	<h1><span class="text-danger">${requestScope.msg1}</span></h1><br/><br/>
	
	
	<button class="btn btn-danger">Devolver</button>
	
</form>

<a href="itenscontroller.do?acao=dev&id=${itenslocacao.locacao}"><button class="btn btn-success" >Continuar Devolução</button></a>

</body>
</html>