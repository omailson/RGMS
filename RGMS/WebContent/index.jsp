<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.ufpe.cin.rgms.util.Properties"%>

<html>
<head>
	<title><%out.println(Properties.getProperty(this.getServletContext(),"login_page"));%></title>
	
	<link rel="shortcut icon" href="/favicon.ico">
	<link rel="stylesheet" type="text/css" href="stylelogin.css" media="screen"/>
</head>

<body>
		<% 
		if(session.getAttribute("usuario") != null){
			response.sendRedirect("home.jsp");
		}
		 %>

	<form id="login-form" action="Login.do" method="POST">
		<fieldset>
		
			<legend><%out.println(Properties.getProperty(this.getServletContext(),"login"));%></legend>
			
			<label for="email"><%out.println(Properties.getProperty(this.getServletContext(),"e_mail"));%></label>
			<input type="text" id="email" name="email"/>
			<div class="clear"></div>
			
			<label for="senha"><%out.println(Properties.getProperty(this.getServletContext(),"senha"));%></label>
			<input type="password" id="senha" name="senha"/>
			<div class="clear"></div>
			
			<label for="remember_me" style="padding: 0;"><%out.println(Properties.getProperty(this.getServletContext(),"lembrar"));%></label>
			<input type="checkbox" id="remember_me" style="position: relative; top: 3px; margin: 0; " name="remember_me"/>
			<div class="clear"></div>
			
			<br>
			
			<input type="submit" style="margin: -20px 0 0 287px;" class="button" name="commit" value="Log in"/>	
			
			<br>
			
			<%
				Object loginSuccess = request.getAttribute("loginsuccess");
				
				if(loginSuccess != null){
					if(!((Boolean) loginSuccess)){
						out.print(
						"<div id=\"invalidpassword\">" +
							"<p>"+Properties.getProperty(this.getServletContext(),"login_invalido")+"</p>" +
						"</div>"
						);
					}
				}
			 %>
			
		</fieldset>
	</form>
	
	
	
</body>
</html>