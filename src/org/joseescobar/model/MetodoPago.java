package org.joseescobar.model;

/**
 * Representa un método de pago disponible en el sistema.
 * Contiene el ID y el nombre del método.
 * @author Jose Daniel Escobar Macario
 */
public class MetodoPago {

    private int id;
    private String nombre;

    /**
     * Constructor principal.
     * 
     * @param id identificador del método de pago
     * @param nombre nombre del método de pago
     */
    public MetodoPago(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }//MetodoPago

    /**
     * @return el ID del método de pago
     */
    public int getId() {
        return id;
    }//getId

    /**
     * @return el nombre del método de pago
     */
    public String getNombre() {
        return nombre;
    }//getNombre

    /**
     * Devuelve el nombre del método de pago como representación en texto.
     * Esto permite que el ComboBox muestre el nombre.
     * 
     * @return nombre del método de pago
     */
    @Override
    public String toString() {
        return nombre; // Esto permite que el ComboBox muestre el nombre
    }//toString
}//class