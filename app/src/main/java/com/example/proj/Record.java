package com.example.proj;

public class Record {
    private String name;
    private int best_score;

    public Record(String name, int best_score) {
        this.name = name;
        this.best_score = best_score;
    }

    // MUST have the constructor  for the FireBase
    public Record() {
    }

    // MUST generate getters and setters for the FireBase
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBest_score() {
        return best_score;
    }

    public void setBest_score(int best_score) {
        this.best_score = best_score;
    }
}
