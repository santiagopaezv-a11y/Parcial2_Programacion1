package co.edu.uniquindio.PixelRent;

import co.edu.uniquindio.PixelRent.equipos_visuales.*;
import co.edu.uniquindio.PixelRent.personas.Cliente;
import co.edu.uniquindio.PixelRent.personas.Operador;
import co.edu.uniquindio.PixelRent.sede.Reserva;
import co.edu.uniquindio.PixelRent.sede.Sede;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showInputDialog;

public class Empresa {
    String nombre="PixelRent";
    public static List<Cliente> listaClientes=new ArrayList<>();
    public static List<Sede> listaSedes=new ArrayList<>();
    public static List<Reserva> listaReservas=new ArrayList<>();
    static void main() {
        var pedro=new Cliente("1231","Pedro","21313123","asdjkads@gmal.com");
        var casanias=new Cliente("1232","Casañas","213112312","asasdadsads@gmal.com");
        listaClientes.add(pedro);
        listaClientes.add(casanias);
        //mostarListaClientes();
        listaReservas.add(new Reserva(pedro,LocalDate.of(2026,11,21),Turno.DIA));
        listaReservas.add(new Reserva(pedro,LocalDate.of(2022,10,9),Turno.TARDE));
        mostrarReservas();
        //mostrarReservaPorId();
        Sede sedeNorte=new Sede("Sede norte","Allí");
        Operador.listaOperadores.add(new Operador("Juan","1234",true,8));
        Operador.listaOperadores.add(new Operador("Paco","123",false,2));
        sedeNorte.getListaEquipos().add(new Camara("adsa","asda","sad12"));
//       sedeNorte.getListaEquipos().add(new Dron("asdadsa","adsasda",31));
        sedeNorte.getListaEquipos().add(new KitIluminacion("asd312","adsadsa",1));
//        mostrarListaEquipos(sedeNorte);

    }
    //Crear o agregar elementos o asignar elementos
    public static void agregarCliente(){
        String nombre= showInputDialog("Ingrese el nombre del cliente");
        String documento= showInputDialog("Ingrese el documento");
        String telefono= showInputDialog("Ingrese el telefono");
        String correo= showInputDialog("Ingrese el correo");
        Cliente nuevoCliente=new Cliente(nombre,documento,telefono,correo);
        listaClientes.add(nuevoCliente);
    }
    public static void contratarOperador(){
        String nombre=JOptionPane.showInputDialog("Ingrese el nombre del operador");
        String documento=JOptionPane.showInputDialog("Ingrese el documento");
        String certificar=JOptionPane.showInputDialog("Ingrese si tiene certificacion (Si, No)");
        boolean certificacion;
        if (certificar.equalsIgnoreCase("si")) {
            certificacion=true;
        }else certificacion=false;
        int aniosExperencia=Integer.parseInt(JOptionPane.showInputDialog("Ingrese los anios de experiencia"));
        Operador nuevoOperador=new Operador(nombre,documento,certificacion,aniosExperencia);
        Operador.listaOperadores.add(nuevoOperador);
    }
    public static void asignarReservaOperador(){
        String idReserva=JOptionPane.showInputDialog("Ingrese el ID de la reserva: ");
        for (Reserva reserva:listaReservas){
            if (reserva.getId().equals(idReserva)){
                String idOperador=JOptionPane.showInputDialog("Ingrese el ID del operador que quiere asignar: ");
                for (Operador operador:Operador.listaOperadores){
                    if (operador.getDocumento().equals(idOperador)) operador.setListaReservas(reserva);
                }
            }
        }
    }

