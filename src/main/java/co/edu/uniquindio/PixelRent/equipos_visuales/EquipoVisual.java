package co.edu.uniquindio.PixelRent.equipos_visuales;

public abstract class EquipoVisual {
    protected final String id;
    protected String marca;
    protected String modelo;
    protected boolean disponibilidad;
    protected static int cantidadEquipos=0;
    protected int horaReserva=0;

    public EquipoVisual(String marca, String modelo,String tipoObjeto) {
        id=tipoObjeto+(++cantidadEquipos);
        this.marca = marca;
        this.modelo = modelo;
        this.disponibilidad = true;
    }

    public String getId() {
        return id;
    }

    public int getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(int horaReserva) {
        this.horaReserva = horaReserva;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public static int getCantidadEquipos() {
        return cantidadEquipos;
    }

    public static void setCantidadEquipos(int cantidadEquipos) {
        EquipoVisual.cantidadEquipos = cantidadEquipos;
    }
    public double calcularTotal(double tarifaBase, double cantidadHorasAlquilado){
        return tarifaBase*cantidadHorasAlquilado;
    }

    @Override
    public String toString() {
        String mensaje="ID "+getId()+
                "\n Marca: "+getMarca()+
                "\n Modelo: "+getModelo()+
                "\n Disponibilidad: "+isDisponibilidad()+"\n";
        return mensaje;
    }
}
