<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Informação</title>
</head>
<body>

<c:import url="/includes/menu.jsp"></c:import>

<div class="container">
<form action="itenscontroller.do"> 
<div class="form-group"> 
<br/>
<br/>
 
<h1>Locação Realizada com sucesso!<h1>
<br/>
<br/>
<br/>
<h4>Obrigado pela preferencia!<h4>
<br/>
<input type="hidden" name="acao" value="voltar">
<button class="btn btn-success"> Inicio </button>

</div>



</form>

</div>
</body>
</html>