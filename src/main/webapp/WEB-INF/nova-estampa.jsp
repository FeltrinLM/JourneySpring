<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.journeyspring.model.Usuario" %>
<%@ page import="org.example.journeyspring.model.Colecao" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp?erro=session_expired");
        return;
    }

    List<Colecao> colecoes = (List<Colecao>) request.getAttribute("colecoes");
    if (colecoes == null) {
        response.sendRedirect(request.getContextPath() + "/nova-estampa");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nova Estampa</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/nova-estampa.css">
</head>
<body>

<div class="form-container">
    <h2>Adicionar Nova Estampa</h2>

    <form action="${pageContext.request.contextPath}/nova-estampa" method="post">
        <label>Nome:</label>
        <input type="text" name="nome" required>

        <label>Quantidade:</label>
        <input type="number" name="quantidade" required>

        <label>Coleção:</label>
        <select name="id_colecao" required>
            <option value="">Selecione uma coleção</option>
            <% for (Colecao c : colecoes) { %>
            <option value="<%= c.getId_colecao() %>"><%= c.getNome() %></option>
            <% } %>
        </select>

        <div class="btn-container">
            <a href="${pageContext.request.contextPath}/dashboard" class="btn-voltar">Voltar</a>
            <button type="submit" class="btn-salvar">Cadastrar</button>
        </div>
    </form>
</div>

</body>
</html>
