package application;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GerenciamentoProduto {
private ArrayList<Produto> produtos = new ArrayList<Produto>();
	
	public void AdicionarProduto(Produto p) {
		produtos.add(p);
	}
	public Produto ConsultaProdutoCod(String cod) {
		for (Produto p :produtos){	
		if(p.getCod().equals(cod)) {
			return p;
		}
		}
		return null;
	}
	
	public Produto ConsultaProdutoNome(String nome) {
		for (Produto p :produtos){	
		if(p.getNome().equals(nome)) {
			return p;
		}
		}
		return null;
	}
	
	
	public void salvarProduto() throws FileNotFoundException, IOException {
			String linha;
			try {
				FileWriter fc = new FileWriter(Main.arquivoProduto());
				BufferedWriter bc = new BufferedWriter(fc);
				for (Produto p : produtos) {
					linha = p.getCod() +";"+ p.getNome()+";"+p.getDescProd() +";"+p.getQuant()+";"
				+p.getFornecedor()+";"+p.getCnpj()+ ";"+p.getCategoria()+";"+p.getNf()+";" +p.getDataComp()+";"+
							p.getDataVal()+";"+p.getPeso()+";"+p.getValorUniComp()+";"+p.getValorTotComp()+";"
				+p.getValorUniComp()+";"+p.getValorTotVend()+";";                                       
					bc.write(linha);
					bc.newLine();
				}
				bc.close();
				fc.close();
			} catch (Exception m) {
				System.out.println("Deu erro");
			}
		}
	public boolean removerProduto(String nome) {
        Produto produto = null;
        for (Produto p  : produtos) {
            if (p.getNome().equals(nome)) {
                produto = p;
            }
        }

        if (produto != null) { 
            produtos.remove(produto);
            return true;
        }
        return false;
    }
	
	
	

}
