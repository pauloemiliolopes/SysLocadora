<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Realizar Locação</title>

</head>
<body>
<c:import url="/includes/menu.jsp"></c:import>
<div class="container">
<br/>

<form role="form"action="locacaocontroller.do" method="get">
                    <div class="form-group">
					<label for="busca">Buscar Cliente: </label>
					<input id="locacao" type="text" class="form-control" name="buscarcli"/>
					<br/>
					<input type="submit" class="btn btn-success" value="Buscar">
					</div>
				</form> <br/>


<form role="form" name="formvenda" action="locacaocontroller.do" method="post">
<h1>Locação</h1>


                 
				
                <input id="locacao" type="hidden" class="form-control" name="idvenda" value="${requestScope.locacao.id}" /> <br /> 
					
				 
	              <div class="form-group">	
					<label for="cliente">Selecione o Cliente :</label>
					<select id="cliente" name="clientes">
						<c:forEach items="${requestScope.listaCliente}" var="cliente">
							<option id="cliente" value="${cliente.id}">${cliente.nome}</option>
						</c:forEach>
					</select>
					 
					
					<br /> 
					</div>
					
					
					<div class="form-group">
					<label for="data">Data da Locação :</label>
					 <input id="locacao" type="text" class="form-control" name="datalocacao" value="<fmt:formatDate value="${requestScope.data}" pattern="dd/MM/YYYY"/>" /><br />
                     
						<input type="hidden" value="cadvenda" name="acao">
						
						</div>
						<input class="btn btn-success" type="submit" value="Iniciar Locação"/>
                     
				</form><br/><br/>
				
    </div>          

</body>
</html>