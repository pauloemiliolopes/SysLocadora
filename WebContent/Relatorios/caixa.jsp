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
        dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado','Domingo'],
        dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
        monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez']
    });



    $( "#datepicker1" ).datepicker({
        dateFormat: 'dd/mm/yy',
        dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado','Domingo'],
        dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
        monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez']
    });



});
</script>


</head>

<body>





<form action="caixacontroller.do" method="get">
<input type="hidden" name="acao" value="buscarforma">
<label>Data Inicial :</label>
<input type="text" name="dataini" id="datepicker" />
<label>Data Final :</label>
<input type="text" name="datafin" id="datepicker1" >
Selecione Forma de pagamento :
<input type="radio" name="fpgto" value="DINHEIRO" checked="checked">
		<label >Dinheiro</label>
		<input type="radio" name="fpgto" value="CARTAO">
		<label >Cartao</label>
        <input type="radio" name="fpgto" value="">
		<label >Todos</label>
		<input type="submit" value="Buscar" >
		
	
		
</form>
<br/>
<br/>
<table class="table table-striped" border='1'>
		<tr bgcolor='EAEAEA' class="success">
			<td>Codigo Locaçao</td>
	        <td>Filme</td>
	        <td>Data Prevista para Devolução</td>
			<td>Data Pagamento</td>
			<td>Preço</td>
			

		</tr>

		<c:forEach items="${requestScope.listaitens}" var="itenslocacao">


			<tr>
				<td>${itenslocacao.locacao}</td>
				<td>${itenslocacao.filme.nome}</td>
				<td><fmt:formatDate
						value="${itenslocacao.dataprevdevolucao.time}"
						pattern="dd/MM/YYYY" /></td>
				<td><fmt:formatDate
						value="${itenslocacao.datadevolucao.time}"
						pattern="dd/MM/YYYY" /></td>
				<td>${itenslocacao.valoritem}</td>
				

				
				

			</tr>

		</c:forEach>
	</table>
		



</body>
</html>