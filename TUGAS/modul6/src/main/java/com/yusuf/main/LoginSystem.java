package com.yusuf.main;

import com.yusuf.users.Admin;
import com.yusuf.users.Mahasiswa;
import com.yusuf.users.User;

public class LoginSystem {
    public static User currentUser = null;

    public static User userLogin(String role, String username, String password) {
        Admin admin = new Admin(null, null, null, null);
        Mahasiswa mhs = new Mahasiswa(null, null);

        User user = null;

        if (role.equalsIgnoreCase("admin")) {
            user = admin.login(new Admin(null, null, username, password)); 
        } else if (role.equalsIgnoreCase("mahasiswa")) {
            user = mhs.login(new Mahasiswa(username, password)); 
        }
        return user;
    }
}
