import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManajemenStok {
    public static void main(String[] args) {
        ArrayList<Barang> daftarBarang = new ArrayList<>();
        // Tambahkan data awal
        try (Scanner scanner = new Scanner(System.in)) {
            // Tambahkan data awal
           // daftarBarang.add(new Barang("Pensil", 10));
           // daftarBarang.add(new Barang("Buku", 5));
           // daftarBarang.add(new Barang("Penghapus", 7));
            
            int pilihan = -1;
            
            while (pilihan != 0) {
                System.out.println("\n=== MENU MANAJEMEN STOK ===");
                System.out.println("1. Tambah Barang Baru");
                System.out.println("2. Tampilkan Semua Barang");
                System.out.println("3. Kurangi Stok Barang");
                System.out.println("0. Keluar");
                System.out.print("Pilih menu: ");
                
                try {
                    pilihan = scanner.nextInt();
                    scanner.nextLine(); // flush newline
                    
                    switch (pilihan) {
                        case 1 -> {
                            System.out.print("Masukkan nama barang: ");
                            String nama = scanner.nextLine();
                            System.out.print("Masukkan stok awal: ");
                            try {
                                int stok = scanner.nextInt();
                                scanner.nextLine();
                                daftarBarang.add(new Barang(nama, stok));
                                System.out.println("Barang berhasil ditambahkan.");
                            } catch (InputMismatchException e) {
                                System.out.println("Input stok harus berupa angka!");
                                scanner.nextLine(); // flush buffer
                            }
                        }
                        
                        case 2 -> {
                            if (daftarBarang.isEmpty()) {
                                System.out.println("Stok barang kosong.");
                            } else {
                                System.out.println("Daftar Barang:");
                                for (int i = 0; i < daftarBarang.size(); i++) {
                                    Barang b = daftarBarang.get(i);
                                    System.out.println(i + ". " + b.getNama() + " (Stok: " + b.getStok() + ")");
                                }
                            }
                        }
                        
                        case 3 -> {
                            if (daftarBarang.isEmpty()) {
                                System.out.println("Tidak ada barang untuk dikurangi.");
                                break;
                            }
                            
                            System.out.println("Pilih barang yang ingin dikurangi stoknya:");
                            for (int i = 0; i < daftarBarang.size(); i++) {
                                Barang b = daftarBarang.get(i);
                                System.out.println(i + ". " + b.getNama() + " (Stok: " + b.getStok() + ")");
                            }
                            
                            try {
                                System.out.print("Masukkan nomor indeks barang: ");
                                int index = scanner.nextInt();
                                
                                System.out.print("Masukkan jumlah yang ingin dikurangi: ");
                                int jumlahDiambil = scanner.nextInt();
                                scanner.nextLine();
                                
                                Barang barangDipilih = daftarBarang.get(index);
                                
                                if (jumlahDiambil > barangDipilih.getStok()) {
                                    throw new StokTidakCukupException("Stok untuk " + barangDipilih.getNama() +
                                            " hanya tersisa " + barangDipilih.getStok());
                                }
                                
                                barangDipilih.setStok(barangDipilih.getStok() - jumlahDiambil);
                                System.out.println("Stok berhasil dikurangi.");
                                
                            } catch (InputMismatchException e) {
                                System.out.println("Input harus berupa angka!");
                                scanner.nextLine();
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Indeks barang tidak valid.");
                            } catch (StokTidakCukupException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                        
                        case 0 -> System.out.println("Terima kasih!");
                        
                        default -> System.out.println("Pilihan tidak valid.");
                    }
                    
                } catch (InputMismatchException e) {
                    System.out.println("Input harus berupa angka!");
                    scanner.nextLine();
                }
            }
        }
    }
}
