import java.time.LocalDate;
import java.util.Scanner;

public class UserData {
    public static void main(String[] args) {
        // Membuat objek Scanner
        Scanner scanner = new Scanner(System.in);

        // Meminta input dari pengguna
        System.out.print("Masukkan Nama               : ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan Jenis Kelamin (L/P): ");
        char jenisKelaminInput = scanner.next().charAt(0);

        System.out.print("Masukkan Tahun Lahir        : ");
        int tahunLahir = scanner.nextInt();

        // Menutup Scanner
        scanner.close();

        // Mendapatkan tahun saat ini
        int tahunSekarang = LocalDate.now().getYear();

        // Menghitung umur
        int umur = tahunSekarang - tahunLahir;

        // Menentukan jenis kelamin
        String jenisKelamin;
        if (jenisKelaminInput == 'L' || jenisKelaminInput == 'l') {
            jenisKelamin = "Laki-laki";
        } else if (jenisKelaminInput == 'P' || jenisKelaminInput == 'p') {
            jenisKelamin = "Perempuan";
        } else {
            jenisKelamin = "Tidak valid";
        }

        // Menampilkan output
        System.out.println("\n=== Data Diri Anda ===");
        System.out.println("Nama         : " + nama.toUpperCase());
        System.out.println("Jenis Kelamin: " + jenisKelamin);
        System.out.println("Umur         : " + umur + " tahun");
    }
}
