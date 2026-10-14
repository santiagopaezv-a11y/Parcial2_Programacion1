package co.edu.uniquindio.PixelRent.personas;

public record Cliente(String id, String nombre, String telefono, String correo){
    @Override
    public String id() {
        return id;
    }

    @Override
    public String nombre() {
        return nombre;
    }

    @Override
    public String telefono() {
        return telefono;
    }

    @Override
    public String correo() {
        return correo;
    }
}
