// Se declaran las librerias necesarias para esta clase.
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;

// Se anuncia la creación de la clase.
public class Control {
    // Se declaran los atributos pertenecientes a la clase.
    private ArrayList<Instrumento> instrumentos; // Conjunto de instrumentos que existen en el sistema.

    // Se declara el metodo constructor del control.
    public Control() {
        instrumentos = new ArrayList<>();
        recuperarArchivo();
    }

    // Getter para obtener los instrumentos que existen.
    public ArrayList<Instrumento> getInstrumentos() {
        return instrumentos;
    }

    // Metodos generales para el control de instrumentos.
    // Metodo para agregar al control un instrumento.
    public void darDeAltaUnInstrumento(Instrumento instrumento) {
        if (instrumento != null) {
            instrumentos.add(instrumento);
            actualizarArchivo();
        }
    }

    // Metodo para eliminar un instrumento.
    public Instrumento eliminarInstrumento(Instrumento instrumento) {
        instrumentos.remove(instrumento);
        actualizarArchivo();
        return instrumento;
    }

    // Metodo para consultar instrumentos de acuerdo a su autor.
    public String consultarPorAutor(String autorBuscado) {
        String resultadoDeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            ArrayList<String> autores = instrumento.getAutores();
            for (String autor : autores) {
                if (autor.equalsIgnoreCase(autorBuscado)) {
                    resultadoDeConsulta += darFormatoAInstrumento(instrumento);
                    break;
                }
            }
        }
        return resultadoDeConsulta;
    }

    // Metodo para consultar instrumentos de acuerdo a su utilidad.
    public String ConsultaPorUtilidad(String utilidad) {
        String resultadoDeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            if (instrumento.getUtilidad().equalsIgnoreCase(utilidad)) {
                resultadoDeConsulta += darFormatoAInstrumento(instrumento);
            }
        }
        return resultadoDeConsulta;
    }

    // Metodo para consultar instrumentos de acuerdo a su tipo.
    public String consultaPorTipo(String tipo) {
        String resultadoDeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            if (instrumento.getTipo().equalsIgnoreCase(tipo)) {
                resultadoDeConsulta += darFormatoAInstrumento(instrumento);
            }
        }
        return resultadoDeConsulta;
    }

    // Metodo para consultar instrumentos de acuerdo a la condicion de enfoque asociada.
    public String consultaPorCondicion(String condicion) {
        String resultadoDeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            if (instrumento.getCondicion().equalsIgnoreCase(condicion)) {
                resultadoDeConsulta += darFormatoAInstrumento(instrumento);
            }
        }
        return resultadoDeConsulta;
    }

    // Metodo para consultar los instrumentos que esten evaluados y sean confiables.
    public String constultaPorEvaluacion(Boolean evaluacion) {
        String resultadoDeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            if(instrumento.estaEvaluado() == evaluacion){
                resultadoDeConsulta += darFormatoAInstrumento(instrumento);
            }
        }
        return resultadoDeConsulta;
    }

    // Metodo para consultar instrumentos de acuerdo a su clave.
    public String consultaPorClave(int clave) {
        String resultadoDeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            if(instrumento.getClave() == clave){
                resultadoDeConsulta += darFormatoAInstrumento(instrumento);
            }
        }
        return resultadoDeConsulta;
    }

    // Muestra todos los instrumentos del sistema.
    public String mostrarTodosLosInstrumentos() {
        String resultadoDeConsulta = "";
        for (Instrumento instrumento : instrumentos) {
            resultadoDeConsulta += darFormatoAInstrumento(instrumento);
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

    // Metodo para sobreescribir el archivo que contiene a los instrumentos.
    public void actualizarArchivo() {
        try {
            FileWriter escritor = new FileWriter("instrumentos.txt");
            for (Instrumento instrumento : instrumentos) {
                String autoresString = "";
                for (String autor : instrumento.getAutores()) {
                    autoresString += autor + ";";
                }

                escritor.write(instrumento.getNombre() + "," +
                        instrumento.getClave() + "," +
                        instrumento.getUtilidad() + "," +
                        instrumento.getTipo() + "," +
                        instrumento.getCondicion() + "," +
                        autoresString + "," +
                        instrumento.estaEvaluado() + "," +
                        instrumento.getLugarDeEvalacion() + "\n");
            }
            escritor.close();
        } catch (IOException error) {
            System.out.println("Error al actualizar el archivo: " + error.getMessage());
        }
    }

    // Metodo para recuperar el archivo que contiene la información de los instrumentos.
    public void recuperarArchivo() {
        try {
            instrumentos.clear();
            BufferedReader lector = new BufferedReader(new FileReader("instrumentos.txt"));
            String linea;
            while ((linea = lector.readLine()) != null) {
                Instrumento instrumento = getInstrumentoDelArchivo(linea);
                instrumentos.add(instrumento);
                }
            lector.close();
        } catch (IOException error) {
            System.out.println("Error al leer archivo" + error.getMessage());
        }
    }

    // Metodo auxiliar para obtener directamente el instrumento del archivo.
    public Instrumento getInstrumentoDelArchivo(String linea) {
        String[] partesDelInstrumento = linea.split(",");
        return new Instrumento(
                partesDelInstrumento[0],
                partesDelInstrumento[2],
                partesDelInstrumento[3],
                partesDelInstrumento[4],
                new ArrayList<>(Arrays.asList(partesDelInstrumento[5].split(";"))),
                Boolean.parseBoolean(partesDelInstrumento[6]),
                partesDelInstrumento[7],
                Integer.parseInt(partesDelInstrumento[1])
        );
    }

    // Metodo para obtener la forma de impresion del instrumento.
    private String darFormatoAInstrumento(Instrumento instrumento) {
        return  "+----------------------------------------+\n" +
                "Nombre: " + instrumento.getNombre() + "\n" +
                "Clave: " + instrumento.getClave() + "\n" +
                "Utilidad: " + instrumento.getUtilidad() + "\n" +
                "Tipo: " + instrumento.getTipo() + "\n" +
                "Condición: " + instrumento.getCondicion() + "\n" +
                "Autores: " + String.join(", ", instrumento.getAutores()) + "\n" +
                "Evaluado: " + (instrumento.estaEvaluado() ? "Sí" : "No") + "\n" +
                "Lugar de evaluación: " + instrumento.getLugarDeEvalacion() + "\n" +
                "+----------------------------------------+\n\n";
    }

}
