// Se declaran las librerias necesarias para esta clase.
import java.util.ArrayList;

// Se anuncia la creación de la clase.
public class Instrumento {

    // Se declaran los atributos pertenecientes a la clase.
    private String nombre; // Nombre del instrumento
    private String utilidad; // Que utilidad tiene, identificar o manejar.
    private String tipo; // Que tipo de instrumento es, escala, test o cuestionario.
    private String condicion; // Para que condicion esta dirigido, estres, ansiedad o ambos.
    private String autorPrincipal; // Primer autor.
    private ArrayList<String> autoresSecundarios;  // Otros autores.
    private boolean evaluado; // Si el instrumento esta validado y es confiable.
    private String lugarDeEvalacion; // Lugar donde se realizo la evaluacion.
    private int clave; // Clave del instrumento.


}
