package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Receita;

public class ReceitaDAO extends DAO {
	public ReceitaDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
    String url = "jdbc:postgresql://localhost:5432/PlanejaPrato"; 
    String usuario = "postgres";
    String senha = "luissql";
    public void cadastrarReceita(Receita receita) {
        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "INSERT INTO Receita (nome, ingredientes,modoDePreparo) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, receita.getNome());
            preparedStatement.setString(2, receita.getIngredientes());
            preparedStatement.setString(3, receita.getModoDePreparo());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    /*
    public void excluirRelogio(int id) {
        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "DELETE FROM Relogio WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void atualizarRelogio(Relogio relogio) {
        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "UPDATE Relogio SET nome = ?, modelo = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, relogio.getNome());
            preparedStatement.setString(2, relogio.getModelo());
            preparedStatement.setInt(3, relogio.getId()); 

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public List<Relogio> listarRelogios() {
        List<Relogio> relogios = new ArrayList<>();
        
        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "SELECT * FROM Relogio";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String modelo = resultSet.getString("modelo");

                Relogio relogio = new Relogio();
                relogio.setId(id);
                relogio.setNome(nome);
                relogio.setModelo(modelo);

                relogios.add(relogio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return relogios;
    }
    */

}
