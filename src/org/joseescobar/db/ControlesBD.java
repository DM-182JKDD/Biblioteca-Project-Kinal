package org.joseescobar.db;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Types;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.joseescobar.model.ArticuloCompra;
import org.joseescobar.model.MetodoPago;
import org.joseescobar.model.Usuario;
import org.joseescobar.validaciones.Validar;

/**
 * Control de acceso a la base de datos.
 * Singleton que maneja consultas y SP de la app.
 * @author Jose Daniel Escobar Macario
 */
public class ControlesBD {

    private static ControlesBD instancia = null;
    private Usuario usuarioLogueado;

    private ControlesBD() { }

    public static ControlesBD getInstancia() {
        if (instancia == null) {
            instancia = new ControlesBD();
        }//if
        return instancia;
    }//getInstancia

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }//getUsuarioLogueado

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }//setUsuarioLogueado

    public List<String> leerGenero() {
        List<String> genero = new ArrayList<>();
        Connection con = Conexion.getInstancia().getConexion(); 
        try (PreparedStatement ps = con.prepareCall("{ call sp_buscar_genero() }");
             ResultSet rs = ps.executeQuery()) 
        {
            while (rs.next()) {
                genero.add(rs.getString("genero"));
            }//while
        } catch (Exception error) {
            error.printStackTrace();
        }//try catch
        return genero;
    }//leerGenero

    public List<String> leerCategoria() {
        List<String> categoria = new ArrayList<>();
        Connection con = Conexion.getInstancia().getConexion();
        try (PreparedStatement ps = con.prepareCall("{ call sp_buscar_categoria() }");
             ResultSet rs = ps.executeQuery()) 
        {
            while (rs.next()) {
                categoria.add(rs.getString("categoria"));
            }//while
        } catch (Exception error) {
            error.printStackTrace();
        }//try catch 
        return categoria;
    }//leerCategoria

    public List<MetodoPago> leerMetodosDePago() {
        List<MetodoPago> metodosDePago = new ArrayList<>();
        Connection con = Conexion.getInstancia().getConexion();
        try (PreparedStatement ps = con.prepareCall("{ call sp_buscar_metodo_de_pago() }");
             ResultSet rs = ps.executeQuery()) 
        {
            while (rs.next()) {
                int id = rs.getInt("id_metodo_de_pago");
                String nombre = rs.getString("metodo_de_pago");
                metodosDePago.add(new MetodoPago(id, nombre));
            }//while
        } catch (Exception error) {
            error.printStackTrace();
        }//try catch 
        return metodosDePago;
    }//leerMetodosDePago

    /**
     * Lee los libros/artículos desde la base de datos usando el SP sp_leer_libros.
     * @return ObservableList con los artículos disponibles.
     */
    public ObservableList<ArticuloCompra> leerLibros() {
        ObservableList<ArticuloCompra> lista = FXCollections.observableArrayList();
        Connection con = Conexion.getInstancia().getConexion();
        try (PreparedStatement ps = con.prepareCall("{ call sp_leer_libros() }");
             ResultSet rs = ps.executeQuery()) 
        {
            while (rs.next()) {
                lista.add(new ArticuloCompra(
                    rs.getInt("id_articulo"),
                    rs.getString("nombre_articulo"),
                    rs.getDouble("precio"),
                    rs.getString("autor"),
                    1 // Cantidad por defecto
                ));
            }//while
        } catch (Exception error) {
            error.printStackTrace();
        }//try catch
        return lista;
    }//leerLibros

public void guardarCompra(List<ArticuloCompra> listaArticulos, int idMetodoSeleccionado) {
    Usuario usuario = getUsuarioLogueado();
    if (usuario == null) {
        Validar.getInstancia().alerta(Alert.AlertType.ERROR, "No hay usuario logueado.");
        return;
    }//if

    int idPagoGenerado = 0;
    Connection con = Conexion.getInstancia().getConexion(); 
    
    try {
        try (CallableStatement csPago = con.prepareCall("{ call sp_registrar_pago(?, ?) }")) {
            csPago.setInt(1, idMetodoSeleccionado);
            csPago.registerOutParameter(2, Types.INTEGER);
            csPago.execute();
            idPagoGenerado = csPago.getInt(2);
        }//try

        if (idPagoGenerado == 0) {
            throw new Exception("Error al registrar el pago: ID generado es cero. Verifique la tabla 'metodo_de_pago'.");
        }//if

        for (ArticuloCompra art : listaArticulos) {
            try (PreparedStatement csCompra = con.prepareCall("{ call sp_guardar_compra(?, ?, ?, ?) }")) {
                csCompra.setInt(1, usuario.getIdUsuario());
                csCompra.setInt(2, art.getIdArticulo());
                csCompra.setInt(3, idPagoGenerado);
                csCompra.setInt(4, art.getCantidad());
                csCompra.execute();
            }//try
        }//for
        Validar.getInstancia().alerta(Alert.AlertType.INFORMATION, "Compra registrada correctamente.");
    } catch (Exception e) {
        e.printStackTrace();
        Validar.getInstancia().alerta(Alert.AlertType.ERROR, "Error al registrar la compra: " + e.getMessage());
    }//try catch
}//guardarCompra
}//class