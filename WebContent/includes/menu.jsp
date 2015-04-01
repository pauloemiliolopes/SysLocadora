
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
<c:import url="/includes/css.jsp"></c:import>
<c:import url="/includes/js.jsp"></c:import>
<div class="container">
<div class="btn-group">
  <button type="button" class="btn btn-primary">Inicio</button>
  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
    <span class="caret"></span>
    <span class="sr-only">Toggle Dropdown</span>
  </button>
  <ul class="dropdown-menu" role="menu">
    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value='index.jsp'/>">Inicio</a></li>
  </ul>
</div>

<div class="btn-group">
  <button type="button" class="btn btn-primary">Cadastros</button>
  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
    <span class="caret"></span>
    <span class="sr-only">Toggle Dropdown</span>
  </button>
<ul class="dropdown-menu" role="menu">
    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value='/usuariocontroller.do?acao=cad'/>">Cadastrar Usuario</a></li>
   	<li class="divider"></li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value='/clientecontroller.do?acao=cad'/>">Cadastrar Cliente</a></li>
    <li class="divider"></li>
     <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value='/generocontroller.do?acao=cad'/>">Cadastrar Genero</a></li>
    <li class="divider"></li>
     <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value='/filmecontroller.do?acao=cad'/>">Cadastrar Filme</a></li>
    </ul>
</div>
<div class="btn-group">
  <button type="button" class="btn btn-primary">Movimentacao</button>
  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
    <span class="caret"></span>
    <span class="sr-only">Toggle Dropdown</span>
  </button>
  <ul class="dropdown-menu" role="menu">
    <li class="divider"></li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value='/usuariocontroller.do?acao=lis'/>">Lista Usuarios</a></li>
  	<li class="divider"></li>
    <li role="presentation"><a role="menuitem" tabindex="-1"  href="<c:url value='/clientecontroller.do?acao=lis'/>">Listar Clientes</a></li>
    <li class="divider"></li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value='/generocontroller.do?acao=lis'/>">Listar Generos</a></li>
  	<li class="divider"></li>
  	<li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value='/filmecontroller.do?acao=lis'/>">Listar Filmes</a></li>
  	<li class="divider"></li>
  	<li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value='/locacaocontroller.do?acao=cad'/>">Alugar Filmes</a></li>
  	<li class="divider"></li>
  	<li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value='/itenscontroller.do?acao=listloc'/>">Devolver Filmes</a></li>
  </ul>
</div>
<div class="btn-group">
  <button type="button" class="btn btn-primary">Ajuda</button>
  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
    <span class="caret"></span>
    <span class="sr-only">Toggle Dropdown</span>
  </button>
  <ul class="dropdown-menu" role="menu">
    <li role="presentation"><a role="menuitem" tabindex="-1"  href="#">Ajuda</a></li>
    <li class="divider divider-background"></li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value='#'/>">Contato</a></li>
    <li class="divider"></li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value='#'/>">Sair</a></li>
  </ul>
</div>
</div>
</body>
 </html>
 
   