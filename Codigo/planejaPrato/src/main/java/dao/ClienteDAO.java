package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Receita;

public class ClienteDAO {
	public class ReceitaDAO {
	    String url = "jdbc:postgresql://localhost:5432/planejaPrato"; 
	    String usuario = "postgres";
	    String senha = "luissql";
	    public void cadastrarCliente(Receita receita) {
	        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
	            String sql = "INSERT INTO Receita (nome, ingredientes,modoDePreparo) VALUES (?, ?, ?)";
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, receita.getNome());
	            preparedStatement.setString(2, receita.getIngredientes());
	            preparedStatement.setString(2, receita.getModoDePreparo());


	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	}
}
