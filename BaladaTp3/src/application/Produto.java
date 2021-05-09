package application;

public class Produto {
	private String cod;
	private String nome;
	private String descProd;
	private String quant;
	private String fornecedor;
	private String cnpj;
	private String categoria;
	private String nf;
	private String dataComp;
	private String dataVal;
	private String peso;
	private String valorUniComp; 
	private String valorTotComp;
	private String valorUniVend;
	private String valorTotVend;

	public Produto(String cod, String nome,String descProd, String quant, String fornecedor, String cnpj, String categoria, String nf, String dataComp, String dataVal, String peso, String valorUniComp, String valorTotComp, String valorUniVend,String valorTotVend){ 
		this.cod = cod;
		this.nome = nome;
		this.descProd = descProd;
		this.quant = quant;
		this.fornecedor = fornecedor;
		this.cnpj = cnpj;
		this.categoria = categoria;
		this.nf = nf;
		this.dataComp = dataComp;
		this.dataVal = dataVal;
		this.peso = peso;
		this.valorUniComp = valorUniComp;
		this.valorTotComp = valorTotComp;
		this.valorUniVend = valorUniVend;
		this.valorTotVend = valorTotVend;
		
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescProd() {
		return descProd;
	}

	public void setDescProd(String descProd) {
		this.descProd = descProd;
	}

	public String getQuant() {
		return quant;
	}

	public void setQuant(String quant) {
		this.quant = quant;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNf() {
		return nf;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}

	public String getDataComp() {
		return dataComp;
	}

	public void setDataComp(String dataComp) {
		this.dataComp = dataComp;
	}

	public String getDataVal() {
		return dataVal;
	}

	public void setDataVal(String dataVal) {
		this.dataVal = dataVal;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getValorUniComp() {
		return valorUniComp;
	}

	public void setValorUniComp(String valorUniComp) {
		this.valorUniComp = valorUniComp;
	}

	public String getValorTotComp() {
		return valorTotComp;
	}

	public void setValorTotComp(String valorTotComp) {
		this.valorTotComp = valorTotComp;
	}

	public String getValorUniVend() {
		return valorUniVend;
	}

	public void setValorUniVend(String valorUniVend) {
		this.valorUniVend = valorUniVend;
	}

	public String getValorTotVend() {
		return valorTotVend;
	}

	public void setValorTotVend(String valorTotVend) {
		this.valorTotVend = valorTotVend;
	}

	

	}