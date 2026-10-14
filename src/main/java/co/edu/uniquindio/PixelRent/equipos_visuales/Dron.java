package co.edu.uniquindio.PixelRent.equipos_visuales;

import co.edu.uniquindio.PixelRent.Empresa;
import co.edu.uniquindio.PixelRent.personas.Operador;

import javax.swing.*;

public class Dron extends EquipoVisual{
    private double tarifaBase=0;
    private int minutosVuelo;
    private int autonomiaVuelo;
    private boolean bateriaAdicicional;
    private int hora;
    private Operador operadorActual;
    public Dron(String marca,String modelo, int autonomiaVuelo){
        String tipoObjeto="D-";
        super(marca,modelo,tipoObjeto);
        this.autonomiaVuelo=autonomiaVuelo;
        asignarDronOperador();
    }

    public boolean isBateriaAdicicional() {
        return bateriaAdicicional;
    }

    public void setBateriaAdicicional(boolean bateriaAdicicional) {
        if (bateriaAdicicional){

        }
    }

    public Operador getOperadorActual() {
        return operadorActual;
    }

    public void setOperadorActual(Operador operadorActual) {
        this.operadorActual = operadorActual;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public int getMinutos() {
        return minutosVuelo;
    }

    public void setMinutos(int minutosVuelo) {
        this.minutosVuelo = minutosVuelo;
    }

    public int getMinutosVuelo() {
        return minutosVuelo;
    }

    public void setMinutosVuelo(int minutosVuelo) {
        if (minutosVuelo>autonomiaVuelo)bateriaAdicicional=true;
    }

    public int getAutonomiaVuelo() {
        return autonomiaVuelo;
    }

    public void setAutonomiaVuelo(int autonomiaVuelo) {
        this.autonomiaVuelo = autonomiaVuelo;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }
    public void preguntarMinutosVuelo(){
        int pregunta= Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos minutos va a usar el dron?"));
        if (pregunta>getAutonomiaVuelo()) {
            setBateriaAdicicional(true);
        }else {
            setBateriaAdicicional(false);
        }
    }
    @Override
    public String toString(){
        String mensaje=super.toString();
        mensaje+="Autonomia de vuelo: "+getAutonomiaVuelo()+
        "\n Operador actual: "+operadorActual.getNombre();
        return mensaje;
    }
    public void asignarDronOperador(){
        String idOperador=JOptionPane.showInputDialog("Ingrese el ID del operador que quiere asignar: ");
            for (Operador operador:Operador.listaOperadores){
                if (operador.getDocumento().equals(idOperador)) {this.operadorActual=operador;
                return;}
            }
            JOptionPane.showMessageDialog(null, "No se encontro el operador");
            asignarDronOperador();
    }
}
