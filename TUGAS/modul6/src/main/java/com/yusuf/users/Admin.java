package com.yusuf.users;

import com.yusuf.actions.AdminActions;
import com.yusuf.data.DataStore;

public class Admin extends User implements AdminActions {
    private final String username;
    private final String password;

    public Admin(String nama, String nim, String username, String password) {
        super(nama, nim);
        this.username = username;
        this.password = password;
    }

    @Override
    public User login(User user) {
        for (User userObj : DataStore.getAdminData()) {
            Admin admin = (Admin) userObj;
            if (((Admin) user).username.equals(admin.username)
                    && ((Admin) user).password.equals(admin.password)) {
                return userObj;
            }
        }
        return null;
    }

    @Override
    public void manageItems() {
    }

    @Override
    public void manageUsers() {
    }

    @Override
    public void displayAppMenu() {
    }

}
