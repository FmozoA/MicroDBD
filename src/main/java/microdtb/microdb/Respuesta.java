package microdtb.microdb;


public class Respuesta {

private String resultado;
private String error;
private String nombrePersona;
public Double numero;
public String getResultado() {
    return resultado;
}
public void setResultado(String resultado) {
    this.resultado = resultado;
}
public String getError() {
    return error;
}
public void setError(String error) {
    this.error = error;
}
public String getNombrePersona() {
    return nombrePersona;
}
public void setNombrePersona(String nombrePersona) {
    this.nombrePersona = nombrePersona;
}
public Double getNumero() {
    return numero;
}
public void setNumero(Double numero) {
    this.numero = numero;
}



}
