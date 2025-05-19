package com.praktikum.users;

import com.praktikum.actions.AdminActions;
import com.praktikum.data.Item;
import com.praktikum.main.LoginSystem;
import java.util.Scanner;

public class Admin extends User implements AdminActions {
    private String username;
    private String password;

    public Admin(String nama, String nim, String username, String password) {
        super(nama, nim);
        this.username = username;
        this.password = password;
    }

    @Override
    public User login(User user) {
        for (User userObj : LoginSystem.userList) {
            if (userObj instanceof Admin admin) {
                if (((Admin) user).username.equals(admin.username) && ((Admin) user).password.equals(admin.password)) {
                    return userObj;
                }
            }
        }
        return null;
    }

    @Override
    public void displayAppMenu() {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        do {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Kelola Laporan Barang");
            System.out.println("2. Kelola Data Mahasiswa");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> manageItems();
                case 2 -> manageUsers();
                case 0 -> {System.out.println("Logout...");
                LoginSystem.main(null);
                    }
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    @Override
    public void manageItems() {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        do {
            System.out.println("\n=== Menu Kelola Barang ===");
            System.out.println("1. Lihat Semua Barang");
            System.out.println("2. Tandai Barang Telah Diambil (Claimed)");
            System.out.println("3. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();
            switch (pilihan) {
                case 1 -> {
                    for (Item item : LoginSystem.reportedItems) {
                        System.out.println("Nama Barang: " + item.getItemName());
                        System.out.println("Deskripsi: " + item.getDescription());
                        System.out.println("Lokasi: " + item.getLocation());
                        System.out.println("Status: " + item.getStatus());
                        System.out.println();
                    }
                }
                case 2 -> {
                    for (int i = 0; i < LoginSystem.reportedItems.size(); i++) {
                        Item item = LoginSystem.reportedItems.get(i);
                        if (item.getStatus().equals("Reported")) {
                            System.out.println(i + ". Nama Barang: " + item.getItemName() +
                                    "\n   Deskripsi: " + item.getDescription() +
                                    "\n   Lokasi: " + item.getLocation() +
                                    "\n   Status: " + item.getStatus());
                            System.err.println();
                        }
                    }
                    System.out.print("Masukkan indeks yang ingin ditandai: ");
                    int selectedIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (selectedIndex < LoginSystem.reportedItems.size() && selectedIndex >= 0) {
                        LoginSystem.reportedItems.get(selectedIndex).setStatus("Claimed");
                    }
                }
                case 3 -> {
                    displayAppMenu();
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 3);
    }

    @Override
    public void manageUsers() {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        do {
            System.out.println("\n=== Menu Kelola User ===");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa");
            System.out.println("3. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();
            switch (pilihan) {
                case 1 -> {
                    System.out.println("== Menu Tambah Mahasiswa ==\n");
                    System.out.println("Masukkan Nama: ");
                    String nama = scanner.nextLine();
                    System.out.println("Masukkan NIM: ");
                    String nim = scanner.nextLine();

                    LoginSystem.userList.add(new Mahasiswa(nama, nim));
                }
                case 2 -> {
                    System.out.println("== Menu Hapus Mahasiswa ==\n");
                    System.out.println("Masukkan NIM: ");
                    String nim = scanner.nextLine();
                    boolean deleted = false;

                    for (int i = 0; i < LoginSystem.userList.size(); i++) {
                        User user = LoginSystem.userList.get(i);
                        if (user instanceof Mahasiswa m && nim.equals(m.getNim())) {
                            LoginSystem.userList.remove(i);
                            deleted = true;
                            break;
                        }
                    }

                    if (!deleted) {
                        System.out.println("Mahasiswa dengan NIM tersebut tidak ditemukan.");
                    } else {
                        System.out.println("Mahasiswa berhasil dihapus.");
                    }
                }
                case 3 -> {
                    displayAppMenu();
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 3);
    }
}
