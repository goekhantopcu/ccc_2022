package de.algebros;

import java.util.ArrayList;
import java.util.List;

public class Fighter {
    private String id;
    private List<Fighter> winsAgains = new ArrayList<>();

    public Fighter(String id, List<Fighter> winsAgains) {
        this.id = id;
        this.winsAgains = winsAgains;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Fighter> getWinsAgains() {
        return winsAgains;
    }

    public void setWinsAgains(List<Fighter> winsAgains) {
        this.winsAgains = winsAgains;
    }
}
