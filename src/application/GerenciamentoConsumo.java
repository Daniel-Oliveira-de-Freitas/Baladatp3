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
	
	public void salvarConsumo() throws FileNotFoundException, IOException {
		String linha;
		try {
			FileWriter fc = new FileWriter(Main.arquivoConsumo());
			BufferedWriter bc = new BufferedWriter(fc);
			for (Consumo co : consumos) {
				linha = co.getQuantidade()+";"+co.getCodproduto()+";";
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
