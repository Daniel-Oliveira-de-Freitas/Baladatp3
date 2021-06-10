package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ClienteController {
	@FXML
	private MenuButton categoriafield, categoriaListar, categoriaAlterar, valoresfield;
	@FXML
	private TableView<Cliente> tabelacliente;
	@FXML
	private TableColumn<String, Cliente> rgcoluna, nomecoluna, categoriacoluna, creditocoluna;
	@FXML
	private AnchorPane telaAlterar;
	@FXML
	private Tab telaincluir, telacredito, telaExibir;
	@FXML
	private Label rglabel, nomelabel, categorialabel, creditolabel, creditolabelalterar, creditolabelcadastro;
	@FXML
	private Label rgcredito, nomecredito, categoriacredito, creditoid;
	@FXML
	private MenuItem item1, item2, item3, item4, item5, item6, item7, item8, item9;
	@FXML
	private TextField rgfield, nomefield, creditofield;
	@FXML
	private TextField rgalterar, nomealterar, creditoalterar;
	@FXML
	private TextField barraPesquisa, barraPesquisaexibir, barraPesquisacredito, barraPesquisalterar;
	@FXML
	private TextField valoradicionalfield;
	@FXML
	private Label insiravalorlabel;
	@FXML
	private MenuItem credito1, credito2, credito3, credito4;

	public void acaoSalvar() {
		if (telaincluir.isSelected()) {
			try {
				cadastroCliente();
			} catch (Exception e) {
				alertMessage("Tipo invalido");
			}

		} else if (telaExibir.isSelected()) {
			try {
				ExibirTabela();
			} catch (Exception c) {
				System.out.println(c.getMessage());
			}
		} else if (telacredito.isSelected()) {
			try {
				AddCredito();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void procuraCliente() {
		Cliente cli;
		if (telacredito.isSelected() && !barraPesquisacredito.getText().isEmpty()) {
			cli = Main.instancia().ConsultaCliente(barraPesquisacredito.getText());
			if (!cli.getCategoria().equals("VIP")) {
				cli = Main.instancia().ConsultaCliente(barraPesquisacredito.getText());
				rgcredito.setText(String.valueOf(cli.getRg()));
				nomecredito.setText(cli.getNome());
				categoriacredito.setText(cli.getCategoria());
				creditoid.setText(String.valueOf(cli.getCredito()));
			} else {
				alertMessage("Cliente VIP não precisa de creditos");
			}
		} else {
			alertMessage("Preencha o campo de pesquisa");
		}
	}

	public void credito1() {
		valoresfield.setText(credito1.getText());
		insiravalorlabel.setVisible(false);
		valoradicionalfield.setVisible(false);
	}

	public void credito2() {
		valoresfield.setText(credito2.getText());
		insiravalorlabel.setVisible(false);
		valoradicionalfield.setVisible(false);
	}

	public void credito3() {
		valoresfield.setText(credito3.getText());
		insiravalorlabel.setVisible(false);
		valoradicionalfield.setVisible(false);
	}

	public void credito4() {
		valoresfield.setText(credito4.getText());
		insiravalorlabel.setVisible(true);
		valoradicionalfield.setVisible(true);
	}

	public void item1() {
		categoriafield.setText(item1.getText());
	}

	public void item2() {
		categoriafield.setText(item2.getText());
		creditofield.setVisible(true);
		creditofield.setText("");
		creditolabelcadastro.setVisible(true);
	}

	public void item3() {
		categoriafield.setText(item3.getText());
		creditofield.setVisible(false);
		creditofield.setText("0");
		creditolabelcadastro.setVisible(false);
	}

	public void item4() {
		categoriaListar.setText(item4.getText());
		creditofield.setVisible(true);
		creditofield.setText("");
		creditolabelcadastro.setVisible(true);
	}

	public void item5() {
		categoriaListar.setText(item5.getText());
	}

	public void item6() {
		categoriaListar.setText(item6.getText());
	}

	public void item7() {
		categoriaAlterar.setText(item7.getText());
		creditoalterar.setVisible(true);
		creditofield.setText("");
		creditolabelalterar.setVisible(true);
	}

	public void item8() {
		categoriaAlterar.setText(item8.getText());
		creditoalterar.setVisible(true);
		creditofield.setText("");
		creditolabelalterar.setVisible(true);
	}

	public void item9() {
		categoriaAlterar.setText(item9.getText());
		creditoalterar.setVisible(false);
		creditoalterar.setText("0");
		creditolabelalterar.setVisible(false);
	}

	public void alertMessage(String m) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Atenção");
		alert.setContentText(m);
		alert.setHeaderText(null);
		alert.showAndWait();
	}

	public void AddCredito() {
		if (!valoradicionalfield.isVisible()) {
			Main.instancia().adicionarCredito(Double.parseDouble(barraPesquisacredito.getText()),
					Double.parseDouble(valoresfield.getText()));
			Main.instancia().salvarCliente();
			alertMessage("Credito alterado");
			acaobotaovoltar();
		} else if (credito4.isVisible()) {
			Main.instancia().adicionarCredito(Double.parseDouble(barraPesquisacredito.getText()),
					Double.parseDouble(valoradicionalfield.getText()));
			// perguntar para a professora um metodo melhor para fazer
			if (Double.parseDouble(valoradicionalfield.getText()) > 0) {
				Main.instancia().salvarCliente();
				alertMessage("Credito alterado");
				acaobotaovoltar();
			} else {
				alertMessage("Valor deve ser maior que 0");
			}
		}
	}

	public void alterarCliente() {
		String cli;
		Cliente c;
		try {
			cli = String.valueOf(tabelacliente.getSelectionModel().getSelectedItem().getRg());
			telaAlterar.setVisible(true);
			c = Main.instancia().ConsultaCliente(cli);

			if (tabelacliente.getSelectionModel().getSelectedItem().getCategoria().equals("VIP")) {
				creditoalterar.setVisible(false);
				creditolabelalterar.setVisible(false);
				creditoalterar.setText("0");
				rgalterar.setText(String.valueOf(c.getRg()));
				nomealterar.setText(c.getNome());
				categoriaAlterar.setText(c.getCategoria());
				creditoalterar.setText(String.valueOf(c.getCredito()));
			} else {
				creditoalterar.setVisible(true);
				creditolabelalterar.setVisible(true);
				rgalterar.setText(String.valueOf(c.getRg()));
				nomealterar.setText(c.getNome());
				categoriaAlterar.setText(c.getCategoria());
				creditoalterar.setText(String.valueOf(c.getCredito()));

			}
		} catch (Exception e) {
			alertMessage("Não clientes para editar");
		}
	}

	public void salvarAlteracaoCliente() {
		String cli;
		Cliente c;
		if (!rgalterar.getText().isEmpty() && !nomealterar.getText().isEmpty() && !categoriaAlterar.getText().isEmpty()
				&& !creditoalterar.getText().isEmpty()) {
			cli = String.valueOf(tabelacliente.getSelectionModel().getSelectedItem().getRg());
			if(categoriaAlterar.getText().equals("VIP")) {
			c = Main.instancia().ConsultaCliente(cli);
			c.setRg(Long.parseLong(rgalterar.getText()));
			c.setNome(nomealterar.getText());
			c.setCategoria(categoriaAlterar.getText());
			c.setCredito(Double.parseDouble(creditoalterar.getText()));
			alertMessage("Alterado com sucesso");
			Main.instancia().salvarCliente();
			telaAlterar.setVisible(false);
			ExibirTabela();
			}else if(!categoriaAlterar.getText().equals("VIP") && !creditoalterar.getText().equals("0.0")) {
					c = Main.instancia().ConsultaCliente(cli);
					c.setRg(Long.parseLong(rgalterar.getText()));
					c.setNome(nomealterar.getText());
					c.setCategoria(categoriaAlterar.getText());
					c.setCredito(Double.parseDouble(creditoalterar.getText()));
					alertMessage("Alterado com sucesso");
					Main.instancia().salvarCliente();
					telaAlterar.setVisible(false);
				ExibirTabela();
				}else {
					alertMessage("Necessario que o valor seja maior que 0");
				}
		} else {
			alertMessage("Preencha todos os campos para salvar");
		}
	}

	public void cadastroCliente() {
		String nome, categoria;
		long rg;
		double credito;
		if (!rgfield.getText().isEmpty() && !nomefield.getText().isEmpty() && !categoriafield.getText().isEmpty()
				&& !creditofield.getText().isEmpty()) {
			rg = Long.parseLong(rgfield.getText());
			nome = nomefield.getText();
			categoria = categoriafield.getText();
			credito = Double.parseDouble(creditofield.getText());
			if(credito>0) {
			Cliente c = new Cliente(rg, nome, categoria, credito);
			Main.instancia().AdicionarCliente(c);
			Main.instancia().salvarCliente();
			alertMessage("Salvo com sucesso");
			acaobotaovoltar();
			}else {alertMessage("Credito deve ser maior que 0");}
		} else {
			alertMessage("Campos Obrigatorios vazios");
		}
	}

	public void acaobotaovoltar() {
		Main.changeScene("mainScene");
		limpaCampos();
	}

	public void limpaCampos() {
		rgfield.setText("");
		nomefield.setText("");
		categoriafield.setText("");
		creditofield.setText("");
		categoriafield.setText("");
		//barraPesquisaexibir.setText("");
		//barraPesquisacredito.setText("");
	//	valoresfield.setText("");
	//	valoradicionalfield.setText("");
	//	telaAlterar.setVisible(false);
	}

	public void deleteCliente() {
		try {
			Cliente c = tabelacliente.getSelectionModel().getSelectedItem();
			tabelacliente.getItems().remove(c);
			if (c == null) {
				throw new NullPointerException("Não há mais clientes para exclusão");
			}
			Main.instancia().removerCliente(c.getRg());
			Main.instancia().salvarCliente();
		} catch (NullPointerException e) {
			alertMessage(e.getMessage());
		}
	}

	public void ExibirTabela() {
		ObservableList<Cliente> items = FXCollections.observableArrayList();
		String nome, categoria, linha;
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
				items.add(new Cliente(rg, nome, categoria, credito));
			}
		} catch (IOException n) {
			System.out.println("Erro na tabela de clientes");
		}
		tabelacliente.setItems(items);
		rgcoluna.setCellValueFactory(new PropertyValueFactory<String, Cliente>("rg"));
		nomecoluna.setCellValueFactory(new PropertyValueFactory<String, Cliente>("nome"));
		categoriacoluna.setCellValueFactory(new PropertyValueFactory<String, Cliente>("categoria"));
		creditocoluna.setCellValueFactory(new PropertyValueFactory<String, Cliente>("credito"));
		tabelacliente.refresh();
	}

	public void acabaovoltartela() {
		telaAlterar.setVisible(false);
		rgalterar.setText("");
		nomealterar.setText("");
		categoriaAlterar.setText("");
		creditoalterar.setText("");
	}

	public void PesquisaTabela() {
		ObservableList<Cliente> items = FXCollections.observableArrayList();
		String nome, categoria, linha;
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
				items.add(new Cliente(rg, nome, categoria, credito));
			}
		} catch (IOException n) {
			System.out.println("Erro na tabela de clientes");
		}
		tabelacliente.setItems(items);
		rgcoluna.setCellValueFactory(new PropertyValueFactory<String, Cliente>("rg"));
		nomecoluna.setCellValueFactory(new PropertyValueFactory<String, Cliente>("nome"));
		categoriacoluna.setCellValueFactory(new PropertyValueFactory<String, Cliente>("categoria"));
		creditocoluna.setCellValueFactory(new PropertyValueFactory<String, Cliente>("credito"));
		tabelacliente.refresh();

		FilteredList<Cliente> itemsfilter = new FilteredList<>(items, b -> true);
		barraPesquisaexibir.textProperty().addListener((observable, oldValue, newValue) -> {
			itemsfilter.setPredicate(c -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (String.valueOf(c.getRg()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (c.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (c.getCategoria().toLowerCase().indexOf(lowerCaseFilter) != -1)
					return true;
				else
					return false;
			});
			SortedList<Cliente> cc = new SortedList<>(itemsfilter);
			cc.comparatorProperty().bind(tabelacliente.comparatorProperty());
			tabelacliente.setItems(cc);
		});
	}
}
