package com.company;

public class WordClass {
    private String word;
    private String classification;

    public WordClass(String word, String classification) {
        this.word = word;
        this.classification = classification;

    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return "WordClass{" +
                "word='" + word + '\'' +
                ", classification='" + classification + '\'' +
                '}';
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
