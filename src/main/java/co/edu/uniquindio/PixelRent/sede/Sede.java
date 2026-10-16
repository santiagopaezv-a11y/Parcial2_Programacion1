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
    public ArrayList<EquipoVisual> listaEquipos;


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
                Camara nuevaCamara=new Camara(marca,modelo,resolucionMaxima);
                this.listaEquipos.add(nuevaCamara);
                JOptionPane.showMessageDialog(null,"Camara creada exictosamente, ID: "+nuevaCamara.getId());
                break;
            case "dron":
                marca=JOptionPane.showInputDialog("Ingrese la marca del dron");
                modelo=JOptionPane.showInputDialog("Ingrese el modelo del dron");
                int autonomiaVuelo=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la autonomia de vuelo del dron"));
                Dron nuevoDron=new Dron(marca,modelo,autonomiaVuelo);
                this.listaEquipos.add(nuevoDron);
                JOptionPane.showMessageDialog(null,"Dron creado exictosamente, ID: "+nuevoDron.getId());
                break;
            case "kit":
                marca=JOptionPane.showInputDialog("Ingrese la marca del kit de iluminacion");
                modelo=JOptionPane.showInputDialog("Ingrese el modelo del kit de iluminacion");
                int cantidadLuces=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de luces del kit de ilumacion"));
                KitIluminacion nuevoKit=new KitIluminacion(marca,modelo,cantidadLuces);
                this.listaEquipos.add(nuevoKit);
                JOptionPane.showMessageDialog(null,"Kit de iluminacion creado exictosamente, ID: "+nuevoKit.getId());
                break;
        }
    }
    public void mostrarListaEquipos() {

        String mensaje = getNombre() + "\n";
        for (EquipoVisual equipoVisual : listaEquipos){
            mensaje += equipoVisual.toString() + "\n -------------" + "\n";
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public void actualizarEquipo() {
        String idEquipoActualizar = JOptionPane.showInputDialog("Ingrese el ID del equipo que desea actualizar: ");
        for (EquipoVisual equipoVisual : getListaEquipos()) {
            if (equipoVisual.getId().equals(idEquipoActualizar)) {

                for (int i = 0; i < equipoVisual.getId().length(); i++) {
                    char caracter = equipoVisual.getId().charAt(i);
                    switch (caracter) {

                        case 'C':
                            actualizarCamara(equipoVisual);
                            break;

                        case 'D':
                            actualizarDron(equipoVisual);

                            break;

                        case 'K':
                            actualizarKitIluminacion(equipoVisual);
                            break;

                    }

                }

            }

        }
    }

    public void actualizarCamara(EquipoVisual equipoVisual) {

        if (equipoVisual instanceof Camara camara) {
            camara.setMarca(JOptionPane.showInputDialog("Ingrese la nueva marca de la camara:"));
            camara.setModelo(JOptionPane.showInputDialog("Ingrese el nuevo modelo de la camara:"));
            camara.setEstadoEquipo(JOptionPane.showInputDialog("Ingrese el nuevo estado del equipo ( ocupado - fuera de servicio - en mantenimiento ): "));
            camara.setEstabilizador(JOptionPane.showInputDialog("¿La camara tiene estabilizador?"));
            camara.setResolucionMaxima(JOptionPane.showInputDialog("Ingrese la resolucion maxima:"));
            camara.preguntarEstabizador();
        }
    }

    public void actualizarDron(EquipoVisual equipoVisual) {
        if (equipoVisual instanceof Dron dron) {
            dron.setMarca(JOptionPane.showInputDialog("Ingrese la nueva marca del dron: "));
            dron.setModelo(JOptionPane.showInputDialog("Ingrese el nuevo modelo del dron: "));
            dron.setEstadoEquipo(JOptionPane.showInputDialog("Ingrese el nuevo estado del equipo ( ocupado - fuera de servicio - en mantenimiento ): "));
            dron.setAutonomiaVuelo(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva autonomia de vuelo del dron: ")));
        }
    }

    public void actualizarKitIluminacion(EquipoVisual equipoVisual) {
        if (equipoVisual instanceof KitIluminacion kitIluminacion) {
            kitIluminacion.setMarca(JOptionPane.showInputDialog("Ingrese la nueva marca del kit de iluminacion: "));
            kitIluminacion.setModelo(JOptionPane.showInputDialog("Ingrese el nuevo modelo del kit de iluminacion: "));
            kitIluminacion.setEstadoEquipo(JOptionPane.showInputDialog("Ingrese el nuevo estado del equipo ( ocupado - fuera de servicio - en mantenimiento ): "));
            kitIluminacion.setLuces(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad de luces")));
            kitIluminacion.preguntarControlInalambrico();
        }
    }
    public void eliminarEquipo() {
        String idEquipoEliminar = JOptionPane.showInputDialog("Ingrese el id del equipo a eliminar.");
        String mensaje = "El equipo no fue encontrado.";

        for (EquipoVisual equipoVisual : listaEquipos) {
            if (equipoVisual.getId().equals(idEquipoEliminar)) {
                listaEquipos.remove(equipoVisual);
                mensaje = "El equipo fue eliminado con exito.";
                break;
            }
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public void moduloSede(){
        try {
            int opcion;
            do {
                opcion=Integer.parseInt(JOptionPane.showInputDialog(
                        "Menu "+ getNombre()+
                                "\n 1. Agregar equipo audiovisual"+
                                "\n 2. Mostrar lista general"+
                                "\n 3. Modificar equipo audiovisual"+
                                "\n 4. Eliminar equipo audiovisual"+
                                "\n 0. Cambiar de sede"
                ));
                switch (opcion){
                    case 1: agregarEquipoVisual(); break;
                    case 2: mostrarListaEquipos(); break;
                    case 3: actualizarEquipo(); break;
                    case 4: eliminarEquipo(); break;
                }
            }while (opcion!=0);
        }catch (Exception e){
            Empresa.moduloPrincipal();
        }
    }
}
