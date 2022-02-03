<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="Model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
@ SuppressWarnings ("unchecked")
List<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("produtos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Elo Biju | Produtos</title>
<link rel="icon"
	href="imagens/6739827_butterfly_fly_insect_nature_spring_icon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Relação de Produtos</h1>
	<div id="snackbar">Produto excluído com sucesso!</div>
	
	<a href="Novo.html" class="botao1">Novo Produto</a>
	<a href="report" class="botao2">Gerar PDF</a>
	
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Preço</th>
				<th>Imagem</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getIdprod()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getPreçoFormatado()%></td>
				<td><%=lista.get(i).getImagem()%></td>
				<td><a href="select?idprod=<%=lista.get(i).getIdprod()%>"
					class="botao1">Editar</a> 
					<a href="javascript: confirmar(<%=lista.get(i).getIdprod()%>)" class="botao2">Excluir</a></td>
			</tr>
			<%}%>
		</tbody>
	</table>
	<div>	
		<img src="imagens/6739827_butterfly_fly_insect_nature_spring_icon.png" class="imgProdutos">
		<a href="index.html" class="botao3 produto">Home</a>
	</div>	
<script src="scripts/confirmador.js"></script>
</body>
</html>