<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
     <link rel="stylesheet" href="register.css">
</head>
<body>
    <h2>Registration</h2>
    
     <div class="con">
         
         <form action="RegistrationServlet" method="post" class="frm">
             <label class="my">Full Name:</label><br>
        <input type="text" name="fullname" required><br>
        <label class="my">Email:</label><br>
        <input type="email" name="email" required><br>
        <label class="my">Username:</label><br>
        <input type="text" name="username" required><br>
        <label class="my">Password:</label><br>
        <input type="password" name="password" required><br>
        <button type="submit" value="Register" id="submit"> Register
        </button>
    </form>
     </div>
</body>
</html>
