package application;


public class Cliente{
	private long rg;
	private String nome;
	private String categoria;
	private double credito;
	
	public Cliente(long rg, String nome, String categoria, double credito) {
		this.rg = rg;
		this.nome = nome;
		this.categoria = categoria;
		this.credito = credito;
	}
	
	
	public long getRg() {
		return rg;
	}
	public void setRg(long rg) {
		this.rg = rg;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public double getCredito() {
		return credito;
	}
	public void setCredito(double credito) {
		this.credito = credito;
	}
	
	public String toString() {
		return "RG: " + rg + " Nome: "+ nome +" Categoria: "+ categoria+" Credito "+ credito;
	}
}
