package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class MainController implements Initializable {
	public void botaoCliente() {
		Main.changeScene("cliente");
	}
	
	public void botaoProduto() {
		Main.changeScene("produto");
	}
	
	public void botaoConsumo() {
		Main.changeScene("consumo");
	}
	
	
	public void leituraCliente() {
		String nome,categoria,linha;
		long rg;
		double credito;
		try {
			FileReader fr = new FileReader(Main.arquivoCliente());
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				linha = br.readLine();
				String dados[] = linha.split(";");
				rg = Long.parseLong(dados[0]);
				nome = dados[1];
				categoria = dados[2];
				credito = Double.parseDouble(dados[3]);
				Cliente cliente = new Cliente(rg, nome, categoria, credito);
				Main.instancia().AdicionarCliente(cliente);		
				}
		} catch (IOException n) {
			System.out.println("Houve algum erro");
		}
		}
	
	public void leituraProduto() {
		String  cod,nome, descProd, quant, fornecedor,cnpj,  categoria,nf,dataComp, dataVal, linha;
		String peso;
		String  valorUniComp, valorTotComp, valorUniVend,valorTotVend;
		try {
			FileReader fr = new FileReader(Main.arquivoProduto());
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				linha = br.readLine();
				String dados[] = linha.split(";");
				cod = dados[0];
				nome = dados[1];
				descProd = dados[2];
				quant = dados[3];
				fornecedor = dados[4];
				cnpj = dados[5];
				categoria = dados [6];
				nf = dados [7];
				dataComp = dados [8];
				dataVal = dados [9];
				peso = dados[10];
				valorUniComp =dados[11];
				valorTotComp = dados[12];
				valorUniVend = dados[13];
				valorTotVend = dados[14];
				Produto produto = new Produto(cod, nome,descProd,quant,fornecedor,cnpj, categoria,nf,dataComp,dataVal,
						peso,valorUniComp,valorTotComp,valorUniVend,valorTotVend);
				Main.instanciaProduto().AdicionarProduto(produto);		
				}
		} catch (IOException n) {
			System.out.println("Houve algum erro");
		}
	}
	
	public void leituraConsumo() {
		String linha;
		int codconsumo;
		long rg;
		int codproduto;
		int quantidade;
		
		try {
			FileReader fr = new FileReader(Main.arquivoConsumo());
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				linha = br.readLine();
				String dados[] = linha.split(";");
				codconsumo = (int) Double.parseDouble(dados[0]);
				rg = Long.parseLong(dados[1]);
				codproduto = (int) Double.parseDouble(dados[2]);
				quantidade = (int) Double.parseDouble(dados[3]);
				Consumo consumo = new Consumo(codconsumo, rg, codproduto, quantidade);
				Main.instanciaConsumo().AdicionarConsumo(consumo);		
				}
		} catch (IOException n) {
		//	System.out.println("Houve algum erro!");
		}
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	leituraCliente();
	leituraProduto();
	leituraConsumo();
	}
}
