package co.edu.uniquindio.PixelRent;

import co.edu.uniquindio.PixelRent.equipos_visuales.*;
import co.edu.uniquindio.PixelRent.personas.Cliente;
import co.edu.uniquindio.PixelRent.personas.Operador;
import co.edu.uniquindio.PixelRent.sede.Reserva;
import co.edu.uniquindio.PixelRent.sede.Sede;

import javax.swing.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showInputDialog;

public class Empresa {
    String nombre = "PixelRent";
    public static List<Cliente> listaClientes = new ArrayList<>();
    public static List<Sede> listaSedes = new ArrayList<>();
    public static List<Reserva> listaReservas = new ArrayList<>();

    static void main() {
        var pedro = new Cliente("1231", "Pedro", "21313123", "asdjkads@gmal.com");
        var casanias = new Cliente("1232", "Casañas", "213112312", "asasdadsads@gmal.com");
        listaClientes.add(pedro);
        listaClientes.add(casanias);
        //mostarListaClientes();
        listaReservas.add(new Reserva(pedro, LocalDate.of(2026, 11, 21), Turno.DIA));
        listaReservas.add(new Reserva(pedro, LocalDate.of(2022, 10, 9), Turno.TARDE));
        mostrarReservas();
        //mostrarReservaPorId();

        Sede sedeNorte = new Sede("PixelRent Norte", "Cra 14 #17N-05");
        Sede sedeSur = new Sede("PixelRent Sur", "Calle 50 #19-23");
        listaSedes.add(sedeNorte);
        listaSedes.add(sedeSur);

        Operador.listaOperadores.add(new Operador("Juan", "1234", true, 8));
        Operador.listaOperadores.add(new Operador("Paco", "123", false, 2));
        sedeNorte.getListaEquipos().add(new Camara("adsa", "asda", 1, "50MPX"));
//       sedeNorte.getListaEquipos().add(new Dron("asdadsa","adsasda",31));
        sedeNorte.getListaEquipos().add(new KitIluminacion("asd312", "adsadsa", 1));
//        mostrarListaEquipos(sedeNorte);


    // MENU PRINCIPAL
        String option = "";
        do {
            option = JOptionPane.showInputDialog
                    ("--- MENU PRINCIPAL PIXELRENT---\n" +
                    "1. \n" +
                    "2.  \n" +
                    "3.   \n" );




        } while ( option != null && !option.equals(""));

    }

    //Crear o agregar elementos o asignar elementos

    public static void agregarReserva(){
        if(listaClientes.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay  clientes registrados, debe registrar uno primero.");
            return;
        }
        String idCliente = JOptionPane.showInputDialog("Ingrese el ID del cliente para la reserva: ");
        Cliente clienteEncontrado = null;

        for (Cliente cliente : listaClientes){
            if (cliente.id().equals(idCliente)){
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado == null){
            JOptionPane.showMessageDialog(null, "El cliente no fue encontrado: ");
            return;
        }
        LocalDate fechaSolicitud = null;
        try {
            String input = JOptionPane.showInputDialog("Ingrese la fecha de la solicitud (AAAA-MM-DD): ");
            if (input == null) return;
                fechaSolicitud = LocalDate.parse(input);
        } catch (DateTimeParseException e){
            JOptionPane.showMessageDialog(null, "Formato erroneo");
            return;
        }

        Turno turno = null;
        try {
            String turnoInput = JOptionPane.showInputDialog("Ingrese el turno (DIA - TARDE - NOCHE): ");
            if(turnoInput==null) return;
            turno = Turno.valueOf(turnoInput.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Turno invalido. Debe ser (DIA - TARDE - NOCHE). ");
        }

        Reserva nuevaReserva = new Reserva(clienteEncontrado, fechaSolicitud, turno);

        boolean agregarMas = true;
        while (agregarMas){
            String idEquipo = JOptionPane.showInputDialog("Ingrese el ID del equipo que desea  alquilar: ");

            if (idEquipo == null || idEquipo.trim().isEmpty()) {
                agregarMas = false;
                break;
            }

            EquipoVisual equipoVisualEncontrado = null;
            for (Sede sede : listaSedes) {
                for (EquipoVisual equipoVisual : sede.getListaEquipos()){
                    if (equipoVisual.getId().equalsIgnoreCase(idEquipo.trim())){
                        equipoVisualEncontrado = equipoVisual;
                        break;
                    }
                }
                if (equipoVisualEncontrado != null)break;;
            }
            if (equipoVisualEncontrado != null) {
                int horas = 0;
                boolean horasValidas=false;

                while (!horasValidas){
                    String horaReserva = JOptionPane.showInputDialog("Ingrese las  horas de uso para " +
                            equipoVisualEncontrado.getMarca() + " " + equipoVisualEncontrado.getModelo() + ": ");
                    if (horaReserva == null){
                        break;
                    }
                try {
                    horas = Integer.parseInt(horaReserva.trim());
                    if (horas > 0){
                        horasValidas = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Las horas de uso deben ser mayor a 0");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Entrada invalida.");
                }
                }
                if (horasValidas){
                    equipoVisualEncontrado.setHoraReserva(horas);
                    if (equipoVisualEncontrado instanceof Dron dron){
                        dron.preguntarMinutosVuelo();
                    }

                    nuevaReserva.agregarEquipo(equipoVisualEncontrado);
                    JOptionPane.showMessageDialog(null, "Equipo " + equipoVisualEncontrado.getId() + " agregado con exito");

                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro equipo a la reserva?", "Agregar equipo", JOptionPane.YES_NO_OPTION);
                    if (respuesta != JOptionPane.YES_OPTION) {
                        agregarMas = false;
                    }
                }
            }

        }

        listaReservas.add(nuevaReserva);
        JOptionPane.showMessageDialog(null, "La reserva fue creada con exito.");
    }


    public static void agregarCliente() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente");
        String documento = JOptionPane.showInputDialog("Ingrese el documento");
        String telefono = showInputDialog("Ingrese el telefono");
        String correo = showInputDialog("Ingrese el correo");
        Cliente nuevoCliente = new Cliente(nombre, documento, telefono, correo);
        listaClientes.add(nuevoCliente);
    }

    public static void contratarOperador() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del operador");
        String documento = JOptionPane.showInputDialog("Ingrese el documento");
        String certificar = JOptionPane.showInputDialog("Ingrese si tiene certificacion (Si, No)");
        boolean certificacion;
        if (certificar.equalsIgnoreCase("si")) {
            certificacion = true;
        } else certificacion = false;
        int aniosExperencia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese los anios de experiencia"));
        Operador nuevoOperador = new Operador(nombre, documento, certificacion, aniosExperencia);
        Operador.listaOperadores.add(nuevoOperador);
    }

