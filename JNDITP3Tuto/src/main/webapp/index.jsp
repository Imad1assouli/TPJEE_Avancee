<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Check Database Connection</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f0f0f0;
        }
        .container {
            background-color: white;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
        }
        .button {
            display: inline-block;
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            font-size: 16px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }
        .button:hover {
            background-color: #45a049;
        }
        #result {
            margin-top: 20px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Check Database Connection</h1>
        <a href="checkConnection" class="button">Test Connection</a>
        <div id="result"></div>
    </div>

    <script>
        document.querySelector('.button').addEventListener('click', function(e) {
            e.preventDefault();
            fetch('checkConnection')
                .then(response => response.text())
                .then(data => {
                    document.getElementById('result').innerHTML = data;
                })
                .catch(error => {
                    document.getElementById('result').innerHTML = 'Error: ' + error;
                });
        });
    </script>
</body>
</html>
