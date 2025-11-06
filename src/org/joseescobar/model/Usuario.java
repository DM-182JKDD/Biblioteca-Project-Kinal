package org.joseescobar.model;

/**
 * Representa un usuario del sistema.
 * Hereda de AbstractContacto e incluye credenciales y datos de contacto específicos.
 * 
 * @author Jose Daniel Escobar Macario
 */
public class Usuario extends AbstractContacto {

    /**
     * Variables de instancia
     */
    private int idUsuario;
    private String email;
    private String password;
    private int telefono;

    /**
     * Constructor vacio o sin parametros
     */
    public Usuario() {}

    /**
     * Contructor lleno
     * 
     * @param idUsuario ID del usuario
     * @param email correo electrónico del usuario
     * @param password contraseña del usuario
     * @param telefono número de teléfono del usuario
     */
    public Usuario(int idUsuario, String email, String password, int telefono) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }//Usuario

    /**
     * Constructor completo con datos heredados de AbstractContacto.
     * 
     * @param idUsuario ID del usuario
     * @param email correo electrónico del usuario
     * @param password contraseña del usuario
     * @param telefono número de teléfono del usuario
     * @param idContacto ID del contacto
     * @param nombre nombre del contacto
     * @param apellido apellido del contacto
     * @param genero género del contacto
     * @param categoria categoría del contacto
     * @param fechaNacimiento fecha de nacimiento del contacto
     * @param direccion dirección del contacto
     * @param telefonoContacto teléfono del contacto
     * @param emailContactol correo electrónico del contacto
     */
    public Usuario(int idUsuario, String email, String password, int telefono,
                   int idContacto, String nombre, String apellido, String genero,
                   String categoria, String fechaNacimiento, String direccion,
                   int telefonoContacto, String emailContactol) {
        super(idContacto, nombre, apellido, genero, categoria, fechaNacimiento, direccion, telefonoContacto, emailContactol);
        this.idUsuario = idUsuario;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }//Usuario

    /**
     * Getters and Setters
     */

    /**
     * @return ID del usuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }//getIdUsuario

    /**
     * @param idUsuario ID del usuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }//setIdUsuario

    /**
     * @return correo electrónico del usuario
     */
    public String getEmail() {
        return email;
    }//getEmail

    /**
     * @param email correo electrónico del usuario
     */
    public void setEmail(String email) {
        this.email = email;
    }//setEmail

    /**
     * @return contraseña del usuario
     */
    public String getPassword() {
        return password;
    }//getPassword

    /**
     * @param password contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }//setPassword

    /**
     * @return número de teléfono del usuario
     */
    public int getTelefono() {
        return telefono;
    }//getTelefono

    /**
     * @param telefono número de teléfono del usuario
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }//setTelefono
}//class