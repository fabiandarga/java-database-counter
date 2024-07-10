package de.fabiandarga.databasecounter;

public class Counter {
    private int value;

    public Counter() {
        this.value = 0;
    }

    public int getValue() {
        return value;
    }

    public void increment() {
        this.value++;
    }
}