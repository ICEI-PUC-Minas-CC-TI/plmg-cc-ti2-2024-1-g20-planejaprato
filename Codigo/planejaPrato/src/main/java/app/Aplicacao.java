/* Responsavel por rodar a WEB, e criar uma instancia de ReceitaService */

package app;

import static spark.Spark.*;
import service.ReceitaService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Aplicacao {
	// Gera uma instancia de ReceitaService
    public static ReceitaService receitaService = new ReceitaService();

    // Criar uma String auxiliar para futuras implementações e usos
    public static String aux = new String();
    
    public static void main(String[] args) {
    	
        // Configura a porta do servidor web
        port(5402);

        // Configura o diretório para arquivos estáticos (HTML, CSS, JS)
        staticFiles.externalLocation("src/main/resources/public");

        // Rota para redirecionar "/index" para "index.html"
        get("/index", (req, res) -> {
            res.redirect("index.html");
            return null;
        });

        // Rota para cadastrar uma receita via POST
        post("/cadastra-receita", (request, response) -> {
            // Obtém os parâmetros da requisição
            String nome = request.queryParams("nome");
            String ingredientes = request.queryParams("ingredientes");
            String modoDePreparo = request.queryParams("modoDePreparo");  
            
            // Converte os valores para minúsculas
            nome = nome.toLowerCase();
            ingredientes = ingredientes.toLowerCase();
            modoDePreparo = modoDePreparo.toLowerCase();
            
            // Imprime os valores recebidos no console
            System.out.println("Nome: " + nome);
            System.out.println("Ingredientes: " + ingredientes);
            System.out.println("Modo de Preparo: " + modoDePreparo);      
            
            // Chama o serviço de cadastro de receita
            // Manda os parametros para a função cadastrarReceita de receitaService
            receitaService.cadastraReceita(nome, ingredientes, modoDePreparo);
            
            // Printar todas as receitar do BD no console
            receitaService.printarReceitas(receitaService.retornarTodasReceitas());
            
            // Preencher a string aux com o conteudo do HTML
            aux = htmlText("yourRecipes.html");
            
            // Editar o front end com o Java
            aux = aux.replace("Nome da receita", nome);
            aux = aux.replace("Nome dos ingredientes", ingredientes);
            aux = aux.replace("Seu modo de preparo", modoDePreparo);
            
            // Retorna a string com o HTML
            return aux;
        });
        
        // ainda nao funciona
        // Rota para atualizar html yourRecipes com receitas do bd via POST
        post("/atualiza-receitas", (request, response) -> {
            // Obtém os parâmetros da requisição
            String nome = request.queryParams("nome");
            String ingredientes = request.queryParams("ingredientes");
            String modoDePreparo = request.queryParams("modoDePreparo");  
            
            // Converte os valores para minúsculas
            nome = nome.toLowerCase();
            ingredientes = ingredientes.toLowerCase();
            modoDePreparo = modoDePreparo.toLowerCase();
            
            // Imprime os valores recebidos no console
            System.out.println("Nome: " + nome);
            System.out.println("Ingredientes: " + ingredientes);
            System.out.println("Modo de Preparo: " + modoDePreparo);      
            
            // Manda os parametros para a função cadastrarReceita de receitaService
                        
            // Printar todas as receitar do BD no console
            receitaService.printarReceitas(receitaService.retornarTodasReceitas());
            
            // Preencher a string aux com o conteudo do HTML
            aux = htmlText("index.html");
            
            // Editar o front end com o Java
            aux = aux.replace("AboutUsTeste", "AAAAAAAAAAAAAAAAAAAAAA");
            
            // Retorna a string com o HTML
            return aux;
        });

    }

    
    // Função para ler um HTML e retornar uma String contendo todo o conteudo
    public static String htmlText(String link){
    	
    	// Criar uma String formatada para redirecionar ao local que deseja ler
    	String caminhoArq = "./src/main/resources/public/" + link;
    	
    	// Criar a string de saida com todo o HTML
    	StringBuilder conteudo = new StringBuilder();
    	
    	// Tentar abrir o Scanner para ler o HTML
    	try {
            File arquivo = new File(caminhoArq);
            Scanner sc = new Scanner(arquivo);

            // Concatencar na String todo o HTML, construindo a String de saida
            while (sc.hasNextLine()) {
                conteudo.append(sc.nextLine());
            }
            
            sc.close();
    	}
    	catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	// Retonar a String contendo todo o HTML
    	return conteudo.toString();
    }
    
}
