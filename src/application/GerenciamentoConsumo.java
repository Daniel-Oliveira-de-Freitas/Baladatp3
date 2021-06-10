package application;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GerenciamentoConsumo {
	private ArrayList<Consumo> consumos = new ArrayList<Consumo>();
	
	public void AdicionarConsumo(Consumo co) {
		consumos.add(co);
	}
	
	public void adicionarValor(long rg, int valoradicional) {
		int valortotal;
		for (Consumo con :consumos){	
			if(con.getRg()== rg) {
				valoradicional = (int)con.getCodconsumo();
				valortotal = valoradicional;
				System.out.println(valortotal);
				con.setCodconsumo(valortotal);
			}
		}
	}
	
	public Consumo ConsultaConsumo(String rg) {
		for (Consumo con :consumos){	
		if(con.getRg()== Long.parseLong(rg)) {
			return con;
		}
		}
		return null;
	}
	
	public boolean removerConsumo(long rg) {
		Consumo com = null;
		for (Consumo cons : consumos) {
			if (cons.getRg() == rg) {
				com = cons;
			}
		}

		if (com != null) { // verfica se é diferente de null se for irá remover o produto
			consumos.remove(com);
			return true;
		}
		return false;
	}

	
	public void salvarConsumo(){
		String linha;
		try {
			FileWriter fc = new FileWriter(Main.arquivoConsumo());
			BufferedWriter bc = new BufferedWriter(fc);
			for (Consumo co : consumos) {
				linha = co.getCodconsumo()+ ";"+ co.getRg()+";"+co.getCodproduto()+";"+co.getQuantidade()+";";
				bc.write(linha);
				bc.newLine();
			}
			bc.close();
			fc.close();
		} catch (Exception m) {
			System.out.println("deu erro!");
		}
	}
}
