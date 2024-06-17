package service;
import java.util.List;

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
    		    		+ "<img src=\"img/"+ todasReceitas.get(i).imagem +"\" class=\"card-img-top\" alt=\"..\" style=\"height: 16rem; width: 100%;\">\r\n"    		            + "<div class=\"card-body\">\r\n"
    		            + "<h5 class=\"card-title\">Nome da receita</h5>\r\n"
    		            + "<p class=\"card-text\">Seu modo de preparo</p>\r\n"
    		            + "<button class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#recipeModal\">View Recipe</button>\r\n"
    		            + "</div>\r\n"
    		            + "</div>";
             // Editar a receita
             receita = receita.replace("Nome da receita", todasReceitas.get(i).getNome());
             receita = receita.replace("Seu modo de preparo", todasReceitas.get(i).getModoDePreparo());
             
             // Concatenar varias Divs
             novaDiv = novaDiv + receita;
             }
             
             // Editar o front end com o Java
             html = html.replace("<CaixaSuasReceitas>", novaDiv);
             
             // Retorna a string com o HTML
             return html;
    }
    // alterar features pelas receitas do bd
    public String replaceFeatures(String html) {
    	String receita = new String();
    	String novaDiv = new String();
    	List<Receita> todasReceitas = retornarTodasReceitas();
    	
    	//
    	int loop = todasReceitas.size();
    	loop = loop > 10 ? 10 : loop;
    	loop--;
    	// loop para retornar as 10 ultimas receitas
    	int pos = todasReceitas.size() - 1;
    	while ( loop >= 0 ) {
    		// codigo
    		receita = "<div class=\"card\" style=\"width: 18rem; margin: 10px;\">\r\n"
            		+ "<img src=\"img/"+ todasReceitas.get(pos).imagem +"\" class=\"card-img-top\" alt=\"..\" style=\"height: 16rem; width: 100%;\">\r\n"
    				+ "<div class=\"card-body\">\r\n"
    				+ "<h5 class=\"card-title\">Nome da receita</h5>\r\n"
    				+ "<p class=\"card-text\">Seu modo de preparo</p>\r\n"
    				+ "<button class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#recipeModal\">View Recipe</button>\r\n"
    				+ "</div>\r\n"
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

  

	public String pesquisaReceita(String html,String nome) {
		String receita = new String();
		String novaDiv = new String();
		List<Receita> todasReceitas = retornarTodasReceitas();

		//
		
   	 for ( int i = 0; i < todasReceitas.size(); i++ ) {
			// codigo
			receita = "<div class=\"card\" style=\"width: 18rem; margin: 10px;\">\r\n"
		            + "<img src=\"img/todasReceitas.get(i).imagem\" class=\"card-img-top\" alt=\"..\" style=\"height: 16rem; width: 100%;\">\r\n"
					+ "<div class=\"card-body\">\r\n" + "<h5 class=\"card-title\">Nome da receita</h5>\r\n"
					+ "<p class=\"card-text\">Seu modo de preparo</p>\r\n"
					+ "<button class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#recipeModal\">View Recipe</button>\r\n"
					+ "</div>\r\n" + "</div>";

			// Editar a receita
			if(nome == todasReceitas.get(i).getNome())
			receita = receita.replace("Nome da receita", todasReceitas.get(i).getNome());
			receita = receita.replace("Seu modo de preparo", todasReceitas.get(i).getModoDePreparo());

			// Concatenar varias Divs
			novaDiv = novaDiv + receita;

		}

		// Editar o front end com o Java
		html = html.replace("<CaixaSuasReceitas>", novaDiv);

		// Retorna a string com o HTML
		return html;
	}

}  