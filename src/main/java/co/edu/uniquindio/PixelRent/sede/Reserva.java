package co.edu.uniquindio.PixelRent.sede;

import co.edu.uniquindio.PixelRent.equipos_visuales.Camara;
import co.edu.uniquindio.PixelRent.equipos_visuales.EquipoVisual;
import co.edu.uniquindio.PixelRent.equipos_visuales.EstadoEquipo;
import co.edu.uniquindio.PixelRent.equipos_visuales.Turno;
import co.edu.uniquindio.PixelRent.personas.Cliente;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Reserva{
    private static int cantidadReservas=0;
    private final String id;
    private final Cliente cliente;
    private final LocalDate fechaSolicitud;
    private Estado estado;
    private final Turno turno;
    ArrayList<EquipoVisual> listaEquiposAudiovisuales;
    public Reserva(Cliente cliente, LocalDate fechaSolicitud, Turno turno){
        this.id="R-"+(++cantidadReservas);
        this.cliente=cliente;
        this.fechaSolicitud=fechaSolicitud;
        this.turno=turno;
        this.estado=Estado.SOLICITADA;
        listaEquiposAudiovisuales=new ArrayList<>();
    }

    public static int getCantidadReservas() {
        return cantidadReservas;
    }

    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public Turno getTurno() {
        return turno;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null){
            return;}
        switch (estado.toLowerCase()) {
            case "confirmada":
            if (this.estado == Estado.CANCELADA || this.estado == Estado.FINALIZADA){
                JOptionPane.showMessageDialog(null, "No se puede confirmar una reserva que esta en " + this.estado);
            } else {
                this.estado = Estado.CONFIRMADA;
            }
            break;

            case "cancelada":
                this.estado=Estado.CANCELADA;
                break;

            case "finalizada":
                this.estado = Estado.FINALIZADA;
                break;

            case "solicitada":
                this.estado = Estado.SOLICITADA;
                break;

            default:
                JOptionPane.showMessageDialog(null, "Estado no valido");
                break;
        }
    }

    @Override
    public String toString() {
        return "Id reserva: "+getId()+"\n"
                +"\n Cliente: "+"\n"+cliente.toString()+"\n"
                +"\nFecha solicitada: "+getFechaSolicitud()+"\n"
                +"Turno: "+getTurno()+"\n"
                +"Estado: "+ getEstado();
    }



    }

