<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
     <link rel="stylesheet" href="login.css">
</head>
<body>
    <h2>Login</h2>
    <div class="con">
    <%-- Display error message if login fails --%>
   
    <form action="LoginServlet" method="post" class="frm">
        <label>Username :</label>
        <input type="text" name="username" required><br>
        <label>Password :</label>
        <input type="password" name="password" required><br>
        <button type="submit" value="Login" id="submit"> login
        </button>
    </form>
    <div>
    <h3>
     <% if (request.getParameter("error") != null) { %>
        <p style="color: red;">Invalid username or password.</p>
    <% } %>
    </h3>
    </div>
    </div>
    
</body>
</html>
