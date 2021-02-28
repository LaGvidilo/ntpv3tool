package fr.informabox.nootropiques;

public class StarsFavorite {
    private int id;
    private String name;
    private transient String value;

    public StarsFavorite(int id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public StarsFavorite(int id, String name) {
        this(id, name, null);
    }
}


