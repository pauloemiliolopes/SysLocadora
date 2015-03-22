<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Generos</title>
</head>
<body>

	<c:import url="/includes/menu.jsp"></c:import>
	<br />
	<div class="simplesTable">
		<table>
			<tr>
				<td>Codigo</td>
				<td>Nome</td>
				<td>Status</td>
				<td>A��o</td>

			</tr>

			<c:forEach items="${requestScope.lista}" var="genero">


				<tr>
					<td>${genero.id}</td>
					<td>${genero.nome}</td>
					<td>${genero.status}</td>
					<td>| <a href="generocontroller.do?acao=alt&id=${genero.id}">Alterar</a>
						| <a href="generocontroller.do?acao=exc&id=${genero.id }">Excluir</a>
						|
					</td>

				</tr>

			</c:forEach>
		</table>
	</div>
</body>
</html>