package co.edu.uniquindio.PixelRent.personas;

import co.edu.uniquindio.PixelRent.Empresa;
import co.edu.uniquindio.PixelRent.equipos_visuales.Turno;
import co.edu.uniquindio.PixelRent.sede.Reserva;

import javax.swing.*;
import java.time.LocalDate;

import static co.edu.uniquindio.PixelRent.Empresa.listaClientes;
import static javax.swing.JOptionPane.showInputDialog;

public record Cliente(String id, String nombre, String telefono, String correo){
    @Override
    public String id() {
        return id;
    }

    @Override
    public String nombre() {
        return nombre;
    }

    @Override
    public String telefono() {
        return telefono;
    }

    @Override
    public String correo() {
        return correo;
    }
    @Override
    public String toString() {
        return "Documento:" +id + "\n" +"Nombre: "+nombre + "\n" +"Telefono: "+telefono + "\n" +"Correo: "+correo ;
    }
}
