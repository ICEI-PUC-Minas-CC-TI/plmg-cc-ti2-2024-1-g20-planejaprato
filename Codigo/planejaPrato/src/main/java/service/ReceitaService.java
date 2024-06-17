package service;
import java.util.List;
import java.util.Collections;

import dao.ReceitaDAO;
import model.Receita;

public class ReceitaService {
    public ReceitaDAO receitaDAO;

    public ReceitaService() {
        // Inicializa a instância de ReceitaDAO
        receitaDAO = new ReceitaDAO(); 
    }

    public void cadastraReceita(String nome, String modoDePreparo, String ingredientes,String img_url) {
        	// Cria um novo objeto Receita e seta os valores no construtor
            Receita receita = new Receita();
            receita.setNome(nome);
            receita.setIngredientes(ingredientes);
            receita.setModoDePreparo(modoDePreparo);
            receita.setImagem(img_url);
          
            receitaDAO.cadastrarReceita(receita.getNome(),receita.getIngredientes(),receita.getModoDePreparo(),receita.getImagem());
    }
    
    // Apenas chamar a função que esta na DAO, para retornar a lista e acessar na Aplicação
    public List<Receita> retornarTodasReceitas(){
    	return receitaDAO.getTodasReceitas();
    }
    
    // Função para printar no Console todas as receitas do SQL, chamar apos chamar listarReceitas
    public void printarReceitas(List<Receita> receitas) {
    	for (Receita receita : receitas) {
            System.out.println("Nome da receita: " + receita.getNome());
            System.out.println("Ingredientes: " + receita.getIngredientes());
            System.out.println("Modo de preparo: " + receita.getModoDePreparo());
            System.out.println("Url da imagem: " + receita.getImagem());

            System.out.println("--------------------------");
        }
    }
    
    //adiciona receitas do bd para o front end yourRecipes
    public String replaceYourRecipes(String html) {
    	String receita = new String();
    	String novaDiv = new String();
    	List<Receita> todasReceitas = retornarTodasReceitas();
    	
    	 for ( int i = 0; i < todasReceitas.size(); i++ ) {
    		// Criar a string com a Div
             receita = "<div class=\"card\" style=\"width: 18rem; margin: 10px;\">\r\n"
 		    		+ "<img src=\"img/"+ todasReceitas.get(i).imagem +"\" class=\"card-img-top\" alt=\"..\" style=\"height: 16rem; width: 100%;\">\r\n" + "<div class=\"card-body\">\r\n"
             		+ "<h5 class=\"card-title\">Nome da receita</h5>\r\n"
             		//+ "<p class=\"card-text\">Seu modo de preparo</p>\r\n" 
             		//<!-- Formulário que envia o nome da receita -->
             		+ "<form action=\"/ver-receita\" method=\"post\" id=\"viewRecipeForm\">"
                    + "<input type=\"hidden\" name=\"recipeName\" value=\"Nome da receita\">"
             		+ "<button type=\"submit\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#recipeModal\">Ver Receita</button>\r\n"
             		+ "</form>"
             		+ "</div>\r\n"
             		+ "</div>";
             
             System.out.println(todasReceitas.get(i).imagem);
    		    
             // Editar a receita
             receita = receita.replace("Nome da receita", todasReceitas.get(i).getNome());
             // receita = receita.replace("Seu modo de preparo", todasReceitas.get(i).getModoDePreparo());
             
             // Concatenar varias Divs
             novaDiv = novaDiv + receita;
             }
             
             // Editar o front end com o Java
             html = html.replace("<CaixaSuasReceitas>", novaDiv);
             
             // Retorna a string como HTML
             return html;
    }
    
