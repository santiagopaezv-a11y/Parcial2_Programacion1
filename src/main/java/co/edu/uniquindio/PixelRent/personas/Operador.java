package co.edu.uniquindio.PixelRent.personas;

import co.edu.uniquindio.PixelRent.sede.Reserva;

import java.util.ArrayList;

public class Operador {
    private String nombre;
    private String documento;
    private boolean certificacion;
    private int aniosExperencia;
    private ArrayList<Reserva> listaReservas;
    public static ArrayList<Operador> listaOperadores=new ArrayList<>();

    public Operador(String nombre, String documento, boolean certificacion, int aniosExperencia) {
        this.nombre = nombre;
        this.documento = documento;
        this.certificacion = certificacion;
        this.aniosExperencia = aniosExperencia;
        listaReservas=new ArrayList<>();
    }

    public static ArrayList<Operador> getListaOperadores() {
        return listaOperadores;
    }

    public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(Reserva reserva) {
        this.listaReservas.add(reserva);
    }

    public static void setListaOperadores(ArrayList<Operador> listaOperadores) {
        Operador.listaOperadores = listaOperadores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public boolean isCertificacion() {
        return certificacion;
    }

    public void setCertificacion(boolean certificacion) {
        this.certificacion = certificacion;
    }

    public int getAniosExperencia() {
        return aniosExperencia;
    }

    public void setAniosExperencia(int aniosExperencia) {
        this.aniosExperencia = aniosExperencia;
    }

    @Override
    public String toString() {
        String mensaje="Nombre: "+getNombre()+"\n Documento: "+getDocumento()
                +"\n Certificacion: "+ isCertificacion()+"\n Anios de esperiencia: "+ getAniosExperencia();
        for (Reserva reserva:listaReservas){
            mensaje+="\n Reservas en las que participa: "+reserva.getId()+"\n";
        }
        return mensaje;
    }
}
