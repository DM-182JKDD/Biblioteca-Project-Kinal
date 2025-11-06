package org.joseescobar.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.joseescobar.db.Conexion;
import org.joseescobar.db.ControlesBD;
import org.joseescobar.validaciones.Validar;
import org.joseescobar.sistema.Main;

/**
 * Controlador para el registro de usuarios.
 * Permite validar los campos, guardar el usuario en la base de datos
 * y redirigir al login si el registro es exitoso.
 * 
 * @author Jose Daniel Escobar Macario
 */
public class RegistrarUsuarioController implements Initializable {

    /**
     * Variables de clase
     */
    private Main escenario;

    @FXML private Button btnGuardar;
    @FXML private Button btnSalir;
    @FXML private ComboBox<String> cmbGenero;
    @FXML private DatePicker dpFechaDenacimiento;
    @FXML private TextField txtApellido;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtEmail;
    @FXML private TextField txtNombre;
    @FXML private PasswordField txtPassword;
    @FXML private TextField txtTelefono;

    /**
     * Obtiene el escenario principal.
     * 
     * @return instancia de Main
     */
    public Main getEscenario() {
        return escenario;
    }//getEscenario

    /**
     * Establece el escenario principal.
     * 
     * @param escenario instancia de Main
     */
    public void setEscenario(Main escenario) {
        this.escenario = escenario;
    }//setEscenario

    /**
     * Métodos privados
     */

    /**
     * Cierra la ventana actual y regresa al login.
     */
    @FXML
    private void cerrarVentana() {
        escenario.inicioLogin();
    }//cerrarVentana

    /**
     * Valida que todos los campos requeridos estén llenos.
     * 
     * @return true si todos los campos están completos, false si falta alguno
     */
    private boolean validarCamposVacios() {
        if (txtNombre.getText().isEmpty() ||
            txtApellido.getText().isEmpty() ||
            txtTelefono.getText().isEmpty() ||
            cmbGenero.getValue() == null || cmbGenero.getValue().isEmpty() ||
            dpFechaDenacimiento.getValue() == null ||
            txtDireccion.getText().isEmpty() ||
            txtEmail.getText().isEmpty() ||
            txtPassword.getText().isEmpty()) {

            Validar.getInstancia().alerta(Alert.AlertType.ERROR, "Revise los campos vacíos");
            return false;
        }//if
        return true;
    }//validarCamposVacios

    /**
     * Valida los campos, verifica el formato de teléfono y correo,
     * guarda el usuario en la base de datos y redirige al login si es exitoso.
     */
    @FXML
    private void guardar() {
        if (validarCamposVacios()) {
            if (Validar.getInstancia().telefono(txtTelefono.getText()) &&
                Validar.getInstancia().email(txtEmail.getText())) {

                if (guardarUsuarioEnBD()) {
                    Validar.getInstancia().alerta(Alert.AlertType.INFORMATION, "Usuario registrado exitosamente.");
                    escenario.inicioLogin();
                } else {
                    Validar.getInstancia().alerta(Alert.AlertType.ERROR, "Hubo un error al guardar el usuario.");
                }//if else

                System.out.println("Usuario valido. Guardando...");
            } else {
                Validar.getInstancia().alerta(Alert.AlertType.ERROR, "Telefono o correo electrónico inválido.");
            }//else
        }//if
    }//guardar

    /**
     * Guarda el usuario en la base de datos utilizando el procedimiento almacenado.
     * 
     * @return true si el usuario fue guardado correctamente, false si hubo error
     */
    private boolean guardarUsuarioEnBD() {
        PreparedStatement consulta;
        ResultSet resultadoConsulta;
        try {
            consulta = Conexion.getInstancia().getConexion().prepareCall("CALL sp_guardar_usuario(?, ?, ?, ?, ?, ?, ?, ?)");
            consulta.setString(1, txtNombre.getText());
            consulta.setString(2, txtApellido.getText());
            consulta.setInt(3, Integer.parseInt(txtTelefono.getText()));
            consulta.setString(4, cmbGenero.getValue().toString());
            consulta.setString(5, dpFechaDenacimiento.getValue().toString());
            consulta.setString(6, txtDireccion.getText());
            consulta.setString(7, txtEmail.getText());
            consulta.setString(8 , txtPassword.getText());
            resultadoConsulta = consulta.executeQuery();
            if (resultadoConsulta.next() && resultadoConsulta.getInt(1) != 0) {
                return true;
            }//if
        } catch (Exception error) {
            error.printStackTrace();
        }//try catch
        return false;
    }//guardarUsuarioEnBD

    /**
     * Método de inicialización
     * 
     * @param url ubicación del archivo FXML
     * @param rb recursos utilizados para internacionalización
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbGenero.setItems(FXCollections.observableArrayList(ControlesBD.getInstancia().leerGenero()));
    }//initialize
}//class