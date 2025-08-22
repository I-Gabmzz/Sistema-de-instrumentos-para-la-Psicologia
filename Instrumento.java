// Se declaran las librerias necesarias para esta clase.
import java.util.ArrayList;

// Se anuncia la creación de la clase.
public class Instrumento {

    // Se declaran los atributos pertenecientes a la clase.
    private String nombre; // Nombre del instrumento
    private String utilidad; // Que utilidad tiene, identificar o manejar.
    private String tipo; // Que tipo de instrumento es, escala, test o cuestionario.
    private String condicion; // Para que condicion esta dirigido, estres, ansiedad o ambos.
    private ArrayList<String> autores;  // Autores del instrumento.
    private boolean evaluado; // Si el instrumento esta validado y es confiable.
    private String lugarDeEvalacion; // Lugar donde se realizo la evaluacion.
    private int clave; // Clave del instrumento.

    // Se declara el metodo constructor del instrumento.
    public Instrumento(String nombre, String utilidad, String tipo, String condicion,
                       ArrayList<String> autores, boolean evaluado, String lugarDeEvalacion,
                       int clave) {
        this.nombre = nombre;
        this.utilidad = utilidad;
        this.condicion = condicion;
        this.tipo = tipo;
        this.autores = autores;
        this.evaluado = evaluado;
        this.lugarDeEvalacion = lugarDeEvalacion;
        this.clave = clave;
    }

    // Se crean todos los respetictivos getters de la clase.
    public String getNombre() {
        return nombre;
    }
    public String getUtilidad() {
        return utilidad;
    }
    public String getTipo() {
        return tipo;
    }
    public String getCondicion() {
        return condicion;
    }
    public ArrayList<String> getAutores() {
        return autores;
    }
    public boolean estaEvaluado() {
        return evaluado;
    }
    public String getLugarDeEvalacion() {
        return lugarDeEvalacion;
    }
    public int getClave() {
        return clave;
    }
    public String getPrimerAutor() {
        return autores.getFirst();
    }

    // Se crean todos los respetictivos setters de la clase.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setUtilidad(String utilidad) {
        this.utilidad = utilidad;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
    public void setAutores(ArrayList<String> autores) {
        this.autores = autores;
    }
    public void setEvaluado(boolean evaluado) {
        this.evaluado = evaluado;
    }
    public void setLugarDeEvalacion(String lugarDeEvalacion) {
        this.lugarDeEvalacion = lugarDeEvalacion;
    }
    public void setClave(int clave) {
        this.clave = clave;
    }
}
