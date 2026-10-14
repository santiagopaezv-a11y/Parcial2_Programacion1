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
        //mostrarReservas();
        //mostrarReservaPorId();
        Sede sedeNorte=new Sede("Sede norte","Allí");
        Operador.listaOperadores.add(new Operador("Juan","1234",true,8));
        Operador.listaOperadores.add(new Operador("Paco","123",false,2));
        sedeNorte.getListaEquipos().add(new Camara("adsa","asda","sad12"));
        sedeNorte.getListaEquipos().add(new Dron("asdadsa","adsasda",31));
        sedeNorte.getListaEquipos().add(new KitIluminacion("asd312","adsadsa",1));
        mostrarListaEquipos(sedeNorte);

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
        String idReserva=JOptionPane.showInputDialog("Ingrese la reserva");
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
            mensaje+=cliente.toString()+"\n ----------------"+"\n";
        }
        JOptionPane.showMessageDialog(null,mensaje);
    }
    public static void mostrarReservas(){
        String mensaje="";
        for (Reserva reserva: listaReservas){
            mensaje+=reserva.toString()+"\n ----------------------"+"\n";
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public static void mostrarReservaPorId(){
        String id=JOptionPane.showInputDialog("Ingrese el id del cliente");
        String mensaje="No hay ninguna reserva con ese cliente";
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
}
