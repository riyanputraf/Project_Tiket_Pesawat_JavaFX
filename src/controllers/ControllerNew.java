package controllers;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Tiket;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerNew implements Initializable {

    @FXML
    private Button orderButton;

    @FXML
    private ComboBox<String> listMaskapai;

    @FXML
    private TableView<Tiket> tableview;

    @FXML
    private TableColumn<Tiket, Integer> colId;

    @FXML
    private TableColumn<Tiket, String> colMaskapai;

    @FXML
    private TableColumn<Tiket, String> colTujuan;

    @FXML
    private TableColumn<Tiket, String> colWaktu;

    @FXML
    private TableColumn<Tiket, String> colKelas;

    @FXML
    private TableColumn<Tiket, Integer> colStok;

    @FXML
    private TextField searchField;
    String valueMaskapai;
    String coba;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showOptionMaskapai();
        showTiket();

    }



    private void showOptionMaskapai() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection =connectionClass.getConnection();
        Statement st;
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM maskapai");
            while (rs.next()){
                list.add(rs.getString("namaMaskapai"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.listMaskapai.setItems(null);
        this.listMaskapai.setItems(list);


    }
    private void executeQuery(String sql) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection =connectionClass.getConnection();
        Statement st;
        try {
            st = connection.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ObservableList<Tiket> getTiketList(){
        ObservableList<Tiket> tiketList = FXCollections.observableArrayList();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection =connectionClass.getConnection();
        String sql = "SELECT * FROM tiket";
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            Tiket tiket;
            while (rs.next()){
                tiket = new Tiket(rs.getInt("idTiket"), rs.getString("tujuan"), rs.getString("waktu"), rs.getInt("stok"),  rs.getString("kelas"), rs.getString("namaMaskapai"));
                tiketList.add(tiket);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tiketList;
    }

    public void showTiket(){
        ObservableList<Tiket> list = getTiketList();
        this.colId.setCellValueFactory(new PropertyValueFactory<Tiket, Integer>("idTiket"));
        this.colMaskapai.setCellValueFactory(new PropertyValueFactory<Tiket, String>("namaMaskapai"));
        this.colTujuan.setCellValueFactory(new PropertyValueFactory<Tiket, String>("tujuan"));
        this.colWaktu.setCellValueFactory(new PropertyValueFactory<Tiket, String>("waktu"));
        this.colStok.setCellValueFactory(new PropertyValueFactory<Tiket, Integer>("stok"));
        this.colKelas.setCellValueFactory(new PropertyValueFactory<Tiket, String>("kelas"));
        this.tableview.setItems(list);
    }

    int selectedIndex = -1;

    public void order(ActionEvent actionEvent) {
        String s = tableview.getSelectionModel().getSelectedItem().toString();
        System.out.println(s);
        String k = colMaskapai.getCellData(2);
        searchField.setText(k);

    }

    public void selectMaskapai(ActionEvent actionEvent) {
        valueMaskapai = listMaskapai.getSelectionModel().getSelectedItem().toString();
    }
    String k = colMaskapai.getCellData(2);
    public void search(ActionEvent actionEvent) {

        if (valueMaskapai == null){
            ObservableList<Tiket> list = getTiketList();
            list.clear();
            this.tableview.setItems(list);
        }else if (valueMaskapai == colMaskapai.getCellData(2)){
            ObservableList<Tiket> list = getTiketList();
            this.tableview.setItems(list);
        }
        /*ObservableList<Tiket> list = getTiketList();
        this.colId.setCellValueFactory(new PropertyValueFactory<Tiket, Integer>("idTiket"));
        this.colMaskapai.setCellValueFactory(new PropertyValueFactory<Tiket, String>("namaMaskapai"));
        this.colTujuan.setCellValueFactory(new PropertyValueFactory<Tiket, String>("tujuan"));
        this.colWaktu.setCellValueFactory(new PropertyValueFactory<Tiket, String>("waktu"));
        this.colStok.setCellValueFactory(new PropertyValueFactory<Tiket, Integer>("stok"));
        this.colKelas.setCellValueFactory(new PropertyValueFactory<Tiket, String>("kelas"));


        this.tableview.setItems(list);
        FilteredList<Tiket> filteredData = new FilteredList<>(list, b -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Tiket -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Tiket.getNamaMaskapai().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (Tiket.getKelas().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Tiket> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableview.setItems(sortedData);*/

    }
}
