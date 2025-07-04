<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.lang.String" %>
<html>
<head>
    <title>Cadastro de Usuário</title>
    <link rel="stylesheet" href="../CSS/cadastro.css">
</head>
<body>

<div class="form-container">
    <h2>Bem-vindo! Crie sua conta ou utilize uma já existente</h2>

    <form action="usuario" method="post">
        <label>Nome:<br>
            <input type="text" name="nome" required />
        </label><br>

        <label>Email:<br>
            <input type="text" name="email" required />
        </label><br>

        <label>Senha:<br>
            <input type="password" name="senha" required />
        </label><br>

        <input type="submit" value="Cadastrar" />
    </form>

    <p>Já tem uma conta? <a href="login">Clique aqui para fazer login</a></p>

    <%
        String mensagem = (String) request.getAttribute("mensagem");
        if (mensagem != null) {
            String texto = mensagem.toLowerCase().contains("usuario_email_key") ? "Este Email já está em uso" : mensagem;
    %>
    <p class="mensagem-erro" id="erro-msg"><%= texto %></p>
    <script>
        setTimeout(() => {
            const msg = document.getElementById('erro-msg');
            if (msg) msg.style.display = 'none';
        }, 5000);
    </script>
    <% } %>

</div>

</body>
</html>
