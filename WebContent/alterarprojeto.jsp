<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="br.ufpe.cin.rgms.projeto.modelo.Projeto"%>
<%@page import="java.util.regex.Pattern"%><html>
<%@page import="br.ufpe.cin.rgms.util.Properties"%>

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
		</div>
	</div>
	
	<div id="contentarea">
		<h2><%out.println(Properties.getProperty(this.getServletContext(),"alterar"));%></h2>
		<% Projeto projeto = ((Projeto) request.getAttribute("projetoalterar"));
		 %>
		
		<p><%out.println(Properties.getProperty(this.getServletContext(),"alterar_dados_projeto"));%></p>
		
		<FORM name="alterarprojeto" action="AlterarDadosProjeto.do" method="post" enctype="multipart/form-data">
    		<p>
    			<LABEL for="nome"><%out.println(Properties.getProperty(this.getServletContext(),"nome"));%> </LABEL>
            	<INPUT onkeypress="return noenter();" type="text" name="nome" size="30" value="<% out.print(projeto.getNome()); %>">
              	<input type="hidden" name="nomeoriginal" value="<% out.print(projeto.getNome()); %>">        	
    			
    			<LABEL for="descricao"> <%out.println(Properties.getProperty(this.getServletContext(),"descricao"));%> </LABEL>
              	<INPUT onkeypress="return noenter();" type="text" name="descricao" maxlength="300" value="<% out.print(projeto.getDescricao()); %>">
              	
            </p>
            
            <p>
              	<br>
    			<INPUT onclick="validarCampos();" name="cadastrar" value=<%out.println(Properties.getProperty(this.getServletContext(),"concluir"));%> type="submit">
    			<INPUT value=<%out.println(Properties.getProperty(this.getServletContext(),"limpar"));%> type="reset">
    		</p>
 		</FORM>
	</div>
	
		
	
	<div id="footer">
		<a href="http://www.free-css-templates.com/">Designed by Free CSS Templates</a>, Thanks to <a href="http://www.openwebdesign.org/">professional web design</a>
	</div>

	</div>
</div>

<script type="text/javascript">

	function noenter() {
  		return !(window.event && window.event.keyCode == 13);
	}


	function validarCampos(){ 
		if (document.alterarprojeto.nome.value.length==0){ 
      	 	alert("<%Properties.getProperty(servletContext,"js_nome_invalid");%>");
      	 	document.alterarprojeto.nome.focus();
      	 
      	 	return false; 
   		} 
   		if (document.alterarprojeto.descricao.value.length==0){ 
      	 	alert("<%Properties.getProperty(servletContext,"js_descricao_invalid");%>");
      	 	document.alterarprojeto.descricao.focus();
      	 
      	 	return false; 
   		}
   		
   		document.alterarprojeto.submit();
	}
</script>

</body>

</html>
