package fauna;

public class Main {
    public static void main(String[] args) {
        // Polymorphism: referensi tipe Hewan menunjuk objek Panda
        Hewan hewan1 = new Panda("Panda Merah");

        // Meskipun tipe referensinya Hewan, method yang dijalankan adalah milik Panda (runtime polymorphism)
        hewan1.suara();  // Output: Panda Merah mengeluarkan suara: 'Munch munch...'
        hewan1.makan();  // Output: Panda Merah sedang makan bambu.

        // Jika ingin mengakses metode khusus milik Panda, perlu casting
        if (hewan1 instanceof Panda panda) {
            panda.tidur();  // Output: Panda Merah sedang tidur nyenyak.
        }
    }
}
