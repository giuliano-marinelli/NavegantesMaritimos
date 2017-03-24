package navegantesMaritimos;

import lineales.lista.Lista;
import especificos.diccionario.Diccionario;
import grafos.Grafo;
import java.util.HashMap;
import java.util.Set;

/**
 * Logica principal del sistema de navegantes, aqui estan todos los metodos que
 * se utilizaran desde la interfaz
 */
public class NavegantesMaritimos {

    private Diccionario<String, Puerto> tablaPuertos;
    private Grafo<String> rutasMaritimas;
    private HashMap<String, Barco> tablaBarcos;

    /**
     * Constructor de la clase
     */
    public NavegantesMaritimos() {
        tablaPuertos = new Diccionario();
        rutasMaritimas = new Grafo();
        tablaBarcos = new HashMap();
    }

    /**
     * Da de alta un puerto, agregandolo a la tabla de puertos y a las rutas
     * maritimas.
     *
     * @param nombre
     * @param pais
     * @param cantDarsenas
     * @return true si el puerto no existe (respecto a su nombre) y se agrego, o
     * false en caso contrario.
     */
    public boolean altaPuerto(String nombre, String pais, int cantDarsenas) {
        Puerto unPuerto = new Puerto(nombre, pais, cantDarsenas);
        boolean correcto = tablaPuertos.insertar(nombre, unPuerto);
        if (correcto) {
            rutasMaritimas.insertarVertice(nombre);
        }
        return correcto;
    }

    /**
     * Da de baja un puerto, quitandolo de la tabla de puertos y de las rutas
     * maritimas.
     *
     * @param puerto
     * @return true si encuentra el puerto para eliminarlo, o false en caso
     * contrario.
     */
    public boolean bajaPuerto(String puerto) {
        boolean correcto = tablaPuertos.eliminar(puerto);
        if (correcto) {
            rutasMaritimas.eliminarVertice(puerto);
        }
        return correcto;
    }

    /**
     * Da de alta una ruta maritima entre dos puertos, agregando el respectivo
     * arco en el grafo de rutas maritimas.
     *
     * @param origen
     * @param destino
     * @param distancia
     * @return true si la ruta no existe y es creada, o false en caso contrario.
     */
    public boolean altaRutaMaritima(String origen, String destino, double distancia) {
        return rutasMaritimas.insertarArco(origen, destino, distancia);
    }

    /**
     * Da de baja una ruta maritima entre dos puertos, eliminando el respectivo
     * arco en el grafo de rutas maritimas.
     *
     * @param origen
     * @param destino
     * @return true si la ruta existe y es eliminada, o false en caso contrario.
     */
    public boolean bajaRutaMaritima(String origen, String destino) {
        return rutasMaritimas.eliminarArco(origen, destino);
    }

    /**
     * Da de alta un barco, agregandolo a la tabla de barcos.
     *
     * @param nombre
     * @param tipo
     * @param pais
     * @return true si la clave de barco no existe en el HashMap y es agregado,
     * o false en caso contrario.
     */
    public boolean altaBarco(String nombre, String tipo, String pais) {
        boolean res = false;
        if (!tablaBarcos.containsKey(nombre)) {
            Barco unBarco = new Barco(nombre, tipo, pais);
            tablaBarcos.put(nombre, unBarco);
            res = true;
        }
        return res;
    }

    /**
     * Lista las rutas maritimas entre todos los puertos.
     *
     * @return la lista mencionada.
     */
    public Lista listarRutasMaritimas() {
        return rutasMaritimas.listarArcos();
    }

    /**
     * Lista los barcos en la tabla de barcos segun esten en el HashMap.
     *
     * @return la lista mencionada.
     */
    public Lista listarBarcos() {
        Lista res = new Lista();
        Set<String> colBarcos = tablaBarcos.keySet();
        Object[] arregloBarcos = colBarcos.toArray();
        for (int i = 0; i < arregloBarcos.length; i++) {
            res.insertar(arregloBarcos[i], res.longitud() + 1);
        }
        return res;
    }

