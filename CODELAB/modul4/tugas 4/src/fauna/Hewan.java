package fauna;

/**
 * Kelas induk (superclass) bernama Hewan.
 * Ini mendefinisikan perilaku umum yang akan dioverride oleh subclass.
 */
public class Hewan {
    protected String nama;

    // Constructor
    public Hewan(String nama) {
        this.nama = nama;
    }

    // Metode yang akan dioverride oleh subclass
    public void suara() {
        System.out.println("Hewan mengeluarkan suara.");
    }

    public void makan() {
        System.out.println("Hewan sedang makan.");
    }
}
