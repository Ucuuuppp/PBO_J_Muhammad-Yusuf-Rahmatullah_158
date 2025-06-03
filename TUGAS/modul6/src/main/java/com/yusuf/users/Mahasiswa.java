package com.yusuf.users;

import com.yusuf.actions.MahasiswaActions;
import com.yusuf.data.DataStore;

public class Mahasiswa extends User implements MahasiswaActions {

    public Mahasiswa(String nama, String nim) {
        super(nama, nim);
    }

    @Override
    public User login(User user) {
        for (User userObj : DataStore.getMahasiswaData()) {
            Mahasiswa mahasiswa = (Mahasiswa) userObj;
            if (((Mahasiswa) user).getName().equals(mahasiswa.getName())
                    && ((Mahasiswa) user).getNim().equals(mahasiswa.getNim())) {
                return userObj;
            }

        }
        return null;
    }

    @Override
    public void reportItem() {
    }

    @Override
    public void viewReportedItems() {
    }

    @Override
    public void displayAppMenu() {
    }

}
