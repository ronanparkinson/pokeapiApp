package model;

import java.util.List;

public class PokemonResponce {
    private List<Pokemon> results;
    private String next;
    private String last;
    private int count;

    public PokemonResponce(List<Pokemon> results, String next, String last, int count) {
        this.results = results;
        this.next = next;
        this.last = last;
        this.count = count;
    }

    public List<Pokemon> getResults() {
        return results;
    }

    public void setResults(List<Pokemon> results) {
        this.results = results;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}