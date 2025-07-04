<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nova Peça</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/nova-peca.css">
</head>
<body>

<div class="form-container">
    <h2>Cadastrar Nova Peça</h2>

    <form method="post" action="${pageContext.request.contextPath}/nova-peca">
        <label>Tipo:</label><br>
        <select name="tipo" required>
            <option value="">Selecione</option>
            <option value="Camiseta">Camiseta</option>
            <option value="Moletom">Moletom</option>
            <option value="Casaco">Casaco</option>
        </select><br>

        <label>Tamanho:</label><br>
        <select name="tamanho" required>
            <option value="">Selecione</option>
            <option value="PP">PP</option>
            <option value="P">P</option>
            <option value="M">M</option>
            <option value="G">G</option>
            <option value="GG">GG</option>
            <option value="EXG">EXG</option>
        </select><br>

        <label>Cor:</label><br>
        <select name="cor" required>
            <option value="">Selecione</option>
            <option value="Preto">Preto</option>
            <option value="Branco">Branco</option>
            <option value="Amarelo">Amarelo</option>
            <option value="Azul marinho">Azul marinho</option>
            <option value="Off white">Off white</option>
            <option value="Vermelho">Vermelho</option>
            <option value="Roxo">Roxo</option>
        </select><br>

        <label>Quantidade:</label><br>
        <input type="number" name="quantidade" required><br>

        <div class="btn-group">
            <a href="${pageContext.request.contextPath}/dashboard" class="btn-voltar">Voltar</a>
            <input type="submit" value="Salvar" class="btn-salvar">
        </div>
    </form>
</div>

</body>
</html>