    public static void asignarReservaOperador() {
        String idReserva = JOptionPane.showInputDialog("Ingrese el ID de la reserva: ");
        for (Reserva reserva : listaReservas) {
            if (reserva.getId().equals(idReserva)) {
                String idOperador = JOptionPane.showInputDialog("Ingrese el ID del operador que quiere asignar: ");
                for (Operador operador : Operador.listaOperadores) {
                    if (operador.getDocumento().equals(idOperador)) operador.setListaReservas(reserva);
                }
            }
        }
    }

    //Mostrar
    public static void mostarListaClientes() {
        String mensaje = "";
        for (Cliente cliente : listaClientes) {
            mensaje += cliente.toString() + "\n ----------------" + "\n";
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public static void mostrarReservas() {
        String mensaje = "";
        for (Reserva reserva : listaReservas) {
            mensaje += reserva.toString() + "\n ----------------------" + "\n";
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public static void mostrarReservaPorId() {
        String id = JOptionPane.showInputDialog("Ingrese el id del cliente");
        String mensaje = "No hay ninguna reserva con ese cliente";
        for (Reserva reserva : listaReservas) {
            if (reserva.getId().equals(id)) {
                mensaje = reserva.toString();
            }
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public static void mostrarOperador() {
        String mensaje = "";
        for (Operador operador : Operador.listaOperadores) {
            mensaje += operador.toString() + "\n--------------------------" + "\n";
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public static void mostrarListaEquipos(Sede sede) {
        String mensaje = sede.getNombre() + "\n";
        for (EquipoVisual equipoVisual : sede.getListaEquipos()) {
            mensaje += equipoVisual.toString() + "\n -------------" + "\n";
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }



    // editar - modificar elementos

    public static void actualizarEquipo(Sede sede) {
        String idEquipoActualizar = JOptionPane.showInputDialog("Ingrese el ID del equipo que desea actualizar: ");
        for (EquipoVisual equipoVisual : sede.getListaEquipos()) {
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

    public static void actualizarCamara(EquipoVisual equipoVisual) {

        if (equipoVisual instanceof Camara camara) {
            camara.setMarca(JOptionPane.showInputDialog("Ingrese la nueva marca de la camara:"));
            camara.setModelo(JOptionPane.showInputDialog("Ingrese el nuevo modelo de la camara:"));
            camara.setEstadoEquipo(JOptionPane.showInputDialog("Ingrese el nuevo estado del equipo ( ocupado - fuera de servicio - en mantenimiento ): "));
            camara.setEstabilizador(JOptionPane.showInputDialog("¿La camara tiene estabilizador?"));
            camara.setResolucionMaxima(JOptionPane.showInputDialog("Ingrese la resolucion maxima:"));
        }
    }

    public static void actualizarDron(EquipoVisual equipoVisual) {
        if (equipoVisual instanceof Dron dron) {
            dron.setMarca(JOptionPane.showInputDialog("Ingrese la nueva marca del dron: "));
            dron.setModelo(JOptionPane.showInputDialog("Ingrese el nuevo modelo del dron: "));
            dron.setEstadoEquipo(JOptionPane.showInputDialog("Ingrese el nuevo estado del equipo ( ocupado - fuera de servicio - en mantenimiento ): "));
            dron.setAutonomiaVuelo(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva autonomia de vuelo del dron: ")));
        }
    }

    public static void actualizarKitIluminacion(EquipoVisual equipoVisual) {
        if (equipoVisual instanceof KitIluminacion kitIluminacion) {
            kitIluminacion.setMarca(JOptionPane.showInputDialog("Ingrese la nueva marca del kit de iluminacion: "));
            kitIluminacion.setModelo(JOptionPane.showInputDialog("Ingrese el nuevo modelo del kit de iluminacion: "));
            kitIluminacion.setEstadoEquipo(JOptionPane.showInputDialog("Ingrese el nuevo estado del equipo ( ocupado - fuera de servicio - en mantenimiento ): "));
            kitIluminacion.setLuces(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad de luces")));
        }
    }

    public static void actualizarOperador(Sede sede) {
        String idOperadorActualizar = JOptionPane.showInputDialog("Ingrese el id del operador a actualizar");
        boolean encontrar = false;
        for (Operador operador : Operador.listaOperadores) {
            if (operador.getDocumento().equals(idOperadorActualizar)) {
                encontrar = true;
                operador.setNombre(JOptionPane.showInputDialog("Ingrese el nombre del operador: "));
                operador.setCertificacion(JOptionPane.showInputDialog("Ingrese si tiene certificacion ( si / no ): "));
                operador.setAniosExperencia(Integer.parseInt(JOptionPane.showInputDialog("Ingrese los años de experiencia: ")));
                JOptionPane.showMessageDialog(null, "Operador actualizado con exito.");
                break;
            }
        }
        if (encontrar != true) {
            JOptionPane.showMessageDialog(null, "El operador no fue encontrado");
        }
    }

    public static void actualizarEstadoReserva(){
        String idReservaAct = JOptionPane.showInputDialog("Ingrese el ID de la reserva que desea actualizar: ");
        String mensaje = "No se encontro la reserva";

        for (Reserva reserva : listaReservas){
            if (reserva.getId().equals(idReservaAct)){
                reserva.setEstado(JOptionPane.showInputDialog("Ingrese el nuevo estado ( solicitada - confirmada - cancelada - finalizada ): "));
                mensaje = "Se actualizo correctamente.";
                break;
            }
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }




    // ELIMINAR - BORRAR - DELETE - 删除
    public static void eliminarOperador(Sede sede) {
        String idOperadorEliminar = JOptionPane.showInputDialog("Ingrese el id del operador a eliminar");
        String mensaje = "El operador no fue encontrado.";

       for (Operador operador : Operador.listaOperadores){
           if (operador.getDocumento().equals(idOperadorEliminar)){
               Operador.listaOperadores.remove(operador);
               mensaje = "El operador fue eliminado con exito";
               break;
           }
           JOptionPane.showMessageDialog(null, mensaje);
       }

    }

    public static void eliminarEquipo(Sede sede) {
        String idEquipoEliminar = JOptionPane.showInputDialog("Ingrese el id del equipo a eliminar.");
        String mensaje = "El equipo no fue encontrado.";

        for (EquipoVisual equipoVisual : sede.listaEquipos) {
            if (equipoVisual.getId().equals(idEquipoEliminar)) {
                sede.listaEquipos.remove(equipoVisual);
                mensaje = "El equipo fue eliminado con exito.";
                break;
            }
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public static void eliminarReserva(Sede sede){
        String idReservaEliminar = JOptionPane.showInputDialog("Ingrese el id de la reserva a eliminar: ");
        String mensaje = "La reserva no fue encontrada";

        for (Reserva reserva : listaReservas){
            if (reserva.getId().equals(idReservaEliminar)){
                listaReservas.remove(reserva);
                mensaje = "La reserva fue eliminada con exito";
                break;
            }
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public static void eliminarCliente(Sede sede){
        String idClienteEliminar = JOptionPane.showInputDialog("Ingrese el id del cliente a eliminar");
        String mensaje = "El cliente no fue encontrado";

        for (Cliente cliente : listaClientes){
            if (cliente.id().equals(idClienteEliminar)){
                listaClientes.remove(cliente);
                mensaje = "El cliente fue eliminado con exito";
                break;
            }
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }
//  COSTO RESERVA
}
