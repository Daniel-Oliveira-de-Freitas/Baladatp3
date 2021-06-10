package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ConsumoController {
	@FXML
	private TableView<Consumo> tabelaconsumo;
	@FXML
	private TableColumn<String, Consumo> codconsumocoluna, rgcoluna, codprodutocoluna, quantidadecoluna;
	@FXML
	private TableView<Consumo> tabelaconsumo2;
	@FXML
	private TableColumn<String, Consumo> codconsumocoluna2, rgcoluna2, codprodutocoluna2, quantidadecoluna2;
	@FXML
	private Tab telaincluir, telagerenciar, telapagar;
	@FXML
	private Button alterarconsumo, deletarconsumo;
	@FXML
	private Pane telaalterar;
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
	
	public void ExibirTabela2() {
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
		tabelaconsumo2.setItems(items);
		codconsumocoluna2.setCellValueFactory(new PropertyValueFactory<String, Consumo>("codconsumo"));
		rgcoluna2.setCellValueFactory(new PropertyValueFactory<String, Consumo>("rg"));
		codprodutocoluna2.setCellValueFactory(new PropertyValueFactory<String, Consumo>("codproduto"));
		quantidadecoluna2.setCellValueFactory(new PropertyValueFactory<String, Consumo>("quantidade"));
		tabelaconsumo2.refresh();
	}
	
	
	public void cadastroConsumo(){
		int codconsumo;
		long rg;
		int codproduto;
		int quantidade;
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
	
	public void PesquisarTabelaConsumo() {
		ObservableList<Consumo> items = FXCollections.observableArrayList();
		String linha;
		long rg;
		int codconsumo, codproduto,quantidade;
		try {
			FileReader fr = new FileReader(Main.arquivoConsumo());
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				linha = br.readLine();
				String dados[] = linha.split(";");
				rg = Long.parseLong(dados[0]);
				codconsumo = Integer.parseInt(dados[1]);
				codproduto = Integer.parseInt(dados[2]);
				quantidade = Integer.parseInt(dados[3]);
				items.add(new Consumo(codconsumo, rg, codproduto, quantidade));
			}
			
		} catch (IOException n) {
			System.out.println("Erro na tabela de clientes");
		}
		tabelaconsumo.setItems(items);
		codconsumocoluna.setCellValueFactory(new PropertyValueFactory<String, Consumo>("codconsumo"));
		rgcoluna.setCellValueFactory(new PropertyValueFactory<String, Consumo>("rg"));
		codprodutocoluna.setCellValueFactory(new PropertyValueFactory<String, Consumo>("codproduto"));
		quantidadecoluna.setCellValueFactory(new PropertyValueFactory<String, Consumo>("quantidade"));
		tabelaconsumo.refresh();

		FilteredList<Consumo> itemsfilter = new FilteredList<>(items, b -> true);
		barrapesquisaconsumo.textProperty().addListener((observable, oldValue, newValue) -> {
			itemsfilter.setPredicate(c -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(c.getCodconsumo()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (String.valueOf(c.getRg()).toLowerCase().indexOf(lowerCaseFilter) != -1) 
					return true;
				else
					return false;
			});
			SortedList<Consumo> cc = new SortedList<>(itemsfilter);
			cc.comparatorProperty().bind(tabelaconsumo.comparatorProperty());
			tabelaconsumo.setItems(cc);
		});
	}
	
	
	public void PesquisarTabelaConsumo2() {
		ObservableList<Consumo> items = FXCollections.observableArrayList();
		String linha;
		long rg;
		int codconsumo, codproduto,quantidade;
		try {
			FileReader fr = new FileReader(Main.arquivoConsumo());
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				linha = br.readLine();
				String dados[] = linha.split(";");
				rg = Long.parseLong(dados[0]);
				codconsumo = Integer.parseInt(dados[1]);
				codproduto = Integer.parseInt(dados[2]);
				quantidade = Integer.parseInt(dados[3]);
				items.add(new Consumo(codconsumo, rg, codproduto, quantidade));
			}
			
		} catch (IOException n) {
			System.out.println("Erro na tabela de clientes");
		}
		tabelaconsumo2.setItems(items);
		codconsumocoluna2.setCellValueFactory(new PropertyValueFactory<String, Consumo>("codconsumo"));
		rgcoluna2.setCellValueFactory(new PropertyValueFactory<String, Consumo>("rg"));
		codprodutocoluna2.setCellValueFactory(new PropertyValueFactory<String, Consumo>("codproduto"));
		quantidadecoluna2.setCellValueFactory(new PropertyValueFactory<String, Consumo>("quantidade"));
		tabelaconsumo2.refresh();

		FilteredList<Consumo> itemsfilter = new FilteredList<>(items, b -> true);
		barrapesquisapagar.textProperty().addListener((observable, oldValue, newValue) -> {
			itemsfilter.setPredicate(c -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(c.getCodconsumo()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (String.valueOf(c.getRg()).toLowerCase().indexOf(lowerCaseFilter) != -1) 
					return true;
				else
					return false;
			});
			SortedList<Consumo> cc = new SortedList<>(itemsfilter);
			cc.comparatorProperty().bind(tabelaconsumo2.comparatorProperty());
			tabelaconsumo2.setItems(cc);
		});
	}

	public void limpaCampos() {
		try {
	codconsumofield.setText("");
	rgfield.setText("");
	codprodutofield.setText("");
	quantidadefield.setText("");
	barrapesquisaconsumo.setText("");
	if(telaalterar.isVisible()) {
		telaalterar.setVisible(false);
	}
		}catch(Exception e) {}
		}
		
	
	
	public void alterarConsumo() {
		String cli;
		Consumo c;
		try {
			cli = String.valueOf(tabelaconsumo.getSelectionModel().getSelectedItem().getRg());
			telaalterar.setVisible(true);
			c = Main.instanciaConsumo().ConsultaConsumo(cli);
				codconsumoalterar.setText(String.valueOf(c.getCodconsumo()));
				rgalterar.setText(String.valueOf(c.getRg()));
				codprodutoalterar.setText(String.valueOf(c.getCodproduto()));
				quantidadealterar.setText(String.valueOf(c.getQuantidade()));

		} catch (Exception e) {
			alertMessage("Não consumos para editar");
		}
	}

	public void salvarAlteracaoConsumo() {
		String cli;
		Consumo c;
		if (!codconsumoalterar.getText().isEmpty() && !rgalterar.getText().isEmpty() && !codprodutoalterar.getText().isEmpty()
				&& !quantidadealterar.getText().isEmpty()) {
			cli = String.valueOf(tabelaconsumo.getSelectionModel().getSelectedItem().getRg());
			c = Main.instanciaConsumo().ConsultaConsumo(cli);
			c.setCodconsumo(Integer.parseInt(codconsumoalterar.getText()));
			c.setRg(Long.parseLong(rgalterar.getText()));
			c.setCodproduto(Integer.parseInt(codprodutoalterar.getText()));
			if(!quantidadealterar.getText().equals("0")){
			c.setQuantidade(Integer.parseInt(quantidadealterar.getText()));
			alertMessage("Alterado com sucesso");
			Main.instanciaConsumo().salvarConsumo();
			telaalterar.setVisible(false);
			ExibirTabela();
				}else {
					alertMessage("Necessario que o valor seja maior que 0");
				}
		} else {
			alertMessage("Preencha todos os campos para salvar");
		}
	}


	//TELAPAGAR
	public void procuraValor() {
		Consumo con;
		if (telapagar.isSelected()) {
			con = Main.instanciaConsumo().ConsultaConsumo(barrapesquisapagar.getText());
			codprodutocoluna.setText(String.valueOf(con.getCodproduto()));
			rgcoluna.setText(String.valueOf(con.getRg()));
			codconsumocoluna.setText(String.valueOf(con.getCodconsumo()));
			quantidadecoluna.setText(String.valueOf(con.getQuantidade()));
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
		if(!codconsumocoluna.isVisible()) {
		Main.instancia().adicionarCredito(Double.parseDouble(barrapesquisaconsumo.getText()),
		Double.parseDouble(codconsumocoluna.getText()));
		Main.instanciaConsumo().salvarConsumo();
		}
	}
	
	public void deleteConsumo() {
		Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
		try {	
			Consumo c = tabelaconsumo.getSelectionModel().getSelectedItem();
			confirmacao.setTitle("Exclusão");
			confirmacao.setContentText("Você deseja excluir o consumo selecionado?");
			confirmacao.setHeaderText(null);
			Optional<ButtonType> resultado = confirmacao.showAndWait() ;
			if(resultado.isPresent()&& resultado.get() == ButtonType.OK) {
			tabelaconsumo.getItems().remove(c);
			Main.instanciaConsumo().removerConsumo(c.getRg());
			Main.instanciaConsumo().salvarConsumo();
			}
		} catch (NullPointerException e) {
			alertMessage("Não há mais consumos para deletar");
		}catch(Exception a) {
			alertMessage("Ocorreu um erro");
		}
	}
	
	public void acaobotaovoltar() {
		Main.changeScene("mainScene");
		limpaCampos();
	}
	
	public void acaobotaovoltartela() {
		telaalterar.setVisible(false);
		limpaCampos();
	}
}
