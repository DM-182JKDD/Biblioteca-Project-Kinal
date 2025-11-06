package org.joseescobar.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.joseescobar.db.ControlesBD;
import org.joseescobar.model.ArticuloCompra;
import org.joseescobar.model.MetodoPago;
import org.joseescobar.sistema.Main;
import org.joseescobar.validaciones.Validar;

/**
 * Controlador para la vista RealizarCompra.fxml.
 * Muestra libros disponibles y permite seleccionar método de pago.
 * 
 * @author Jose Daniel Escobar Macario
 */
public class RealizarCompraController implements Initializable {

    private Main escenario;

    @FXML private Button btnComprar;
    @FXML private Button btnSalir;

    @FXML private CheckBox chbAgregar1, chbAgregar2, chbAgregar3, chbAgregar4, chbAgregar5, chbAgregar6,
                         chbAgregar7, chbAgregar8, chbAgregar9, chbAgregar10, chbAgregar11, chbAgregar12;

    @FXML private ComboBox<MetodoPago> cmbMetodoDePago;

    @FXML private Label lblTitulo1, lblTitulo2, lblTitulo3, lblTitulo4, lblTitulo5, lblTitulo6,
                  lblTitulo7, lblTitulo8, lblTitulo9, lblTitulo10, lblTitulo11, lblTitulo12;

    @FXML private Label lblAutor1, lblAutor2, lblAutor3, lblAutor4, lblAutor5, lblAutor6,
                  lblAutor7, lblAutor8, lblAutor9, lblAutor10, lblAutor11, lblAutor12;

    @FXML private Label lblPrecio1, lblPrecio2, lblPrecio3, lblPrecio4, lblPrecio5, lblPrecio6,
                  lblPrecio7, lblPrecio8, lblPrecio9, lblPrecio10, lblPrecio11, lblPrecio12;

    private Label[] titulos;
    private Label[] autores;
    private Label[] precios;
    private CheckBox[] checkboxes;

    private List<ArticuloCompra> listaLibros;

    /**
     * Inicializa la vista cargando los métodos de pago disponibles
     * y los libros desde la base de datos.
     * 
     * @param url ubicación del archivo FXML
     * @param rb recursos utilizados para internacionalización
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Cargar métodos de pago
        cmbMetodoDePago.setItems(FXCollections.observableArrayList(
                ControlesBD.getInstancia().leerMetodosDePago()
        ));

        // Inicializar arreglos de labels y checkboxes
        titulos = new Label[]{lblTitulo1, lblTitulo2, lblTitulo3, lblTitulo4, lblTitulo5, lblTitulo6,
                              lblTitulo7, lblTitulo8, lblTitulo9, lblTitulo10, lblTitulo11, lblTitulo12};
        autores = new Label[]{lblAutor1, lblAutor2, lblAutor3, lblAutor4, lblAutor5, lblAutor6,
                              lblAutor7, lblAutor8, lblAutor9, lblAutor10, lblAutor11, lblAutor12};
        precios = new Label[]{lblPrecio1, lblPrecio2, lblPrecio3, lblPrecio4, lblPrecio5, lblPrecio6,
                              lblPrecio7, lblPrecio8, lblPrecio9, lblPrecio10, lblPrecio11, lblPrecio12};
        checkboxes = new CheckBox[]{chbAgregar1, chbAgregar2, chbAgregar3, chbAgregar4,
                                    chbAgregar5, chbAgregar6, chbAgregar7, chbAgregar8,
                                    chbAgregar9, chbAgregar10, chbAgregar11, chbAgregar12};

        cargarLibros();
    }//initialize

    /**
     * Carga los libros disponibles desde la base de datos
     * y los muestra en la interfaz.
     */
    private void cargarLibros() {
        listaLibros = ControlesBD.getInstancia().leerLibros();

        if (listaLibros.isEmpty()) {
            Validar.getInstancia().alerta(Alert.AlertType.INFORMATION, "No hay libros disponibles.");
            return;
        }//if

        for (int i = 0; i < listaLibros.size() && i < 12; i++) {
            ArticuloCompra art = listaLibros.get(i);
            titulos[i].setText(art.getNombreArticulo());
            autores[i].setText(art.getAutor());
            precios[i].setText("Q" + art.getPrecio());
            checkboxes[i].setUserData(art); // Guardamos objeto completo
        }//for
    }//cargarLibros

    /**
     * Regresa a la vista de inicio de sesión.
     */
    @FXML
    private void salir() {
        escenario.inicioLogin();
    }//salir

    /**
     * Procesa la compra según el método de pago seleccionado.
     * Puede redirigir a la vista de tarjeta o directamente al resumen de compra.
     */
    @FXML
    private void comprar() {
        if (!validarCampos()) return;

        MetodoPago seleccionado = cmbMetodoDePago.getValue();
        List<ArticuloCompra> seleccionados = obtenerArticulosSeleccionados();

        // Guardar en la base de datos si el método de pago es directo (id = 2)
        if (seleccionado.getId() == 2) {
            try {
                ControlesBD.getInstancia().guardarCompra(seleccionados, seleccionado.getId());
            } catch (Exception e) {
                e.printStackTrace();
                Validar.getInstancia().alerta(Alert.AlertType.ERROR, "No se pudo guardar la compra en la base de datos.");
                return;
            }//try catch
        }//if

        // Depuración
        System.out.println("Método de pago: " + seleccionado.getNombre());
        System.out.println("Artículos seleccionados:");
        for (ArticuloCompra art : seleccionados) {
            System.out.println(art);
        }//for

        if (seleccionado.getId() == 1) {
            // Método de pago 1 → abrir formulario de tarjeta
            try {
                escenario.inicioDatosDeTarjeta(seleccionados, seleccionado);
            } catch (Exception e) {
                e.printStackTrace();
                Validar.getInstancia().alerta(Alert.AlertType.ERROR, "No se pudo abrir Datos de Tarjeta.");
            }//try catch
        } else if (seleccionado.getId() == 2) {
            // Método de pago 2 → abrir directamente DatosDeCompra
            escenario.inicioDatosDeCompra(seleccionados, seleccionado);
        } else {
            Validar.getInstancia().alerta(Alert.AlertType.ERROR, "Método de pago no soportado.");
        }//else if
    }//comprar

    /**
     * Obtiene los artículos seleccionados por el usuario.
     * 
     * @return lista de artículos seleccionados
     */
    private List<ArticuloCompra> obtenerArticulosSeleccionados() {
        List<ArticuloCompra> seleccion = new ArrayList<>();
        for (CheckBox chk : checkboxes) {
            if (chk.isSelected() && chk.getUserData() != null) {
                seleccion.add((ArticuloCompra) chk.getUserData());
            }//if
        }//for
        return seleccion;
    }//obtenerArticulosSeleccionados

    /**
     * Valida que se haya seleccionado al menos un producto
     * y un método de pago.
     * 
     * @return true si la validación es exitosa, false si hay errores
     */
    private boolean validarCampos() {
        if (cmbMetodoDePago.getValue() == null) {
            Validar.getInstancia().alerta(Alert.AlertType.ERROR, "Debe seleccionar un método de pago.");
            return false;
        }//if

        boolean algunoSeleccionado = false;
        for (CheckBox chk : checkboxes) {
            if (chk.isSelected()) {
                algunoSeleccionado = true;
                break;
            }//for
        }//if

        if (!algunoSeleccionado) {
            Validar.getInstancia().alerta(Alert.AlertType.ERROR, "Debe seleccionar al menos un producto.");
            return false;
        }//if
        return true;
    }//validarCampos

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