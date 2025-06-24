<%@ page import="org.example.journeyspring.model.Colecao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Colecao colecao = (Colecao) request.getAttribute("colecao");
    String erro = (String) request.getAttribute("erro");

    if (colecao == null) {
        response.sendRedirect("dashboard");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Coleção</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/editar-colecao.css">
</head>
<body>

<div class="form-container">
    <h2>Editar Coleção</h2>

    <form action="editar-colecao" method="post">
        <input type="hidden" name="id" value="<%= colecao.getId_colecao() %>">

        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome" value="<%= colecao.getNome() %>" required>

        <label for="data_inicio">Data Início:</label>
        <input type="date" name="data_inicio" id="data_inicio" value="<%= colecao.getData_inicio() %>" required>

        <label for="data_fim">Data Fim:</label>
        <input type="date" name="data_fim" id="data_fim"
               value="<%= colecao.getData_fim() != null ? colecao.getData_fim() : "" %>">

        <% if (erro != null) { %>
        <p class="mensagem-erro" id="erro-msg"><%= erro %></p>
        <script>
            setTimeout(() => {
                const msg = document.getElementById('erro-msg');
                if (msg) msg.style.display = 'none';
            }, 5000);
        </script>
        <% } %>

        <div class="btn-group">
            <a href="dashboard" class="btn-voltar">Voltar</a>
            <input type="submit" value="Salvar" class="btn-salvar">
        </div>
    </form>
</div>
</body>
</html>
