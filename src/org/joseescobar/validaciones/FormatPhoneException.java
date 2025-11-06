package org.joseescobar.validaciones;

/**
 *
 * @author Jose Daniel Escobar Macario
 */
public class FormatPhoneException extends Exception{
    /**
     * Contructor de la clase que permite personalizar una excepcion
     * @param mensaje
     */
    public FormatPhoneException( String mensaje ){
        super( mensaje );
    }//Fin del contructor 
}//class
