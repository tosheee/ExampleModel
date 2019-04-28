package com.examplemodel.examplemodel;

public class Words {

    public int id;
    public String en_word;
    public String bg_word;

    public Words(){ }

    public Words(int id, String en_word, String bg_word) {
        this.id = id;
        this.en_word = en_word;
        this.bg_word = bg_word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEn_word() {
        return en_word;
    }

    public void setEn_word(String en_word) {
        this.en_word = en_word;
    }

    public String getBg_word() {
        return bg_word;
    }

    public void setBg_word(String bg_word) {
        this.bg_word = bg_word;
    }
}
