package co.edu.uniquindio.PixelRent.sede;

import co.edu.uniquindio.PixelRent.equipos_visuales.*;
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

    public ArrayList<EquipoVisual> getListaEquiposAudiovisuales(){
        return listaEquiposAudiovisuales;
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
                return;
            }
            if (this.listaEquiposAudiovisuales.isEmpty()){
                JOptionPane.showMessageDialog(null, "No se puede confirmar la reserva (Debe tener al menos un equipo audiovisual asignado). ");
                return;
            }
            for (EquipoVisual equipoVisual : this.listaEquiposAudiovisuales){
                if (equipoVisual instanceof Dron dron) {
                    if (dron.getOperadorActual() == null){
                        JOptionPane.showMessageDialog(null, "No se puede confirmar la reserva del dron porque no tiene un operador asignado. ");
                        return;
                    }
                }
            }
            this.estado = Estado.CONFIRMADA;
            JOptionPane.showMessageDialog(null, "Reserva " + this.id + " confirmada exitosamente");
            break;

            case "cancelada":
                if (this.estado == Estado.FINALIZADA || this.estado == Estado.CANCELADA){
                JOptionPane.showMessageDialog(null, "No se puede CANCELAR una reserva que ya esta " + this.estado + ".");
                return;
            }
                this.estado = Estado.CANCELADA;
                JOptionPane.showMessageDialog(null, "Reserva " + this.id + " ha sido cancelada. ");
                break;

            case "finalizada":
                if (this.estado!=Estado.CONFIRMADA){
                    JOptionPane.showMessageDialog(null,"Solo se pueden finalizar reservas que se encuentran CONFIRMADAS.");
                    return;
                }
                this.estado = Estado.FINALIZADA;
                JOptionPane.showMessageDialog(null, "Reserva " + this.id + " ha sido finalizada. ");
                break;

            case "solicitada":
                if (this.estado != Estado.SOLICITADA){
                    JOptionPane.showMessageDialog(null, "No se puede regresar una reserva al estado SOLICITADA. ");
                return;
                }
                break;

            default:
                JOptionPane.showMessageDialog(null, "Estado no valido (solicitada - confirmada - finalizada - cancelada). ");
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

    public void agregarEquipo(EquipoVisual equipoVisual){
        if(equipoVisual != null){
            this.listaEquiposAudiovisuales.add(equipoVisual);
        }
    }

    public double calcularCostoTotal(){
        double total = 0;
        for (EquipoVisual equipoVisual :listaEquiposAudiovisuales){
            total+= equipoVisual.calcularPrecio();
        }
        return total;
    }
 }

