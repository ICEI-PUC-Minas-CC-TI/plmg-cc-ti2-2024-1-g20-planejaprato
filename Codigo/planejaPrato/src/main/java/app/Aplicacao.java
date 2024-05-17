package app;

import static spark.Spark.*;
import service.ReceitaService;

public class Aplicacao {
    public static ReceitaService receitaService = new ReceitaService();

    public static void main(String[] args) {
        port(5402);

        staticFiles.externalLocation("src/main/resources/public");

        get("/index", (req, res) -> {
            res.redirect("index.html");
            return null;
        });

        post("/cadastra-receita", (request, response) -> {
            String nome = request.queryParams("nome");
            String ingredientes = request.queryParams("ingredientes");
            String modoDePreparo = request.queryParams("modoDePreparo");
            
           // Imprimindo os valores recebidos no console
           // System.out.println("Nome: " + nome);
           //System.out.println("Ingredientes: " + ingredientes);
           //System.out.println("Modo de Preparo: " + modoDePreparo);      
            
            
            // sucesso e redirecionamento
            response.status(302);

            // Redirecionamento para a página desejada após o cadastro com as variaveis
            response.redirect("/yourRecipes.html");
            
            receitaService.cadastraReceita(nome, ingredientes, modoDePreparo);
            
            return null;
        });

        
    }
}

    