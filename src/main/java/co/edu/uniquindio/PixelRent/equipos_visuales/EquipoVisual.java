package co.edu.uniquindio.PixelRent.equipos_visuales;

public abstract class EquipoVisual {
    protected final String id;
    protected String marca;
    protected String modelo;
    protected boolean disponibilidad;
    protected static int cantidadEquipos=0;
    protected int horaReserva;
    protected EstadoEquipo estadoEquipo;

    public EquipoVisual(String marca, String modelo,String tipoObjeto, int horaReserva) {
        id=tipoObjeto+(++cantidadEquipos);
        this.marca = marca;
        this.modelo = modelo;
        this.disponibilidad = true;
        this.horaReserva = horaReserva;
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
    public void setEstadoEquipo(String estado) {

        switch (estado.toLowerCase()) {

         case "ocupado":
             this.estadoEquipo = EstadoEquipo.OCUPADO;
             disponibilidad = false;
             break;
         case "en mantenimiento":
             this.estadoEquipo=EstadoEquipo.EN_MANTENIMIENTO;
             disponibilidad = false;
             break;
         case "fuera de servicio":
             this.estadoEquipo=EstadoEquipo.FUERA_DE_SERVICIO;
             disponibilidad = false;
             break;

         case "cancelar":
                break;
        }
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
