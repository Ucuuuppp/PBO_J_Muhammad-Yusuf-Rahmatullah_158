package com.praktikum.users;

import com.praktikum.actions.MahasiswaActions;
import com.praktikum.data.Item;
import com.praktikum.main.LoginSystem;
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
        int pilihan;
        do {
            System.out.println("\n=== Menu Mahasiswa ===");
            System.out.println("1. Laporkan Barang Temuan/Hilang");
            System.out.println("2. Lihat Daftar Laporan");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> reportItem();
                case 2 -> viewReportedItems();
                case 0 -> {System.out.println("Logout...");
                LoginSystem.main(null);
                    }
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    @Override
    public void reportItem() { // untuk melaporkan barang
        Scanner scanner = new Scanner(System.in);
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
    }

    @Override
    public void viewReportedItems() {
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
    }
}
