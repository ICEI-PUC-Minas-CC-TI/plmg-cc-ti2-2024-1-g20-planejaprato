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

            receitaService.cadastrarReceita(nome, ingredientes,modoDePreparo);
            response.status(201); 
            return "Receita cadastrada com sucesso!";
        });
/*
        post("/excluir-relogio", (request, response) -> {
            int id = Integer.parseInt(request.queryParams("excluir-id"));
            receitaService.excluirRelogio(id);
            response.status(200);
            return "Relógio excluído com sucesso!";
        });

        post("/atualizar-relogio", (request, response) -> {
            int id = Integer.parseInt(request.queryParams("atualizar-id"));
            String novoNome = request.queryParams("novo-nome");
            String novoModelo = request.queryParams("novo-modelo");
            receitaService.atualizarRelogio(id, novoNome, novoModelo);
            response.status(200);
            return "Relógio atualizado com sucesso!";
        });

        
        
        
        
        get("/listar-relogios", (request, response) -> {
            List<Relogio> listaDeRelogios = relogioService.listarRelogios();

            StringBuilder html = new StringBuilder();
            html.append("<html>");
            html.append("<head><title>Lista de Relógios</title></head>");
            html.append("<body>");
            html.append("<h1>Lista de Relógios Cadastrados</h1>");
            html.append("<ul>");

            for (Relogio relogio : listaDeRelogios) {
                html.append("<li>ID: ").append(relogio.getId()).append("</li>");
                html.append("<li>Nome: ").append(relogio.getNome()).append("</li>");
                html.append("<li>Modelo: ").append(relogio.getModelo()).append("</li>");
                html.append("<br>");
            }

            html.append("</ul>");
            html.append("</body>");
            html.append("</html>");

            return html.toString();
        });
    }
 */
    }
}

    