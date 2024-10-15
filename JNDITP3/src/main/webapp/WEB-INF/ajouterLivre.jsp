<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Livre</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            line-height: 1.6;
            color: #333;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f4f4f4;
        }
        h1 {
            color: #2c3e50;
            text-align: center;
        }
        .container {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-top: 10px;
        }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #3498db;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin-top: 20px;
        }
        input[type="submit"]:hover {
            background-color: #2980b9;
        }
        .back-link {
            display: inline-block;
            margin-top: 20px;
            color: #3498db;
            text-decoration: none;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Ajouter un Livre</h1>
        <form action="LivreManager" method="post">
            <input type="hidden" name="action" value="add">
            
            <label for="isbn">ISBN:</label>
            <input type="text" id="isbn" name="isbn" required>
            
            <label for="titre">Titre:</label>
            <input type="text" id="titre" name="titre" required>
            
            <label for="auteur">Auteur:</label>
            <input type="text" id="auteur" name="auteur" required>
            
            <label for="prix">Prix:</label>
            <input type="number" id="prix" name="prix" step="0.01" required>
            
            <label for="anneePublication">Année de publication:</label>
            <input type="number" id="anneePublication" name="anneePublication" required>
            
            <input type="submit" value="Ajouter le livre">
        </form>
        <a href="LivreManager" class="back-link">Retour à la liste des livres</a>
    </div>
</body>
</html>
