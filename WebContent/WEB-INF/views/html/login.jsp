<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/login.css" />
</head>

<body>
	<div id="register" class="main-container">
		<!--inline centering-->
		<h1>Register to view our movie catalog</h1>

		<div class="form-container">
			<!--Form has 2 uses, validating emails as well as taking user to landing page once fields are completed and user is "registered"-->
			<form id="form" method = "post" action="registerUser">
			
				<label for="name">Name:</label> 
				<input type="text" id="name" name = "name" required> 
				
				<label for="email">Email:</label>
				<!--regex in pattern for email validation-->
				<input type="email" id="email" name = "email" required
					pattern="[a-z0-9._%+-]+@[A-Za-z0-9.-]+\.[a-z]{2,}$"> 
					
				<label for="pwd">Pwd: (min 8 chars, 1 letter, 1 num)</label>
				<!--regex in pattern for password validation-->
				<input type="password" id="pwd" name="password" required pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"> 
				<br /> 
				<img id="popcorn-pic" alt="popcorn"
					src="<%=request.getContextPath()%>/resources/images/popcorn.jpg" />

				<!--Drop-down menu-->
				<label for="gender">Gender:</label> 
				<select name="gender" id="gender" required>
					<option value="Male">Male</option>
					<option value="Female">Female</option>
				</select> 
				<label for="dob">DOB:</label> 
				<input type="date" id=dob name = "dob" required>
				<br> 
				<input id="register-button" type="submit" value="register" />


			</form>
		</div>

		<h1 id="login-header">Login if you already have an account</h1>

		<div class="form-container">
			<form id="login-form" action="login" method = "post">

				<label for="email">Email:</label>
				<!--regex in pattern for email validation-->
				<input type="email" id="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" required> 
				
				<label for="pwd">Pwd:</label>
				<!--regex in pattern for password validation-->
				<input type="password" id="pwd" name="password" required>
				<br /> 
				<input id="login-button" type="submit" value="Login" />
			</form>
		</div>



	</div>


</body>
</html>
