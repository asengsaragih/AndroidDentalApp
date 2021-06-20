package com.mobile.dental.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Bot implements Serializable {

    private List<Layanan> layanan;
    private List<Chatbot> chatbot;

    public List<Layanan> getLayanan() {
        return layanan;
    }

    public List<Chatbot> getChatbot() {
        return chatbot;
    }

    public String getFirstChat() {
        StringBuilder result = new StringBuilder("Selamat datang, cari keluhan anda" + "\n\n");

        for (int i = 0; i < getLayanan().size(); i++) {
            result.append(getLayanan().get(i).getIdLayanan()).append(". ");
            result.append(getLayanan().get(i).getNamaLayanan()).append("\n");
        }

        return result.toString();
    }

    public class Layanan implements Serializable {
        @SerializedName("id_layanan")
        private String idLayanan;

        @SerializedName("nama_layanan")
        private String namaLayanan;

        public Integer getIdLayananInt() {
            return Integer.parseInt(idLayanan);
        }

        public String getIdLayanan() {
            return idLayanan;
        }

        public String getNamaLayanan() {
            return namaLayanan;
        }
    }

    public class Chatbot implements Serializable {
        private String id;
        private String keyword;
        private String deskripsi;

        public Integer getIdInt() {
            return Integer.parseInt(id);
        }

        public String getId() {
            return id;
        }

        public String getKeyword() {
            return keyword;
        }

        public String getDeskripsi() {
            return deskripsi;
        }
    }
}
