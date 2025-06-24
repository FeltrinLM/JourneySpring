<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.journeyspring.model.Usuario" %>
<%@ page import="org.example.journeyspring.model.Peca" %>
<%@ page import="org.example.journeyspring.model.Colecao" %>
<%@ page import="org.example.journeyspring.model.Estampa" %>


<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("index.jsp?erro=session_expired");
        return;
    }

    List<Peca> pecas = (List<Peca>) request.getAttribute("pecas");
    List<Colecao> colecoes = (List<Colecao>) request.getAttribute("colecoes");
    List<Estampa> estampas = (List<Estampa>) request.getAttribute("estampas");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Visualização Geral</title>
    <link rel="stylesheet" href="../CSS/visualizacao-geral.css">
</head>
<body>

<h2>Bem-vindo, <%= usuario.getNome() %></h2>

<div class="section">
    <h3>Lista de Peças</h3>
    <table>
        <tr>
            <th>Tipo</th>
            <th>Tamanho</th>
            <th>Cor</th>
            <th>Quantidade</th>
            <th>Ações</th>
        </tr>
        <% if (pecas != null) {
            for (Peca p : pecas) { %>
        <tr>
            <td><%= p.getTipo() %></td>
            <td><%= p.getTamanho() %></td>
            <td><%= p.getCor() %></td>
            <td><%= p.getQuantidade() %></td>
            <td class="btn-group">
                <form method="post" action="dashboard">
                    <input type="hidden" name="acao" value="excluir">
                    <input type="hidden" name="id" value="<%= p.getPeca_id() %>">
                    <input type="submit" value="Excluir" class="btn-excluir">
                </form>
                <form method="get" action="editar-peca">
                    <input type="hidden" name="id" value="<%= p.getPeca_id() %>">
                    <input type="submit" value="Editar" class="btn-editar">
                </form>
            </td>
        </tr>
        <%  }} %>
    </table>
    <div class="btn-wrapper">
        <form action="nova-peca.jsp" method="get">
            <input type="submit" value="Cadastrar nova peça" class="btn-add">
        </form>
    </div>
</div>

<div class="section">
    <h3>Lista de Coleções</h3>
    <table>
        <tr>
            <th>Nome</th>
            <th>Data Início</th>
            <th>Data Fim</th>
            <th>Ações</th>
        </tr>
        <% if (colecoes != null) {
            for (Colecao c : colecoes) { %>
        <tr>
            <td><%= c.getNome() %></td>
            <td><%= c.getData_inicio() %></td>
            <td><%= c.getData_fim() %></td>
            <td class="btn-group">
                <form method="post" action="dashboard">
                    <input type="hidden" name="acao" value="excluir-colecao">
                    <input type="hidden" name="id" value="<%= c.getId_colecao() %>">
                    <input type="submit" value="Excluir" class="btn-excluir">
                </form>
                <form method="get" action="editar-colecao">
                    <input type="hidden" name="id" value="<%= c.getId_colecao() %>">
                    <input type="submit" value="Editar" class="btn-editar">
                </form>
            </td>
        </tr>
        <%  }} %>
    </table>
    <div class="btn-wrapper">
        <form method="get" action="nova-colecao.jsp">
            <input type="submit" value="Cadastrar nova coleção" class="btn-add">
        </form>
    </div>
</div>

<div class="section">
    <h3>Lista de Estampas</h3>
    <table>
        <tr>
            <th>Nome</th>
            <th>Quantidade</th>
            <th>Coleção</th>
            <th>Ações</th>
        </tr>
        <% if (estampas != null) {
            for (Estampa e : estampas) { %>
        <tr>
            <td><%= e.getNome() %></td>
            <td><%= e.getQuantidade() %></td>
            <td><%= e.getNomeColecao() %></td>
            <td class="btn-group">
                <form method="post" action="dashboard">
                    <input type="hidden" name="acao" value="excluir-estampa">
                    <input type="hidden" name="id" value="<%= e.getId_estampa() %>">
                    <input type="submit" value="Excluir" class="btn-excluir">
                </form>
                <form method="get" action="editar-estampa">
                    <input type="hidden" name="estampa_id" value="<%= e.getId_estampa() %>">
                    <input type="submit" value="Editar" class="btn-editar">
                </form>
            </td>
        </tr>
        <%  }} %>
    </table>
    <div class="btn-wrapper">
        <form method="get" action="nova-estampa.jsp">
            <input type="submit" value="Cadastrar nova estampa" class="btn-add">
        </form>
    </div>
</div>

</body>
</html>
