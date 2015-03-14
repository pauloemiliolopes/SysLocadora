<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Filmes</title>
</head>
<body>

<c:import url="/includes/menu.jsp"></c:import><br />

<h1> Cadastro de Filmes</h1>
<form action="filmecontroller.do" method="post">

 <input type="hidden" name="id" value="${requestScope.filme.id }"/> <br />
 Nome: <input type="text" name="nome" value="${requestScope.filme.nome}"/> <br />
 Data Lançamento: <input type="text" name="lancamento" value="<fmt:formatDate value="${requestScope.filme.datalanc.time}" pattern="dd/MM/YYYY"/>"/> <br />
 Sinopse: <input type="text" name="sinopse" value="${requestScope.filme.sinopse}"/> <br />
 Tempo de Locação: <input type="text" name="tempolocacao" value="${requestScope.filme.tempoloc}"/> <br />
 Quantidade: <input type="text" name="qtd" value="${requestScope.filme.qtd}"/> <br />
 Preço: <input type="text" name="preco" value="${requestScope.filme.preco}"/> <br />
 
 Selecione Genero : <select name="generos">
<c:forEach items="${requestScope.listaGenero}" var="genero">
<option value="${genero.id}">${genero.nome}</option>
</c:forEach> 
</select><br/>
 
 Status: <input type="text" name="status" value="${requestScope.filme.status }"/> <br />
 <input type="submit" value="Gravar" />

</form>


</body>
</html>