    //Mostrar
    public static void mostarListaClientes(){
        String mensaje="";
        for (Cliente cliente:listaClientes){
            mensaje += cliente.toString()+"\n ----------------" + "\n";
        }
        JOptionPane.showMessageDialog(null,mensaje);
    }
    public static void mostrarReservas(){
        String mensaje="";
        for (Reserva reserva: listaReservas){
            mensaje+= reserva.toString() + "\n ----------------------" + "\n";
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public static void mostrarReservaPorId(){
        String id=JOptionPane.showInputDialog("Ingrese el id del cliente");
        String mensaje = "No hay ninguna reserva con ese cliente";
        for (Reserva reserva:listaReservas){
            if (reserva.getId().equals(id)){
                mensaje=reserva.toString();
            }
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public static void mostrarOperador(){
        String mensaje="";
        for (Operador operador: Operador.listaOperadores){
            mensaje+=operador.toString()+"\n--------------------------"+"\n";
        }
        JOptionPane.showMessageDialog(null,mensaje);
    }
    public static void mostrarListaEquipos(Sede sede){
        String mensaje=sede.getNombre()+"\n";
        for (EquipoVisual equipoVisual: sede.getListaEquipos()){
            mensaje+=equipoVisual.toString()+"\n -------------"+"\n";
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }

    // editar - modificar elementos

    public static void actualizarEquipo(Sede sede){
        String idEquipoActualizar = JOptionPane.showInputDialog("Ingrese el ID del equipo que desea actualizar: ");
        for (EquipoVisual equipoVisual : sede.getListaEquipos()){
            if (equipoVisual.getId().equals(idEquipoActualizar)){

                for (int i = 0; i < equipoVisual.getId().length(); i++) {
                    char caracter = equipoVisual.getId().charAt(i);
                    switch (caracter){

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
    public static void actualizarCamara(EquipoVisual equipoVisual){

        if (equipoVisual instanceof Camara camara){
            camara.setMarca(JOptionPane.showInputDialog("Ingrese la nueva marca de la camara:"));
            camara.setModelo(JOptionPane.showInputDialog("Ingrese el nuevo modelo de la camara:"));
            camara.setEstadoEquipo(JOptionPane.showInputDialog("Ingrese el nuevo estado del equipo ( ocupado - fuera de servicio - en mantenimiento ): "));
            camara.setEstabilizador(JOptionPane.showInputDialog("¿La camara tiene estabilizador?"));
            camara.setResolucionMaxima(JOptionPane.showInputDialog("Ingrese la resolucion maxima:"));
        }
    }
    public static void actualizarDron(EquipoVisual equipoVisual){
        if (equipoVisual instanceof Dron dron){
            dron.setMarca(JOptionPane.showInputDialog("Ingrese la nueva marca del dron: "));
            dron.setModelo(JOptionPane.showInputDialog("Ingrese el nuevo modelo del dron: "));
            dron.setEstadoEquipo(JOptionPane.showInputDialog("Ingrese el nuevo estado del equipo ( ocupado - fuera de servicio - en mantenimiento ): "));
            dron.setAutonomiaVuelo(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva autonomia de vuelo del dron: ")));
        }
    }
    public static void actualizarKitIluminacion(EquipoVisual equipoVisual){
        if(equipoVisual instanceof KitIluminacion kitIluminacion){
            kitIluminacion.setMarca(JOptionPane.showInputDialog("Ingrese la nueva marca del kit de iluminacion: "));
            kitIluminacion.setModelo(JOptionPane.showInputDialog("Ingrese el nuevo modelo del kit de iluminacion: "));
            kitIluminacion.setEstadoEquipo(JOptionPane.showInputDialog("Ingrese el nuevo estado del equipo ( ocupado - fuera de servicio - en mantenimiento ): "));
            kitIluminacion.setLuces(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad de luces")));
        }
    }

    public static void actualizarOperador(Sede sede){
        String idOperadorActualizar = JOptionPane.showInputDialog("Ingrese el id del operador a actualizar");
        boolean encontrar = false;
        for(Operador operador : Operador.listaOperadores){
            if(operador.getDocumento().equals(idOperadorActualizar)){
                encontrar = true;
                operador.setNombre(JOptionPane.showInputDialog("Ingrese el nombre del operador: "));
                operador.setCertificacion(JOptionPane.showInputDialog("Ingrese si tiene certificacion ( si / no ): "));
                operador.setAniosExperencia(Integer.parseInt(JOptionPane.showInputDialog("Ingrese los años de experiencia: ")));
                JOptionPane.showMessageDialog(null, "Operador actualizado con exito.");
                break;
            }
        }
        if (encontrar != true){
            JOptionPane.showMessageDialog(null ,"El operador no fue encontrado");
        }
    }

    public static void eliminarOperador (Sede sede) {
        String idOperadorActualizar = JOptionPane.showInputDialog("Ingrese el id del operador a actualizar");
        boolean encontrar = false;

        for (int i = 0; i < Operador.listaOperadores.size(); i++) {
            Operador operador = Operador.listaOperadores.get(i);
            if (operador.getDocumento().equals(idOperadorActualizar)){
                Operador.listaOperadores.remove(i);
                encontrar = true;
                JOptionPane.showMessageDialog(null, "Operador eliminado exitosamente.");
                break;
            }
        }
        if (encontrar != true){
            JOptionPane.showMessageDialog(null, "El operador no fue encontrado:");
        }

    }
}
