// Kelas Rubah merepresentasikan hewan karnivora dengan dua metode unik
class Rubah {
    
    // Metode untuk menampilkan aksi mengintai mangsa
    public void mengintai() {
        System.out.println("Rubah sedang mengintai mangsa di hutan.");
    }
    
    // Metode untuk menampilkan aksi bersembunyi
    public void bersembunyi() {
        System.out.println("Rubah bersembunyi di balik semak-semak untuk menghindari bahaya.");
    }
}

// Kelas Main sebagai titik masuk eksekusi program
public class mod2 {
    public static void main(String[] args) {
        // Membuat objek Rubah
        Rubah rubah = new Rubah();
        
        // Memanggil metode untuk aksi rubah
        rubah.mengintai();
        rubah.bersembunyi();
    }
}
