package modelo.pojos;

/**
    Clase utilizada para guardar la relacion que tienen las experiencias educativas
    con los salones en donde se pueden impartir las experiencias educativas
    @author Daniel Escamilla Cortés
*/
public class Relacion {
    private Integer idEE;
    private Integer idSalon;

    public Relacion(Integer idEE, Integer idSalon) {
        this.idEE = idEE;
        this.idSalon = idSalon;
    }

    public Relacion() {
    }

    public Integer getIdEE() {
        return idEE;
    }

    public void setIdEE(Integer idEE) {
        this.idEE = idEE;
    }

    public Integer getIdSalon() {
        return idSalon;
    }

    public void setIdSalon(Integer idSalon) {
        this.idSalon = idSalon;
    }
    
    
}
