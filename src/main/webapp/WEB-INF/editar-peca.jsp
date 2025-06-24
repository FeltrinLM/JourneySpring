<%@ page import="org.example.journeyspring.model.Peca" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Peca peca = (Peca) request.getAttribute("peca");
    if (peca == null) {
        response.sendRedirect("dashboard");
        return;
    }

    String[] tamanhos = {"PP", "P", "M", "G", "GG", "EXG"};
    String[] tipos = {"Camiseta", "Moletom", "Casaco"};
    String[] cores = {"Preto", "Branco", "Amarelo", "Azul marinho", "Off white", "Vermelho", "Roxo"};
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Peça</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/editar-peca.css">
</head>
<body>

<div class="form-container">
    <h2>Editar Peça</h2>

    <form method="post" action="editar-peca">
        <input type="hidden" name="peca_id" value="<%= peca.getPeca_id() %>">

        <label>Tipo:</label>
        <select name="tipo" required>
            <% for (String t : tipos) { %>
            <option value="<%= t %>" <%= t.equals(peca.getTipo()) ? "selected" : "" %>><%= t %></option>
            <% } %>
        </select>

        <label>Tamanho:</label>
        <select name="tamanho" required>
            <% for (String t : tamanhos) { %>
            <option value="<%= t %>" <%= t.equals(peca.getTamanho()) ? "selected" : "" %>><%= t %></option>
            <% } %>
        </select>

        <label>Cor:</label>
        <select name="cor" required>
            <% for (String c : cores) { %>
            <option value="<%= c %>" <%= c.equals(peca.getCor()) ? "selected" : "" %>><%= c %></option>
            <% } %>
        </select>

        <label>Quantidade:</label>
        <input type="number" name="quantidade" value="<%= peca.getQuantidade() %>" required>

        <div class="btn-container">
            <a href="dashboard" class="btn-voltar">Voltar</a>
            <button type="submit" class="btn-salvar">Salvar alterações</button>
        </div>
    </form>
</div>

</body>
</html>
