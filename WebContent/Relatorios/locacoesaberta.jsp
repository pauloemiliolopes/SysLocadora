<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Locações em Aberto</title>
<style media="print">
.botao {
display: none;
}
</style>
</head>
<body>
<c:import url="/includes/css.jsp"></c:import>
<c:import url="/includes/js.jsp"></c:import>
<br/>
<h3>Relatório de Locações Abertas</h3>

<br/>

		<table class="table table-hover" >
			<tr bgcolor='EAEAEA'>
				<td>Codigo</td>
				<td>Nome Cliente</td>
				<td>Valor</td>
				<td>Data Locação</td>
				

			</tr>

			<c:forEach items="${requestScope.listalocacoes}" var="locacao">


				<tr>
					<td>${locacao.id}</td>
					<td>${locacao.cliente.nome}</td>
					<td>${locacao.valor}</td>
					<td><fmt:formatDate value="${locacao.dataloc.time}"
							pattern="dd/MM/YYYY" /></td>
			
				

				</tr>

			</c:forEach>
		</table>
		<input type="button" class="botao" name="print" value="Imprimir" onClick="javascript:window.print();">
		<input type="button" class="botao" name="print" value="Voltar" onClick="history.back();">



</body>
</html>