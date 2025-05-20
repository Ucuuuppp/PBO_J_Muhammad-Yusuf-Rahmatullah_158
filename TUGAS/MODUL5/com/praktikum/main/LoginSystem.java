package com.praktikum.main;

import com.praktikum.data.Item;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginSystem {
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Item> reportedItems = new ArrayList<>();

    static {
        userList.add(new Admin("yusuf", "158", "ysf", "158"));
        userList.add(new Mahasiswa("y", "111"));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Admin admin = new Admin(null, null, null, null);
        Mahasiswa mhs = new Mahasiswa(null, null);

        try {
            System.out.println("=== Login System ===");
            System.out.println("1. Admin");
            System.out.println("2. Mahasiswa");
            System.out.print("Pilih Login: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            User user = null;

            switch (pilihan) {
                case 1 -> {
                    System.out.print("Username: ");
                    String u = scanner.nextLine();
                    System.out.print("Password: ");
                    String p = scanner.nextLine();
                    user = admin.login(new Admin(null, null, u, p)); // POLYMORPHISM
                    if (user == null) {
                        System.out.println("Login Admin gagal.");
                    }
                }
                case 2 -> {
                    System.out.print("Nama: ");
                    String n = scanner.nextLine();
                    System.out.print("NIM: ");
                    String nim = scanner.nextLine();
                    user = mhs.login(new Mahasiswa(n, nim)); // POLYMORPHISM
                    if (user == null) {
                        System.out.println("Login Mahasiswa gagal.");
                    }
                }
                default -> System.out.println("Pilihan tidak valid.");
            }

            if (user != null) {
                user.displayAppMenu();
            } else {
                main(null); // ulang jika login gagal
            }
        } catch (InputMismatchException e) {
            System.out.println("Input harus berupa angka. Silakan coba lagi.");
            scanner.nextLine(); // bersihkan buffer
            main(null); // restart program
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
            main(null); // restart program
        } finally {
            scanner.close();
        }
    }
}
