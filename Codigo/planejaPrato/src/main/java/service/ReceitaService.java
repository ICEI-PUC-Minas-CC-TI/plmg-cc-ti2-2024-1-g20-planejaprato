package service;

import dao.ReceitaDAO;
import model.Receita;

/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
*/

public class ReceitaService {
    public ReceitaDAO receitaDAO;
    private int proximoId = 1;

    public ReceitaService() {
        receitaDAO = new ReceitaDAO(); 
    }

    public void cadastrarReceita(String nome, String modoDePreparo, String ingredientes) {
            Receita Receita = new Receita();
            Receita.setId(proximoId++);
            Receita.setNome(nome);
            Receita.setIngredientes(ingredientes);
            Receita.setModoDePreparo(modoDePreparo);
            
            
            receitaDAO.cadastrarReceita(Receita);
        }
    /*
    public void atualizarRelogio(int id, String novoNome, String novoModelo) {
        Relogio relogio = new Relogio();
        relogio.setId(id); 
        relogio.setNome(novoNome);
        relogio.setModelo(novoModelo);
        
        relogioDAO.atualizarRelogio(relogio);
    }
    
    public void excluirRelogio(int id) {
        relogioDAO.excluirRelogio(id);
    }
    
    public List<Relogio> listarRelogios() {
        return relogioDAO.listarRelogios();
    }
*/


}  