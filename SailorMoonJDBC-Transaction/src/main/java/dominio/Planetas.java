package dominio;

import java.util.Objects;

public class Planetas {
    
    private String planeta;
    
    private int id_sailor;

    public Planetas(String planeta, int id_sailor) {
        this.planeta = planeta;
        this.id_sailor = id_sailor;
    }

    public Planetas(String planeta) {
        this.planeta = planeta;
    }
    
    public String getPlaneta() {
        return planeta;
    }

    public void setPlaneta(String planeta) {
        this.planeta = planeta;
    }

    public int getId_sailor() {
        return id_sailor;
    }

    public void setId_sailor(int id_sailor) {
        this.id_sailor = id_sailor;
    }

    @Override
    public String toString() {
        return planeta;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.planeta);
        hash = 47 * hash + this.id_sailor;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Planetas other = (Planetas) obj;
        if (this.id_sailor != other.id_sailor) {
            return false;
        }
        if (!Objects.equals(this.planeta, other.planeta)) {
            return false;
        }
        return true;
    }
    
    
}
