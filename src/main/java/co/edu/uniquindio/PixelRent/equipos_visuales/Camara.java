package co.edu.uniquindio.PixelRent.equipos_visuales;
public class Camara extends EquipoVisual {
        private String resolucionMaxima;
        private double tarifaBase=0;
        private boolean estabilizador;
        public Camara(String marca, String modelo,String resolucionMaxima, boolean estabilizador){
            String tipoObjeto="C-";
            super(marca,modelo,tipoObjeto);
            setEstabilizador(estabilizador);
            this.resolucionMaxima=resolucionMaxima;
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

    public void setEstabilizador(boolean estabilizador) {
            if (estabilizador) {
                this.tarifaBase=15000;
                this.estabilizador=estabilizador;
            }else {
                this.tarifaBase=12000;
                this.estabilizador = estabilizador;
            }

    }
}
