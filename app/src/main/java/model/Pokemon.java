package model;

public class Pokemon{
    private String pokeName;
    private String pokeUrl;

    public Pokemon(String pokeName, String pokeUrl) {
        this.pokeName = pokeName;
        this.pokeUrl = pokeUrl;
    }

    public String getPokeName() {
        return pokeName;
    }

    public void setPokeName(String pokeName) {
        this.pokeName = pokeName;
    }

    public String getPokeUrl() {
        return pokeUrl;
    }

    public void setPokeUrl(String pokeUrl) {
        this.pokeUrl = pokeUrl;
    }
}