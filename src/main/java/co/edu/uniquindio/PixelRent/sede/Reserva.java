package co.edu.uniquindio.PixelRent.sede;

import co.edu.uniquindio.PixelRent.equipos_visuales.EquipoVisual;
import co.edu.uniquindio.PixelRent.equipos_visuales.Turno;
import co.edu.uniquindio.PixelRent.personas.Cliente;

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
        this.id="Reserva-"+(++cantidadReservas);
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

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