    // alterar features pelas receitas do bd
    public String replaceFeatures(String html) {
    	String receita = new String();
    	String novaDiv = new String();
    	List<Receita> todasReceitas = retornarTodasReceitas();
    	
    	//
    	int loop = todasReceitas.size();
    	loop = loop > 4 ? 4 : loop;
    	loop--;
    	// loop para retornar as 10 ultimas receitas
    	int pos = todasReceitas.size() - 1;
    	while ( loop >= 0 ) {
    		// codigo
    		receita = "<div class=\"card\" style=\"width: 18rem; margin: 10px;\">\r\n"
		    		+ "<img src=\"img/"+ todasReceitas.get(pos).imagem +"\" class=\"card-img-top\" alt=\"..\" style=\"height: 16rem; width: 100%;\">\r\n" + "<div class=\"card-body\">\r\n"
             		+ "<h5 class=\"card-title\">Nome da receita</h5>\r\n"
             		//+ "<p class=\"card-text\">Seu modo de preparo</p>\r\n" 
             		//<!-- Formulário que envia o nome da receita -->
             		+ "<form action=\"/ver-receita\" method=\"post\" id=\"viewRecipeForm\">"
                    + "<input type=\"hidden\" name=\"recipeName\" value=\"Nome da receita\">"
             		+ "<button type=\"submit\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#recipeModal\">Ver Receita</button>\r\n"
             		+ "</div>\r\n"
             		+ "</form>"
             		+ "</div>";
    		
    		// Editar a receita
    		receita = receita.replace("Nome da receita", todasReceitas.get(pos).getNome());
    		receita = receita.replace("Seu modo de preparo", todasReceitas.get(pos).getModoDePreparo());
    		
    		// Concatenar varias Divs
    		novaDiv = novaDiv + receita;
    	  		
    		loop--;
    		pos--;
    	}
            
             // Editar o front end com o Java
             html = html.replace("<CaixaSuasReceitas>", novaDiv);
             
             // Retorna a string com o HTML
             return html;
    }

  
    // Pesquisar receita
    public String pesquisaReceita(String html, String nome) {
		// String auxiliares para criar a nova div
    	String receita = new String();
    	String novaDiv = new String();
    	
    	// Lista com todas as receitas do BD para pesquisa
    	List<Receita> todasReceitas = retornarTodasReceitas();

	 // Loop por todas as receitas para encontrar a procurada
   	 for ( int i = 0; i < todasReceitas.size(); i++ ) {
			// codigo
         receita = "<div class=\"card\" style=\"width: 18rem; margin: 10px;\">\r\n"
		    		+ "<img src=\"img/"+ todasReceitas.get(i).imagem +"\" class=\"card-img-top\" alt=\"..\" style=\"height: 16rem; width: 100%;\">\r\n"	+ "<div class=\"card-body\">\r\n"
          		+ "<h5 class=\"card-title\">Nome da receita</h5>\r\n"
          		//+ "<p class=\"card-text\">Seu modo de preparo</p>\r\n" 
          		//<!-- Formulário que envia o nome da receita -->
          		+ "<form action=\"/ver-receita\" method=\"post\" id=\"viewRecipeForm\">"
                 + "<input type=\"hidden\" name=\"recipeName\" value=\"Nome da receita\">"
                 + "</form>"
          		+ "<button type=\"submit\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#recipeModal\">Ver Receita</button>\r\n"
          		+ "</div>\r\n"
          		+ "</div>";

			// Editar a receita
			if(nome == todasReceitas.get(i).getNome()) {
			// Encontrou a receita procurada, e adiciou na div
			receita = receita.replace("Nome da receita", todasReceitas.get(i).getNome());
			//receita = receita.replace("Seu modo de preparo", todasReceitas.get(i).getModoDePreparo());
			
			// Sair do loop
			i = todasReceitas.size();			
			}

			// Concatenar varias Divs
			novaDiv = novaDiv + receita;
		}

		// Editar o front end com o Java
		html = html.replace("<CaixaSuasReceitas>", novaDiv);

		// Retorna a string como HTML
		return html;
	}

	
	// Adicionar os ingredientes do BD
	public String replaceIngredientes(String html, Receita receita) {
		// Replace para editar os textos
		html = html.replace("Nome da receita", receita.getNome());
		html = html.replace("Nome dos ingredientes",  receita.getIngredientes());
		html = html.replace("Seu modo de preparo", receita.getModoDePreparo());	
		
		// Retorna a string com o HTML
		return html;
	}
	
    // Retornar a Receita em formato de instancia
    public Receita procurarReceita(String nome) {
    	List<Receita> receitas = retornarTodasReceitas();
    	Receita resp = new Receita();
    	
    	// Procurar a receita
    	for (Receita receita : receitas) {
            if ( receita.getNome().equals(nome) ) {
            	resp = receita;
            	break;
            }
        }
    	
    	return resp;
    }

    //Pesquisar receitas pelo input e adicionar ao front
    public String replacePesquisaFeatureRecipes(String html, String input){    	
    	// String auxiliares para criar a nova div
    	String receita = new String();
    	String novaDiv = new String();
    	
    	// Lista com todas as receitas do BD para pesquisa
    	List<Receita> todasReceitas = retornarTodasReceitas();
    	
    	for ( int i = 0; i < todasReceitas.size(); i++ ) {
    		// Verificar se o nome da receita começa com o input de pesquisa do usuario
    		if ( todasReceitas.get(i).getNome().contains(input)) {
    			// Criar a string com a Div
                receita = "<div class=\"card\" style=\"width: 18rem; margin: 10px;\">\r\n"
    		    		+ "<img src=\"img/"+ todasReceitas.get(i).imagem +"\" class=\"card-img-top\" alt=\"..\" style=\"height: 16rem; width: 100%;\">\r\n" + "<div class=\"card-body\">\r\n"
                		+ "<h5 class=\"card-title\">Nome da receita</h5>\r\n"
                		//+ "<p class=\"card-text\">Seu modo de preparo</p>\r\n" 
                		//<!-- Formulário que envia o nome da receita -->
                		+ "<form action=\"/ver-receita\" method=\"post\" id=\"viewRecipeForm\">"
                       + "<input type=\"hidden\" name=\"recipeName\" value=\"Nome da receita\">"
                		+ "<button type=\"submit\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#recipeModal\">Ver Receita</button>\r\n"
                		+ "</form>"
                		+ "</div>\r\n"
                		+ "</div>";
                
                // Editar a receita
                receita = receita.replace("Nome da receita", todasReceitas.get(i).getNome());
                //receita = receita.replace("Seu modo de preparo", todasReceitas.get(i).getModoDePreparo());
                
                // Concatenar varias Divs
                novaDiv = novaDiv + receita;
               }
    	}
    	
    	// Editar o front end com o Java
        html = html.replace("<DestaquesReceitas>", novaDiv);
        html = html.replace("Receitas em Destaque!", "Pesquisa por: " + input);
    	
        // Chamar funcao para editar o resto da pagina features como os NewRecipes e retornar tudo pronto
    	return replaceNewRecipes(html, 0);
    }

