<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="br.ufpe.cin.rgms.projeto.modelo.Projeto"%>
<%@page import="java.util.List"%>
<%@page import="br.ufpe.cin.rgms.util.Properties"%>
<html>

<% ServletContext servletContext = getServletContext(); %>

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
			<a href="membros.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"membros"));%></a>
			<a class="active" href="projetos.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"projetos"));%></a>
			<a href="publicacoes.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"publicacoes"));%></a>
			<a href="contato.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"contato"));%></a>
		</div>
	</div>
		
	
	<div id="contentarea">
		<% 
		
		Projeto projeto = (Projeto) request.getAttribute("projetoconsulta");
		
		out.print("<h2>" + Properties.getProperty(this.getServletContext(),"projeto_detalhado") + " - " + Properties.getProperty(this.getServletContext(),"id") + ": " + projeto.getId() + "</h2><br>");
		%>
		
		<table align=center>
		<tr><td>
		<%
		out.print("<table align=left>");
		out.print("<tr>");
		out.print("<td>"+Properties.getProperty(this.getServletContext(),"nome")+" " + projeto.getNome() + "</td>");
		out.print("<td>"+Properties.getProperty(this.getServletContext(),"descricao")+" " + projeto.getDescricao() + "</td>");
		out.print("</tr>");
		
		out.print("</table>");
		%>
		</td></tr>
		
		<tr><td>
		<%
		out.print("<table align=left>");
		
		out.print("</table>");
		 %>	 
		 </td></tr>
		 <tr><td>
		
		<table align=center>
  			<tr>
    			<td>
    				<form name="formAlterar" method="get" action="AlterarProjeto.do">
    				<input type="hidden" name="projeto" value="<% out.print(projeto.getNome()); %>">
    				<input value=<%out.println(Properties.getProperty(this.getServletContext(),"button_alterar"));%> type="submit">
    				</form>
    			</td>
    			<td>
    				<form name="formRemover" method="get" action="RemoverProjeto.do">
    					<input type="hidden" name="projeto" value="<% out.print(projeto.getNome()); %>">
    					<input value=<%out.println(Properties.getProperty(this.getServletContext(),"button_remover"));%> onclick="confirmarRemocao();" type="button">	
    				</form>
    			</td>
  			</tr>
		</table>
		</td></tr>
		</table>
	</div>
	
	<div id="footer">
		<a href="http://www.free-css-templates.com/">Designed by Free CSS Templates</a>, Thanks to <a href="http://www.openwebdesign.org/">professional web design</a>
	</div>

	</div>
</div>
<script type="text/javascript">

	function confirmarRemocao(){ 
   		if (confirm("<%Properties.getProperty(this.getServletContext(),"projeto_rem_conf");%>")){ 
   			document.formRemover.submit();
   		}
	}
</script>

</body>

</html>