    /**
     * Lista los puertos en la tabla de puertos, la forma de listar es InOrden
     * de AVL por lo tanto los puertos quedan en orden alfabetico.
     *
     * @return la lista mencionada.
     */
    public Lista listarPuertos() {
        return tablaPuertos.listarClaves();
    }

    /**
     * Lista los puertos que estan entre ini y fin (alfabeticamente).
     *
     * @param ini
     * @param fin
     * @return la lista mencionada.
     */
    public Lista listarPuertosRango(String ini, String fin) {
        return tablaPuertos.listarClavesRango(ini, fin);
    }

    /**
     * Lista todos los puertos que tienen barcos en espera.
     *
     * @return la lista mencionada.
     */
    public Lista listarPuertosBarcosEspera() {
        Lista res = new Lista();
        Lista<Puerto> puertos = tablaPuertos.listarDatos();
        for (int i = 1; i <= puertos.longitud(); i++) {
            Puerto aux = puertos.recuperar(i);
            if (aux.tieneBarcosEspera()) {
                res.insertar(aux.getNombre(), res.longitud() + 1);
            }
        }
        return res;
    }

    /**
     * Devuelve una lista con los barcos en la cola de espera del puerto
     * indicado.
     *
     * @param puerto
     * @return la lista mencionada.
     */
    public Lista listarBarcosEspera(String puerto) {
        Lista res = new Lista();
        if (tablaPuertos.existeClave(puerto)) {
            res = tablaPuertos.obtenerDato(puerto).listarBarcosEspera();
        }
        return res;
    }

    /**
     * Devuelve una lista con el camino de puertos mas corto y que no pasa por
     * el puerto indicado.
     *
     * @param origen
     * @param destino
     * @param noPasa
     * @return la lista mencionada.
     */
    public Lista caminoNoPasaPor(String origen, String destino, String noPasa) {
        return rutasMaritimas.caminoNoPasaPor(origen, destino, noPasa);
    }

    /**
     * Devuelve una lista con el camino de puertos mas corto en relacion a la
     * cantidad de puertos entre medio.
     *
     * @param origen
     * @param destino
     * @return la lista mencionada.
     */
    public Lista caminoMasCorto(String origen, String destino) {
        return rutasMaritimas.caminoMasCorto(origen, destino);
    }

    /**
     * Devuelve la distancia de la ruta mas corta entre un puerto origen y un
     * puerto destino.
     *
     * @param origen
     * @param destino
     * @return el valor de la distancia, o en caso de que no haya camino
     * devuelve -1.
     */
    public double distanciaMasCorta(String origen, String destino) {
        return rutasMaritimas.longitudCaminoMenorLongitud(origen, destino);
    }

    /**
     * Verifica que exista una ruta entre un puerto origen y un puerto destino,
     * cuya distancia no supere a la recibida.
     *
     * @param origen
     * @param destino
     * @param distancia
     * @return true si se cumple lo mencionado, o false en caso contrario.
     */
    public boolean verificarRutaDistancia(String origen, String destino, double distancia) {
        boolean res = false;
        double result = rutasMaritimas.longitudCaminoMenorLongitud(origen, destino);
        if (result > 0 && result <= distancia) {
            res = true;
        }
        return res;
    }

    /**
     * Manda un barco desde las darsenas un puerto origen a la cola de espera
     * para ingresar de un puerto destino.
     *
     * @param barco
     * @param origen
     * @param destino
     * @return true si el barco esta en las darsenas del puerto origen y es
     * enviado al puerto destino, o false en caso contrario.
     */
    public boolean enviarBarco(String barco, String origen, String destino) {
        Puerto puertoOrigen = tablaPuertos.obtenerDato(origen);
        boolean res = puertoOrigen.varificarBarcoDarsena(barco);
        if (res) {
            Puerto puertoDestino = tablaPuertos.obtenerDato(destino);
            puertoOrigen.sacarBarcoDarsena(barco);
            puertoDestino.ponerBarcoEspera(tablaBarcos.get(barco));
        }
        return res;
    }

