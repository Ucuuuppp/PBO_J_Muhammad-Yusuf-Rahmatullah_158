    package com.praktikum.users;

    public abstract class User {
        private final String nama;
        private final String nim;

        public User(String nama, String nim) {
            this.nama = nama;
            this.nim = nim;
        }

        public String getNama() {
            return nama;
        }

        public String getNim() {
            return nim;
        }
        
        public abstract User login(User user);
        public abstract void displayAppMenu(); // method untuk menampilkan menu spesifik peran
    }
