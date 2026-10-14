package co.edu.uniquindio.PixelRent.personas;

import co.edu.uniquindio.PixelRent.Empresa;
import co.edu.uniquindio.PixelRent.equipos_visuales.Turno;
import co.edu.uniquindio.PixelRent.sede.Reserva;

import javax.swing.*;
import java.time.LocalDate;

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
    public void crearReserva(){
        String idCliente= JOptionPane.showInputDialog("Ingrese el ID del cliente");
        for (Cliente cliente: Empresa.listaClientes){
            if (idCliente.equals(cliente.id())){
                LocalDate fechaSolicitada=LocalDate.parse(showInputDialog("Ingrese la fecha solicitada (Anio-mes-dia)"));
                String turnoSolicitado=JOptionPane.showInputDialog("Ingresa el turno (dia,tarde,noche)");
                Turno turno=Turno.NOCHE;
                Operador operador;
                do {
                    switch (turnoSolicitado){
                        case "dia":turno=Turno.DIA;
                            break;
                        case "tarde":turno=Turno.TARDE;
                            break;
                        case "noche":turno=Turno.NOCHE;
                            break;
                    }
                    JOptionPane.showMessageDialog(null, "Turno Invalido");
                }while (turnoSolicitado.equals("dia")||turnoSolicitado.equals("tarde")||turnoSolicitado.equals("noche"));
                Reserva nuevaReserva=new Reserva(cliente,fechaSolicitada,turno);
                Empresa.listaReservas.add(nuevaReserva);
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"No existe ese ID de algun cliente");
    }
    @Override
    public String toString() {
        return "Documento:" +id + "\n" +"Nombre: "+nombre + "\n" +"Telefono: "+telefono + "\n" +"Correo: "+correo ;
    }
}
