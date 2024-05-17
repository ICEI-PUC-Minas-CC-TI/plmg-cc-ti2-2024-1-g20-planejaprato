package dao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Receita;


public class ReceitaServlet extends HttpServlet {
	String url = "jdbc:postgresql://localhost:5432/postgres"; 
	String usuario = "postgres";
	String senha = "edson";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Conexão com o banco de dados
            Connection connection = DriverManager.getConnection(url, usuario, senha);
            String sql = "SELECT * FROM Receita";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Lista para armazenar as receitas
            List<Receita> listReceitas = new ArrayList<>();

            while (resultSet.next()) {
                Receita receita = new Receita();
                receita.setId(resultSet.getInt("id"));
                receita.setNome(resultSet.getString("nome"));
                receita.setIngredientes(resultSet.getString("ingredientes"));
                receita.setModoDePreparo(resultSet.getString("modoDePreparo"));
                listReceitas.add(receita);
            }

            // Fechando conexões
            resultSet.close();
            preparedStatement.close();
            connection.close();

            // Enviando a lista de receitas para a página JSP ou HTML
            request.setAttribute("receitas", listReceitas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("exibirReceitas.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