    /**
     * Pone un barco en la cola de espera para ingresar de un puerto.
     *
     * @param barco
     * @param puerto
     * @return true si el barco y el puerto no son nulos y si el barco no se
     * encuentra en ningun otro puerto, o false en caso contrario.
     */
    public boolean ponerBarco(String barco, String puerto) {
        boolean res = true;
        Barco unBarco = tablaBarcos.get(barco);
        Puerto unPuerto = tablaPuertos.obtenerDato(puerto);
        if (unBarco != null && unPuerto != null) {
            Lista<Puerto> puertosDarsenas = tablaPuertos.listarDatos();
            int i = 1;
            while (i <= puertosDarsenas.longitud() && res) {
                Puerto aux = puertosDarsenas.recuperar(i);
                res = !(aux.varificarBarcoDarsena(barco) || aux.verificarBarcoEspera(barco));
                i++;
            }
            if (res) {
                unPuerto.ponerBarcoEspera(unBarco);
            }
        } else {
            res = false;
        }
        return res;
    }

    /**
     * Mueve los barcos en la cola de espera de un puerto las darsenas del mismo
     * segun su prioridad.
     *
     * @param puerto
     */
    public void actualizarDarsenas(String puerto) {
        tablaPuertos.obtenerDato(puerto).actualizarDarsenas();
    }

    /**
     * Devuelve un arreglo con los datos de un barco: nombre, tipo y pais.
     *
     * @param barco
     * @return el arreglo mencionado.
     */
    public String[] arregloDatosBarco(String barco) {
        String[] res = null;
        if (tablaBarcos.containsKey(barco)) {
            res = tablaBarcos.get(barco).toArray();
        }
        return res;
    }

    /**
     * Muestra los datos de un barco: nombre, tipo y pais.
     *
     * @param barco
     * @return un String con lo mencionado.
     */
    public String mostrarDatosBarco(String barco) {
        String res = "";
        if (tablaBarcos.containsKey(barco)) {
            res = tablaBarcos.get(barco).toString();
        }
        return res;
    }

    /**
     * Devuelve un arreglo con los datos de un puerto: nombre, pais, cantidad de
     * darsenas (como String).
     *
     * @param puerto
     * @return el arreglo mencionado.
     */
    public String[] arregloDatosPuerto(String puerto) {
        String[] res = null;
        if (tablaPuertos.existeClave(puerto)) {
            res = tablaPuertos.obtenerDato(puerto).toArray();
        }
        return res;
    }

    /**
     * Muestra los nombres de los barcos en la cola de espera de un puerto,
     * ordenados segun su prioridad.
     *
     * @param puerto
     * @return un String con lo mencionado.
     */
    public String mostrarBarcosEspera(String puerto) {
        String res = "";
        if (tablaPuertos.existeClave(puerto)) {
            res = tablaPuertos.obtenerDato(puerto).toStringBarcosEspera();
        }
        return res;
    }

    /**
     * Devuelve un arreglo con los barcos de las darsenas, aquellas darsenas
     * vacias tambien estan en el arreglo como null.
     *
     * @param puerto
     * @return el arreglo mencionado.
     */
    public String[] arregloDarsenas(String puerto) {
        String[] res = null;
        if (tablaPuertos.existeClave(puerto)) {
            res = tablaPuertos.obtenerDato(puerto).toArrayDarsenas();
        }
        return res;
    }

    /**
     * Devuelve la cantidad de barcos en la cola de espera de un puerto.
     *
     * @param puerto
     * @return el valor mencionado.
     */
    public int cantBarcosEsperaPuerto(String puerto) {
        int res = 0;
        if (tablaPuertos.existeClave(puerto)) {
            res = tablaPuertos.obtenerDato(puerto).cantBarcosEspera();
        }
        return res;
    }

    /**
     * Imprime la tabla de puertos en la consola de java (tener en cuenta que se
     * dibuja un poco corrido), este metodo es solo para testear.
     */
    public void printTablaPuertos() {
        tablaPuertos.print();
    }

}
