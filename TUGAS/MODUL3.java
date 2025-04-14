package TUGAS;

import java.util.Scanner;

// Kelas LoginSystem sebagai program utama
public class MODUL3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Inisialisasi objek Admin dan Mahasiswa dengan data contoh
        Admin admin = new Admin("Administrator", "0000", "Admin158", "password158");
        Mahasiswa mahasiswa = new Mahasiswa("yusuf", "202110370311158");

        // Tampilan pilihan login
        System.out.println("Pilih Jenis Login:");
        System.out.println("1. Admin");
        System.out.println("2. Mahasiswa");
        System.out.print("Masukkan pilihan: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Mengonsumsi newline yang tersisa
        
        if (pilihan == 1) {
            // Login sebagai Admin
            System.out.print("Masukkan Username: ");
            String inputUsername = scanner.nextLine();
            System.out.print("Masukkan Password: ");
            String inputPassword = scanner.nextLine();
            
            if (admin.login(inputUsername, inputPassword)) {
                admin.displayInfo();
            } else {
                System.out.println("Login gagal! Username atau password salah.");
            }
        } else if (pilihan == 2) {
            // Login sebagai Mahasiswa
            System.out.print("Masukkan Nama: ");
            String inputNama = scanner.nextLine();
            System.out.print("Masukkan NIM: ");
            String inputNim = scanner.nextLine();
            
            if (mahasiswa.login(inputNama, inputNim)) {
                mahasiswa.displayInfo();
            } else {
                System.out.println("Login gagal! Nama atau NIM salah.");
            }
        } else {
            System.out.println("Pilihan tidak valid.");
        }
        
        scanner.close();
    }
}

// Superclass: User
class User {
    // Atribut dienkapsulasi
    private String nama;
    private String nim;

    // Constructor untuk menginisialisasi nama dan nim
    public User(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    // Getter dan Setter untuk atribut nama dan nim
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getNim() {
        return nim;
    }
    
    public void setNim(String nim) {
        this.nim = nim;
    }

    // Method login yang akan dioverride oleh subclass
    public boolean login(String input1, String input2) {
        // Implementasi default tidak melakukan apa-apa
        return false;
    }

    // Method untuk menampilkan informasi pengguna
    public void displayInfo() {
        System.out.println("Informasi Pengguna:");
        System.out.println("Nama: " + this.nama);
        System.out.println("NIM: " + this.nim);
    }
}

// Subclass: Admin
class Admin extends User {
    // Atribut tambahan khusus Admin
    private String username;
    private String password;

    // Constructor menggunakan super untuk menginisialisasi nama dan nim dari User
    public Admin(String nama, String nim, String username, String password) {
        super(nama, nim);
        this.username = username;
        this.password = password;
    }

    // Override method login untuk memverifikasi username dan password
    @Override
    public boolean login(String inputUsername, String inputPassword) {
        return inputUsername.equals(this.username) && inputPassword.equals(this.password);
    }

    // Override method displayInfo untuk menampilkan pesan login sukses bagi Admin
    @Override
    public void displayInfo() {
        System.out.println("Login Admin berhasil!");
        // Menggunakan getter dari superclass untuk mengakses nama dan nim
        System.out.println("Nama: " + getNama());
        System.out.println("NIM: " + getNim());
    }
}

// Subclass: Mahasiswa
class Mahasiswa extends User {

    // Constructor menggunakan super untuk menginisialisasi atribut nama dan nim
    public Mahasiswa(String nama, String nim) {
        super(nama, nim);
    }

    // Override method login untuk mencocokkan input nama dan nim
    @Override
    public boolean login(String inputNama, String inputNim) {
        return inputNama.equals(getNama()) && inputNim.equals(getNim());
    }

    // Override method displayInfo untuk menampilkan pesan login sukses bagi Mahasiswa
    @Override
    public void displayInfo() {
        System.out.println("Login Mahasiswa berhasil!");
        System.out.println("Nama: " + getNama());
        System.out.println("NIM: " + getNim());
    }
}
