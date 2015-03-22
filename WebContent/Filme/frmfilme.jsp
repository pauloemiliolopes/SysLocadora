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

<c:import url="/includes/menu.jsp"></c:import>

<div class="container">
<form role="form" action="filmecontroller.do" method="post">
	<h1> Cadastro de Filmes</h1>
	<div class="form-group">	
		 <input type="hidden" name="id" value="${requestScope.filme.id }"/> 
		 <label for="nome">Nome</label>
		 <input id="nome" type="text" class="form-control" name="nome" value="${requestScope.filme.nome}"/> 
	 </div>
	 <div class="form-group"> 
 		<label for="data">Data Lançamento</label>
 		<input id="data" type="text" class="form-control" name="lancamento" value="<fmt:formatDate value="${requestScope.filme.datalanc.time}" pattern="dd/MM/YYYY"/>"/> 
 	</div>
 	<div class="form-group"> 
 	<label for="data"> Sinopse </label>
 	 <input id="data" type="text" class="form-control" name="sinopse" value="${requestScope.filme.sinopse}"/> 
 	</div>
 	<div class="form-group">
 		<label for="tempo"> Tempo de Locação</label>
 		<input id="tempo" type="text" class="form-control" name="tempolocacao" value="${requestScope.filme.tempoloc}"/>
 	</div>
 	<div class="form-group"> 
 		<label for="qtd"> Quantidade</label>
 		<input id="qtd" type="text" class="form-control" name="qtd" value="${requestScope.filme.qtd}"/>
 	</div>
 	<div class="form-group"> 
 		<label for="preco" >Preço</label>
  		<input id="preco" type="text" class="form-control" name="preco" value="${requestScope.filme.preco}"/> 
 	</div>

	<div class="form-group">
	 	<label for="preco" >Selecione Genero :</label>
	 	<select name="generos">
		<c:forEach items="${requestScope.listaGenero}" var="genero">
		<option value="${genero.id}">${genero.nome}</option>
		</c:forEach> 
		</select>
	<c:if test="${filme.status == 'A' || filme.status == null}">
	   <input type="radio" name="status" value="A" checked="checked">
		<label >Ativo</label>
		<input type="radio" name="status" value="I">
		<label >Inativo</label>
	</c:if>
	<c:if test="${filme.status == 'I'}">
	<input type="radio" name="status" value="A" >
		<label >Ativo</label>
		<input type="radio" name="status" value="I" checked="checked" >
		<label >Inativo</label>
	</c:if> 
	<button class="btn btn-success">Gravar</button>
	</div>
		
 </form>
</div>
</body>
</html>