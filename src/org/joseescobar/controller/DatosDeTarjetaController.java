package org.joseescobar.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.joseescobar.db.ControlesBD;
import org.joseescobar.model.ArticuloCompra;
import org.joseescobar.model.MetodoPago;
import org.joseescobar.sistema.Main;
import org.joseescobar.validaciones.Validar;

/**
 * Controlador para la vista de ingreso de datos de tarjeta.
 * Permite validar los campos, guardar la compra en la base de datos
 * y redirigir al resumen de compra.
 * 
 * @author Jose Daniel Escobar Macario
 */
public class DatosDeTarjetaController implements Initializable {

    private Main escenario;
    private List<ArticuloCompra> articulosSeleccionados;
    private MetodoPago metodoPago;

    @FXML private Button btnPagar;
    @FXML private Button btnSalir;
    @FXML private DatePicker dpExpiracion;
    @FXML private TextField txtCVV;
    @FXML private PasswordField txtTarjeta;
    @FXML private TextField txtTitular;

    /**
     * Establece el escenario principal de la aplicación.
     * 
     * @param escenario instancia principal de la aplicación
     */
    public void setEscenario(Main escenario) {
        this.escenario = escenario;
    }//setEscenario

    /**
     * Recibe los datos de compra: artículos seleccionados y método de pago.
     * 
     * @param articulos lista de artículos seleccionados
     * @param metodo método de pago elegido
     */
    public void setDatosCompra(List<ArticuloCompra> articulos, MetodoPago metodo) {
        this.articulosSeleccionados = articulos;
        this.metodoPago = metodo;
    }//setDatosCompra

    /**
     * Regresa a la ventana anterior de selección de compra.
     */
    @FXML
    private void salir() {
        escenario.inicioRealizarCompra();
    }//salir

    /**
     * Valida los campos, guarda la compra en la base de datos,
     * muestra un mensaje de éxito y redirige al resumen de compra.
     */
    @FXML
    private void pagar() {
        if (!validarCamposVacios()) return;

        // Guardar la compra en la base de datos
        ControlesBD.getInstancia().guardarCompra(articulosSeleccionados, metodoPago.getId());

        // Mostrar mensaje de éxito
        Validar.getInstancia().alerta(Alert.AlertType.INFORMATION, "Compra realizada con éxito");

        // Pasar al formulario de DatosDeCompra para mostrar el resumen
        escenario.inicioDatosDeCompra(articulosSeleccionados, metodoPago);
    }//pagar

    /**
     * Verifica que todos los campos requeridos estén llenos.
     * 
     * @return true si todos los campos están completos, false si falta alguno
     */
    private boolean validarCamposVacios() {
        if (txtTitular.getText().isEmpty() ||
            txtCVV.getText().isEmpty() ||
            txtTarjeta.getText().isEmpty() ||
            dpExpiracion.getValue() == null) {
            Validar.getInstancia().alerta(Alert.AlertType.ERROR, "Revise los campos vacíos");
            return false;
        }//if
        return true;
    }//validarCamposVacios

    /**
     * Método de inicialización del controlador.
     * 
     * @param url ubicación del archivo FXML
     * @param rb recursos utilizados para internacionalización
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización si es necesaria
    }//initialize
}//class