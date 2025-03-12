// Kelas Hewan dengan atribut nama, jenis, dan suara
class Hewan {
    String nama;
    String jenis;
    String suara;

    // Konstruktor untuk inisialisasi atribut, this=memanggil att kls
    public Hewan(String nama, String jenis, String suara) {
        this.nama = nama;
        this.jenis = jenis;
        this.suara = suara;
    }

    // Metode untuk menampilkan informasi hewan
    public void tampilkanInfo() {
        System.out.println("Nama  : " + this.nama);
        System.out.println("Jenis : " + this.jenis);
        System.out.println("Suara : " + this.suara);
    }
}

// Kelas Main dengan metode main untuk menjalankan program
public class Main {
    public static void main(String[] args) {
        // Membuat objek Hewan pertama: hewan1
        Hewan hewan1 = new Hewan("Kucing", "Mamalia", "Nyann~~");
        // Membuat objek Hewan kedua: hewan2
        Hewan hewan2 = new Hewan("Anjing", "Mamalia", "Woof-Woof!!");

        // Memanggil metode tampilkanInfo() untuk masing-masing objek
        System.out.println("Informasi Hewan 1:");
        hewan1.tampilkanInfo();
        
        System.out.println("\nInformasi Hewan 2:");
        hewan2.tampilkanInfo();
    }
}
