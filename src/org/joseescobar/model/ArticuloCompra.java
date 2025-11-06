package org.joseescobar.model;

/**
 * Representa un artículo seleccionado en una compra.
 * Incluye nombre, autor, cantidad, precio y subtotal.
 * @author Jose Daniel Escobar Macario
 */
public class ArticuloCompra {

    private int id_articulo;
    private String nombreArticulo;
    private String autor;
    private int cantidad;
    private double precio;
    private double subTotal;

    /**
     * Constructor vacío.
     */
    public ArticuloCompra() {}

    /**
     * Constructor principal.
     * Calcula automáticamente el subtotal.
     * 
     * @param nombreArticulo nombre del artículo
     * @param autor autor del artículo
     * @param cantidad cantidad seleccionada
     * @param precio precio unitario
     */
    public ArticuloCompra(String nombreArticulo, String autor, int cantidad, double precio) {
        this.nombreArticulo = nombreArticulo;
        this.autor = autor;
        this.cantidad = cantidad;
        this.precio = precio;
        actualizarSubTotal();
    }//ArticuloCompra

    /**
     * Constructor completo, útil para cargar desde base de datos.
     * 
     * @param id_articulo ID del artículo
     * @param nombreArticulo nombre del artículo
     * @param precio precio unitario
     * @param autor autor del artículo
     * @param cantidad cantidad seleccionada
     */
    public ArticuloCompra(int id_articulo, String nombreArticulo, double precio, String autor, int cantidad) {
        this.id_articulo = id_articulo;
        this.nombreArticulo = nombreArticulo;
        this.autor = autor;
        this.cantidad = cantidad;
        this.precio = precio;
        actualizarSubTotal();
    }//ArticuloCompra

    /**
     * @return ID del artículo
     */
    public int getIdArticulo() {
        return id_articulo;
    }//getIdArticulo

    /**
     * @param id_articulo ID del artículo
     */
    public void setIdArticulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }//setIdArticulo

    /**
     * @return nombre del artículo
     */
    public String getNombreArticulo() {
        return nombreArticulo;
    }//getNombreArticulo

    /**
     * @param nombreArticulo nombre del artículo
     */
    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }//setNombreArticulo

    /**
     * @return autor del artículo
     */
    public String getAutor() {
        return autor;
    }//getAutor

    /**
     * @param autor autor del artículo
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }//setAutor

    /**
     * @return cantidad seleccionada
     */
    public int getCantidad() {
        return cantidad;
    }//getCantidad

    /**
     * Establece la cantidad y actualiza el subtotal.
     * 
     * @param cantidad cantidad seleccionada
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        actualizarSubTotal();
    }//setCantidad

    /**
     * @return precio unitario
     */
    public double getPrecio() {
        return precio;
    }//getPrecio

    /**
     * Establece el precio y actualiza el subtotal.
     * 
     * @param precio precio unitario
     */
    public void setPrecio(double precio) {
        this.precio = precio;
        actualizarSubTotal();
    }//setPrecio

    /**
     * @return subtotal calculado (cantidad * precio)
     */
    public double getSubTotal() {
        return subTotal;
    }//getSubTotal

    /**
     * Actualiza el subtotal automáticamente.
     */
    private void actualizarSubTotal() {
        this.subTotal = this.cantidad * this.precio;
    }//actualizarSubTotal

    /**
     * Representación en texto del artículo.
     * 
     * @return cadena con detalles del artículo
     */
    @Override
    public String toString() {
        return nombreArticulo + " | Autor: " + autor + " | Cantidad: " + cantidad + " | Precio: " + precio + " | SubTotal: " + subTotal;
    }//toString
}//class