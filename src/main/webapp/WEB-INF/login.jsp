<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/login.css">
</head>
<body>

<div class="form-container">
    <h2>Entrar no sistema</h2>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <label>Email:<br>
            <input type="email" name="email" required />
        </label><br>

        <label>Senha:<br>
            <input type="password" name="senha" required />
        </label><br>

        <% if (request.getAttribute("erro") != null) { %>
        <p class="mensagem-erro" id="erro-msg"><%= request.getAttribute("erro") %></p>
        <script>
            setTimeout(() => {
                const msg = document.getElementById('erro-msg');
                if (msg) msg.style.display = 'none';
            }, 5000);
        </script>
        <% } %>

        <div class="button-group">
            <button type="button" class="btn-voltar" onclick="window.location.href='${pageContext.request.contextPath}/usuario'">Voltar</button>
            <input type="submit" value="Entrar" class="btn-entrar" />
        </div>
    </form>
</div>

</body>
</html>
