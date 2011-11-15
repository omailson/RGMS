<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="br.ufpe.cin.rgms.membro.modelo.Membro"%>
<%@page import="java.util.List"%>
<%@page import="br.ufpe.cin.rgms.util.Properties"%>
<%@page import="br.ufpe.cin.rgms.membro.modelo.Estudante"%>
<%@page import="br.ufpe.cin.rgms.publicacao.modelo.Publicacao"%>

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
    <a class="active" href="membros.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"membros"));%></a>
    <a href="projetos.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"projetos"));%></a>
    <a href="publicacoes.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"publicacoes"));%></a>
    <a href="contato.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"contato"));%></a>

		</div>
	</div>
		
	
	<div id="contentarea">
		<% 
		
		Membro membro = (Membro) request.getAttribute("membroconsulta");
		
		out.print("<h2>" + Properties.getProperty(this.getServletContext(),"membro_detalhado") + " - " + Properties.getProperty(this.getServletContext(),"id") + ": " + membro.getId() + "</h2><br>");
		%>
		
		<div id="foto">
			<img src="Foto.do?membro=<% out.print(membro.getEmail()); %>">
		</div>
		
		<table align=center>
		<tr><td>
		<%
		out.print("<table align=left>");
		out.print("<tr>");
		out.print("<td>"+Properties.getProperty(this.getServletContext(),"nome")+" " + membro.getNome() + "</td>");
		out.print("<td>"+Properties.getProperty(this.getServletContext(),"sobrenome")+" " + membro.getSobrenome() + "</td>");
		out.print("<td>"+Properties.getProperty(this.getServletContext(),"vinculo")+" " + membro.getTipo() + "</td>");
		out.print("</tr>");
		
		out.print("<tr>");
		out.print("<td>"+Properties.getProperty(this.getServletContext(),"universidade")+" " + membro.getUniversidade() + "</td>");
		out.print("<td>"+Properties.getProperty(this.getServletContext(),"departamento")+" " + membro.getDepartamento() + "</td>");
		out.print("<td>"+Properties.getProperty(this.getServletContext(),"situacao")+" " + membro.getSituacao() + "</td>");
		out.print("</tr>");
		
		out.print("<tr>");
		out.print("<td>"+Properties.getProperty(this.getServletContext(),"cidade")+" " + membro.getCidade() + "</td>");
		out.print("<td>"+Properties.getProperty(this.getServletContext(),"pais")+" " + membro.getPais() + "</td>");
		out.print("<td>"+Properties.getProperty(this.getServletContext(),"fone")+" " + membro.getTelefone() + "</td>");
		out.print("</tr>");
		out.print("</table>");
		%>
		</td></tr>
		
		<tr><td>
		<%
		out.print("<table align=left>");
		
		if(membro instanceof Estudante){
			Estudante estudante = (Estudante) membro;
			
			out.print("<tr>");
			out.print("<td>"+Properties.getProperty(this.getServletContext(),"orientador")+" " + estudante.getOrientador() + "</td>");
			out.print("<td>"+Properties.getProperty(this.getServletContext(),"co_orientador")+" " + estudante.getCoOrientador() + "</td>");
			out.print("</tr>");
		}
		
		out.print("<tr>");
		out.print("<td>"+Properties.getProperty(this.getServletContext(),"e_mail")+" " + membro.getEmail() + "</td>");
		out.print("<td>"+Properties.getProperty(this.getServletContext(),"web_site")+" " + membro.getWebsite() + "</td>");
		out.print("</tr>");
		
		out.print("<tr>");
		out.print("<td><br><b><h3>" + Properties.getProperty(this.getServletContext(),"publicacoes") + "</h3></b></td>");
		out.print("</tr>");
		
		
		Publicacao publicacaoAnterior = null;
		
		out.print("<div id=\"filtermessage\">");
		
		if(membro.getPublicacoes().isEmpty()){
			out.print("<tr><td><p>" + Properties.getProperty(this.getServletContext(),"sem_publicacoes") + "</p></td></tr>");
		}
		
		out.print("</div>");
		
		for(Publicacao publicacao : membro.getPublicacoes()){
			if(publicacaoAnterior == null || !publicacaoAnterior.getTipo().equals(publicacao.getTipo())){
				out.print("<tr>");
				out.print("</tr>");
			}
			out.print("<td><a href=DetalharPublicacao.do?publicacao=" + publicacao.getId() + "> "+ publicacao.getTitulo()+ "</a></td>");
			out.print("<td>" + publicacao.getAno()+ "</td>");
			
			publicacaoAnterior = publicacao;
		}
		out.print("</table>");
		 %>	 
		 </td></tr>
		 <tr><td>
		
		<table align=center>
  			<tr>
    			<td>
    				<form name="formAlterar" method="get" action="AlterarMembro.do">
    				<input type="hidden" name="membro" value="<% out.print(membro.getEmail()); %>">
    				<input value=<%out.println(Properties.getProperty(this.getServletContext(),"button_alterar"));%> type="submit">
    				</form>
    			</td>
    			<td>
    				<form name="formRemover" method="get" action="RemoverMembro.do">
    					<input type="hidden" name="membro" value="<% out.print(membro.getEmail()); %>">
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
   		if (confirm("<%Properties.getProperty(this.getServletContext(),"membro_rem_conf");%>")){ 
   			document.formRemover.submit();
   		}
	}
</script>

</body>

</html>