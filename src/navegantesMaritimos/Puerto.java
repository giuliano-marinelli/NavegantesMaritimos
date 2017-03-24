package navegantesMaritimos;

import lineales.cola.Cola;
import lineales.lista.Lista;

/**
 * Esta clase representa un puerto, con sus darsenas y cola de espera.
 */
public class Puerto {

    private String nombre;
    private String pais;
    private int cantDarsenas;
    private Barco[] darsenas;
    private Cola<Barco>[] barcosEspera;

    /**
     * Constructor de la clase con variable nombre como clave del puerto.
     *
     * @param nombre
     * @param pais
     * @param cantDarsenas
     */
    public Puerto(String nombre, String pais, int cantDarsenas) {
        this.nombre = nombre;
        this.pais = pais;
        this.cantDarsenas = cantDarsenas;
        darsenas = new Barco[cantDarsenas];
        barcosEspera = new Cola[3];
        //Prioridad para tipo de barco "Pesquero"
        barcosEspera[0] = new Cola();
        //Prioridad para tipo de barco "Pasajeros"
        barcosEspera[1] = new Cola();
        //Prioridad para tipo de barco "Carga"
        barcosEspera[2] = new Cola();
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getCantDarsenas() {
        return cantDarsenas;
    }

    public void setCantDarsenas(int cantDarsenas) {
        this.cantDarsenas = cantDarsenas;
    }

    /**
     * Verifica si un barco indicado esta en las darsenas del puerto.
     *
     * @param barco
     * @return true si el barco esta, false en caso contrario.
     */
    public boolean varificarBarcoDarsena(String barco) {
        boolean res = false;
        int i = 0;
        while (i < cantDarsenas && !res) {
            if (darsenas[i] != null && darsenas[i].getNombre().equals(barco)) {
                res = true;
            }
            i++;
        }
        return res;
    }

    /**
     * Mueve los barcos de la cola de espera a las darsenas segun el orden de
     * prioridad que tienen para entrar, hasta que las darsenas esten llenas.
     */
    public void actualizarDarsenas() {
        int i = 0;
        boolean continuar = true;
        while (i < cantDarsenas && continuar) {
            if (darsenas[i] == null) {
                if (!barcosEspera[0].esVacia()) {
                    darsenas[i] = barcosEspera[0].obtenerFrente();
                    barcosEspera[0].sacar();
                } else if (!barcosEspera[1].esVacia()) {
                    darsenas[i] = barcosEspera[1].obtenerFrente();
                    barcosEspera[1].sacar();
                } else if (!barcosEspera[2].esVacia()) {
                    darsenas[i] = barcosEspera[2].obtenerFrente();
                    barcosEspera[2].sacar();
                } else {
                    continuar = false;
                }
            }
            i++;
        }
    }

    /**
     * Quita el barco de una darsena.
     *
     * @param barco
     * @return true si el barco estaba en alguna darsena, false en caso
     * contrario.
     */
    public boolean sacarBarcoDarsena(String barco) {
        boolean res = false;
        int i = 0;
        while (i < cantDarsenas && !res) {
            if (darsenas[i] != null && darsenas[i].getNombre().equals(barco)) {
                darsenas[i] = null;
                res = true;
            }
            i++;
        }
        return res;
    }

    /**
     * Verifica si un barco indicado esta en la cola de espera del puerto.
     *
     * @param barco
     * @return true si el barco esta, false en caso contrario.
     */
    public boolean verificarBarcoEspera(String barco) {
        boolean res = false;
        int i = 0;
        while (i <= 2 && !res) {
            if (!barcosEspera[i].esVacia()) {
                Cola<Barco> clon = barcosEspera[i].clonar();
                Barco aux = clon.obtenerFrente();
                while (aux != null && !res) {
                    res = (aux.getNombre().equals(barco));
                    clon.sacar();
                    aux = clon.obtenerFrente();
                }
            }
            i++;
        }
        return res;
    }

    /**
     * Determina si el puerto tiene barcos en la cola de espera.
     *
     * @return true si tiene barcos, false en caso contrario.
     */
    public boolean tieneBarcosEspera() {
        return (!barcosEspera[0].esVacia() || !barcosEspera[1].esVacia() || !barcosEspera[2].esVacia());
    }

    /**
     * Pone un barco en la cola de espera del puerto.
     *
     * @param barco
     */
    public void ponerBarcoEspera(Barco barco) {
        switch (barco.getTipo()) {
            case "Pesquero":
                barcosEspera[0].poner(barco);
                break;
            case "Pasajeros":
                barcosEspera[1].poner(barco);
                break;
            case "Carga":
                barcosEspera[2].poner(barco);
                break;
        }
    }

    /**
     * Crea un cadena con los nombres de todos los barcos en la cola de espera
     * del puerto.
     *
     * @return la cadena mencionada.
     */
    public String toStringBarcosEspera() {
        String res = "";
        if (!barcosEspera[0].esVacia() || !barcosEspera[1].esVacia() || !barcosEspera[2].esVacia()) {
            res += "[";
            for (int i = 0; i <= 2; i++) {
                if (!barcosEspera[i].esVacia()) {
                    Cola<Barco> clon = barcosEspera[i].clonar();
                    Barco aux = clon.obtenerFrente();
                    while (aux != null) {
                        res += aux.getNombre();
                        clon.sacar();
                        aux = clon.obtenerFrente();
                        if (aux != null) {
                            res += ", ";
                        }
                    }
                    if (i != 2 && !barcosEspera[i + 1].esVacia()) {
                        res += ", ";
                    }
                }
            }
            res += "]";
        }
        return res;
    }

    /**
     * Permite crear un arreglo de String con los datos basicos del puerto.
     *
     * @return el arreglo mencionado.
     */
    public String[] toArray() {
        String[] res = {nombre, pais, "" + cantDarsenas};
        return res;
    }

    /**
     * Permite crear un arreglo con los nombres de todos los barcos que estan en
     * las darsenas del puerto.
     *
     * @return el arreglo mencionado.
     */
    public String[] toArrayDarsenas() {
        String[] res = new String[cantDarsenas];
        for (int i = 0; i < cantDarsenas; i++) {
            if (darsenas[i] != null) {
                res[i] = darsenas[i].getNombre();
            }
        }
        return res;
    }

    /**
     * Lista todos los barcos en la cola de espera del puerto.
     *
     * @return la lista mencionada.
     */
    public Lista listarBarcosEspera() {
        Lista<String> res = new Lista();
        if (!barcosEspera[0].esVacia() || !barcosEspera[1].esVacia() || !barcosEspera[2].esVacia()) {
            for (int i = 0; i <= 2; i++) {
                if (!barcosEspera[i].esVacia()) {
                    Cola<Barco> clon = barcosEspera[i].clonar();
                    Barco aux = clon.obtenerFrente();
                    while (aux != null) {
                        res.insertar(aux.getNombre(), res.longitud() + 1);
                        clon.sacar();
                        aux = clon.obtenerFrente();
                    }
                }
            }
        }
        return res;
    }

    /**
     * Devuelve la cantidad de barcos que se encuentran en la cola de espera del
     * puerto.
     *
     * @return la cantidad mencionada.
     */
    public int cantBarcosEspera() {
        int res = 0;
        if (!barcosEspera[0].esVacia() || !barcosEspera[1].esVacia() || !barcosEspera[2].esVacia()) {
            for (int i = 0; i <= 2; i++) {
                if (!barcosEspera[i].esVacia()) {
                    Cola<Barco> clon = barcosEspera[i].clonar();
                    Barco aux = clon.obtenerFrente();
                    while (aux != null) {
                        res++;
                        clon.sacar();
                        aux = clon.obtenerFrente();
                    }
                }
            }
        }
        return res;
    }

}
