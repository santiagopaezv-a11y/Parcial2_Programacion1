package co.edu.uniquindio.PixelRent.sede;

import co.edu.uniquindio.PixelRent.Empresa;
import co.edu.uniquindio.PixelRent.equipos_visuales.Camara;
import co.edu.uniquindio.PixelRent.equipos_visuales.Dron;
import co.edu.uniquindio.PixelRent.equipos_visuales.EquipoVisual;
import co.edu.uniquindio.PixelRent.equipos_visuales.KitIluminacion;
import co.edu.uniquindio.PixelRent.personas.Operador;

import javax.swing.*;
import java.util.ArrayList;

public class Sede {
    private static int cantidadSede=0;
    private String id;
    private String nombre;
    private String direccion;
    public static ArrayList<EquipoVisual> listaEquipos;


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
    public void agregarEquipoVisual(){
        String marca;
        String modelo;
        String tipo= JOptionPane.showInputDialog("Ingrese que tipo de equipo es (camara, dron, kit): ");
        switch (tipo.toLowerCase()){
            case "camara":
                marca=JOptionPane.showInputDialog("Ingrese la marca de la camara");
                modelo=JOptionPane.showInputDialog("Ingrese el modelo de la camara");
                String resolucionMaxima=JOptionPane.showInputDialog("¿Cual es la resolucion de la camara?");
                Camara nuevaCamara=new Camara(marca,modelo,0,resolucionMaxima);
                Sede.listaEquipos.add(nuevaCamara);
                JOptionPane.showMessageDialog(null,"Dron creado exictosamente, ID: "+nuevaCamara.getId());
                break;
            case "dron":
                marca=JOptionPane.showInputDialog("Ingrese la marca del dron");
                modelo=JOptionPane.showInputDialog("Ingrese el modelo del dron");
                int autonomiaVuelo=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la autonomia de vuelo del dron"));
                Dron nuevoDron=new Dron(marca,modelo,0,autonomiaVuelo);
                Sede.listaEquipos.add(nuevoDron);
                JOptionPane.showMessageDialog(null,"Dron creado exictosamente, ID: "+nuevoDron.getId());
                break;
            case "kit":
                marca=JOptionPane.showInputDialog("Ingrese la marca del kit de iluminacion");
                modelo=JOptionPane.showInputDialog("Ingrese el modelo del kit de iluminacion");
                int cantidadLuces=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de luces del kit de ilumacion"));
                KitIluminacion nuevoKit=new KitIluminacion(marca,modelo,cantidadLuces);
                Sede.listaEquipos.add(nuevoKit);
                JOptionPane.showMessageDialog(null,"Dron creado exictosamente, ID: "+nuevoKit.getId());
                break;
        }
    }
}
