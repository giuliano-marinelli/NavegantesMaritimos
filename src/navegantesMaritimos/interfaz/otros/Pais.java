package navegantesMaritimos.interfaz.otros;

/**
 * Permite representar un pais con su alias.
 */
public class Pais {

    private String alias;
    private String nombre;

    public Pais(String alias, String nombre) {
        this.alias = alias;
        this.nombre = nombre;
    }

    public String getAlias() {
        return this.alias;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
