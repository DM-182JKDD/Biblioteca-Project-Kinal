package org.joseescobar.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joseescobar.model.ArticuloCompra;
import org.joseescobar.model.MetodoPago;
import org.joseescobar.sistema.Main;
import org.joseescobar.validaciones.Validar;

/**
 * Controlador para la vista de datos de compra.
 * Se encarga de mostrar los artículos seleccionados, calcular totales,
 * y gestionar la interacción con el usuario en la pantalla de resumen de compra.
 * 
 * @author Jose Daniel Escobar Macario
 */
public class DatosDeCompraController implements Initializable {

    private Main escenario;
    private MetodoPago metodoSeleccionado;
    private ObservableList<ArticuloCompra> listaArticulos = FXCollections.observableArrayList();

    @FXML private Button btnCompra;
    @FXML private Button btnSalir;
    @FXML private TableView<ArticuloCompra> tblArticulos;
    @FXML private TableColumn<ArticuloCompra, String> clmArticulos;
    @FXML private TableColumn<ArticuloCompra, String> clmAutor;
    @FXML private TableColumn<ArticuloCompra, Integer> clmCantidad;
    @FXML private TableColumn<ArticuloCompra, Double> clmPrecio;
    @FXML private TableColumn<ArticuloCompra, Double> clmSubTotal;

    @FXML private TextField txtFecha;
    @FXML private TextField txtIVA;
    @FXML private TextField txtMetodoDePago;
    @FXML private TextField txtReferencia;
    @FXML private TextField txtSubTotal;
    @FXML private TextField txtTotal;

    /**
     * Inicializa la vista configurando las columnas de la tabla,
     * mostrando la fecha actual, generando una referencia aleatoria
     * y bloqueando los campos de texto que no deben ser editables.
     * 
     * @param url ubicación del archivo FXML
     * @param rb recursos utilizados para internacionalización
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurar columnas de la tabla con nombres de propiedades
        clmArticulos.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
        clmAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        clmCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        clmPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        clmSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

        // Mostrar fecha actual y generar referencia
        txtFecha.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        txtReferencia.setText("REF-" + (int)(Math.random()*100000));

        // Bloquear los TextField
        txtFecha.setEditable(false);
        txtReferencia.setEditable(false);
        txtMetodoDePago.setEditable(false);
        txtSubTotal.setEditable(false);
        txtIVA.setEditable(false);
        txtTotal.setEditable(false);
    }//initialize

    /**
     * Recibe los datos de compra (lista de artículos y método de pago),
     * los muestra en la tabla y calcula los totales.
     * 
     * @param articulos lista de artículos comprados
     * @param metodo método de pago seleccionado
     */
    public void setDatosCompra(List<ArticuloCompra> articulos, MetodoPago metodo) {
        this.metodoSeleccionado = metodo;
        listaArticulos.setAll(articulos);
        System.out.println("Artículos seleccionados:");
        for(ArticuloCompra art : listaArticulos){
            System.out.println(art);
        }
        tblArticulos.setItems(listaArticulos);
        // Método de pago
        txtMetodoDePago.setText(metodo.getNombre());
        // Totales
        calcularTotales();
    }//setDatosCompra

    /**
     * Calcula el subtotal, IVA (12%) y total de la compra,
     * y los muestra en los campos correspondientes.
     */
    private void calcularTotales() {
        double subtotal = 0;
        for (ArticuloCompra art : listaArticulos) {
            subtotal += art.getSubTotal();
        }
        double iva = subtotal * 0.12;
        double total = subtotal + iva;

        txtSubTotal.setText(String.format("%.2f", subtotal));
        txtIVA.setText(String.format("%.2f", iva));
        txtTotal.setText(String.format("%.2f", total));
        System.out.println("Subtotal: " + subtotal);
        System.out.println("IVA: " + iva);
        System.out.println("Total: " + total);
    }//calcularTotales

    /**
     * Cierra la aplicación.
     */
    @FXML
    private void salir() {
        System.exit(0);
    }//salir

    /**
     * Regresa a la ventana de compra principal.
     * Muestra una alerta si ocurre un error.
     */
    @FXML
    private void compra() {
        try {
            escenario.inicioRealizarCompra();
        } catch (Exception e) {
            Validar.getInstancia().alerta(Alert.AlertType.ERROR, "No se pudo volver a la ventana de compra.");
        }
    }//compra

    /**
     * Obtiene el escenario principal.
     * 
     * @return instancia de Main
     */
    public Main getEscenario() { return escenario; }

    /**
     * Establece el escenario principal.
     * 
     * @param escenario instancia de Main
     */
    public void setEscenario(Main escenario) { this.escenario = escenario; }
}//class