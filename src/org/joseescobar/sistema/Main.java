package org.joseescobar.sistema;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;
import java.util.List;
import org.joseescobar.controller.DatosDeCompraController;
import org.joseescobar.controller.DatosDeTarjetaController;
import org.joseescobar.controller.LoginController;
import org.joseescobar.controller.RealizarCompraController;
import org.joseescobar.controller.RegistrarUsuarioController;
import org.joseescobar.model.ArticuloCompra;
import org.joseescobar.model.MetodoPago;

/**
 * Clase principal que lanza la aplicación JavaFX.
 * Controla el flujo entre vistas y permite mover la ventana personalizada.
 * 
 * @since Martes, 09 de septiembre de 2025
 * @author Jose Daniel Escobar Macario
 * @version 10
 */
public class Main extends Application {

    private final String RUTA_PAQUETE_VISTA = "/org/joseescobar/view/";
    private Stage ventanaPrincipal;
    private Scene escenaEnPantalla;
    private double x = 0, y = 0;

    /**
     * Método principal que lanza la aplicación.
     * 
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        launch(args);
    }//main

    /**
     * Método que inicia la aplicación y muestra la ventana principal.
     * 
     * @param ventanaEntrada ventana principal de JavaFX
     * @throws Exception si ocurre un error al cargar la vista
     */
    @Override
    public void start(Stage ventanaEntrada) throws Exception {
        this.ventanaPrincipal = ventanaEntrada;
        ventanaPrincipal.setTitle("Agenda de contactos");
        ventanaPrincipal.initStyle(StageStyle.TRANSPARENT);
        inicioLogin();
        ventanaPrincipal.show();
    }//start

    /**
     * Cambia la escena actual por una nueva vista FXML.
     * 
     * @param nombreDelFXML nombre del archivo FXML
     * @param ancho ancho de la ventana
     * @param alto alto de la ventana
     * @return controlador inicializado de la nueva vista
     * @throws Exception si ocurre un error al cargar el FXML
     */
    public Initializable cambiarEscena(String nombreDelFXML, int ancho, int alto) throws Exception {
        Initializable inicializarEscena = null;
        FXMLLoader instanciaCargaDeFXML = new FXMLLoader();
        InputStream archivoEntrada = Main.class.getResourceAsStream(RUTA_PAQUETE_VISTA + nombreDelFXML);
        instanciaCargaDeFXML.setBuilderFactory(new JavaFXBuilderFactory());
        instanciaCargaDeFXML.setLocation(Main.class.getResource(RUTA_PAQUETE_VISTA + nombreDelFXML));
        Parent nodoRaiz = instanciaCargaDeFXML.load(archivoEntrada);
        escenaEnPantalla = new Scene(nodoRaiz, ancho, alto);
        ventanaPrincipal.setScene(escenaEnPantalla);
        permitirMoverVentana(nodoRaiz, ventanaPrincipal);
        ventanaPrincipal.sizeToScene();
        inicializarEscena = instanciaCargaDeFXML.getController();
        return inicializarEscena;
    }

    /**
     * Permite mover la ventana personalizada al arrastrar con el mouse.
     * 
     * @param nodoRaiz nodo raíz de la escena
     * @param ventana ventana principal
     */
    private void permitirMoverVentana(Parent nodoRaiz, Stage ventana) {
        nodoRaiz.setOnMousePressed((MouseEvent evento) -> {
            x = evento.getSceneX();
            y = evento.getSceneY();
        });

        nodoRaiz.setOnMouseDragged((MouseEvent evento) -> {
            ventana.setX(evento.getScreenX() - x);
            ventana.setY(evento.getScreenY() - y);
            ventana.setOpacity(0.8);
        });

        nodoRaiz.setOnMouseReleased((MouseEvent evento) -> {
            ventana.setOpacity(1.0);
        });
    }

    /**
     * Inicia la vista de login.
     */
    public void inicioLogin() {
        try {
            LoginController login = (LoginController) cambiarEscena("LoginView.fxml", 600, 400);
            login.setEscenario(this);
        } catch (Exception error) {
            error.printStackTrace();
        }//try-catch
    }//inicioLogin

    /**
     * Inicia la vista de registro de usuario.
     */
    public void inicioRegistrarUsuario() {
        try {
            RegistrarUsuarioController usuarioARegistrar = (RegistrarUsuarioController) cambiarEscena("RegistrarUsuarioView.fxml", 600, 540);
            usuarioARegistrar.setEscenario(this);
        } catch (Exception error) {
            error.printStackTrace();
        }//try-catch
    }//inicioRegistrarUsuario

    /**
     * Inicia la vista para realizar una compra.
     */
    public void inicioRealizarCompra() { 
        try {
            RealizarCompraController compra = (RealizarCompraController) cambiarEscena("RealizarCompra.fxml", 1183, 537);
            compra.setEscenario(this);
        } catch (Exception error) {
            error.printStackTrace();
        }//try-catch
    }//realizarCompra

    /**
     * Inicia la vista para ingresar datos de tarjeta.
     * 
     * @param seleccionados lista de artículos seleccionados
     * @param seleccionado método de pago seleccionado
     */
    public void inicioDatosDeTarjeta(List<ArticuloCompra> seleccionados, MetodoPago seleccionado) {
        try {
            DatosDeTarjetaController tarjeta = (DatosDeTarjetaController)
                    cambiarEscena("DatosDeTarjeta.fxml", 600, 400);
            tarjeta.setEscenario(this);
            // Pasar los datos de compra al controlador de tarjeta
            tarjeta.setDatosCompra(seleccionados, seleccionado);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }//inicioDatosDeTarjeta

    /**
     * Inicia la vista de resumen de compra.
     * 
     * @param seleccionados lista de artículos seleccionados
     * @param seleccionado método de pago seleccionado
     */
    public void inicioDatosDeCompra(List<ArticuloCompra> seleccionados, MetodoPago seleccionado){
        try {
            DatosDeCompraController compra = (DatosDeCompraController) cambiarEscena("DatosDeCompra.fxml", 600, 400);
            compra.setEscenario(this);
            compra.setDatosCompra(seleccionados, seleccionado);
        } catch (Exception error) {
            error.printStackTrace();
        }//try catch
    }//inicioDatosDeCompra
}//class