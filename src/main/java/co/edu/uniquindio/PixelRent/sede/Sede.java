package co.edu.uniquindio.PixelRent.sede;

import co.edu.uniquindio.PixelRent.equipos_visuales.EquipoVisual;

import java.util.ArrayList;

public class Sede {
    private static int cantidadSede=0;
    private String id;
    private String nombre;
    private String direccion;
    private ArrayList<EquipoVisual> listaEquipos;

    public Sede(String nombre, String direccion) {
        this.id = "Sede-"+(++cantidadSede);
        this.nombre = nombre;
        this.direccion = direccion;
        listaEquipos= new ArrayList<>();
    }

    public static int getCantidadSede() {
        return cantidadSede;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<EquipoVisual> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(ArrayList<EquipoVisual> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }
}
