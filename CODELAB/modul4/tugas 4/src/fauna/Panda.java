package fauna;

/**
 * Kelas Panda adalah subclass dari Hewan.
 * Ini menerapkan polymorphism dengan cara override method dari superclass.
 */
public class Panda extends Hewan {

    // Constructor memanggil constructor superclass
    public Panda(String nama) {
        super(nama);
    }

    // Override metode suara() dari kelas Hewan
    @Override
    public void suara() {
        System.out.println(nama + " mengeluarkan suara: 'Munch munch...'");
    }

    // Override metode makan() dari kelas Hewan
    @Override
    public void makan() {
        System.out.println(nama + " sedang makan bambu.");
    }

    // Metode khusus milik Panda
    public void tidur() {
        System.out.println(nama + " sedang tidur nyenyak.");
    }
}
