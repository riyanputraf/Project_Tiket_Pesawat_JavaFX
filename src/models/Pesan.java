package models;

public class Pesan {

    private String namaMaskapai;
    private String tujuan;
    private String waktu;
    private String kelas;

    public Pesan(String namaMaskapai,String tujuan,String waktu,String kelas){
        this.setNamaMaskapai(namaMaskapai);
        this.setTujuan(tujuan);
        this.setWaktu(waktu);
        this.setKelas(kelas);
    }

    public String getNamaMaskapai() {
        return namaMaskapai;
    }

    public void setNamaMaskapai(String namaMaskapai) {
        this.namaMaskapai = namaMaskapai;
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
