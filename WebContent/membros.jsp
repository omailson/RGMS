<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="br.ufpe.cin.rgms.membro.modelo.Membro"%>
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
	<a href="publicacoes.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"publicacoes"));%></a>
	<a href="contato.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"contato"));%></a>
</div>
</div>


<div id="contentarea">
<h2><%out.println(Properties.getProperty(this.getServletContext(),"lista_membros"));%></h2>

<% 
				Object formsFieldsObject = request.getAttribute("campos");
				
				HashMap<String,String> formsFields = null;
				
				if(formsFieldsObject != null){
					formsFields = (HashMap<String,String>) formsFieldsObject;
				}
			 %>
<div id="filter">

<FORM name="filtrarmembro" action="FiltrarMembros.do" method="POST">
<p><LABEL for="nome"><%out.println(Properties.getProperty(this.getServletContext(),"nome"));%></LABEL> <INPUT
	onkeypress="return noenter();" type="text" name="nome" size="29"
	value="<%
            	if(formsFields != null && formsFields.get("nome") != null) out.print(formsFields.get("nome"));%>">

<LABEL for="lastname"><%out.println(Properties.getProperty(this.getServletContext(),"sobrenome"));%></LABEL> <INPUT
	onkeypress="return noenter();" type="text" name="sobrenome" size="29"
	value="<%
              	if(formsFields != null && formsFields.get("sobrenome") != null) out.print(formsFields.get("sobrenome"));%>">

</p>	

<p><LABEL for="universidade"><%out.println(Properties.getProperty(this.getServletContext(),"universidade"));%></LABEL> <INPUT
	onkeypress="return noenter();" type="text" name="universidade"
	size="25"
	value="<%
              	if(formsFields != null && formsFields.get("universidade") != null) out.print(formsFields.get("universidade"));%>">

<LABEL for="departamento"><%out.println(Properties.getProperty(this.getServletContext(),"departamento"));%></LABEL> <INPUT
	onkeypress="return noenter();" type="text" name="departamento"
	size="25"
	value="<%
              	if(formsFields != null && formsFields.get("departamento") != null) out.print(formsFields.get("departamento"));%>">

<br>

<LABEL for="vinculo"><%out.println(Properties.getProperty(this.getServletContext(),"vinculo"));%></LABEL> <select
	onkeypress="return noenter();" name="tipo">
	<option></option>
	<option><%out.println(Properties.getProperty(this.getServletContext(),"estudante"));%></option>
	<option><%out.println(Properties.getProperty(this.getServletContext(),"pesquisador"));%></option>
	<option><%out.println(Properties.getProperty(this.getServletContext(),"outros"));%></option>
</select> <input type="hidden" name="vinculoHidden"
	value="<%
              	if(formsFields != null && formsFields.get("tipo") != null) out.print(formsFields.get("tipo"));%>">

<LABEL for="situacao"><%out.println(Properties.getProperty(this.getServletContext(),"situacao"));%></LABEL> <select
	onkeypress="return noenter();" name="situacao">
	<option></option>
	<option><%out.println(Properties.getProperty(this.getServletContext(),"ativo"));%></option>
	<option><%out.println(Properties.getProperty(this.getServletContext(),"inativo"));%></option>
</select> <input type="hidden" name="situacaoHidden"
	value="<%
              	if(formsFields != null && formsFields.get("situacao") != null) out.print(formsFields.get("situacao"));%>">

<LABEL for="email"><%out.println(Properties.getProperty(this.getServletContext(),"e_mail"));%> </LABEL> <INPUT
	onkeypress="return noenter();" type="text" name="email" size="29"
	value="<%
              	if(formsFields != null && formsFields.get("email") != null) out.print(formsFields.get("email"));%>">

</p>
<p><INPUT  value=<%out.println(Properties.getProperty(this.getServletContext(),"filtrar"));%> type="submit"> <INPUT 
	value=<%out.println(Properties.getProperty(this.getServletContext(),"limpar"));%> type="reset"></p>
</FORM>

</div>

<table align=center>
	<% 
		
		Object membrosObject = request.getAttribute("membros");
		List<Membro> members = new ArrayList<Membro>();
		
		if(membrosObject != null){
			members = (List<Membro>) membrosObject;
		}
		else{
			members = Facade.getInstance().getMembros();
		}
		
		Membro membroAnterior = null;
		
		out.print("<div id=\"filtermessage\">");
		
		if(members.isEmpty() && membrosObject != null){
			out.print("<br><br><p>"+Properties.getProperty(this.getServletContext(),"sem_membros_criterios")+"</p>");
		}
		else if(members.isEmpty()){
			out.print("<br><br><p>"+Properties.getProperty(this.getServletContext(),"sem_membros")+"</p>");
		}
		
		out.print("</div>");
		
		for(Membro membro : members){
			if(membroAnterior == null || !membroAnterior.getTipo().equals(membro.getTipo())){
				out.print("<tr>");
				
				if(membro.getTipo().equals(Membro.ESTUDANTE)){
					out.print("<td><br><h3>"+Properties.getProperty(this.getServletContext(),"estudantes")+"</h3></td>");
				}
				else if(membro.getTipo().equals(Membro.PESQUISADOR)){
					out.print("<td><br><h3>"+Properties.getProperty(this.getServletContext(),"pesquisadores")+"</h3></td>");
				}
				else if(membro.getTipo().equals(Membro.OUTROS)){
					out.print("<td><br><h3>"+Properties.getProperty(this.getServletContext(),"outros")+"</h3></td>");
				}
				
				out.print("</tr>");
			}
			out.print("<tr>");
			out.print("<td><a href=DetalharMembro.do?membro=" + membro.getEmail() + ">" + membro.getNome() + " " + membro.getSobrenome() + "</a></td>");
			out.print("<td>" + membro.getTipo() + "</td>");
			out.print("<td>" + membro.getDepartamento() + "</td>");
			out.print("<td>" + membro.getUniversidade() + "</td>");
			out.print("</tr>");
			
			membroAnterior = membro;
		}
		
		
		 %>
</table>

<br>
<br>
<br>

<table align=center>
	<tr>
		<td>
		<form id="botaoAdicionar" action="adicionarmembro.jsp"><input
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

<script type="text/javascript">
	if(document.filtrarmembro.vinculoHidden.value == "Estudante"){
		document.filtrarmembro.tipo.selectedIndex = 1;
	}
	else if(document.filtrarmembro.vinculoHidden.value == "Pesquisador"){
		document.filtrarmembro.tipo.selectedIndex = 2;
	}
	else if(document.filtrarmembro.vinculoHidden.value == "Outros"){
		document.filtrarmembro.tipo.selectedIndex = 3;
	}
	else{
		document.filtrarmembro.tipo.selectedIndex = 0;
	}
	
	if(document.filtrarmembro.situacaoHidden.value == "Ativo"){
		document.filtrarmembro.situacao.selectedIndex = 1;
	}
	else if(document.filtrarmembro.situacaoHidden.value == "Inativo"){
		document.filtrarmembro.situacao.selectedIndex = 2;
	}
	else{
		document.filtrarmembro.situacao.selectedIndex = 0;
	}
</script>

</body>



</html>

