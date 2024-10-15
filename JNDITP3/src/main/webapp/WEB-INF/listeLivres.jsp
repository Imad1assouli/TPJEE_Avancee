<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Livres</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            line-height: 1.6;
            color: #333;
            max-width: 1200px;
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #e0e0e0;
        }
        th {
            background-color: #3498db;
            color: #ffffff;
        }
        tr:nth-child(even) {
            background-color: #f8f8f8;
        }
        a {
            display: inline-block;
            background-color: #3498db;
            color: #ffffff;
            text-decoration: none;
            padding: 8px 16px;
            border-radius: 4px;
            transition: background-color 0.3s ease;
            margin-right: 10px;
        }
        a:hover {
            background-color: #2980b9;
        }
        .add-book {
            margin-top: 20px;
        }
        .button-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Liste des Livres</h1>
        <table>
            <tr>
                <th>ISBN</th>
                <th>Titre</th>
                <th>Auteur</th>
                <th>Prix</th>
                <th>Année de publication</th>
                <th>Action</th>
            </tr>
            <c:forEach var="livre" items="${livres}">
                <tr>
                    <td>${livre.isbn}</td>
                    <td>${livre.titre}</td>
                    <td>${livre.auteur}</td>
                    <td>${livre.prix}</td>
                    <td>${livre.anneePublication}</td>
                    <td>
                        <a href="LivreManager?action=delete&isbn=${livre.isbn}">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="button-container">
            <a href="LivreManager?action=add">Ajouter un nouveau livre</a>
            <a href="${pageContext.request.contextPath}/">Retour à l'accueil</a>
        </div>
    </div>
</body>
</html>
