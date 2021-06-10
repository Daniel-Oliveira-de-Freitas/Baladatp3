package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Gerenciamento {
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	public void AdicionarCliente(Cliente c) {
		clientes.add(c);
	}

	public Cliente ConsultaCliente(String rg) {
		for (Cliente cli : clientes) {
			if (cli.getRg() == Long.parseLong(rg)) {
				return cli;
			}
		}
		return null;
	}

	public boolean removerCliente(long rg) {
		Cliente cliente = null;
		for (Cliente cli : clientes) {
			if (cli.getRg() == rg) {
				cliente = cli;
			}
		}

		if (cliente != null) { // verfica se é diferente de null se for irá remover o produto
			clientes.remove(cliente);
			return true;
		}
		return false;
	}

	public void adicionarCredito(double rg, double creditoadicional) {
		double creditoatual, creditototal;
		for (Cliente cli : clientes) {
			if (cli.getRg() == rg) {
				if (creditoadicional > 0 && !cli.getCategoria().equals("VIP")) {
					creditoatual = (double) cli.getCredito();
					creditototal = creditoatual + creditoadicional;
					cli.setCredito(creditototal);
				}
			}
		}
	}

	public void salvarCliente() {
		String linha;
		try {
			FileWriter fc = new FileWriter(Main.arquivoCliente());
			BufferedWriter bc = new BufferedWriter(fc);
			for (Cliente c : clientes) {
				linha = c.getRg() + ";" + c.getNome() + ";" + c.getCategoria() + ";" + c.getCredito() + ";";
				bc.write(linha);
				bc.newLine();
			}
			bc.close();
			fc.close();
		} catch (Exception m) {
			System.out.println("Deu erro");
		}
	}


}
