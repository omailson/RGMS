<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.ufpe.cin.rgms.membro.modelo.Membro"%>
<%@page import="br.ufpe.cin.rgms.projeto.modelo.Projeto"%>
<%@page import="br.ufpe.cin.rgms.publicacao.modelo.Publicacao"%>
<%@page import="br.ufpe.cin.rgms.util.Properties"%>


<%@page import="java.util.List"%>
<%@page import="br.ufpe.cin.rgms.Facade"%>
<%@page import="br.ufpe.cin.rgms.util.Properties"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>

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
    <a href="projetos.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"projetos"));%></a>
    <a class="active" href="publicacoes.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"publicacoes"));%></a>
    <a href="contato.jsp"><%out.println(Properties.getProperty(this.getServletContext(),"contato"));%></a>

		</div>
	</div>
	
	<div id="contentarea">
		<h2><%out.println(Properties.getProperty(this.getServletContext(),"cadastro"));%></h2>
		<p><%out.println(Properties.getProperty(this.getServletContext(),"preenche_publicacao"));%></p>
		
		<FORM name="cadastropublicacao" action="AdicionarPublicacao.do" method="post" enctype="multipart/form-data">
    		<p>  	
              	<LABEL for="tipo"> <%out.println(Properties.getProperty(this.getServletContext(),"tipo_publicacao"));%></LABEL>
              	<select onkeypress="return noenter();" name="tipo">
              		<option value="CONF"><%out.println(Properties.getProperty(this.getServletContext(),"artigo_conferencia"));%></option>
					<option value="ARTI"><%out.println(Properties.getProperty(this.getServletContext(),"artigo_periodico"));%></option>
					<option value="PGRA"><%out.println(Properties.getProperty(this.getServletContext(),"pos"));%></option>
				</select>
			</p>   	
    		<p>
    			<LABEL for="titulo"><%out.println(Properties.getProperty(this.getServletContext(),"titulo"));%></LABEL>
            	<INPUT onkeypress="return noenter();" type="text" name="titulo" size="90">
    			
    			<LABEL for="ano"> <%out.println(Properties.getProperty(this.getServletContext(),"ano"));%> </LABEL>
              	<INPUT onkeypress="return noenter();" type="text" name="ano" size="4">
              	
            </p>
            <p>
              	
    			<LABEL for="autoresmembros" id="labelautoresmembros"> <%out.println(Properties.getProperty(this.getServletContext(),"autores_membros"));%> </LABEL>
              	<INPUT onkeypress="return noenter();" type="text" name="autoresmembros" size="50">
              	<br>
              	<LABEL for="autoresnaomembros"> <%out.println(Properties.getProperty(this.getServletContext(),"autores_nao_membros"));%> </LABEL>
              	<INPUT onkeypress="return noenter();" type="text" name="autoresnaomembros" size="50">
            </p>
            <p>
                <LABEL for="pdffile"><%out.println(Properties.getProperty(this.getServletContext(),"pdf"));%> </LABEL>
              	<input onkeypress="return noenter();" type="hidden" name="MAX_FILE_SIZE" value="500" />
				<input name="pdffile" type="file" />
            </p>
            
            <p>  	
              	<LABEL for="projeto"> <%out.println(Properties.getProperty(this.getServletContext(),"projeto"));%></LABEL>
              	<select onkeypress="return noenter();" name="projeto">
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
			
			out.print("<option>" + projeto.getNome() + "</option>");
			
		}
		
		
		 %>
              	
				</select>
			<p> 
	    		<LABEL for="conferencia" id="labelconferencia"> <%out.println(Properties.getProperty(this.getServletContext(),"conferencia")+ "*");%> </LABEL>
	            <INPUT onkeypress="return noenter();"  type="text" name="conferencia" size="80">  	
	            <br>
	            <LABEL for="paginasconf" id="labelpaginasconf"> <%out.println(Properties.getProperty(this.getServletContext(),"paginas")+ "*");%> </LABEL>
	            <INPUT onkeypress="return noenter();" type="text" name="paginasconf" size="27">
	            <LABEL for="mes" id="labelmes"> <%out.println(Properties.getProperty(this.getServletContext(),"mes")+ "*");%> </LABEL>
	            <INPUT onkeypress="return noenter();" type="text" name="mes" size="10">	
	       </p>

		    <p> 
    			<LABEL for="jornal" id="labeljornal"> <%out.println(Properties.getProperty(this.getServletContext(),"jornal")+ "**");%> </LABEL>
              	<INPUT onkeypress="return noenter();"  type="text" name="jornal" size="31">
                <LABEL for="volume" id="labelvolume"> <%out.println(Properties.getProperty(this.getServletContext(),"volume")+ "**");%> </LABEL>
              	<INPUT onkeypress="return noenter();" type="text" name="volume" size="27">
              	<LABEL for="numero" id="labelnumero"> <%out.println(Properties.getProperty(this.getServletContext(),"numero")+ "**");%> </LABEL>
              	<INPUT onkeypress="return noenter();" type="text" name="numero" size="27">
              	<LABEL for="paginas" id="labelpaginas"> <%out.println(Properties.getProperty(this.getServletContext(),"paginas")+ "**");%> </LABEL>
              	<INPUT onkeypress="return noenter();" type="text" name="paginas" size="10">
            </p>
            <p>
              	<LABEL for="universidade" id="labeluniversidade"> <%out.println(Properties.getProperty(this.getServletContext(),"universidade")+ "***");%> </LABEL>
              	<INPUT onkeypress="return noenter();" type="text" name="universidade" size="50">
            
                <LABEL for="mesdefesa" id="labelmesdefesa"> <%out.println(Properties.getProperty(this.getServletContext(),"mes")+ "***");%> </LABEL>
              	<INPUT onkeypress="return noenter();" type="text" name="mesdefesa" size="10">					
			
				<LABEL for="nivel" id="labelnivel"> <%out.println(Properties.getProperty(this.getServletContext(),"nivel")+ "***");%> </LABEL>
              	<select onkeypress="return noenter();" name="nivel">
              		<option ><%out.println(Properties.getProperty(this.getServletContext(),"mestrado"));%></option>
					<option><%out.println(Properties.getProperty(this.getServletContext(),"doutorado"));%></option>
				</select>					
			</p>			
			
	        <br>
            <p>
    			<INPUT onclick="validarCampos();" name="cadastrar" value=<%out.println(Properties.getProperty(this.getServletContext(),"cadastrar"));%> type="button">
    			<INPUT value=<%out.println(Properties.getProperty(this.getServletContext(),"limpar"));%> type="reset">
    		
    		</p>
    	
    		<p>*<%out.println(Properties.getProperty(this.getServletContext(),"nova_conferencia_aviso"));%></p>

    		<p>**<%out.println(Properties.getProperty(this.getServletContext(),"novo_periodico_aviso"));%></p>

    		<p>**<%out.println(Properties.getProperty(this.getServletContext(),"novo_pos_aviso"));%></p>
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
		if (document.cadastropublicacao.titulo.value.length==0){ 
      	 	alert("<%Properties.getProperty(servletContext,"js_titulo_invalid"); %>");
      	 	document.cadastropublicacao.titulo.focus();
      	 
      		return false; 
   		}
   		
        if (document.cadastropublicacao.ano.value.length==0){ 
      	 	alert("<%Properties.getProperty(servletContext,"js_ano_invalid"); %>");
      	 	document.cadastropublicacao.ano.focus();
      	 
      		return false; 
   		}
   		
        if (document.cadastropublicacao.autoresmembros.value.length==0){ 
      	 	alert("<%Properties.getProperty(servletContext,"js_autores_invalid"); %>");
      	 	document.cadastropublicacao.autoresmembros.focus();
      	 
      		return false; 
   		}

        if (document.cadastropublicacao.autoresnaomembros.value.length==0){ 
      	 	alert("<%Properties.getProperty(servletContext,"js_autores_invalid"); %>");
      	 	document.cadastropublicacao.autoresnaomembros.focus();
      	 
      		return false; 
   		}
	if( document.cadastropublicacao.tipo.value=="Artigo em Conferï¿½ncia"){
		if (document.cadastropublicacao.conferencia.value.length==0){ 
      	 	alert("<%Properties.getProperty(servletContext,"js_conferencia_invalid"); %>");
      	 	document.cadastropublicacao.conferencia.focus();
      	 
      	 	return false; 
   		} 
   		if (document.cadastropublicacao.paginasconf.value.length==0){ 
      	 	alert("<%Properties.getProperty(servletContext,"js_paginas_invalid"); %>");
      	 	document.cadastropublicacao.paginasconf.focus();
      	 
      	 	return false; 
   		}
   		if (document.cadastropublicacao.mes.value.length==0){ 
      	 	alert("<%Properties.getProperty(servletContext,"js_mes_invalid"); %>");
      	 	document.cadastropublicacao.mes.focus();
      	 
      	 	return false; 
   		}

	}
	else{
		if(document.cadastropublicacao.tipo.value=="Artigo em Periï¿½dicos e Revistas"){
			if (document.cadastropublicacao.jornal.value.length==0){ 
	      	 	alert("<%Properties.getProperty(servletContext,"js_jornal_invalid"); %>");
	      	 	document.cadastropublicacao.jornal.focus();
	      	 
	      		return false; 
	   		}
	        if(document.cadastropublicacao.volume.value.length==0){  
	            alert("<%Properties.getProperty(servletContext,"js_volume_invalid"); %>");  
	            document.cadastropublicacao.volume.focus();  
	            
	            return false;
	        }
	        if (document.cadastropublicacao.numero.value.length==0){ 
	      	 	alert("<%Properties.getProperty(servletContext,"js_numero_invalid"); %>");
	      	 	document.cadastropublicacao.numero.focus();
	      	 
	      		return false; 
	   		}
	   		if (document.cadastropublicacao.paginas.value.length==0){ 
	      	 	alert("<%Properties.getProperty(servletContext,"js_paginas_invalid"); %>");
	      	 	document.cadastropublicacao.paginas.focus();
	      	 
	      		return false; 
	   		}
		}
		else{
			if(document.cadastropublicacao.tipo.value == "Pï¿½s-Graduaï¿½ï¿½o"){
				if(document.cadastropublicacao.universidade.value.length==0){  
		            alert("<%Properties.getProperty(servletContext,"js_universidade_invalid"); %>");  
		            document.cadastropublicacao.universidade.focus();  
		            
		            return false;
		        }
		        if (document.cadastropublicacao.mesdefesa.value.length==0){ 
		      	 	alert("<%Properties.getProperty(servletContext,"js_mes_invalid"); %>");
		      	 	document.cadastropublicacao.mesdefesa.focus();
		      	 
		      		return false; 
		   		}
		    }
		}		
	}
		document.cadastropublicacao.submit();
	}
</script>

</body>

</html>
