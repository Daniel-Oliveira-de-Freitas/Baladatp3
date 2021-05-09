package application;

public class Consumo {
	private long rg;
	private int codconsumo;
	private int codproduto;
	private int quantidade;
		
	public Consumo(int codconsumo, long rg, int codproduto, int quantidade) {
		this.codconsumo = codconsumo;
		this.codproduto = codproduto;
		this.rg = rg;
		this.quantidade = quantidade;
	}

	public String toString() {
		return "Cód.Consumo:"+codconsumo+ "Rg:"+rg+ "Cód. Produto"+codproduto+ "Quantidade:"+quantidade;
	}
	
	public int getCodconsumo() {
		return codproduto;
	}
	public void setCodconsumo(int codconsumo) {
		this.codconsumo = codconsumo;
	}
	
	public long getRg() {
		return rg;
	}
	
	public long setRg() {
		return rg;
	}
	
	public int getCodproduto() {
		return codproduto;
	}
	public void setCodproduto(int codproduto) {
		this.codproduto = codproduto;
	}

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
		
}
