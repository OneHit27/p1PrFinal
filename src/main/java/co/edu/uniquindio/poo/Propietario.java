package co.edu.uniquindio.poo;

public class Propietario {
    //Atributos clase propietario.
    private final String nombre;
    private final String documentoIdentidad;
    private final String telefono;

    /**
     * Metodo constructor de la clase Propietario.
     * @param nombre nombre del propietario.
     * @param documentoIdentidad documento que identifica al propietario.
     * @param telefono telefono del propietario.
     */    
    public Propietario(String nombre, String documentoIdentidad, String telefono){
        this.nombre = nombre;
        this.documentoIdentidad = documentoIdentidad;
        this.telefono = telefono;
        assert nombre != null;
        assert documentoIdentidad != null;
        assert telefono != null;
    }

    /**
     * Metodo para obtener el nombre del propietario.
     * @return nombre del propietario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para obtener el documento de identidad  del propietario.
     * @return documento de identidad del propietario.
     */
    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    /**
     * Metodo para obtener el numero de telefono  del propietario.
     * @return telefono del propietario
     */
    public String getTelefono() {
        return telefono;
    }
  
}