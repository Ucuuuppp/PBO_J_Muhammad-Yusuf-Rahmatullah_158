package com.yusuf.data;

import com.yusuf.users.Admin;
import com.yusuf.users.Mahasiswa;
import com.yusuf.users.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataStore {

    public static ObservableList<Item> reportedItemsData = FXCollections.observableArrayList();
    public static ObservableList<User> userData = FXCollections.observableArrayList();

    static {
        userData.add(new Admin("yusuf", "158", "ysf", "158"));
        userData.add(new Mahasiswa("y", "111"));
    }

    public static ObservableList<Mahasiswa> getMahasiswaData() {
        ObservableList<Mahasiswa> mahasiswaData = FXCollections.observableArrayList();
        for (User user : userData) {
            if (user instanceof Mahasiswa) { //filtr
                mahasiswaData.add((Mahasiswa) user);
            }
        }
        return mahasiswaData;
    }

    public static ObservableList<Admin> getAdminData() {
        ObservableList<Admin> adminData = FXCollections.observableArrayList();
        for (User user : userData) {
            if (user instanceof Admin) {
                adminData.add((Admin) user);
            }
        }
        return adminData;
    }

}