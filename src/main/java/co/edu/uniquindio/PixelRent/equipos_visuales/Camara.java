package co.edu.uniquindio.PixelRent.equipos_visuales;

import javax.swing.*;

public class Camara extends EquipoVisual {
        private String resolucionMaxima;
        private double tarifaBase;
        private boolean estabilizador;
        public Camara(String marca, String modelo,String resolucionMaxima){
            String tipoObjeto="C-";
            super(marca,modelo,tipoObjeto);
            this.resolucionMaxima=resolucionMaxima;
            this.estabilizador = false;
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
            if (estabilizador.equalsIgnoreCase("si")) {
                this.tarifaBase=15000;
                this.estabilizador = true;
            }else {
                this.tarifaBase=12000;
                this.estabilizador = false;
            }
    }
    public void preguntarEstabizador(){
            String pregunta= JOptionPane.showInputDialog("¿Tiene estabilizador?");
            setEstabilizador(pregunta);
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
