package com.praktikum.main;

import com.praktikum.data.Item;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginSystem {
    static public ArrayList<User> userList = new ArrayList<>();
    static public ArrayList<Item> reportedItems = new ArrayList<>();

    static {
        userList.add(new Admin("Yusuf", "158", "admin158", "pass158"));
        userList.add(new Mahasiswa("Yusuff", "158"));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Admin admin = new Admin(null,null,null,null);
        Mahasiswa mhs = new Mahasiswa(null,null);

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

            user = admin.login(new Admin(null, null, u, p)); // POLYMORPHISM
            if (user == null) {
                System.out.println("Login Admin gagal.");
            }
        } else if (pilihan == 2) {
            System.out.print("Nama: ");
            String n = scanner.nextLine();
            System.out.print("NIM: ");
            String nim = scanner.nextLine();

            user = mhs.login(new Mahasiswa(n, nim)); // POLYMORPHISM
            if (user == null) {
                System.out.println("Login Admin gagal.");
            }
        } else {
            System.out.println("Pilihan tidak valid.");
        }

        if (user != null) {
            user.displayAppMenu();
        }else{
            main(null);
        }

        scanner.close();
    }
}
