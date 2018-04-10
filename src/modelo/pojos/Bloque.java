package modelo.pojos;

/**
    Clase para guardar la relacion del dia correspondiente con base en la hora y dia,
    la experiencia educativa y el salon correspondientes a la hora y dia especificados
    @author Daniel Escamilla Cortés
*/
public class Bloque {
    private Integer idSemana;
    private Integer Dia;
    private Integer Hora;
    private Integer idEE;
    private Integer idSalon;

    public Bloque() {
    }

    public Bloque(Integer Dia, Integer Hora, Integer idEE, Integer idSalon) {
        this.Hora = Hora;
        this.Dia = Dia;
        this.idEE = idEE;
        this.idSalon = idSalon;
    }

    public Integer getIdSemana() {
        return idSemana;
    }

    public void setIdSemana(Integer idSemana) {
        this.idSemana = idSemana;
    }

    public Integer getHora() {
        return Hora;
    }

    public void setHora(Integer Hora) {
        this.Hora = Hora;
    }

    public Integer getDia() {
        return Dia;
    }

    public void setDia(Integer Dia) {
        this.Dia = Dia;
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
