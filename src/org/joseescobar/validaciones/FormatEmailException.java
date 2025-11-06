package org.joseescobar.validaciones;

/**
 *
 * @author Jose Daniel Escobar Macario
 */
public class FormatEmailException extends Exception{
    /**
     * Contructor de la excepcion personalizada para email
     * @param mensaje
     */
    public FormatEmailException( String mensaje ){
        super( mensaje );
    }//Fin del contructor
}//class
