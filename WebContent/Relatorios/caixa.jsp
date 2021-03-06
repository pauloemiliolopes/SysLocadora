<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Relatorio de Caixa</title>
<c:import url="/includes/css.jsp"></c:import>
<c:import url="/includes/js.jsp"></c:import>


 <script>
$(function() {
    $( "#datepicker" ).datepicker({
        dateFormat: 'dd/mm/yy',
        dayNames: ['Domingo','Segunda','Ter�a','Quarta','Quinta','Sexta','S�bado','Domingo'],
        dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S�b','Dom'],
        monthNames: ['Janeiro','Fevereiro','Mar�o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez']
    });



    $( "#datepicker1" ).datepicker({
        dateFormat: 'dd/mm/yy',
        dayNames: ['Domingo','Segunda','Ter�a','Quarta','Quinta','Sexta','S�bado','Domingo'],
        dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S�b','Dom'],
        monthNames: ['Janeiro','Fevereiro','Mar�o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez']
    });



});
</script>


</head>

<body>


<form action="caixacontroller.do" method="get">
<input type="hidden" name="acao" value="listcaixa">
<label>Data Inicial :</label>
<input type="text" name="dataini" id="datepicker" />
<label>Data Final :</label>
<input type="text" name="datafin" id="datepicker1" >
		<input type="submit" value="Buscar" >
</form>
		
<h3>Movimento de Caixa :</h3>	
		

		<table class="table table-hover">
			<tr bgcolor='EAEAEA'>
				<td>Codigo</td>
				<td>Descri��o</td>
				<td>Data</td>
				<td>Valor</td>
                <td>Tipo</td>

			</tr>

			<c:forEach items="${requestScope.listacaixa}" var="caixa">


				<tr>
					<td>${caixa.id}</td>
					<td>${caixa.descricao}</td>
					<td><fmt:formatDate value="${caixa.datapagamento.time}"
							pattern="dd/MM/YYYY" /></td>
					<td>${caixa.valorpagamento}</td>
					<c:if test="${caixa.tipo == 'R'}">
					<td>Credito</td>
					</c:if>
					<c:if test="${caixa.tipo == 'P'}">
					<td>Debito</td>
					</c:if>
					
					</tr>

			</c:forEach>
		</table>

	<br/>
	<h3>Vendas Cartao :</h3>
	<br/>
	

		<table class="table table-hover">
			<tr bgcolor='EAEAEA'>
				<td>Codigo</td>
				<td>Descri��o</td>
				<td>Data</td>
				<td>Valor</td>
                <td>Tipo</td>

			</tr>

			<c:forEach items="${requestScope.listacartao}" var="caixa">


				<tr>
					<td>${caixa.id}</td>
					<td>${caixa.descricao}</td>
					<td><fmt:formatDate value="${caixa.datapagamento.time}"
							pattern="dd/MM/YYYY" /></td>
					<td>${caixa.valorpagamento}</td>
					<c:if test="${caixa.tipo == 'R'}">
					<td>Credito</td>
					</c:if>
					<c:if test="${caixa.tipo == 'P'}">
					<td>Debito</td>
					</c:if>
					
					</tr>

			</c:forEach>
		</table>




</body>
</html>