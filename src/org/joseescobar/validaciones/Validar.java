package org.joseescobar.validaciones;

import javafx.scene.control.Alert;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase utilitaria para validaciones comunes en la aplicación.
 * Implementa el patrón Singleton para asegurar una única instancia.
 * 
 * @author Jose Daniel Escobar Macario
 */
public class Validar {

    /**
     * Variables de clase
     */
    private static Validar instancia = null;

    /**
     * Constructor vacío o sin parámetros
     */
    private Validar() {}

    /**
     * Método getInstancia permite manipular la instancia única de esta clase.
     * Solo una vez se va a instanciar.
     * 
     * @return instancia única de Validar
     */
    public static Validar getInstancia() {
        if (instancia == null) {
            instancia = new Validar();
        }
        return instancia;
    }//getInstancia

    /**
     * Método para mostrar una alerta
     * 
     * @param tipoDeAlerta tipo de alerta (INFORMATION, ERROR, etc.)
     * @param mensajeAMostrar mensaje que se mostrará en la alerta
     */
    public void alerta(Alert.AlertType tipoDeAlerta, String mensajeAMostrar) {
        Alert alerta = new Alert(tipoDeAlerta, mensajeAMostrar);
        alerta.showAndWait();
    }//alerta

    /**
     * Método para validar teléfono
     * Verifica que el número tenga exactamente 8 dígitos y sea positivo.
     * 
     * @param telefonoAValidar número de teléfono como cadena
     * @return true si es válido, false si no lo es
     */
    public boolean telefono(String telefonoAValidar) {
        try {
            int telefono = Integer.parseInt(telefonoAValidar);
            if (telefono <= 0 || telefonoAValidar.length() != 8) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException error) {
            alerta(Alert.AlertType.ERROR, "Verifique el telefono ingresado.");
            return false;
        }
        return true;
    }//Fin del método teléfono

    /**
     * Método para validar email
     * Utiliza una expresión regular para verificar el formato del correo.
     * 
     * @param emailAValidar correo electrónico a validar
     * @return true si el formato es válido, false si no lo es
     */
    public boolean email(String emailAValidar) {
        String guia = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        String mensajeEmailNoValido = "El formato del email no es válido.";
        Pattern patron = Pattern.compile(guia);
        Matcher comparacion = patron.matcher(emailAValidar);

        try {
            if (!comparacion.matches()) {
                throw new Exception(mensajeEmailNoValido);
            }
        } catch (Exception error) {
            alerta(Alert.AlertType.ERROR, mensajeEmailNoValido);
            return false;
        }//try-catch
        return true;
    }//Fin del método email
}//Class