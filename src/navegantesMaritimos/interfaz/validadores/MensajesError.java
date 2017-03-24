package navegantesMaritimos.interfaz.validadores;

import java.awt.*;
import javax.swing.*;

/**
 * Esta clase permite mostrar mensajes de error en una ventana emergente y
 * colorear el text field del campo para mostrar si es correcto o incorrecto.
 * Los mensajes son recibidos de la clase validador que utiliza a esta clase.
 */
public class MensajesError {

    /**
     * Muestra un mensaje de error y colorea de rojo el campo si es incorrecto,
     * colorea de verde el campo si es correcto.
     *
     * @param componente
     * @param correcto
     * @param mensajeError
     */
    public static void mostrarMensaje(JComponent componente, boolean correcto, String mensajeError) {
        if (correcto) {
            componente.setBackground(new Color(160, 250, 200));
        } else {
            componente.setBackground(new Color(250, 160, 160));
            JOptionPane.showMessageDialog(componente, mensajeError, "Error", 0);
        }
    }

}
