<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Loja Elo Biju</title>
<link rel="icon"
	href="imagens/6739827_butterfly_fly_insect_nature_spring_icon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar Produto</h1>
	<a href="main" class="botao3">Voltar</a>
	<p></p>
	<form name="frmContato" action="update">
		<table>
			<tr>
				<td><input type="text" name="idprod" id="caixa3" readonly value="<%out.print(request.getAttribute("idprod"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="caixa1" value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="preço" class="caixa2" value="<%out.print(request.getAttribute("preço"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="imagem" class="caixa1" value="<%out.print(request.getAttribute("imagem"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="botao1" onclick="validar()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>