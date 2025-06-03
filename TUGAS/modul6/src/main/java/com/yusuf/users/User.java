    package com.yusuf.users;

    public abstract class User {
        private final String name;
        private final String nim;

        public User(String nama, String nim) {
            this.name = nama;
            this.nim = nim;
        }

        public String getName() {
            return name;
        }

        public String getNim() {
            return nim;
        }
        
        public abstract User login(User user);  
        public abstract void displayAppMenu(); 
    }
