package controllers;
/**
 * Version 0.1
 * Class Controller untuk mengatur admin
 */

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import models.Maskapai;
import models.Tiket;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label lblStatus1;

    @FXML
    private Button createMaskapai;

    @FXML
    private Button createTiket;

    @FXML
    private Button showMaskapai;

    @FXML
    private Button showTiket;

    @FXML
    private Button history;

    @FXML
    private Button close;

    @FXML
    private GridPane pnHistory;

    @FXML
    private TableColumn<?, ?> tableHistoryId;

    @FXML
    private TableColumn<?, ?> tableHistoryMaskapai;

    @FXML
    private TableColumn<?, ?> tableHistoryTujuan;

    @FXML
    private TableColumn<?, ?> tableHistoryWaktu;

    @FXML
    private TableColumn<?, ?> tableHistoryJumlah;

    @FXML
    private TableColumn<?, ?> tableHistoryKelas;

    @FXML
    private TableColumn<?, ?> tableHistoryTotal;

    @FXML
    private TextField inputSearchHistory;

    @FXML
    private Button buttonSearchHistory;

    @FXML
    private GridPane pnShowMaskapai;

    @FXML
    private TableColumn<?, ?> tableShowMaskapaiId;

    @FXML
    private TableColumn<?, ?> tableShowMaskapai;

    @FXML
    private TableColumn<?, ?> tableShowMaskapaiAksi;

    @FXML
    private TextField inputSearchMaskapai;

    @FXML
    private Button buttonShowMaskapai;

    @FXML
    private GridPane pnShowTiket;

    @FXML
    private TableView<Tiket> tableViewShowTiket;

    @FXML
    private TableColumn<Tiket, Integer> tableShowTiketId;

    @FXML
    private TableColumn<Tiket, String> tableShowTiketMaskapai;

    @FXML
    private TableColumn<Tiket, String> tableShowTiketTujuan;

    @FXML
    private TableColumn<Tiket, String> tableShowTiketWaktu;

    @FXML
    private TableColumn<Tiket, String> tableShowTiketKelas;

    @FXML
    private TableColumn<Tiket, Integer> tableShowTiketStok;

    @FXML
    private TextField inputSearchShowTiket;

    @FXML
    private Button buttonSearchShowTiket;

    @FXML
    private GridPane pnTiket;

    @FXML
    private ComboBox<String> optionMaskapai;

    @FXML
    private TextField inputTujuan;

    @FXML
    private DatePicker inputWaktu;

    @FXML
    private TextField inputStok;

    @FXML
    private TextField inputKelas;

    @FXML
    private Button createButtonTiket;

    @FXML
    private TextField inputIdTiket;

    @FXML
    private Button updateButtonTiket;

    @FXML
    private GridPane pnMaskapai;

    @FXML
    private TableView<Maskapai> tableViewMaskapai;

    @FXML
    private TableColumn<Maskapai, Integer> colIdMaskapai;

    @FXML
    private TableColumn<Maskapai, String> tableMaskapai;

    @FXML
    private TextField inputCreateMaskapai;

    @FXML
    private TextField inputUpdateMaskapai;

    @FXML
    private Button createButtonMaskapai;

    @FXML
    private Button updateButtonMaskapai;

    @FXML
    private Button deleteButtonMaskapai;

    @FXML
    private Pane pnlStatus;

    @FXML
    private Label lblStatus;

    @FXML
    private Label labelTiket;

    /**
     * Method buttonCreateMaskapai dari onAction pane create maskapai untuk mengatur fungsi button dalam insert, update, delete
     *
     * @param event untuk menampung nilai dari aksi yang dilakukan button
     */
    @FXML
    void buttonCreateMaskapai(ActionEvent event) {
        if (event.getSource() == createButtonMaskapai) {
            if (!inputCreateMaskapai.getText().isBlank()) {
                dialogAlert("Create Maskapai", "Maskapai berhasil dibuat");
                insertMaskapai();
                inputCreateMaskapai.clear();
            }else{
                dialogAlertDanger("Create Maskapai", "Inputan maskapai tidak boleh kosong");
            }
        } else if (event.getSource() == updateButtonMaskapai) {
            if (!inputCreateMaskapai.getText().isBlank() && !inputUpdateMaskapai.getText().isBlank()) {
                dialogAlert("Update Maskapai", "Maskapai dengan id "+inputUpdateMaskapai.getText()+" berhasil diupdate");
                updateMaskapai();
                inputCreateMaskapai.clear();
            }else {
                dialogAlertDanger("Update Maskapai", "Inputan id dan maskapai baru tidak boleh kosong");
            }
        } else if (event.getSource() == deleteButtonMaskapai) {
            if (!inputUpdateMaskapai.getText().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Maskapai");
                alert.setContentText("Apakah anda yakin menghapus maskapai id "+inputUpdateMaskapai.getText()+" ?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    dialogAlert("Delete Maskapai", "Maskapai dengengan id "+inputUpdateMaskapai.getText()+" berhasil dihapus");
                    deleteMaskapai();
                }else {
                    System.out.println("close");
                }
                inputCreateMaskapai.clear();
            }else {
                dialogAlertDanger("Delete Maskapai", "Inputan id maskapai tidak boleh kosong");
            }
        }
    }

    public void dialogAlert(String title, String konteks){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(konteks);
        alert.showAndWait();
    }

    public void dialogAlertDanger(String title, String konteks){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(konteks);
        alert.showAndWait();
    }
    /**
     * Method buttonCreateTiket dari onAction pane create maskapai untuk mengatur fungsi button dalam insert, update, delete
     *
     * @param event untuk menampung nilai dari aksi yang dilakukan button
     */
    @FXML
    void buttonCreateTiket(ActionEvent event) {
        if (event.getSource() == createButtonTiket) {
            if (!inputTujuan.getText().isBlank() || !inputStok.getText().isBlank() || !inputKelas.getText().isBlank()) {
                if (inputStok.getText().matches("[0-9]")) {
                    dialogAlert("Create Tiket", "Tiket berhasil dibuat");
                    insertTiket();
                }else {
                    dialogAlertDanger("Create tiket", "Stok harus angka");
                    labelTiket.setText("Stok harus angka");
                }
                inputTujuan.clear();
                inputStok.clear();
                inputKelas.clear();
            } else {
                dialogAlertDanger("Create Tiket", "Field tidak boleh kosong");
                labelTiket.setText("Field tidak boleh kosong");
            }
        } else if (event.getSource() == updateButtonTiket) {
            if (!inputTujuan.getText().isBlank() || !inputStok.getText().isBlank() || !inputKelas.getText().isBlank()) {
                if(!inputIdTiket.getText().isBlank()){
                    dialogAlert("Update Tiket", "Tiket dengan id "+inputIdTiket.getText()+" berhasil diupdate");
                    updateTiket();
                }else {
                    dialogAlertDanger("Update Tiket", "Field id tidak ada yang boleh kosong dalam update");
                }
                inputTujuan.clear();
                inputStok.clear();
                inputKelas.clear();
            } else {
                dialogAlertDanger("Update Tiket", "Field tidak ada yang boleh kosong");
                labelTiket.setText("Field tidak boleh kosong");
            }
        } else if (event.getSource() == buttonSearchShowTiket) {
            if (!inputSearchShowTiket.getText().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Tiket");
                alert.setContentText("Apakah anda yakin menghapus tiket id "+inputSearchShowTiket.getText()+" ?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    dialogAlert("Delete Tiket", "Tiket dengengan id "+inputSearchShowTiket.getText()+" berhasil dihapus");
                    deleteTiket();
                }else {
                    System.out.println("close");
                }
                inputSearchShowTiket.clear();
            } else {
                dialogAlertDanger("Delete Tiket", "Field id tidak boleh kosong");
                labelTiket.setText("tidak boleh kosong");
            }
        }
    }

    /**
     * Method untuk menginisialisasi ketika program dibuka maka akan menjalankan method di dalamnya
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showMaskapai();
        showOptionMaskapai();
        showTiket();
    }

    /**
     * Method untuk menampilkan data namaMaskapai dari tabel db maskapai untuk ditampilkan pada dropdown option
     */
    private void showOptionMaskapai() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement st;
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM maskapai");
            while (rs.next()) {
                list.add(rs.getString("namaMaskapai"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        optionMaskapai.setItems(null);
        optionMaskapai.setItems(list);
    }

    /**
     * Method untuk mengatur button yang ada pada menu admin dan mengarahkan ke gridpane sesuai yang dipilih
     *
     * @param actionEvent untuk menampung nilai dari aksi yang dilakukan mouse
     */
    @FXML
    public void handleClicks(javafx.event.ActionEvent actionEvent) {
        if (actionEvent.getSource() == createMaskapai) {
            lblStatus.setText("Create Maskapai");
            pnMaskapai.toFront();
//            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(63, 43, 99), CornerRadii.EMPTY, Insets.EMPTY)));
        } else if (actionEvent.getSource() == createTiket) {
            lblStatus.setText("Create Tiket");
            pnTiket.toFront();
        } else if (actionEvent.getSource() == showMaskapai) {
            lblStatus.setText("Show Maskapai");
            pnShowMaskapai.toFront();
        } else if (actionEvent.getSource() == showTiket) {
            lblStatus.setText("Show Tiket");
            pnShowTiket.toFront();
        }else if (actionEvent.getSource() == history) {
            lblStatus.setText("Show History");
            pnHistory.toFront();
        }
    }

    /**
     * Method untuk menghentikan program ketika mengeklik close pada menu admin
     * @param actionEvent untuk menampung nilai dari aksi klik yang dilakukan mouse
     */
    @FXML
    public void handleClose(MouseEvent actionEvent) {
        if (actionEvent.getSource() == close) {
            System.exit(0);
        }
    }

    /**
     * Method untuk menampilkan data maskapai dari db untuk ditampilkan pada tabel admin, memanggil class Maskapai untuk menyimpan data dari db
     *
     * @return maskapaiList
     */
    public ObservableList<Maskapai> getMaskapaiList() {
        ObservableList<Maskapai> maskapaiList = FXCollections.observableArrayList();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql = "SELECT * FROM MASKAPAI";
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            Maskapai maskapai;
            while (rs.next()) {
                maskapai = new Maskapai(rs.getInt("idMaskapai"), rs.getString("namaMaskapai"));
                maskapaiList.add(maskapai);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return maskapaiList;
    }

    /**
     * Method untuk menampilkan pada tabel dengan memakai nama kolom dan diberikan nilai
     */
    public void showMaskapai() {
        ObservableList<Maskapai> list = getMaskapaiList();
        colIdMaskapai.setCellValueFactory(new PropertyValueFactory<Maskapai, Integer>("idMaskapai"));
        tableMaskapai.setCellValueFactory(new PropertyValueFactory<Maskapai, String>("namaMaskapai"));
        tableViewMaskapai.setItems(list);
    }

    /**
     * Method yang berisi query untuk memasukan data dari form menuju database
     */
    private void insertMaskapai() {
        String sql = "INSERT INTO maskapai (namaMaskapai) VALUES('" + inputCreateMaskapai.getText() + "')";
        executeQuery(sql);
        showOptionMaskapai();
        showMaskapai();
    }

    /**
     * Method untuk mengupdate data jika terjadi kesalahan pada data yang telah diinputkan
     */
    private void updateMaskapai() {
        String sql = "UPDATE maskapai SET namaMaskapai = '" + inputCreateMaskapai.getText() + "' WHERE idMaskapai = " + inputUpdateMaskapai.getText() + "";
        executeQuery(sql);
        showOptionMaskapai();
        showMaskapai();
    }

    /**
     * Method untuk menget data dari database untuk nantinya ditampilkan pada tabel
     *
     * @return tiketList
     */
    public ObservableList<Tiket> getTiketList() {
        ObservableList<Tiket> tiketList = FXCollections.observableArrayList();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql = "SELECT * FROM tiket";
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            Tiket tiket;
            while (rs.next()) {
                tiket = new Tiket(rs.getInt("idTiket"), rs.getString("tujuan"), rs.getString("waktu"), rs.getInt("stok"), rs.getString("kelas"), rs.getString("namaMaskapai"));
                tiketList.add(tiket);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tiketList;
    }

    /**
     * Method untuk menampilkan data dari database ke tabel halaman show tiket
     */
    public void showTiket() {
        ObservableList<Tiket> list = getTiketList();
        tableShowTiketId.setCellValueFactory(new PropertyValueFactory<Tiket, Integer>("idTiket"));
        tableShowTiketMaskapai.setCellValueFactory(new PropertyValueFactory<Tiket, String>("namaMaskapai"));
        tableShowTiketTujuan.setCellValueFactory(new PropertyValueFactory<Tiket, String>("tujuan"));
        tableShowTiketWaktu.setCellValueFactory(new PropertyValueFactory<Tiket, String>("waktu"));
        tableShowTiketStok.setCellValueFactory(new PropertyValueFactory<Tiket, Integer>("stok"));
        tableShowTiketKelas.setCellValueFactory(new PropertyValueFactory<Tiket, String>("kelas"));
        tableViewShowTiket.setItems(list);
    }

    /**
     * Method untuk memasukan data dari form tiket menuju database tabel tiket
     */
    private void insertTiket() {
        String sql = "INSERT INTO tiket (namaMaskapai, tujuan, waktu, stok, kelas) VALUES('" + optionMaskapai.getValue() + "','" + inputTujuan.getText() + "','" + inputWaktu.getValue() + "'," + inputStok.getText() + ",'" + inputKelas.getText() + "')";
        executeQuery(sql);
        showTiket();
        showOptionMaskapai();
        System.out.println("lalalal" + inputWaktu.toString());
    }

    /**
     * Method untuk mengupdate data tiket yang telah diinputkan jika terjadi kesalahan input
     */
    private void updateTiket() {
        String sql = "UPDATE tiket SET namaMaskapai = '" + optionMaskapai.getValue() + "', tujuan = '" + inputTujuan.getText() + "', waktu = '" + inputWaktu.getValue() + "', stok = " + inputStok.getText() + ", kelas = '" + inputKelas.getText() + "' WHERE idTiket = " + inputIdTiket.getText() + "";
        executeQuery(sql);
        showOptionMaskapai();
        showTiket();
    }

    /**
     * Method berfungsi untuk menjalankan query yang telah dibuat pada sebuah method
     *
     * @param sql untuk menampung data dari argumen yang berisi query sql untuk dieksekusi
     */
    private void executeQuery(String sql) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement st;
        try {
            st = connection.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Method untuk menghapus data maskapai dengan id tertentu
     */
    private void deleteMaskapai() {
        String sql = "DELETE FROM maskapai WHERE idMaskapai = " + inputUpdateMaskapai.getText() + "";
        executeQuery(sql);
        showOptionMaskapai();
        showMaskapai();
    }

    /**
     * Method untuk menghapus data tiket dengan id tertentu
     */
    private void deleteTiket() {
        String sql = "DELETE FROM tiket WHERE idtiket = " + inputSearchShowTiket.getText() + "";
        executeQuery(sql);
        showOptionMaskapai();
        showTiket();
    }

    /**
     * method untuk memberikan efek hover dengan berbeda warna dan ukuran dari normal
     *
     * @param mouseEvent untuk menampung nilai dari aksi yang dilakukan mouse
     */
    public void hover(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == createMaskapai) {
            createMaskapai.setStyle("-fx-background-color: #66b3ff;-fx-font-size:17;");
        } else if (mouseEvent.getSource() == createTiket) {
            createTiket.setStyle("-fx-background-color: #66b3ff;-fx-font-size:17;");
        } else if (mouseEvent.getSource() == showMaskapai) {
            showMaskapai.setStyle("-fx-background-color: #66b3ff;-fx-font-size:17;");
        } else if (mouseEvent.getSource() == showTiket) {
            showTiket.setStyle("-fx-background-color: #66b3ff;-fx-font-size:17;");
        }else if (mouseEvent.getSource() == close) {
            close.setStyle("-fx-background-color: #66b3ff;-fx-font-size:17;");
        } else if (mouseEvent.getSource() == history) {
            history.setStyle("-fx-background-color: #66b3ff;-fx-font-size:17;");
        }
    }

    /**
     * Method untuk mengembalikan kondisi setlah hover
     *
     * @param mouseEvent untuk menampung nilai dari aksi yang dilakukan mouse
     */
    public void unhover(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == createMaskapai) {
            createMaskapai.setStyle("-fx-background-color:transparent;");
        } else if (mouseEvent.getSource() == createTiket) {
            createTiket.setStyle("-fx-background-color: transparent;");
        } else if (mouseEvent.getSource() == showMaskapai) {
            showMaskapai.setStyle("-fx-background-color: transparent;");
        } else if (mouseEvent.getSource() == showTiket) {
            showTiket.setStyle("-fx-background-color: transparent;");
        }else if (mouseEvent.getSource() == close) {
            close.setStyle("-fx-background-color: transparent;");
        } else if (mouseEvent.getSource() == history) {
            history.setStyle("-fx-background-color: transparent;");
        }
    }
}