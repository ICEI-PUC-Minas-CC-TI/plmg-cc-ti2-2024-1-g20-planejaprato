package model;

public class Receita {
		
	    public String nomeReceita;
	    public String ingredientes;
	    public String modoDePreparo;
	    public String imagem;
	    public int idReceita;
	    
	 // Construtor
	    public Receita(String nomeReceita, String ingredientes, String modoDePreparo, String imagem) {
	        this.nomeReceita = nomeReceita;
	        this.ingredientes = ingredientes;
	        this.modoDePreparo = modoDePreparo;
	        this.imagem = imagem;
	    }
	    
	    public Receita() {}
	    
	    // Gettes e Setters
	    public void setNome(String nome) {
	    	this.nomeReceita = nome;
	    }
	    public void setImagem(String imagem) {
	    	this.imagem = imagem;
	    }

	    public void setIngredientes(String ingredientes) {
	    	this.ingredientes = ingredientes;
	    }
	    public void setModoDePreparo(String modoDePreparo) {
	    	this.modoDePreparo = modoDePreparo;
	    }

	    public void setId(int idReceita) {
	    	this.idReceita = idReceita;
	    }
	    public int getId() {
	        return idReceita;
	    }

	    public String getNome() {
	        return nomeReceita;
	    }


	    public String getModoDePreparo() {
	        return modoDePreparo;
	    }

	    public String getIngredientes() {
	        return ingredientes;
	    }
	    public String getImagem() {
	        return imagem;
	    }


}
