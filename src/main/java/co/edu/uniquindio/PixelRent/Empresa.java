package co.edu.uniquindio.PixelRent;

import co.edu.uniquindio.PixelRent.equipos_visuales.*;
import co.edu.uniquindio.PixelRent.personas.Cliente;
import co.edu.uniquindio.PixelRent.personas.Operador;
import co.edu.uniquindio.PixelRent.sede.Estado;
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
    static Sede sedeNorte;
    static Sede sedeSur;
    static Sede sedeEste;
    static Sede sedeOeste;

    static void main() {
    sedeNorte=new Sede("Sede Norte", "Carrera 14, calle 20");
    sedeSur=new Sede("Sede Sur","Carrera 20, calle 17");
    sedeEste=new Sede("Sede Este","Carrera 12, calle 2");
    sedeOeste=new Sede("Sede Oeste","Carrera 24, calle 6");
    listaSedes.add(sedeNorte);
    listaSedes.add(sedeSur);
    listaSedes.add(sedeEste);
    listaSedes.add(sedeOeste);
    moduloPrincipal();
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
                String inputFecha = JOptionPane.showInputDialog("Ingrese la fecha de la solicitud (AAAA-MM-DD): ");
                if (inputFecha == null) return;
                fechaSolicitud = LocalDate.parse(inputFecha.trim());
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Formato erroneo. debe ser (AAAA-MM-DD).");
            return;
        }
        Turno turno = null;
        try {
            String turnoInput = JOptionPane.showInputDialog("Ingrese el turno (DIA - TARDE - NOCHE): ");
            if (turnoInput == null)return;
            turno = Turno.valueOf(turnoInput.trim().toUpperCase());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Turno invalido. Debe ser (DIA - TARDE - NOCHE). ");
            return;
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
                if (equipoVisualEncontrado!= null)break;;
            }
            boolean disponible = EquipoDisponibleFecha(equipoVisualEncontrado, fechaSolicitud, turno);
            if (equipoVisualEncontrado!= null && disponible) {
                int horas = 0;
                boolean horasValidas=false;

                while (!horasValidas){
                    String horaReserva = JOptionPane.showInputDialog("Ingrese las  horas de uso para el equipo: ");
                    if (horaReserva == null){
                        break;
                    }
                try {
                    horas = Integer.parseInt(horaReserva.trim());
                    if (horas > 0){
                        horasValidas = true;
                    }else {
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
                        dron.asignarDronOperador();

                        if (dron.getOperadorActual()==null){
                            JOptionPane.showMessageDialog(null, "No se puede agregar un operador a la reserva sin un operador asignado. ");
                        continue;
                        }
                    }
                    nuevaReserva.agregarEquipo(equipoVisualEncontrado);
                    JOptionPane.showMessageDialog(null, "Equipo " + equipoVisualEncontrado.getId() + " agregado con exito.");

                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro equipo a la reserva?", "Agregar equipo", JOptionPane.YES_NO_OPTION);
                    if (respuesta != JOptionPane.YES_OPTION) {
                        agregarMas = false;
                    }
                }
                }else if (equipoVisualEncontrado != null && !equipoVisualEncontrado.isDisponibilidad()){
                JOptionPane.showMessageDialog(null, "El equipo " + idEquipo + " no esta disponible actualmente. ");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro ningun equipo con ese ID. ");
            }
        }
        listaReservas.add(nuevaReserva);
        JOptionPane.showMessageDialog(null, "La reserva fue creada con exito.");
    }
    public static void agregarCliente() {
        String documento = JOptionPane.showInputDialog("Ingrese el documento");
        if (documento == null || documento.trim().isEmpty()) return;
        if (existeCliente(documento)){
            JOptionPane.showMessageDialog(null, "Ya existe un cliente registrado con este ID.");
            return;
        }
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente");
        String telefono = showInputDialog("Ingrese el telefono");
        String correo = showInputDialog("Ingrese el correo");
        Cliente nuevoCliente = new Cliente(documento.trim(), nombre, telefono, correo);
        listaClientes.add(nuevoCliente);
        JOptionPane.showMessageDialog(null,"Cliente registrado con exito.");
    }
    public static void contratarOperador() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del operador");
        String documento = JOptionPane.showInputDialog("Ingrese el documento");
        if (documento == null || documento.trim().isEmpty())return;
        if (existeOperador(documento)){
            JOptionPane.showMessageDialog(null, "Ya existe un operador registrado con este ID. ");
        return;
        }
        String certificar = JOptionPane.showInputDialog("Ingrese si tiene certificacion (Si, No)");
        boolean certificacion;
        if (certificar.equalsIgnoreCase("si")) {
            certificacion = true;
        } else certificacion = false;
        int aniosExperencia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese los anios de experiencia"));
        Operador nuevoOperador = new Operador(nombre, documento.trim(), certificacion, aniosExperencia);
        Operador.listaOperadores.add(nuevoOperador);
        JOptionPane.showMessageDialog(null, "Operador registrado con exito. ");
    }
    public static void asignarReservaOperador() {
        String idReserva = JOptionPane.showInputDialog("Ingrese el ID de la reserva: ");
        for (Reserva reserva : listaReservas) {
            if (reserva.getId().equals(idReserva)) {
                String idOperador = JOptionPane.showInputDialog("Ingrese el ID del operador que quiere asignar: ");
                for (Operador operador : Operador.listaOperadores) {
                    if (operador.getDocumento().equals(idOperador)) operador.setListaReservas( reserva);
                }
            }
        }
    }
    //Mostrar
    public static void consultarReservasConfirmadas(){
        if (listaClientes.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay clientes registrados. ");
            return;
        }

        String idCliente = JOptionPane.showInputDialog("Ingrese el ID del cliente: ");
        if (idCliente == null || idCliente.trim().isEmpty()) return;

        String mensaje = "=== RESERVAS CONFIRMADAS POR EL CLIENTE " + idCliente.trim() + " ===\n\n";
        boolean confirmadas = false;

        for (Reserva reserva : listaReservas){
            if (reserva.getCliente().id().equalsIgnoreCase(idCliente.trim()) &&
                    reserva.getEstado() == Estado.CONFIRMADA){
                mensaje += reserva.toString() + "\n--------------------------\n";
                confirmadas = true;
            }
        }
        if (!confirmadas){
            JOptionPane.showMessageDialog(null,"No se encontraron reservas CONFIRMADAS.");
        } else {
            JOptionPane.showMessageDialog(null, mensaje);
        }
    }

    public static void generarFactura(){
        if (listaReservas.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay reservas registradas en el momento. ");
            return;
        }
        String idReservaInput = JOptionPane.showInputDialog("Ingrese el ID de la reserva para consultar el costo:");
        if (idReservaInput == null || idReservaInput.trim().isEmpty()) return;

        Reserva reservaEncontrada = null;

        for (Reserva reserva : listaReservas){
            if (reserva.getId().equalsIgnoreCase(idReservaInput.trim())) {
                reservaEncontrada = reserva;
                break;
            }
        }

        if (reservaEncontrada != null){
            double total = reservaEncontrada.calcularCostoTotal();

            String factura = "===== FACTURA DE RESERVA - PIXELRENT =====\n" +
                            "ID Reserva: " + reservaEncontrada.getId() + "\n" +
                            "Cliente: " + reservaEncontrada.getCliente().nombre() + "ID: " + reservaEncontrada.getCliente().id() + "\n" +
                            "Fecha Solicitud: " + reservaEncontrada.getFechaSolicitud() + "\n" +
                            "Turno:" + reservaEncontrada.getTurno() + "\n" +
                            "----------------------------------------------\n" +
                            "TOTAL: $" + total;
            JOptionPane.showMessageDialog(null, factura);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro ninguna reserva con el ID " + idReservaInput);
        }

    }
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
            if (reserva.getId().equalsIgnoreCase(id)) {
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
    // editar - modificar elementos
    public static void actualizarOperador() {
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
        if (idReservaAct == null || idReservaAct.trim().isEmpty())return;
        Reserva reservaEncontrada = null;

        for (Reserva reserva : listaReservas){
            if (reserva.getId().equalsIgnoreCase(idReservaAct.trim())){
                reservaEncontrada = reserva;
                break;
            }
        }
        if (reservaEncontrada!=null){
            String nuevoEstado = JOptionPane.showInputDialog("Ingrese el nuevo estado (solicitada - confirmada - finalizada - cancelada).");
            if (nuevoEstado!=null){
                reservaEncontrada.setEstado(nuevoEstado.trim());
            }
        } else {
        JOptionPane.showMessageDialog(null, "No se encontro la reserva.");
    }
    }


    // ELIMINAR - BORRAR - DELETE - 删除
    public static void eliminarOperador() {
        String idOperadorEliminar = JOptionPane.showInputDialog("Ingrese el id del operador a eliminar");
        String mensaje = "El operador no fue encontrado.";
       for (Operador operador : Operador.listaOperadores){
           if (operador.getDocumento().equals(idOperadorEliminar)){
               Operador.listaOperadores.remove(operador);
               mensaje = "El operador fue eliminado con exito";
               break;
           }
       }
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public static void eliminarReserva(){
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
    public static void eliminarCliente(){
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


//  Modulos
    public static void moduloPrincipal() {
        try {
            int opcion;
            do {
                opcion=Integer.parseInt(JOptionPane.showInputDialog(
                        "Ingrese alguna opcion: " +
                                "\n 1. Acceder a sede"+
                                "\n 2. Acceder como cliente"+
                                "\n 3. Contratar operador"+
                                "\n 0. Salir del programa"));
                switch (opcion){
                    case 1: moduloSede(); break;
                    case 2: moduloCliente(); break;
                    case 3: moduloOperador(); break;
                }
            }while (opcion!=0);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Saliendo del programa...");
        }
    }
    public static void moduloSede(){
        try {
            int opcion;
            do {
                opcion=Integer.parseInt(JOptionPane.showInputDialog(
                        "Accede a alguna opcion: "+
                                "\n 1. Sede Norte"+
                                "\n 2. Sede Sur"+
                                "\n 3. Sede Este"+
                                "\n 4. Sede Oeste"+
                                "\n 0. Volver al menu principal"
                ));
                switch (opcion){
                    case 1: sedeNorte.moduloSede(); break;
                    case 2: sedeSur.moduloSede(); break;
                    case 3: sedeEste.moduloSede(); break;
                    case 4: sedeOeste.moduloSede(); break;
                }
            }while (opcion!=0);
        }catch (Exception e){
            moduloPrincipal();
        }
    }
    public static void moduloCliente(){
        int opcion;
        do {
            opcion= Integer.parseInt(JOptionPane.showInputDialog("Opciones del cliente :"
            +"\n 1. Agregar cliente"+
             "\n 2. Mostrar lista de clientes"
            +"\n 3. Eliminar cliente"
            +"\n 4. Realizar reserva por cliente"
            +"\n 0. Volver al menu principal"));
            switch (opcion){
                case 1:agregarCliente(); break;
                case 2:mostarListaClientes(); break;
                case 3:eliminarCliente(); break;
                case 4:moduloReservas();
            }
        }while (opcion!=0);
    }
    public static void moduloReservas(){
        int opcion;
        do {
            opcion=Integer.parseInt(JOptionPane.showInputDialog(
                    "Opciones de reserva: "+
                    "\n 1. Agregar reserva"+
                    "\n 2. Mostrar lista de reservas o buscar por ID"+
                    "\n 3. Modificar reserva"+
                    "\n 4. Eliminar reserva"+
                    "\n 5. Consultar costo" +
                    "\n 6. Consultar equipos disponibles por fecha y turno" +
                    "\n 7. Consultar reservas CONDIRMADAS de un cliente" +
                    "\n 0. Salir"));
            switch (opcion){
                case 1: agregarReserva(); break;
                case 2: String pregunta=JOptionPane.showInputDialog("Desea buscar por ID (Si o No)");
                if (pregunta.equalsIgnoreCase("si")) {
                    mostrarReservaPorId();
                }else {
                    mostrarReservas();
                } break;
                case 3: actualizarEstadoReserva();break;
                case 4:eliminarReserva(); break;
                case 5:generarFactura();break;
                case 6:consultarEquipoDisponiblesFyT(); break;
                case 7: consultarReservasConfirmadas(); break;
            }
        }while (opcion!=0);
    }
    public static void moduloOperador(){
        int opcion;
        do {
            opcion=Integer.parseInt(JOptionPane.showInputDialog("Opciones de contratacion;" +
                    "\n 1. Contratar operador"+
                    "\n 2. Mostrar operadores"+
                    "\n 3. Actualizar datos de operador"+
                    "\n 4. Remover operador"));
            switch (opcion){
                case 1: contratarOperador();break;
                case 2: mostrarOperador(); break;
                case 3: actualizarOperador(); break;
                case 4: eliminarOperador(); break;
            }
        }while (opcion!=0);
    }

// DISPONIBLE FECHA-TURNO

    public static boolean EquipoDisponibleFecha(EquipoVisual equipoVisual, LocalDate fecha, Turno turno){
    if (equipoVisual == null || !equipoVisual.isDisponibilidad()){
        return false;
    }
    for (Reserva reserva : listaReservas){
        if (reserva.getEstado() == Estado.CONFIRMADA && reserva.getFechaSolicitud().equals(fecha)&&
            reserva.getTurno() == turno){
            for (EquipoVisual equipo : reserva.getListaEquiposAudiovisuales()){
                if (equipo.getId().equalsIgnoreCase(equipoVisual.getId())){
                    return false;
                }
            }
        }
    }
    return true;
    }

    public static void consultarEquipoDisponiblesFyT() {
        LocalDate fecha = null;
        try {
            String inputFecha = JOptionPane.showInputDialog("Ingresar la fecha a consultar (AAAA-MM-DD)");
            if (inputFecha == null || inputFecha.trim().isEmpty()) return;
            fecha = LocalDate.parse(inputFecha.trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato erroneo intente con (AAAA-MM-DD).");
            return;
        }

        Turno turno = null;
        try {
            String inputTurno = JOptionPane.showInputDialog("Ingrese el turno (MAÑANA - TARDE - NOCHE)");
            if (inputTurno == null || inputTurno.trim().isEmpty()) return;
            turno = Turno.valueOf(inputTurno.trim().toUpperCase());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Turno invalido intente con (MAÑANA - TARDE - NOCHE).");
            return;
        }
        String mensaje = "==== EQUIPOS DISPONIBLLES (" + fecha + " - " + turno + ") ====\n\n";
        boolean disponibles = false;
        for (Sede sede : listaSedes) {
            mensaje += "--- " + sede.getNombre() + "----\n";
            for (EquipoVisual equipoVisual : sede.getListaEquipos()){
                if (EquipoDisponibleFecha(equipoVisual, fecha, turno)) {
                    mensaje += "ID: " + equipoVisual.getId() + " Marca: " + equipoVisual.getMarca() + " Modelo: " + equipoVisual.getModelo() + "\n";
                    disponibles = true;
                }
            }
            mensaje+= "\n";
        }
        if (!disponibles){
            JOptionPane.showMessageDialog(null, "No hay equipos disponibles para la fecha " + fecha + " y turno " + turno + ".");
        } else {
            JOptionPane.showMessageDialog(null, mensaje);
        }
    }

    // VERIFICAR DOBLE
    public static boolean existeCliente(String id){
        if (id==null) return false;
        for (Cliente cliente : listaClientes){
            if (cliente.id().trim().equalsIgnoreCase(id.trim())){
                return true;
            }
        }
        return false;
    }
    public static boolean existeOperador(String id){
        if (id==null) return false;
        for (Operador operador : Operador.getListaOperadores() ){
            if (operador.getDocumento().trim().equalsIgnoreCase(id.trim())){
                return true;
            }
        }
        return false;
    }
  }

