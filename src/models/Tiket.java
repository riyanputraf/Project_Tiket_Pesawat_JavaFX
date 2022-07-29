package models;

public class Tiket {
    private int idTiket;
    private int stok;
    private String namaMaskapai;
    private String tujuan;
    private String waktu;
    private String kelas;

    public Tiket(int idTiket, String tujuan, String waktu, int stok, String kelas, String namaMaskapai) {
        this.idTiket = idTiket;
        this.namaMaskapai = namaMaskapai;
        this.stok = stok;
        this.tujuan = tujuan;
        this.waktu = waktu;
        this.kelas = kelas;

    }

    public Tiket(int idTiket, String namaMaskapai, String tujuan, String waktu, String kelas ) {
        this.idTiket = idTiket;
        this.namaMaskapai = namaMaskapai;
        this.tujuan = tujuan;
        this.waktu = waktu;
        this.kelas = kelas;

    }



    public int getIdTiket() {
        return idTiket;
    }

    public void setIdTiket(int idTiket) {
        this.idTiket = idTiket;
    }

    public String getNamaMaskapai() {
        return namaMaskapai;
    }

    public void setNamaMaskapai(String namaMaskapai) {
        this.namaMaskapai = namaMaskapai;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }
}
