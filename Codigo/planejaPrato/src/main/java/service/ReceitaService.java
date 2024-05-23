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

    public void cadastraReceita(String nome, String modoDePreparo, String ingredientes) {
        	// Cria um novo objeto Receita e seta os valores no construtor
            Receita receita = new Receita();
            receita.setNome(nome);
            receita.setIngredientes(ingredientes);
            receita.setModoDePreparo(modoDePreparo);
            
            
            receitaDAO.cadastrarReceita(receita.getNome(),receita.getIngredientes(),receita.getModoDePreparo());
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
            System.out.println("--------------------------");
        }
    }



}  