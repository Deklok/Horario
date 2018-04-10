
package modelo.pojos;

/**
    Clase ocupada para guardar las experiencias educativas durante la ejecucion del programa
    @author Daniel Escamilla Cortés
*/
public class EE {
    private Integer idEE;
    private String nombre;
    private String maestro;

    public EE() {
    }

    public EE(Integer idEE, String nombre, String maestro) {
        this.idEE = idEE;
        this.nombre = nombre;
        this.maestro = maestro;
    }

    public Integer getIdEE() {
        return idEE;
    }

    public void setIdEE(Integer idEE) {
        this.idEE = idEE;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMaestro() {
        return maestro;
    }

    public void setMaestro(String maestro) {
        this.maestro = maestro;
    }
    
}
