package co.edu.uniquindio.PixelRent.equipos_visuales;

import javax.swing.*;

public class KitIluminacion extends EquipoVisual{
    private int luces;
    private boolean controlInalambrico;
    private double tarifaBase;
    public KitIluminacion(String marca,String modelo, int luces){
        String tipoObjeto="K-";
        super(marca,modelo,tipoObjeto);
        this.luces=luces;
        setControlInalambrico(false);
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public int getLuces() {
        return luces;
    }

    public void setLuces(int luces) {
        this.luces = luces;
    }

    public boolean isControlInalambrico() {
        return controlInalambrico;
    }

    public void setControlInalambrico(boolean controlInalambrico) {
        if (controlInalambrico){
            this.controlInalambrico = controlInalambrico;
            tarifaBase=12000;
        }else {
            this.controlInalambrico=controlInalambrico;
            tarifaBase=10000;
        }
    }
    public void preguntarControlInalambrico(){
        String pregunta= JOptionPane.showInputDialog("¿Nesecita control inalambrico?");
        if (pregunta.equalsIgnoreCase("si")) {
            setControlInalambrico(true);
        }else {
            setControlInalambrico(false);
        }
    }
    @Override
    public String toString(){
        String mensaje=super.toString();
        mensaje+=" Cantidad luces: "+getLuces()+
            "\n Control inalambrico: "+isControlInalambrico()+
            "\n Tarifa Base: "+getTarifaBase()+"\n";
        return mensaje;
    }
}
