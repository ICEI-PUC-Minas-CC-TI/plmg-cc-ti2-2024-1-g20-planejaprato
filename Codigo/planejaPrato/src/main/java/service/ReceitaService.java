package service;
import dao.ReceitaDAO;
import model.Receita;

public class ReceitaService {
    public ReceitaDAO receitaDAO;

    public ReceitaService() {
        receitaDAO = new ReceitaDAO(); 
    }

    public void cadastraReceita(String nome, String modoDePreparo, String ingredientes) {
            Receita receita = new Receita();
            receita.setNome(nome);
            receita.setIngredientes(ingredientes);
            receita.setModoDePreparo(modoDePreparo);
            
            
            receitaDAO.cadastrarReceita(receita.getNome(),receita.getIngredientes(),receita.getModoDePreparo());
        }


}  