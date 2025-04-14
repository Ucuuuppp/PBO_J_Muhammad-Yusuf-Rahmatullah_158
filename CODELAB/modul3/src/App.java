// Superclass KarakterGame
class KarakterGame {
    private String nama;
    private int kesehatan;

    public KarakterGame(String nama, int kesehatan) {
        this.nama = nama;
        this.kesehatan = kesehatan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public void setKesehatan(int kesehatan) {
        this.kesehatan = kesehatan;
    }

    public void serang(KarakterGame target) {
        // Akan dioverride oleh subclass
    }
}

// Subclass Pahlawan
class Pahlawan extends KarakterGame {
    public Pahlawan(String nama, int kesehatan) {
        super(nama, kesehatan);
    }

    @Override
    public void serang(KarakterGame target) {
        System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan pedang!");
        int sisaKesehatan = target.getKesehatan() - 20;
        target.setKesehatan(sisaKesehatan);
        System.out.println("Kesehatan " + target.getNama() + " sekarang: " + sisaKesehatan);
    }
}

// Subclass Musuh
class Musuh extends KarakterGame {
    public Musuh(String nama, int kesehatan) {
        super(nama, kesehatan);
    }

    @Override
    public void serang(KarakterGame target) {
        System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan sihir!");
        int sisaKesehatan = target.getKesehatan() - 15;
        target.setKesehatan(sisaKesehatan);
        System.out.println("Kesehatan " + target.getNama() + " sekarang: " + sisaKesehatan);
    }
}

// Kelas utama (main)
public class App {
    public static void main(String[] args) {
        // Membuat objek
        KarakterGame karakterUmum = new KarakterGame("Karakter Umum", 100);
        Pahlawan brimstone = new Pahlawan("Brimstone", 150);
        Musuh viper = new Musuh("Viper", 200);

        // Menampilkan status awal
        System.out.println("Status Awal:");
        System.out.println(brimstone.getNama() + " - Kesehatan: " + brimstone.getKesehatan());
        System.out.println(viper.getNama() + " - Kesehatan: " + viper.getKesehatan());
        System.out.println();

        // Simulasi pertarungan
        System.out.println("Pertarungan Dimulai:");
        System.out.println("Brimstone menyerang Viper menggunakan Orbital Strike!");
        brimstone.serang(viper);
        System.out.println();

        System.out.println("Viper menyerang Brimstone menggunakan Snake Bite!");
        viper.serang(brimstone);
    }
}
