package com.example.carbook;

public class carClass {
        private String poster;
        private String carName;
        private String carDesc;

        public carClass(String poster, String carName, String carDesc) {
            this.poster = poster;
            this.carName = carName;
            this.carDesc = carDesc;
        }
        public String getPoster() {
            return poster;
        }
        public String getcarName() {
            return carName;
        }
        public String getcarDesc() {
            return carDesc;
        }
    }
