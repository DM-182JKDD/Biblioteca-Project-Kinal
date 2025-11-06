package org.joseescobar.model;

/**
 * Clase base que representa un contacto con información personal.
 * Puede ser extendida por otras clases que requieran atributos comunes de contacto.
 * @author Jose Daniel Escobar Macario
 */
public class AbstractContacto {

    /**
     * Seccion de declaracion de variables de instancia
     */
    private int idContacto;
    private String nombre;
    private String apellido;
    private String genero;
    private String categoria;
    private String fechaNacimiento;
    private String direccion;
    private int telefonoContacto;
    private String emailContactol;

    /**
     * Constructor vacio o sin parametros
     */
    public AbstractContacto(){}

    /**
     * Constructor lleno
     * 
     * @param idContacto ID del contacto
     * @param nombre nombre del contacto
     * @param apellido apellido del contacto
     * @param genero género del contacto
     * @param categoria categoría del contacto
     * @param fechaNacimiento fecha de nacimiento del contacto
     * @param direccion dirección del contacto
     * @param telefonoContacto número de teléfono del contacto
     * @param emailContactol correo electrónico del contacto
     */
    public AbstractContacto(int idContacto, String nombre, String apellido, String genero, String categoria, String fechaNacimiento, String direccion, int telefonoContacto, String emailContactol) {
        this.idContacto = idContacto;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.categoria = categoria;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefonoContacto = telefonoContacto;
        this.emailContactol = emailContactol;
    }//AbstractContacto

    /**
     * Getters and Setters
     */

    public int getIdContacto() {
        return idContacto;
    }//getIdContacto

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }//setIdContacto

    public String getNombre() {
        return nombre;
    }//getNombre

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }//setNombre

    public String getApellido() {
        return apellido;
    }//getApellido

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }//setApellido

    public String getGenero() {
        return genero;
    }//getGenero

    public void setGenero(String genero) {
        this.genero = genero;
    }//setGenero

    public String getCategoria() {
        return categoria;
    }//getCategoria

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }//setCategoria

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }//getFechaNacimiento

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }//setFechaNacimiento

    public String getDireccion() {
        return direccion;
    }//getDireccion

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }//setDireccion

    public int getTelefonoContacto() {
        return telefonoContacto;
    }//getTelefonoContacto

    public void setTelefonoContacto(int telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }//setTelefonoContacto

    public String getEmailContactol() {
        return emailContactol;
    }//getEmailContactol

    public void setEmailContactol(String emailContactol) {
        this.emailContactol = emailContactol;
    }//setEmailContactol
}//class