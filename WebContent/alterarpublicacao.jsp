<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="br.ufpe.cin.rgms.publicacao.MapeamentoTipo"%>
<%@page import="br.ufpe.cin.rgms.membro.modelo.Membro"%>
<%@page import="br.ufpe.cin.rgms.publicacao.modelo.Publicacao"%>
<%@page import="br.ufpe.cin.rgms.publicacao.modelo.ArtigoConferencia"%>
<%@page import="br.ufpe.cin.rgms.publicacao.modelo.ArtigoPeriodico"%>
<%@page import="br.ufpe.cin.rgms.publicacao.modelo.PublicacaoPosGraduacao"%>
<%@page import="br.ufpe.cin.rgms.membro.modelo.Estudante"%>
<%@page import="java.util.List"%>
<%@page import="br.ufpe.cin.rgms.util.Properties"%>
<%@page import="java.util.regex.Pattern"%>

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
		<h2><%out.println(Properties.getProperty(this.getServletContext(),"alterar_publicacao"));%></h2>
		<% 
		
		Publicacao publicacao = ((Publicacao) request.getAttribute("publicacaoalterar"));
		if(publicacao==null){
			System.out.println("T�tulo nulo ");
		}
		%>
		
		<p><%out.println(Properties.getProperty(this.getServletContext(),"alterar_dados"));%></p>
		
		<FORM name="alterarpublicacao" action="AlterarDadosPublicacao.do" method="post" enctype="multipart/form-data">
    		<p>
    			<LABEL for="titulo"><%out.println(Properties.getProperty(this.getServletContext(),"titulo"));%> </LABEL>
            	<INPUT onkeypress="return noenter();" type="text" name="titulo" size="90" value="<% out.print(publicacao.getTitulo()); %>">
    			
    			<LABEL for="ano"> <%out.println(Properties.getProperty(this.getServletContext(),"ano"));%> </LABEL>
              	<INPUT onkeypress="return noenter();" type="text" name="ano" size="4" value="<% out.print(publicacao.getAno()); %>">
            </p>
            <p>
    			<LABEL for="autoresmembros"><%out.println(Properties.getProperty(this.getServletContext(),"autores_membros"));%> </LABEL>
              	<INPUT onkeypress="return noenter();" type="text" name="autoresmembros" size="90" value="<% List<Membro> lista = publicacao.getAutores();
    				if(lista.isEmpty()){
    					out.print("-");	
    				}
    				for (int x = 0; x < lista.size(); x++) {
    					Membro membro = lista.get(x);
    					out.println(membro.getNome() + " "+ membro.getSobrenome());
    				}; %>">
              	
              	<LABEL for="autoresnaomembros"> <%out.println(Properties.getProperty(this.getServletContext(),"autores_nao_membros"));%> </LABEL>
              	<INPUT onkeypress="return noenter();" type="text" name="autoresnaomembros" size="90" value="<% List<String> naomembro = publicacao.getAutoresNaoMembros();
    				if(!naomembro.isEmpty()){
    					for (int y = 0; y < naomembro.size(); y++) {
    						out.println(naomembro.get(y));
    					}					
    				}
    				else{
    					out.println("-");
    				} %>">
                
                <LABEL for="pdffile"><%out.println(Properties.getProperty(this.getServletContext(),"pdf"));%> </LABEL>
              	<input onkeypress="return noenter();" type="hidden" name="MAX_FILE_SIZE" value="500" />
				<input name="pdffile" type="file" />
                 	
            </p>  	
			<p> 
				<% if (publicacao.getTipo().equals(MapeamentoTipo.CONFERENCIA)){%> 
	    			<LABEL for="conferencia" id="labelconferencia"> <%out.println(Properties.getProperty(this.getServletContext(),"conferencia"));%> </LABEL>
	            	<INPUT onkeypress="return noenter();"  type="text" name="conferencia" size="80" value="<%if(publicacao instanceof ArtigoConferencia) out.print(((ArtigoConferencia)publicacao).getConferencia()); %>">  	
	            
	            	<LABEL for="paginasconf" id="labelpaginasconf"> <%out.println(Properties.getProperty(this.getServletContext(),"paginas"));%> </LABEL>
	            	<INPUT onkeypress="return noenter();" type="text" name="paginasconf" size="27" value="<%if(publicacao instanceof ArtigoConferencia) out.print(((ArtigoConferencia)publicacao).getPaginas()); %>">
	            
	            	<LABEL for="mes" id="labelmes"> <%out.println(Properties.getProperty(this.getServletContext(),"mes"));%> </LABEL>
	            	<INPUT onkeypress="return noenter();" type="text" name="mes" size="10" value="<%if(publicacao instanceof ArtigoConferencia) out.print(((ArtigoConferencia)publicacao).getMes()); %>">
	            <%}%>  	
	       </p>  
	       <p> 
	       		<% if (publicacao.getTipo().equals(MapeamentoTipo.PERIODICO)){%>
    				<LABEL for="jornal" id="labeljornal"> <%out.println(Properties.getProperty(this.getServletContext(),"jornal"));%> </LABEL>
              		<INPUT onkeypress="return noenter();"  type="text" name="jornal" size="31" value="<%if(publicacao instanceof ArtigoPeriodico) out.print(((ArtigoPeriodico)publicacao).getJornal()); %>">
                	<LABEL for="volume" id="labelvolume"> <%out.println(Properties.getProperty(this.getServletContext(),"volume"));%> </LABEL>
              		<INPUT onkeypress="return noenter();" type="text" name="volume" size="27" value="<%if(publicacao instanceof ArtigoPeriodico) out.print(((ArtigoPeriodico)publicacao).getVolume()); %>">
              		<LABEL for="numero" id="labelnumero"> <%out.println(Properties.getProperty(this.getServletContext(),"numero"));%> </LABEL>
              		<INPUT onkeypress="return noenter();" type="text" name="numero" size="27" value="<%if(publicacao instanceof ArtigoPeriodico) out.print(((ArtigoPeriodico)publicacao).getNumero()); %>">
              		<LABEL for="paginas" id="labelpaginas"> <%out.println(Properties.getProperty(this.getServletContext(),"paginas"));%> </LABEL>
              		<INPUT onkeypress="return noenter();" type="text" name="paginas" size="10" value="<%if(publicacao instanceof ArtigoPeriodico) out.print(((ArtigoPeriodico)publicacao).getPaginas()); %>">
              	<%}%>
            </p>              
            <p>
            	<% if (publicacao.getTipo().equals(MapeamentoTipo.POSGRADUACAO)){%>
              		<LABEL for="universidade" id="labeluniversidade"> <%out.println(Properties.getProperty(this.getServletContext(),"universidade"));%> </LABEL>
              		<INPUT onkeypress="return noenter();" type="text" name="universidade" size="50" value="<%if(publicacao instanceof PublicacaoPosGraduacao) out.print(((PublicacaoPosGraduacao)publicacao).getUniversidade()); %>">
                	<LABEL for="mesdefesa" id="labelmesdefesa"> <%out.println(Properties.getProperty(this.getServletContext(),"mes"));%> </LABEL>
              		<INPUT onkeypress="return noenter();" type="text" name="mesdefesa" size="10" value="<%if(publicacao instanceof PublicacaoPosGraduacao) out.print(((PublicacaoPosGraduacao)publicacao).getMes()); %>">					
				<%}%>
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

	function habilitaCampos(){
		if((document.alterarpublicacao.tipo.value == "Artigo em Peri�dicos e Revistas") ||
				(document.alterarpublicacao.tipo.value != "Artigo em Confer�ncia") ||
				(document.alterarpublicacao.tipo.value != "P�s-Gradua��o")){
		    
			document.alterarpublicacao.conferencia.disabled = true;
			document.alterarpublicacao.conferencia.style.visibility="hidden";
			document.getElementById('labelconferencia').style.visibility = "hidden";
			
			document.alterarpublicacao.paginasconf.disabled = true;
			document.alterarpublicacao.paginasconf.style.visibility="hidden";
			document.getElementById('labelpaginasconf').style.visibility = "hidden";
			
			document.alterarpublicacao.mes.disabled = true;
			document.alterarpublicacao.mes.style.visibility="hidden";
			document.getElementById('labelmes').style.visibility = "hidden";
			
			document.alterarpublicacao.universidade.disabled = true;
			document.alterarpublicacao.universidade.style.visibility="hidden";
			document.getElementById('labeluniversidade').style.visibility = "hidden";
			
			document.alterarpublicacao.mesdefesa.disabled = true;
			document.alterarpublicacao.mesdefesa.style.visibility="hidden";
			document.getElementById('labelmesdefesa').style.visibility = "hidden";

			document.alterarpublicacao.universidade.disabled = true;
			document.alterarpublicacao.universidade.style.visibility="hidden";
			document.getElementById('labeluniversidade').style.visibility = "hidden";
			
			document.alterarpublicacao.mesdefesa.disabled = true;
			document.alterarpublicacao.mesdefesa.style.visibility="hidden";
			document.getElementById('labelmesdefesa').style.visibility = "hidden";	

			document.alterarpublicacao.nivel.disabled = true;
			document.alterarpublicacao.nivel.style.visibility="hidden";
			document.getElementById('labelnivel').style.visibility = "hidden";	

			document.alterarpublicacao.jornal.disabled = false;
			document.alterarpublicacao.jornal.style.visibility="visible";
			document.getElementById('labeljornal').style.visibility = "visible";
			
			document.alterarpublicacao.volume.disabled = false;
			document.alterarpublicacao.volume.style.visibility="visible";
			document.getElementById('labelvolume').style.visibility = "visible";
			
			document.alterarpublicacao.numero.disabled = false;
			document.alterarpublicacao.numero.style.visibility="visible";
			document.getElementById('labelnumero').style.visibility = "visible";
			
			document.alterarpublicacao.paginas.disabled = false;
			document.alterarpublicacao.paginas.style.visibility="visible";
			document.getElementById('labelpaginas').style.visibility = "visible";
		
	    }	
		else{
		if((document.alterarpublicacao.tipo.value == "Artigo em Confer�ncia") ||
				(document.alterarpublicacao.tipo.value != "Artigo em Peri�dicos e Revistas") ||
				(document.alterarpublicacao.tipo.value != "P�s-Gradua��o")){
			
			document.alterarpublicacao.jornal.disabled = true;
			document.alterarpublicacao.jornal.style.visibility="hidden";
			document.getElementById('labeljornal').style.visibility = "hidden";
			
			document.alterarpublicacao.volume.disabled = true;
			document.alterarpublicacao.volume.style.visibility="hidden";
			document.getElementById('labelvolume').style.visibility = "hidden";
			
			document.alterarpublicacao.numero.disabled = true;
			document.alterarpublicacao.numero.style.visibility="hidden";
			document.getElementById('labelnumero').style.visibility = "hidden";
			
			document.alterarpublicacao.paginas.disabled = true;
			document.alterarpublicacao.paginas.style.visibility="hidden";
			document.getElementById('labelpaginas').style.visibility = "hidden";
		    
			document.alterarpublicacao.universidade.disabled = true;
			document.alterarpublicacao.universidade.style.visibility="hidden";
			document.getElementById('labeluniversidade').style.visibility = "hidden";
			
			document.alterarpublicacao.mesdefesa.disabled = true;
			document.alterarpublicacao.mesdefesa.style.visibility="hidden";
			document.getElementById('labelmesdefesa').style.visibility = "hidden";

			document.alterarpublicacao.universidade.disabled = true;
			document.alterarpublicacao.universidade.style.visibility="hidden";
			document.getElementById('labeluniversidade').style.visibility = "hidden";
			
			document.alterarpublicacao.mesdefesa.disabled = true;
			document.alterarpublicacao.mesdefesa.style.visibility="hidden";
			document.getElementById('labelmesdefesa').style.visibility = "hidden";	

			document.alterarpublicacao.nivel.disabled = true;
			document.alterarpublicacao.nivel.style.visibility="hidden";
			document.getElementById('labelnivel').style.visibility = "hidden";	

			document.alterarpublicacao.conferencia.disabled = false;
			document.alterarpublicacao.conferencia.style.visibility="visible";
			document.getElementById('labelconferencia').style.visibility = "visible";
			
			document.alterarpublicacao.paginasconf.disabled = false;
			document.alterarpublicacao.paginasconf.style.visibility="visible";
			document.getElementById('labelpaginasconf').style.visibility = "visible";
			
			document.alterarpublicacao.mes.disabled = false;
			document.alterarpublicacao.mes.style.visibility="visible";
			document.getElementById('labelmes').style.visibility = "visible";
		
	    }	}
	    
	    }

	function validarCampos(){
		 if (document.alterarpublicacao.titulo.value.length==0){ 
	      	 	alert("<%Properties.getProperty(servletContext,"js_titulo_invalid"); %>");
	      	 	document.alterarpublicacao.titulo.focus();
	      	 
	      		return false; 
	   		}
	   		
	        if (document.alterarpublicacao.ano.value.length==0){ 
	      	 	alert("<%Properties.getProperty(servletContext,"js_ano_invalid"); %>");
	      	 	document.alterarpublicacao.ano.focus();
	      	 
	      		return false; 
	   		}
	   		
	        if (document.alterarpublicacao.autoresmembros.value.length==0){ 
	      	 	alert("<%Properties.getProperty(servletContext,"js_autores_invalid"); %>");
	      	 	document.alterarpublicacao.autoresmembros.focus();
	      	 
	      		return false; 
	   		}

	        if (document.alterarpublicacao.autoresnaomembros.value.length==0){ 
	      	 	alert("<%Properties.getProperty(servletContext,"js_autores_invalid"); %>");
	      	 	document.alterarpublicacao.autoresnaomembros.focus();
	      	 
	      		return false; 
	   		}
		if( document.alterarpublicacao.tipo.value=="Artigo em Confer�ncia"){
			if (document.alterarpublicacao.conferencia.value.length==0){ 
	      	 	alert("<%Properties.getProperty(servletContext,"js_conferencia_invalid"); %>");
	      	 	document.alterarpublicacao.conferencia.focus();
	      	 
	      	 	return false; 
	   		} 
	   		if (document.alterarpublicacao.paginasconf.value.length==0){ 
	      	 	alert("<%Properties.getProperty(servletContext,"js_paginas_invalid"); %>");
	      	 	document.alterarpublicacao.paginasconf.focus();
	      	 
	      	 	return false; 
	   		}
	   		if (document.alterarpublicacao.mes.value.length==0){ 
	      	 	alert("<%Properties.getProperty(servletContext,"js_mes_invalid"); %>");
	      	 	document.alterarpublicacao.mes.focus();
	      	 
	      	 	return false; 
	   		}

		}
		else{
			if(document.alterarpublicacao.tipo.value=="Artigo em Peri�dicos e Revistas"){
				if (document.alterarpublicacao.jornal.value.length==0){ 
		      	 	alert("<%Properties.getProperty(servletContext,"js_jornal_invalid"); %>");
		      	 	document.alterarpublicacao.jornal.focus();
		      	 
		      		return false; 
		   		}
		        if(document.alterarpublicacao.volume.value.length==0){  
		            alert("<%Properties.getProperty(servletContext,"js_volume_invalid"); %>");  
		            document.alterarpublicacao.volume.focus();  
		            
		            return false;
		        }
		        if (document.alterarpublicacao.numero.value.length==0){ 
		      	 	alert("<%Properties.getProperty(servletContext,"js_numero_invalid"); %>");
		      	 	document.alterarpublicacao.numero.focus();
		      	 
		      		return false; 
		   		}
		   		if (document.alterarpublicacao.paginas.value.length==0){ 
		      	 	alert("<%Properties.getProperty(servletContext,"js_paginas_invalid"); %>");
		      	 	document.alterarpublicacao.paginas.focus();
		      	 
		      		return false; 
		   		}
			}
			else{
				if(document.alterarpublicacao.tipo.value == "P�s-Gradua��o"){
					if(document.alterarpublicacao.universidade.value.length==0){  
			            alert("<%Properties.getProperty(servletContext,"js_universidade_invalid"); %>");  
			            document.alterarpublicacao.universidade.focus();  
			            
			            return false;
			        }
			        if (document.alterarpublicacao.mesdefesa.value.length==0){ 
			      	 	alert("<%Properties.getProperty(servletContext,"js_mes_invalid"); %>");
			      	 	document.alterarpublicacao.mesdefesa.focus();
			      	 
			      		return false; 
			   		}
			    }
			}		
		}
   		document.alterarpublicacao.submit();
	}
</script>

</body>

</html>
