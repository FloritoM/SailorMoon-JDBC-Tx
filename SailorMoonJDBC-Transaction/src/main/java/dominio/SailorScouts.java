package dominio;

import java.util.Objects;

public class SailorScouts {
    
    private int id_sailor;
    
    private String nombre;
    
    private String apellido;
    
    private String planeta;

    public SailorScouts() {
    }

    public SailorScouts(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public SailorScouts(int id_sailor, String nombre, String apellido) {
        this.id_sailor = id_sailor;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public SailorScouts(int id_sailor, String nombre, String apellido, String planeta) {
        this.id_sailor = id_sailor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.planeta = planeta;
    }
    
    public SailorScouts(String nombre, String apellido, String planeta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.planeta = planeta;
    }
    
    public int getId_sailor() {
        return id_sailor;
    }

    public void setId_sailor(int id_sailor) {
        this.id_sailor = id_sailor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPlaneta() {
        return planeta;
    }

    public void setPlaneta(String planeta) {
        this.planeta = planeta;
    }

    @Override
    public String toString() {
        return "SailorScouts{" + "id_sailor=" + id_sailor + ", nombre=" + nombre + ", apellido=" + apellido + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id_sailor;
        hash = 61 * hash + Objects.hashCode(this.nombre);
        hash = 61 * hash + Objects.hashCode(this.apellido);
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
        final SailorScouts other = (SailorScouts) obj;
        if (this.id_sailor != other.id_sailor) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        return true;
    }
    
    
}
