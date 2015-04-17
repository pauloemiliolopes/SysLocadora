<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Movimentações</title>
<c:import url="/includes/menu.jsp"></c:import>
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


	
	<br />
	
	<center><h3>Buscar Movimento de Caixa</h3></center>
	
<br/>
	<br/>
<form action="caixacontroller.do" method="get">
<input type="hidden" name="acao" value="buscarmov">
<label>Data Inicial :</label>
<input type="text" name="dataini" id="datepicker" />
<label>Data Final :</label>
<input type="text" name="datafin" id="datepicker1" >
		<input type="submit" value="Buscar" >
		
	
		
</form>
<br/>
	<br/>
	
	<div class="simplesTable">
		<table border='1'>
			<tr bgcolor='EAEAEA'>
				<td>Codigo</td>
				<td>Descrição</td>
				<td>Data</td>
				<td>Valor</td>
                <td>Tipo</td>
				<td>Ação</td>

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
					
			
					<td><a href="caixacontroller.do?acao=alt&id=${caixa.id}"><button class="btn btn-primary" >Alterar</button></a>
					<a href="caixacontroller.do?acao=exc&id=${caixa.id}"><button class="btn btn-danger" >Excluir</button></a>
					</td>

				</tr>

			</c:forEach>
		</table>
	</div>

</body>
</html>