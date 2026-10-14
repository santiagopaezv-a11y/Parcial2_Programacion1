package co.edu.uniquindio.PixelRent.equipos_visuales;

public class KitIluminacion extends EquipoVisual{
    private int luces;
    private boolean controlInalambrico;
    private double tarifaBase;
    public KitIluminacion(String marca,String modelo, int luces, boolean controlInalambrico){
        String tipoObjeto="K-";
        super(marca,modelo,tipoObjeto);
        this.luces=luces;
        setControlInalambrico(controlInalambrico);
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
}