 // alterar features pelas receitas do bd
    public String replaceNewRecipes(String html, int teste) {
    	// String auxiliares para criar a nova div
    	String receita = new String();
    	String novaDiv = new String();
    	
    	// Lista com todas as receitas do BD para pesquisa
    	List<Receita> todasReceitas = retornarTodasReceitas();
    	
    	// verificar se tem menos de 4 
    	int loop = todasReceitas.size();
    	loop = loop > 4 ? 4 : loop;
    	loop--;
    	// loop para retornar as 4 ultimas receitas
    	int pos = todasReceitas.size() - 1;
    	
    	// Editar as receitas recentes
    	while ( loop >= 0 ) {
    		// codigo
            receita = "<div class=\"card\" style=\"width: 18rem; margin: 10px;\">\r\n"
		    		+ "<img src=\"img/"+ todasReceitas.get(pos).imagem +"\" class=\"card-img-top\" alt=\"..\" style=\"height: 16rem; width: 100%;\">\r\n" + "<div class=\"card-body\">\r\n"
             		+ "<h5 class=\"card-title\">Nome da receita</h5>\r\n"
             		//+ "<p class=\"card-text\">Seu modo de preparo</p>\r\n" 
             		//<!-- Formulário que envia o nome da receita -->
             		+ "<form action=\"/ver-receita\" method=\"post\" id=\"viewRecipeForm\">"
                    + "<input type=\"hidden\" name=\"recipeName\" value=\"Nome da receita\">"
             		+ "<button type=\"submit\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#recipeModal\">Ver Receita</button>\r\n"
             		+ "</div>\r\n"
             		+ "</form>"
             		+ "</div>";
    		
    		// Editar a receita
    		receita = receita.replace("Nome da receita", todasReceitas.get(pos).getNome());
    		//receita = receita.replace("Seu modo de preparo", todasReceitas.get(pos).getModoDePreparo());
    		
    		// Concatenar varias Divs
    		novaDiv = novaDiv + receita;
    	  		
    		loop--;
    		pos--;
    	}
    	// Editar o front end com o Java
        html = html.replace("<CaixaNovasReceitas>", novaDiv);
        
        if ( teste != 0 ) {
	        // Resetar Strings
	        receita = new String();
	        novaDiv = new String();
	        
	        // sortear para os destaques
	    	Collections.shuffle(todasReceitas);
	    	
	    	// verificar se tem menos de 4 
	    	loop = todasReceitas.size();
	    	loop = loop > 4 ? 4 : loop;
	    	loop--;
	    	// loop para retornar as 4 ultimas receitas
	    	pos = 0;
	    	
	    	// Editar as receitas destaques
	    	while ( loop >= 0 ) {
	    		// codigo
	            receita = "<div class=\"card\" style=\"width: 18rem; margin: 10px;\">\r\n"
    		    		+ "<img src=\"img/"+ todasReceitas.get(pos).imagem +"\" class=\"card-img-top\" alt=\"..\" style=\"height: 16rem; width: 100%;\">\r\n" + "<div class=\"card-body\">\r\n"
	             		+ "<h5 class=\"card-title\">Nome da receita</h5>\r\n"
	             		//+ "<p class=\"card-text\">Seu modo de preparo</p>\r\n" 
	             		//<!-- Formulário que envia o nome da receita -->
	             		+ "<form action=\"/ver-receita\" method=\"post\" id=\"viewRecipeForm\">"
	                    + "<input type=\"hidden\" name=\"recipeName\" value=\"Nome da receita\">"
	             		+ "<button type=\"submit\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#recipeModal\">Ver Receita</button>\r\n"
	             		+ "</div>\r\n"
	             		+ "</form>"
	             		+ "</div>";
	    		
	    		// Editar a receita
	    		receita = receita.replace("Nome da receita", todasReceitas.get(pos).getNome());
	    		//receita = receita.replace("Seu modo de preparo", todasReceitas.get(pos).getModoDePreparo());
	    		
	    		// Concatenar varias Divs
	    		novaDiv = novaDiv + receita;
	    	  		
	    		loop--;
	    		pos++;
	    	}
	    	
	            
	        // Editar o front end com o Java
	        html = html.replace("<DestaquesReceitas>", novaDiv);
    	}
             
        // Retorna a string com o HTML
        return html;
    }

}  