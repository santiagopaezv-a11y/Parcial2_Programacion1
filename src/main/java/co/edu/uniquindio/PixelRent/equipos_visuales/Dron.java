package co.edu.uniquindio.PixelRent.equipos_visuales;

public class Dron extends EquipoVisual{
    private double tarifaBase=0;
    private int minutosVuelo;
    private int autonomiaVuelo;
    private int hora;
    public Dron(String marca,String modelo, int autonomiaVuelo){
        String tipoObjeto="D-";
        super(marca,modelo,tipoObjeto);
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
        this.minutosVuelo = minutosVuelo;
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
}
