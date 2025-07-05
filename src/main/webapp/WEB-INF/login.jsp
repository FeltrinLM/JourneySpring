<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/login.css">
</head>
<body>

<div class="form-container">
    <h2>Entrar no sistema</h2>

    <form action="<%= request.getContextPath() %>/login" method="post">
        <label>Email:<br>
            <input type="email" name="email" required />
        </label><br>

        <label>Senha:<br>
            <input type="password" name="senha" required />
        </label><br>

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

        <div class="button-group">
            <button type="button" class="btn-voltar" onclick="window.location.href='<%= request.getContextPath() %>/usuario'">Voltar</button>
            <input type="submit" value="Entrar" class="btn-entrar" />
        </div>
    </form>
</div>

</body>
</html>
