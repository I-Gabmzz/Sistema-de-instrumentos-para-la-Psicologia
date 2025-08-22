// Se declaran las librerias necesarias para esta clase.
import java.util.ArrayList;
import java.util.Objects;

// Se anuncia la creación de la clase.
public class Control {
    // Se declaran los atributos pertenecientes a la clase.
    private ArrayList<Instrumento> instrumentos; // Conjunto de instrumentos que existen en el sistema.

    // Se declara el metodo constructor del control.
    public Control() {
        instrumentos = new ArrayList<>();
    }

    // Getter para obtener los instrumentos que existen.
    public ArrayList<Instrumento> getInstrumentos() {
        return instrumentos;
    }

    // Getter para obtener cierto instrumento dependiendo la posicion.
    public Instrumento getInstrumento(int posicion) {
        return instrumentos.get(posicion);
    }

    // Metodos generales para el control de instrumentos.
    // Metodo para eliminar un instrumento.
    public Instrumento eliminarInstrumento(Instrumento instrumento) {
        instrumentos.remove(instrumento);
        return instrumento;
    }

    // Metodo para consultar instrumentos de acuerdo a su autor.
    public String consultarPorAutor(String autor) {
        String resultadoDeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            ArrayList<String> consultaPorAutores = instrumento.getAutores();
            if (consultaPorAutores.contains(autor)) {
                resultadoDeConsulta += instrumento.getNombre() + "\n";
            }
        }
        return resultadoDeConsulta;
    }

    // Metodo para consultar instrumentos de acuerdo a su utilidad.
    public String ConsultaPorUtilidad(String utilidad) {
        String resultadoDeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            if (instrumento.getUtilidad().equalsIgnoreCase(utilidad)) {
                resultadoDeConsulta += instrumento.getNombre() + "\n";
            }
        }
        return resultadoDeConsulta;
    }

    // Metodo para consultar instrumentos de acuerdo a su tipo.
    public String consultaPorTipo(String tipo) {
        String resultadoDeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            if (instrumento.getTipo().equalsIgnoreCase(tipo)) {
                resultadoDeConsulta += instrumento.getNombre() + "\n";
            }
        }
        return resultadoDeConsulta;
    }

    // Metodo para consultar instrumentos de acuerdo a la condicion de enfoque asociada.
    public String consultaPorCondicion(String condicion) {
        String resultadoDeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            if (instrumento.getCondicion().equalsIgnoreCase(condicion)) {
                resultadoDeConsulta += instrumento.getNombre() + "\n";
            }
        }
        return resultadoDeConsulta;
    }

    // Metodo para consultar los instrumentos que esten evaluados y sean confiables.
    public String constultaPorEvaluacion(Boolean evaluacion) {
        String resultadoDeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            if(instrumento.estaEvaluado() == evaluacion){
                resultadoDeConsulta += instrumento.getNombre() + "\n";
            }
        }
        return resultadoDeConsulta;
    }

    // Metodo para consultar instrumentos de acuerdo a su clave.
    public String consultaPorClave(int clave) {
        String resultadoDeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            if(instrumento.getClave() == clave){
                resultadoDeConsulta += instrumento.getNombre() + "\n";
            }
        }
        return resultadoDeConsulta;
    }

    // Muestra todos los instrumentos del sistema.
    public String mostrarTodosLosInstrumentos() {
        String resultadoDeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            resultadoDeConsulta += instrumento.getNombre() + "\n";
        }
        return resultadoDeConsulta;
    }

    // Mediante este metodo se ordenan los instrumentos por clave.
    public void ordenarPorClave() {
        instrumentos.sort((a1, a2)
                -> a1.getClave() - a2.getClave());
    }

    // Metodo para ordenar los instrumentos por su primer autor.
    public void ordenarPorPrimerAutor() {
        instrumentos.sort((a1, a2)
                -> a1.getPrimerAutor().compareToIgnoreCase(a2.getPrimerAutor()));
    }

}
