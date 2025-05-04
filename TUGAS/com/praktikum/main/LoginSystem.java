package com.praktikum.main;

import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;
import java.util.Scanner;

public class LoginSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Admin admin = new Admin("Yusuf", "158", "admin158", "pass158");
        Mahasiswa mhs = new Mahasiswa("Yusuff", "158");

        System.out.println("=== Login System ===");
        System.out.println("1. Admin");
        System.out.println("2. Mahasiswa");
        System.out.print("Pilih Login: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        User user = null;

        if (pilihan == 1) {
            System.out.print("Username: ");
            String u = scanner.nextLine();
            System.out.print("Password: ");
            String p = scanner.nextLine();

            if (admin.login(u, p)) {
                user = admin; // POLYMORPHISM
            } else {
                System.out.println("Login Admin gagal.");
            }
        } else if (pilihan == 2) {
            System.out.print("Nama: ");
            String n = scanner.nextLine();
            System.out.print("NIM: ");
            String nim = scanner.nextLine();

            if (mhs.login(n, nim)) {
                user = mhs; // POLYMORPHISM
            } else {
                System.out.println("Login Mahasiswa gagal.");
            }
        } else {
            System.out.println("Pilihan tidak valid.");
        }

        if (user != null) {
            user.displayAppMenu();
        }

        scanner.close();
    }
}
