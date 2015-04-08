<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="JavaScript"> 
function moveRelogio(){ 
   	momentoAtual = new Date(); 
   	hora = momentoAtual.getHours() ;
   	minuto = momentoAtual.getMinutes(); 
   	segundo = momentoAtual.getSeconds(); 

   	horaImprimivel = hora + " : " + minuto + " : " + segundo ;

   	document.relogio.relogio.value = horaImprimivel ;

   	setTimeout("moveRelogio()",1000); 
} 
</script> 
</head>
<body>
<c:import url="/includes/css.jsp"></c:import>
<c:import url="/includes/js.jsp"></c:import>

<span>Usuário : ${sessionScope.usuLogado.nome} </span>

<label id="relogio" onblur="moveRelogio()" ></label>


</body>
</html>