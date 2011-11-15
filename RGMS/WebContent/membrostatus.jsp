<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="br.ufpe.cin.rgms.membro.modelo.Membro"%>
<%@page import="br.ufpe.cin.rgms.util.Properties"%>

<html>

<head>
<title><%out.println(Properties.getProperty(this.getServletContext(),"time_pesquisa"));%></title>
<LINK REL=StyleSheet HREF="style.css" TYPE="text/css" MEDIA=screen>
</head>

<body>

<div id="page">
	<div id="header"></div>
	
	<div id="mainarea">
	<div id="sidebar">
		<div id="headerleft">
			<h1><a href="home.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"time_pesquisa"));%></a></h1>
		</div>
		<div id="menulinks">
			<a href="home.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"home"));%></a>
			<a class="active" href="membros.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"membros"));%></a>
			<a href="projetos.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"projetos"));%></a>
			<a href="publicacoes.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"publicacoes"));%></a>
			<a href="contato.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"contato"));%></a>
		</div>
	</div>
		
	<div id="contentarea">
	
		<br><br><br><br><br><br>
		<% out.print("<center><h2>" + request.getAttribute("membrostatus") + "</h2></center>"); %>
		
		
	</div>
	
	
	
	
	<div id="footer">
		<a href="http://www.free-css-templates.com/">Designed by Free CSS Templates</a>, Thanks to <a href="http://www.openwebdesign.org/">professional web design</a>
	</div>

	</div>
</div>

</body>

</html>