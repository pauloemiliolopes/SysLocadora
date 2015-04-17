<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Baixar</title>
</head>
<body>


<c:import url="/includes/menu.jsp"></c:import><br />



</head>

<body>

<form action="financeirocontroller.do" method="get">
<input type="hidden" name="id" value="${financeiro.id }">					
<input type="hidden" name="acao" value="baixarparcial">					

<br/>
<br/>
<table class="table table-striped" border='1'>
		<tr bgcolor='EAEAEA' class="success">
			<td>Codigo Financeiro :</td>
			<td>${financeiro.id}</td>
		</tr>
		<tr>	
			<td>Codigo Locaçao :</td>
			<td>${financeiro.locacao.id}</td>
		</tr>
		<tr>
	        <td>Nome do Cliente :</td>
	        <td>${financeiro.locacao.cliente.nome}</td>
	    </tr>
	    <tr>    
	        <td>Data Emissao :</td>
	        <td><fmt:formatDate
						value="${financeiro.dataemissao.time}"
						pattern="dd/MM/YYYY" /></td>
		</tr>
		<tr>				
			<td>Data Vencimento :</td>
			<td><fmt:formatDate
						value="${financeiro.datavencimento.time}"
						pattern="dd/MM/YYYY" /></td>
		</tr>
		<tr>
			<td>Valor :</td>
			<td>${financeiro.valor}</td>
		</tr>
        <tr>
			<td>Saldo</td>
			<td>${financeiro.saldo}</td>
		</tr>
		<tr>
			<td>Valor do Pagamento :</td>
			<td><input type="text" name="valor"></td>
		</tr>
		<tr>
			<td>Forma de pagamento :</td>
	        <td>
			<input type="radio" name="fpgto" value="Dinheiro" checked="checked">
            <label >Dinheiro</label>
            <input type="radio" name="fpgto" value="Cartao"  >
             <label >Cartão</label>
            </td>
          
		</tr>
            <center><span class="text-danger">${requestScope.msg}</span></center><br/><br/>
			<tr>
		
				<td><button class="btn btn-primary" >Finalizar Baixa</button></td>
	
			</tr>

		
	</table>
		
</form> 

</body>
</html>