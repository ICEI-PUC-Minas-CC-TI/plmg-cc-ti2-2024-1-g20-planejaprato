package service;

import dao.IngredienteDAO;
import model.Ingrediente;

public class IngredienteService {
    private IngredienteDAO ingredienteDAO;

    public IngredienteService() {
        ingredienteDAO = new IngredienteDAO(); 
    }

    public void cadastrarIngrediente(int id, String nome, float preco) {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome(nome);
        ingrediente.setPreco(preco);
        
        ingredienteDAO.cadastrarIngrediente(ingrediente);
    }

    public void atualizarIngrediente(int id, String nome, float preco) {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome(nome);
        ingrediente.setPreco(preco);
        
        ingredienteDAO.atualizarIngrediente(ingrediente);
    }

    public void deletarIngrediente(int id) {
        ingredienteDAO.deletarIngrediente(id);
    }
}
