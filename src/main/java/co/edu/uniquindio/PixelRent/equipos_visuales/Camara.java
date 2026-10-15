package co.edu.uniquindio.PixelRent.equipos_visuales;

import javax.swing.*;

public class Camara extends EquipoVisual {
        private String resolucionMaxima;
        private double tarifaBase = 12000;
        private boolean estabilizador;
        public Camara(String marca, String modelo,String resolucionMaxima){
            String tipoObjeto="C-";
            super(marca,modelo,tipoObjeto);
            this.resolucionMaxima=resolucionMaxima;
            this.estabilizador = false;
            this.tarifaBase = 12000;
            preguntarEstabizador();
        }

    public String getResolucionMaxima() {
        return resolucionMaxima;
    }

    public void setResolucionMaxima(String resolucionMaxima) {
        this.resolucionMaxima = resolucionMaxima;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public boolean isEstabilizador() {
        return estabilizador;
    }

    public void setEstabilizador(String estabilizador) {

            if (estabilizador != null && estabilizador.equalsIgnoreCase("si")){
            this.estabilizador = true;
            } else {
                this.estabilizador = false;
            }
    }
    public void preguntarEstabizador(){
            String pregunta= JOptionPane.showInputDialog("¿Tiene estabilizador?");
            setEstabilizador(pregunta);
    }
    @Override
    public double calcularPrecio(){
            double total = this.tarifaBase * getHoraReserva();
            if (this.estabilizador){
                total+= 15000;
            }
            return total;
    }

    @Override
    public String toString(){
            String mensaje=super.toString();
            mensaje+= "Resolucion maxima: "+getResolucionMaxima()+
            "\n Tiene estabilizador: "+isEstabilizador()+
            "\n Tarifa base: "+ getTarifaBase();
            return mensaje;
    }
}
