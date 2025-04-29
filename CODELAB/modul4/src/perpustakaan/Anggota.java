package perpustakaan;

public class Anggota implements Peminjaman {
    private String nama;
    private String idAnggota;

// construktor    
    public Anggota(String nama, String idAnggota) {
        this.nama = nama;
        this.idAnggota = idAnggota;
    }

    @Override
    public void pinjamBuku(String judul) {
        System.out.println(nama + " meminjam buku: " + judul);
    }

    // Overloaded method paramater durasi
    public void pinjamBuku(String judul, int durasi) {
        System.out.println(nama + " meminjam buku: " + judul + " untuk " + durasi + " hari.");
    }

    @Override
    public void kembalikanBuku(String judul) {
        System.out.println(nama + " mengembalikan buku: " + judul);
    }

    public String getNama() {
        return nama;
    }

    public String getIdAnggota() {
        return idAnggota;
    }
}
