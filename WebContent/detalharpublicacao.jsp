<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="br.ufpe.cin.rgms.publicacao.modelo.Publicacao"%>
<%@page import="br.ufpe.cin.rgms.membro.modelo.Membro"%>
<%@page import="java.util.List"%>
<%@page import="br.ufpe.cin.rgms.publicacao.modelo.ArtigoConferencia"%>
<%@page import="br.ufpe.cin.rgms.publicacao.modelo.ArtigoPeriodico"%>
<%@page import="br.ufpe.cin.rgms.publicacao.modelo.PublicacaoPosGraduacao"%>
<%@page import="br.ufpe.cin.rgms.util.Properties"%>

<% ServletContext servletContext = getServletContext(); %>


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
			<a href="membros.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"membros"));%></a>
			<a class="active" href="publicacoes.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"publicacoes"));%></a>
			<a href="contato.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"contato"));%></a>
		</div>
	</div>
		
	
	<div id="contentarea">
		<%

			Publicacao publicacao = (Publicacao) request.getAttribute("publicacaoconsulta");
			
			out.print("<h2>" + Properties.getProperty(this.getServletContext(),"pub") + " - " + Properties.getProperty(this.getServletContext(),"id") + ": " + publicacao.getId() + "</h2><br>");
			
			if (publicacao == null) {
				out.print(Properties.getProperty(this.getServletContext(),"erro_recuperar_publicacao"));
			} else {
				out.print("<table align=left>");
				out.print("<tr>");
				out.print("<td>" + Properties.getProperty(this.getServletContext(),"titulo") + publicacao.getTitulo() + "</td>");
				out.print("</tr>");
				out.print("<tr>");
				out.print("<td>" + Properties.getProperty(this.getServletContext(),"ano") + publicacao.getAno() + "</td>");
				out.print("</tr>");

				out.print("<tr>");
				out.print("<td>"+Properties.getProperty(this.getServletContext(),"autores_membros")+"</td>");
				List<Membro> lista = publicacao.getAutores();
				if(!lista.isEmpty()){
					for (int x = 0; x < lista.size(); x++) {
						Membro membro = lista.get(x);
						out.println("<td>" + membro.getNome() + " "+ membro.getSobrenome() + "</td>");
					}
				}
				out.print("</tr>");
				
				out.print("<tr>");
				out.print("<td>"+Properties.getProperty(this.getServletContext(),"autores_nao_membros")+"</td>");
				List<String> naomembro = publicacao.getAutoresNaoMembros();
				if(!naomembro.isEmpty()){
					for (int y = 0; y < naomembro.size(); y++) {
						out.println("<td>" + naomembro.get(y) + "</td>");
					}					
				}
				else{
					out.println("<td> - </td>");
				}
				out.print("</tr>");

				out.print("<tr>");
				if(publicacao.getPdf()!=null){
				%>
				<a href="Pdf.do?publicacao=<%out.print(publicacao.getId()); %>">PDF</a>
				<%
				}
				
				%>
				<a href="Bibtex.do?publicacao=<%out.print(publicacao.getId()); %>">Bibtex</a>
				<%
				out.print("</tr>");
				out.print("</table>");
				if (publicacao instanceof ArtigoConferencia) {
					ArtigoConferencia artigoConf = (ArtigoConferencia) publicacao;
					out.print("<table align=center>");
					out.print("<tr>");
					out.print("<td>"+Properties.getProperty(this.getServletContext(),"conferencia")+" "+artigoConf.getConferencia()+ "</td>");
					out.print("</tr>");
					out.print("<tr>");
					out.print("<td>"+Properties.getProperty(this.getServletContext(),"paginas")+" "+ artigoConf.getPaginas()+ "</td>");
					out.print("</tr>");
					out.print("<tr>");
					out.print("<td>"+Properties.getProperty(this.getServletContext(),"mes")+" "+ artigoConf.getMes() + "</td>");
					out.print("</tr>");
					out.print("</table>");
					
				}
				if (publicacao instanceof ArtigoPeriodico) {
					ArtigoPeriodico artigoPeriodico = (ArtigoPeriodico) publicacao;
					out.print("<table align=center>");
					out.print("<tr>");
					out.print("<td>"+Properties.getProperty(this.getServletContext(),"jornal")+" "+ artigoPeriodico.getJornal()+ "</td>");
					out.print("</tr>");
					out.print("<tr>");
					out.print("<td>"+Properties.getProperty(this.getServletContext(),"volume")+" "+ artigoPeriodico.getVolume()+ "</td>");
					out.print("</tr>");
					out.print("<tr>");
					out.print("<td>"+Properties.getProperty(this.getServletContext(),"numero")+" "+ artigoPeriodico.getNumero()+ "</td>");
					out.print("</tr>");
					out.print("<tr>");
					out.print("<td>"+Properties.getProperty(this.getServletContext(),"paginas")+" "+ artigoPeriodico.getPaginas()+ "</td>");
					out.print("</tr>");
					out.print("</table>");
				}
				if (publicacao instanceof PublicacaoPosGraduacao) {
					PublicacaoPosGraduacao publicacaoPos = (PublicacaoPosGraduacao) publicacao;
					out.print("<table align=center>");
					out.print("<tr>");
					out.print("<td>"+Properties.getProperty(this.getServletContext(),"universidade")+" "+ publicacaoPos.getUniversidade() + "</td>");
					out.print("</tr>");
					out.print("<tr>");
					out.print("<td>"+Properties.getProperty(this.getServletContext(),"mes")+" "+ publicacaoPos.getMes() + "</td>");
					out.print("</tr>");
					out.print("<tr>");
					out.print("<td>"+Properties.getProperty(this.getServletContext(),"nivel")+" "+ publicacaoPos.getNivel()+ "</td>");
					out.print("</tr>");
					out.print("</table>");
				}
			}
		%>

		<br><br><br><br><br><br><br><br><br><br><br><br>

		<table align=center>
  			<tr>
    			<td><form name="formAlterar" method="get" action="AlterarPublicacao.do">
    				<input type="hidden" name="publicacao" value="<%out.print(publicacao.getId());%>">
    				<input value=<%out.println(Properties.getProperty(this.getServletContext(),"button_alterar"));%> type="submit">
    				</form>
    			</td>
    			<td>
    				<form name="formRemover" action="RemoverPublicacao.do">
    					<input type="hidden" name="publicacao" value="<%if(publicacao!=null){out.print(publicacao.getTitulo());}%>">
    					<input value=<%out.println(Properties.getProperty(this.getServletContext(),"button_remover"));%> onclick="confirmarRemocao();" type="button">	
    				</form></td>
  			</tr>
		</table>
	</div>
	
	<div id="footer">
		<a href="http://www.free-css-templates.com/">Designed by Free CSS Templates</a>, Thanks to <a href="http://www.openwebdesign.org/">professional web design</a>
	</div>

	</div>
</div>
<script type="text/javascript">

	function confirmarRemocao(){ 
   		if (confirm("<%Properties.getProperty(servletContext,"publicacao_rem_conf");%>")){ 
   			document.formRemover.submit();
   		}
	}
   
	</script>

</body>

</html>