<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table border='1'>
					<tr bgcolor='EAEAEA'>
						<th>Codigo</th>
						<th>Cliente</th>
						<th>Data Locação</th>
						<th>Valor</th>
						<th>Ação</th>

					</tr>

					<c:forEach items="${requestScope.lista}" var="locacao">


						<tr>
							<td>${locacao.id}</td>
							<td>${locacao.cliente.nome}</td>
							<td><fmt:formatDate value="${locacao.dataloc.time}" pattern="dd/MM/YYYY"/></td>
							<td>${locacao.valor}</td>

							<td>| <a href="locacaocontroller.do?acao=alt&id=${locacao.id}">Alterar</a>
								|<a href="locacaocontroller.do?acao=exc&id=${locacao.id }">Excluir</a>|
							</td>

						</tr>

					</c:forEach>
				</table>


</body>
</html>