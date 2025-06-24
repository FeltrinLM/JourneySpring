<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nova Coleção</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/nova-colecao.css">
</head>
<body>

<div class="form-container">
    <h2>Cadastrar Nova Coleção</h2>

    <form method="post" action="nova-colecao">
        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome" required>

        <label for="data_inicio">Data Início:</label>
        <input type="date" name="data_inicio" id="data_inicio"
               value="<%= request.getAttribute("data_inicio") != null ? request.getAttribute("data_inicio") : "" %>" required>

        <label for="data_fim">Data Fim:</label>
        <input type="date" name="data_fim" id="data_fim"
               value="<%= request.getAttribute("data_fim") != null ? request.getAttribute("data_fim") : "" %>">

        <% String erro = (String) request.getAttribute("erro");
            if (erro != null) { %>
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
