package com.praktikum.users;

import com.praktikum.actions.MahasiswaActions;
import com.praktikum.data.Item;
import com.praktikum.main.LoginSystem;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Mahasiswa extends User implements MahasiswaActions {

    public Mahasiswa(String nama, String nim) {
        super(nama, nim);
    }

    @Override
    public User login(User user) {
        for (User userObj : LoginSystem.userList) {
            if (userObj instanceof Mahasiswa mahasiswa) {
                if (((Mahasiswa) user).getNama().equals(mahasiswa.getNama())
                        && ((Mahasiswa) user).getNim().equals(mahasiswa.getNim())) {
                    return userObj;
                }
            }
        }
        return null;
    }

    @Override
    public void displayAppMenu() {
        Scanner scanner = new Scanner(System.in);
        int pilihan = -1;
        do {
            try {
                System.out.println("\n=== Menu Mahasiswa ===");
                System.out.println("1. Laporkan Barang Temuan/Hilang");
                System.out.println("2. Lihat Daftar Laporan");
                System.out.println("0. Logout");
                System.out.print("Pilih menu: ");
                pilihan = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (pilihan) {
                    case 1 -> reportItem();
                    case 2 -> viewReportedItems();
                    case 0 -> {
                        System.out.println("Logout...");
                        LoginSystem.main(null);
                    }
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka. Coba lagi.");
                scanner.nextLine(); // clear buffer
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        } while (pilihan != 0);
    }

    @Override
    public void reportItem() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Nama Barang: ");
            String namaBarang = scanner.nextLine();
            System.out.print("Deskripsi Barang: ");
            String deskripsi = scanner.nextLine();
            System.out.print("Lokasi Ditemukan/Terakhir Dilihat: ");
            String lokasi = scanner.nextLine();

            System.out.println("Laporan berhasil dikirim:");
            System.out.println("Nama Barang: " + namaBarang);
            System.out.println("Deskripsi: " + deskripsi);
            System.out.println("Lokasi: " + lokasi);

            LoginSystem.reportedItems.add(new Item(namaBarang, deskripsi, lokasi, "Reported"));
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat melaporkan barang: " + e.getMessage());
        }
    }

    @Override
    public void viewReportedItems() {
        try {
            if (LoginSystem.reportedItems.size() == 0) {
                System.out.println("Belum ada laporan barang.");
            } else {
                for (Item item : LoginSystem.reportedItems) {
                    if (item.getStatus().equals("Reported")) {
                        System.out.println("Nama Barang: " + item.getItemName());
                        System.out.println("Deskripsi: " + item.getDescription());
                        System.out.println("Lokasi: " + item.getLocation());
                        System.out.println();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menampilkan laporan: " + e.getMessage());
        }
    }
}
