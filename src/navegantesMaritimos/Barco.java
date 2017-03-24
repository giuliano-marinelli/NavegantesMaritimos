package navegantesMaritimos;

/**
 * Esta clase representa los barcos que estaran en los puertos.
 */
public class Barco {

    private String nombre;
    private String tipo;
    private String pais;

    /**
     * Constructor de la clase con variable nombre como clave del barco.
     *
     * @param nombre
     * @param tipo
     * @param pais
     */
    public Barco(String nombre, String tipo, String pais) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "[" + nombre + ", " + tipo + ", " + pais + "]";
    }

    /**
     * Permite crear un arreglo de String con los datos del barco.
     *
     * @return el arreglo mencionado.
     */
    public String[] toArray() {
        String[] res = {nombre, tipo, pais};
        return res;
    }

}
