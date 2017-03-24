package navegantesMaritimos.interfaz.validadores;

import javax.swing.JLabel;
import javax.swing.text.*;

/**
 * Esta clase permite realizar validaciones de los campos de un formulario.
 */
public class Validador {

    /**
     * Valida si el text field recibido contiene valores de tipo entero.
     *
     * @param textField
     * @return true si valida, false en caso contrario.
     */
    public static boolean entero(JTextComponent textField) {
        boolean returnValue;
        try {
            Integer.parseInt(textField.getText());
            returnValue = true;
        } catch (NumberFormatException e) {
            returnValue = false;
        }
        MensajesError.mostrarMensaje(textField, returnValue, "El valor ingresado debe ser un numero entero.");
        return returnValue;
    }

    /**
     * Valida si el text field recibido contiene valores de tipo entero que
     * esten dentro del rango indicado.
     *
     * @param textField
     * @param min
     * @param max
     * @return true si valida, false en caso contrario.
     */
    public static boolean enteroRango(JTextComponent textField, int min, int max) {
        boolean returnValue = false;
        try {
            int valorTextField = Integer.parseInt(textField.getText());
            if (valorTextField >= min && valorTextField <= max) {
                returnValue = true;
            }
        } catch (NumberFormatException e) {
        }
        MensajesError.mostrarMensaje(textField, returnValue, "El valor ingresado debe ser un numero entero entre " + min + " y " + max + ".");
        return returnValue;
    }

    /**
     * Valida que el text field recibido contenga una cantidad de caracteres
     * indicada.
     *
     * @param textField
     * @param min
     * @param max
     * @return true si valida, false en caso contrario.
     */
    public static boolean cantCarac(JTextComponent textField, int min, int max) {
        boolean returnValue = false;
        int tamTextField = textField.getText().length();
        if (tamTextField >= min && tamTextField <= max) {
            returnValue = true;
        }
        MensajesError.mostrarMensaje(textField, returnValue, "El texto ingresado debe tener entre " + min + " y " + max + " caracteres.");
        return returnValue;
    }

    /**
     * Valida que el text field recibido no este vacio.
     *
     * @param textField
     * @return true si valida, false en caso contrario.
     */
    public static boolean noVacio(JTextComponent textField) {
        boolean returnValue = false;
        if (!textField.getText().isEmpty()) {
            returnValue = true;
        }
        MensajesError.mostrarMensaje(textField, returnValue, "El texto ingresado no puede ser vacio.");
        return returnValue;
    }

    /**
     * Valida que el label recibido no este vacio.
     *
     * @param label
     * @return true si valida, false en caso contrario.
     */
    public static boolean noVacio(JLabel label) {
        boolean returnValue = false;
        if (!label.getText().isEmpty()) {
            returnValue = true;
        }
        MensajesError.mostrarMensaje(label, returnValue, "El valor del campo no puede ser vacio.");
        return returnValue;
    }

}
