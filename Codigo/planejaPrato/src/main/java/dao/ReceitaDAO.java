package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Receita;


public class ReceitaDAO extends DAO {
	//conexao com bd	
	public ReceitaDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
//	localhost -> nome do servidor, postgres -> nome da base de dados
    String url = "jdbc:postgresql://localhost:5432/postgres"; 
    String usuario = "postgres";
    String senha = "edson";
    public void cadastrarReceita(String nome,String ingredientes,String modoDePreparo) {
        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "INSERT INTO Receita (nome, ingredientes,modoDePreparo) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, ingredientes);
            preparedStatement.setString(3, modoDePreparo);


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //atualizar bd
    public boolean update(Receita receita) {
		boolean status = false;
		try {  
			String sql = "UPDATE produto SET descricao = '" + receita.getNome() + "', "
					   + "preco = " + receita.getIngredientes() + ", " 
					   + "quantidade = " + receita.getModoDePreparo() + ","
					   + "datafabricacao = ?, " 
					   + "datavalidade = ? WHERE id = " + receita.getId();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
    //	deletar receitas
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM produto WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
    // retorna todas as receitas
	public List<Receita> getTodasReceitas() { 
		List<Receita> listReceitas = new ArrayList<>(); 
		try (Connection connection = DriverManager.getConnection(url,usuario, senha)) {
			String sql = "SELECT * FROM Receita";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
	  
			while (resultSet.next()) { 
			Receita receita = new Receita();
			receita.setNome(resultSet.getString("nome"));
			receita.setIngredientes(resultSet.getString("ingredientes"));
			receita.setModoDePreparo(resultSet.getString("modoDePreparo"));
			listReceitas.add(receita); 
			} 
		}catch (SQLException e) { 
			e.printStackTrace();
	  } 
		return listReceitas; 
	}
	 

}
