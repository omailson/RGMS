<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="br.ufpe.cin.rgms.membro.modelo.Vinculo"%>
<%@page import="br.ufpe.cin.rgms.membro.modelo.Membro"%>
<%@page import="br.ufpe.cin.rgms.projeto.modelo.Projeto"%>
<%@page import="java.util.List"%>
<%@page import="br.ufpe.cin.rgms.Facade"%>
<%@page import="br.ufpe.cin.rgms.util.Properties"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%><html>
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
<h2><%out.println(Properties.getProperty(this.getServletContext(),"projetos_pesquisa"));%></h2>

<% 
				Object formsFieldsObject = request.getAttribute("campos");
				
				HashMap<String,String> formsFields = null;
				
				if(formsFieldsObject != null){
					formsFields = (HashMap<String,String>) formsFieldsObject;
				}
			 %>
<div id="filter">

<FORM name="filtrarprojeto" action="FiltrarProjetos.do" method="POST">
<p><LABEL for="nome"><%out.println(Properties.getProperty(this.getServletContext(),"nome"));%></LABEL> <INPUT
	onkeypress="return noenter();" type="text" name="nome" size="29"
	value="<%
            	if(formsFields != null && formsFields.get("nome") != null) out.print(formsFields.get("nome"));%>">
</p>	


<p><INPUT  value=<%out.println(Properties.getProperty(this.getServletContext(),"filtrar"));%> type="submit"> <INPUT 
	value=<%out.println(Properties.getProperty(this.getServletContext(),"limpar"));%> type="reset"></p>
</FORM>

</div>

<table align=center>
	<% 
		
		Object projetosObject = request.getAttribute("projetos");
		List<Projeto> projects = new ArrayList<Projeto>();
		
		if(projetosObject != null){
			projects = (List<Projeto>) projetosObject;
		}
		else{
			projects = Facade.getInstance().getProjetos();
		}
		
		
		out.print("<div id=\"filtermessage\">");
		
		if(projects.isEmpty() && projetosObject != null){
			out.print("<br><br><p>"+Properties.getProperty(this.getServletContext(),"sem_projetos_criterios")+"</p>");
		}
		else if(projects.isEmpty()){
			out.print("<br><br><p>"+Properties.getProperty(this.getServletContext(),"sem_projetos")+"</p>");
		}
		
		out.print("</div>");
		
		for(Projeto projeto : projects){
			out.print("<tr>");
			out.print("<td><a href=DetalharProjeto.do?projeto=" + projeto.getNome() + ">" + projeto.getNome() + "</a></td>");
			out.print("<td>" + projeto.getDescricao() + "</td>");
			out.print("</tr>");
			
		}
		
		
		 %>
</table>

<br>
<br>
<br>

<table align=center>
	<tr>
		<td>
		<form id="botaoAdicionar" action="adicionarprojeto.jsp"><input
			value=<%out.println(Properties.getProperty(this.getServletContext(),"adicionar"));%> type="submit"></form>
		</td>
	</tr>
</table>
</div>



<div id="footer"><a href="http://www.free-css-templates.com/">Designed
by Free CSS Templates</a>, Thanks to <a href="http://www.openwebdesign.org/">professional
web design</a></div>

</div>
</div>


</body>



</html>

