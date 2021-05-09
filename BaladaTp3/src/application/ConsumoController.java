package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ConsumoController {
	@FXML
	private TableView<Consumo> tabelaconsumo, tabelaconsumo2;
	@FXML
	private TableColumn<String, Consumo> codconsumocoluna, rgcoluna, codprodutocoluna, quantidadecoluna;
	@FXML
	private TableColumn<String, Consumo> codconsumocoluna2, rgcoluna2, codprodutocoluna2, quantidadecoluna2;
	@FXML
	private Tab telaincluir, telagerenciar, telapagar;
	@FXML
	private Button alterarconsumo, deletarconsumo;
	@FXML
	private AnchorPane telaalterar;
	@FXML
	private TextField codconsumofield, rgfield, codprodutofield, quantidadefield;
	@FXML
	private TextField codconsumoalterar, rgalterar, codprodutoalterar, quantidadealterar;
	@FXML
	private TextField barrapesquisaconsumo, barrapesquisapagar;
	@FXML
	private Label insiravalorlabel;

	public void acaoSalvar() {
		if (telaincluir.isSelected()) {
			try {
				cadastroConsumo();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (telagerenciar.isSelected()) {
			try {
				ExibirTabela();
			} catch (Exception c) {
				System.out.println(c.getMessage());
			}
		} else if (telapagar.isSelected()) {
			try {
				AddValor();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void ExibirTabela() {
		ObservableList<Consumo> items = FXCollections.observableArrayList();
		int codconsumo;
		long rg;
		int codproduto;
		int quantidade;
		try {
			FileReader fr = new FileReader(Main.arquivoConsumo());
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				String linha = br.readLine();
				String dados[] = linha.split(";");
				codconsumo = (int) Double.parseDouble(dados[0]);
				rg = Long.parseLong(dados[1]);
				codproduto = (int) Double.parseDouble(dados[2]);
				quantidade = (int) Double.parseDouble(dados[3]);
				
				items.add(new Consumo(codconsumo, rg, codproduto, quantidade));
			}
		} catch (IOException n) {
			System.out.println("Erro na tabela de consumos");
		}
		tabelaconsumo.setItems(items);
		codconsumocoluna.setCellValueFactory(new PropertyValueFactory<String, Consumo>("codconsumo"));
		rgcoluna.setCellValueFactory(new PropertyValueFactory<String, Consumo>("rg"));
		codprodutocoluna.setCellValueFactory(new PropertyValueFactory<String, Consumo>("codproduto"));
		quantidadecoluna.setCellValueFactory(new PropertyValueFactory<String, Consumo>("quantidade"));
		tabelaconsumo.refresh();
	}
	
	private void cadastroConsumo() throws FileNotFoundException, IOException {
		int codconsumo;
		long rg;
		int codproduto;
		int quantidade = 0;
		if (!codconsumofield.getText().isEmpty() && !rgfield.getText().isEmpty() && !codprodutofield.getText().isEmpty() && !quantidadefield.getText().isEmpty()){
			codconsumo = (int) Double.parseDouble(codconsumofield.getText());
			rg = Long.parseLong(rgfield.getText());
			codproduto = (int) Double.parseDouble(codprodutofield.getText());
			quantidade = (int) Double.parseDouble(quantidadefield.getText());
			Consumo co = new Consumo(codconsumo, rg, codproduto, quantidade);
			Main.instanciaConsumo().AdicionarConsumo(co);
			Main.instanciaConsumo().salvarConsumo();
			System.out.println(co);
		} else {
			alertMessage("Campos Obrigatorios vazios!");
		}
	}
	
	//TELAGERENCIAR
	public void procuraConsumo() {
		Consumo con;
		if (telapagar.isSelected()&& !barrapesquisapagar.getText().isEmpty()) {
			con = Main.instanciaConsumo().ConsultaConsumo(barrapesquisapagar.getText());
			codprodutocoluna.setText(String.valueOf(con.getCodproduto()));
			rgcoluna.setText(String.valueOf(con.getRg()));
			codconsumocoluna.setText(String.valueOf(con.getCodconsumo()));
			quantidadecoluna.setText(String.valueOf(con.getQuantidade()));
		} else {
			System.out.print("Faça uma compra");
		}
		alertMessage("Preencha o campo de pesquisa");
	}


	public void limpaCampos() {
		//codconsumocoluna.setText("");
		//rgcoluna.setText("");
	  //  codprodutocoluna.setText("");
	//	quantidadecoluna.setText("");
	}
	public void alterarConsumo() {
		String con;
		Consumo co;
		try {
			con = String.valueOf(tabelaconsumo.getSelectionModel().getSelectedItem().getRg());
			co = Main.instanciaConsumo().ConsultaConsumo(con);			
			codconsumoalterar.setText(String.valueOf(co.getCodconsumo()));
			rgalterar.setText(String.valueOf(co.getRg()));
			codprodutoalterar.setText(String.valueOf(co.getCodproduto()));
			quantidadealterar.setText(String.valueOf(co.getQuantidade()));
		} catch (Exception e) {
			alertMessage("Não consumo para editar");
		}
	}

	public void salvarAlteracaoConsumo() throws FileNotFoundException, IOException {
		String con;
		Consumo co;
		if (!codconsumoalterar.getText().isEmpty() && !rgalterar.getText().isEmpty() && !codprodutoalterar.getText().isEmpty()
				&& !quantidadealterar.getText().isEmpty()) {
			con = String.valueOf(tabelaconsumo.getSelectionModel().getSelectedItem().getRg());
			co = Main.instanciaConsumo().ConsultaConsumo(con);
			co.setCodconsumo((int) Double.parseDouble(codconsumoalterar.getText()));
			co.setCodproduto((int) Double.parseDouble(codprodutoalterar.getText()));
			co.setQuantidade((int) Double.parseDouble(quantidadealterar.getText()));
			alertMessage("Alterado com sucesso");
			Main.instanciaConsumo().salvarConsumo();
			ExibirTabela();
				}else {
				   alertMessage("Preencha todos os campos para salvar");
				}
		}

	//TELAPAGAR
	public void procuraValor() {
		Consumo con;
		if (telapagar.isSelected()) {
			con = Main.instanciaConsumo().ConsultaConsumo(barrapesquisapagar.getText());
			codprodutocoluna2.setText(String.valueOf(con.getCodproduto()));
			rgcoluna2.setText(String.valueOf(con.getRg()));
			codconsumocoluna2.setText(String.valueOf(con.getCodconsumo()));
			quantidadecoluna2.setText(String.valueOf(con.getQuantidade()));
		} 
		else if(telapagar.isSelected()){
			System.out.print("tela de realizar o pagamento");
		}
	}

	public void alertMessage(String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Atenção");
		alert.setContentText(msg);
		alert.setHeaderText(null);
		alert.showAndWait();
	}
	
	public void AddValor() throws FileNotFoundException, IOException {
		if(!codconsumocoluna2.isVisible()) {
		Main.instancia().adicionarCredito(Double.parseDouble(barrapesquisaconsumo.getText()),
		Double.parseDouble(codconsumocoluna2.getText()));
		Main.instanciaConsumo().salvarConsumo();
		}
	}
	
	public void acaobotaovoltar() {
		Main.changeScene("mainScene");
		limpaCampos();
	}
}
