<html>
<head>
    <title>
        login
    </title>
</head>
<body>
login page body
    <pre>${errorMessage}</pre>
    <!--post로 설정하지 않으면 url에 이름과 password가 노출된다.-->
    <form method="post">
        Name : <input type="text" name="name">
        Password : <input type="password" name = "password">
        <input type="submit">

    </form>
</body>
</html>