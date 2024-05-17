package service;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteService {
    private ClienteDAO clienteDAO;

    public ClienteService() {
        clienteDAO = new ClienteDAO(); 
    }

    public void cadastrarCliente(String nome, String endereco, String email, String telefone) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setEmail(email);
        cliente.setNumeroTelefone(telefone);
        
        clienteDAO.cadastrarCliente(cliente);
    }
}