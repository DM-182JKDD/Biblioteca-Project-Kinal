package org.joseescobar.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.joseescobar.model.Usuario;
import org.joseescobar.db.Conexion;
import org.joseescobar.db.ControlesBD;
import org.joseescobar.validaciones.Validar;
import org.joseescobar.sistema.Main;

/**
 *
 * @author Jose Daniel Escobar Macario
 */

public class LoginController implements Initializable {
    /**
     * Variables de clase
     */
    private Main escenario;

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    public Main getEscenario() {
        return escenario;
    }//getEscenario

    public void setEscenario(Main escenario) {
        this.escenario = escenario;
    }//setEscenario

    /**
     * Metodos privados de clase
     */
    @FXML
    private void salir() {
        System.exit(0);
    }//salir

    @FXML
    private void registrarse() {
        escenario.inicioRegistrarUsuario();
    }//registrarse

    private boolean validarCamposDeLogin() {
        if (txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            Validar.getInstancia().alerta(Alert.AlertType.ERROR, "Revise los campos vacios");
            return false;
        }//if
        return true;
    }//validarCamposDeLogin

	@FXML
	private void iniciarSesion() {
    if (validarCamposDeLogin()) {
        Usuario usuarioLogueado = iniciarSesion(txtEmail.getText(), txtPassword.getText());

        if (usuarioLogueado != null && usuarioLogueado.getIdUsuario() != 0) {
            Validar.getInstancia().alerta(Alert.AlertType.CONFIRMATION, 
                "Has iniciado sesión correctamente: " + usuarioLogueado.getNombre());
            
            ControlesBD.getInstancia().setUsuarioLogueado(usuarioLogueado);
                escenario.inicioRealizarCompra();
        } else {
            Validar.getInstancia().alerta(Alert.AlertType.ERROR, 
                "Usuario o contraseña incorrectos");
        }//if-else
    }//if
}//iniciarSesion

    
    private Usuario iniciarSesion(String email, String password){
        Usuario usuarioHallado = new Usuario();
        PreparedStatement consulta;
        ResultSet resultadoConsulta;
        try {
              consulta = Conexion.getInstancia().getConexion().prepareCall("call sp_iniciar_sesion(?,?)");
              consulta.setString(1, email);
              consulta.setString(2, password);
              resultadoConsulta = consulta.executeQuery();
              if( resultadoConsulta.next() ) {
                  usuarioHallado.setIdUsuario( resultadoConsulta.getInt(1) );
                  usuarioHallado.setEmail( resultadoConsulta.getString(2) );
                  usuarioHallado.setPassword(resultadoConsulta.getString(3));
                  usuarioHallado.setTelefono(resultadoConsulta.getInt(4));
                  usuarioHallado.setNombre(resultadoConsulta.getString(5));
                  usuarioHallado.setApellido(resultadoConsulta.getString(6));
                  usuarioHallado.setGenero(resultadoConsulta.getString(7));
                  usuarioHallado.setFechaNacimiento(resultadoConsulta.getString(8));
                  usuarioHallado.setDireccion(resultadoConsulta.getString(9));
              }//if
        } catch (Exception error) {
                error.printStackTrace();
        }//try-catch
        return usuarioHallado;
    }//iniciarSesion

    /**
     * Métodos públicos de clase
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializacion si es necesaria
    }//initialize
}//class
