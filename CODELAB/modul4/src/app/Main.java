package app;

import perpustakaan.*;

public class Main {
    public static void main(String[] args) {
        Buku buku1 = new Fiksi("silat", "yofi");
        Buku buku2 = new NonFiksi("Indonesia maju", "Yofie");

        buku1.displayInfo();
        System.out.println();
        buku2.displayInfo();
        System.out.println();

        Anggota anggota1 = new Anggota("Yusuf", "202158");
        Anggota anggota2 = new Anggota("alif", "202100");

        anggota1.pinjamBuku("silat");
        anggota2.pinjamBuku("Indonesia maju", 7);

        System.out.println();

        anggota1.kembalikanBuku("silat");
    }
}
