<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.journeyspring.model.Estampa" %>
<%@ page import="org.example.journeyspring.model.Colecao" %>

<%
    Estampa estampa = (Estampa) request.getAttribute("estampa");
    List<Colecao> colecoes = (List<Colecao>) request.getAttribute("colecoes");

    if (estampa == null || colecoes == null) {
        response.sendRedirect("dashboard");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Estampa</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/editar-estampa.css">
</head>
<body>

<div class="form-container">
    <h2>Editar Estampa</h2>

    <form method="post" action="editar-estampa">
        <input type="hidden" name="estampa_id" value="<%= estampa.getId_estampa() %>">

        <label>Nome:</label>
        <input type="text" name="nome" value="<%= estampa.getNome() %>" required>

        <label>Quantidade:</label>
        <input type="number" name="quantidade" value="<%= estampa.getQuantidade() %>" required>

        <label>Coleção:</label>
        <select name="id_colecao" required>
            <% for (Colecao c : colecoes) { %>
            <option value="<%= c.getId_colecao() %>" <%= c.getId_colecao() == estampa.getId_colecao() ? "selected" : "" %>>
                <%= c.getNome() %>
            </option>
            <% } %>
        </select>

        <div class="btn-container">
            <a href="dashboard" class="btn-voltar">Voltar</a>
            <button type="submit" class="btn-salvar">Salvar alterações</button>
        </div>
    </form>
</div>

</body>
</html>
