// Se declaran las librerias necesarias para esta clase.
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Se anuncia la creación de la clase.
public class Sistema {

    // Se declaran los atributos pertenecientes a la clase.
    private static Control control; // Control de todos los instrumentos.
    static JTextArea areaTexto = new JTextArea(); // Jtext del area de consulta.

    // Se crea el constructor de la clase sistema.
    public Sistema(){
        control = new Control();
        menuPrincipal();
    }

    public static void main(String[] args) {
       new Sistema();
    }

    // Metodo que muestra y controla totalmente la interfaz principal (menu de agregar, consultar, salir, eliminar y creditos).
    // Toda esta interfaz esta personalizada y se basa en el uso de java swing y java awt.
    public static void menuPrincipal() {
        ImageIcon imagenIcono = new ImageIcon("Recursos/menuPrincipal.png");

        JFrame ventana = new JFrame("Sistema de Instrumentos Psicológicos");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(imagenIcono.getIconWidth(), imagenIcono.getIconHeight());
        ventana.setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagenIcono.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelPrincipal.setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 20, 20));
        panelBotones.setOpaque(false);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 70, 100, 0));

        JButton botonAgregar = new JButton("Agregar Instrumento");
        JButton botonEliminar = new JButton("Eliminar Instrumento");
        JButton botonConsultar = new JButton("Consultar Instrumento");
        JButton botonCreditos = new JButton("Créditos");
        JButton botonSalir = new JButton("Salir");

        Font fuenteBotones = new Font("Noto Sans", Font.BOLD, 36);
        Color colorBoton = new Color(255, 255, 255);

        for (JButton boton : new JButton[]{botonAgregar, botonEliminar, botonConsultar, botonCreditos, botonSalir}) {
            boton.setFont(fuenteBotones);
            boton.setBackground(colorBoton);
            boton.setFocusPainted(false);
            boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            boton.setPreferredSize(new Dimension(450, 90));
            boton.setOpaque(true);
            boton.setContentAreaFilled(true);
            boton.setBorderPainted(true);
        }

        panelBotones.add(botonAgregar);
        panelBotones.add(botonEliminar);
        panelBotones.add(botonConsultar);
        panelBotones.add(botonCreditos);
        panelBotones.add(botonSalir);

        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSur.setOpaque(false);
        panelSur.add(panelBotones);

        panelPrincipal.add(panelSur, BorderLayout.SOUTH);

        botonAgregar.addActionListener(e -> {
            control.darDeAltaUnInstrumento(llenarInstrumento());
        });

        botonEliminar.addActionListener(e -> {
            eliminarInstrumento();
        });

        botonConsultar.addActionListener(e -> {
            mostrarMenuConsultas();
            ventana.dispose();
        });

        botonCreditos.addActionListener(e -> {
            mostrarCreditos();
        });

        botonSalir.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(
                    ventana,
                    "¿Estás seguro que quieres salir del sistema?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        ventana.add(panelPrincipal);
        ventana.setVisible(true);
    }

    // Metodo que permite recabar la información del instrumento para así lograr añadirlo al sistema.
    public static Instrumento llenarInstrumento() {
        try {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del instrumento:");
            if (nombre == null) return null;

            String claveStr = JOptionPane.showInputDialog("Ingrese la clave del instrumento:");
            if (claveStr == null) return null;
            int clave = Integer.parseInt(claveStr);

            Object[] opcionUtilidad = {"Manejar", "Identificar"};
            int utilidadInt = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione la utilidad", "Utilidad",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionUtilidad,
                    opcionUtilidad[0]
            );
            if (utilidadInt == JOptionPane.CLOSED_OPTION) return null;
            String utilidad = (String) opcionUtilidad[utilidadInt];

            Object[] opcionTipo = {"Test", "Cuestionario", "Escala"};
            Object tipoObj = JOptionPane.showInputDialog(
                    null,
                    "Seleccione el tipo", "Tipo",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionTipo,
                    opcionTipo[0]
            );
            if (tipoObj == null) return null;
            String tipo = tipoObj.toString();
            Object[] opcionCondicion = {"Ansiedad", "Estrés", "Ambos"};
            int condicionInt = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione la condición", "Condición",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionCondicion,
                    opcionCondicion[0]
            );
            if (condicionInt == JOptionPane.CLOSED_OPTION) return null;
            String condicion = (String) opcionCondicion[condicionInt];

            String numAutoresStr = JOptionPane.showInputDialog("Ingrese el número de autores:");
            if (numAutoresStr == null) return null;
            int numAutores = Integer.parseInt(numAutoresStr);

            ArrayList<String> autores = new ArrayList<>();
            for (int i = 0; i < numAutores; i++) {
                String autor = JOptionPane.showInputDialog("Ingrese el nombre del autor " + (i + 1) + ":");
                if (autor == null) return null;
                autores.add(autor);
            }

            int evaluadoInt = JOptionPane.showConfirmDialog(
                    null,
                    "¿El instrumento está evaluado y es confiable?",
                    "Evaluado",
                    JOptionPane.YES_NO_OPTION
            );
            if (evaluadoInt == JOptionPane.CLOSED_OPTION) return null;
            boolean evaluado = (evaluadoInt == JOptionPane.YES_OPTION);

            String lugarEvaluacion = JOptionPane.showInputDialog("Ingrese el lugar de evaluación:");
            if (lugarEvaluacion == null) return null;

            return new Instrumento(
                    nombre,
                    utilidad,
                    tipo,
                    condicion,
                    autores,
                    evaluado,
                    lugarEvaluacion,
                    clave
            );

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en formato numérico: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el instrumento: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Metodo para eliminar un instrumento con base en su clave.
    public static void eliminarInstrumento() {
        String claveStr = JOptionPane.showInputDialog("Ingrese la clave del instrumento a eliminar:");
        if (claveStr == null) return;

        int clave = Integer.parseInt(claveStr);

        Instrumento instrumentoAEliminar = null;
        for (Instrumento instrumento : control.getInstrumentos()) {
            if (instrumento.getClave() == clave) {
                instrumentoAEliminar = instrumento;
                break;
            }
        }

        if (instrumentoAEliminar != null) {
            control.eliminarInstrumento(instrumentoAEliminar);
            JOptionPane.showMessageDialog(null, "Instrumento eliminado: " + instrumentoAEliminar.getNombre());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró instrumento con clave: " + clave);
        }
    }

    // Metodo que permite mostrar la segunda interfaz de consulta, en donde se permite realizar
    // diversas consultas en base a algun atributo.
    public static void mostrarMenuConsultas() {
        if (areaTexto != null) {
            areaTexto.setText("");
        }
        ImageIcon imagenIcono = new ImageIcon("Recursos/menuDeConsultas.png");

        JFrame ventana = new JFrame("Sistema de Consultas");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(imagenIcono.getIconWidth(), imagenIcono.getIconHeight());
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagenIcono.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelPrincipal.setLayout(new BorderLayout());

        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setOpaque(false);
        panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(325, 30, 0, 0));

        JPanel panelBotones = new JPanel(new GridLayout(8, 1, 10, 25));
        panelBotones.setOpaque(false);

        JButton botonPorAutor = new JButton("Por Autor");
        JButton botonPorTipo = new JButton("Por Tipo");
        JButton botonPorUtilidad = new JButton("Por Utilidad");
        JButton botonPorCondicion = new JButton("Por Condición");
        JButton botonPorEvaluacion = new JButton("Por Evaluación");
        JButton botonOrdenadosClave = new JButton("Ordenados por Clave");
        JButton botonOrdenadosAutor = new JButton("Ordenados por Primer Autor");
        JButton botonRegresar = new JButton("Regresar");

        Font fuenteBotones = new Font("Noto Sans", Font.BOLD, 36);
        Color colorBoton = new Color(255, 255, 255);

        for (JButton boton : new JButton[]{botonPorAutor, botonPorTipo, botonPorUtilidad, botonPorCondicion,
                botonPorEvaluacion, botonOrdenadosClave, botonOrdenadosAutor, botonRegresar}) {
            boton.setFont(fuenteBotones);
            boton.setBackground(colorBoton);
            boton.setFocusPainted(false);
            boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            boton.setPreferredSize(new Dimension(320, 55));
            boton.setOpaque(true);
            boton.setContentAreaFilled(true);
            boton.setBorderPainted(true);
            boton.setCursor(Cursor.getDefaultCursor());
        }

        panelBotones.add(botonPorAutor);
        panelBotones.add(botonPorTipo);
        panelBotones.add(botonPorUtilidad);
        panelBotones.add(botonPorCondicion);
        panelBotones.add(botonPorEvaluacion);
        panelBotones.add(botonOrdenadosClave);
        panelBotones.add(botonOrdenadosAutor);
        panelBotones.add(botonRegresar);

        panelIzquierdo.add(panelBotones, BorderLayout.NORTH);

        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setOpaque(false);
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 100));

        areaTexto.setFont(new Font("Noto Sans", Font.PLAIN, 14));
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setBackground(new Color(255, 255, 255));
        areaTexto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));


        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setPreferredSize(new Dimension(350, 500));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        panelDerecho.add(scrollPane, BorderLayout.CENTER);

        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(panelIzquierdo);
        horizontalBox.add(Box.createHorizontalStrut(50));
        horizontalBox.add(panelDerecho);

        panelPrincipal.add(horizontalBox, BorderLayout.CENTER);

        botonPorAutor.addActionListener(e -> {
            consultarPorAutor();
        });

        botonPorTipo.addActionListener(e -> {
            consultarPorTipo();
        });

        botonPorUtilidad.addActionListener(e -> {
            consultarPorUtilidad();
        });

        botonPorCondicion.addActionListener(e -> {
            consultarPorCondicion();
        });

        botonPorEvaluacion.addActionListener(e -> {
            consultarPorEvaluacion();
        });

        botonOrdenadosClave.addActionListener(e -> {
            control.ordenarPorClave();
            areaTexto.setText(control.mostrarTodosLosInstrumentos());
        });

        botonOrdenadosAutor.addActionListener(e -> {
            control.ordenarPorPrimerAutor();
            areaTexto.setText(control.mostrarTodosLosInstrumentos());
        });

        botonRegresar.addActionListener(e -> {
            ventana.dispose();
            menuPrincipal();
        });

        ventana.add(panelPrincipal);
        ventana.setVisible(true);
    }

    // Metodo que permite consultar por autor y mostrarlo en la interfaz.
    public static void consultarPorAutor() {
        String autor = JOptionPane.showInputDialog("Ingrese el nombre del autor a buscar:");
        String resultado = control.consultarPorAutor(autor);

        if (resultado.isEmpty()) {
            areaTexto.setText("No se encontraron instrumentos del autor: " + autor);
        } else {
            areaTexto.setText("Instrumentos del autor " + autor + ":\n\n" + resultado);
        }
    }

    // Metodo que permite consultar por tipo y mostrarlo en la interfaz.
    public static void consultarPorTipo() {
        Object[] opcionTipo = {"Test", "Cuestionario", "Escala"};
        Object tipoObj = JOptionPane.showInputDialog(
                null,
                "Seleccione el tipo a buscar", "Tipo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionTipo,
                opcionTipo[0]
        );

        if (tipoObj != null) {
            String tipo = tipoObj.toString();
            Control control = new Control();
            String resultado = control.consultaPorTipo(tipo);

            if (resultado.isEmpty()) {
                areaTexto.setText("No se encontraron instrumentos del tipo: " + tipo);
            } else {
                areaTexto.setText("Instrumentos tipo: " + tipo + ":\n\n" + resultado);
            }
        }
    }

    // Metodo que permite consultar por utilidad y mostrarlo en la interfaz.
    public static void consultarPorUtilidad() {
        Object[] opcionUtilidad = {"Manejar", "Identificar"};
        int utilidadInt = JOptionPane.showOptionDialog(
                null,
                "Seleccione la utilidad a buscar", "Utilidad",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionUtilidad,
                opcionUtilidad[0]
        );

        if (utilidadInt != JOptionPane.CLOSED_OPTION) {
            String utilidad = (String) opcionUtilidad[utilidadInt];
            Control control = new Control();
            String resultado = control.ConsultaPorUtilidad(utilidad);

            if (resultado.isEmpty()) {
                areaTexto.setText("No se encontraron instrumentos con la utilidad: " + utilidad);
            } else {
                areaTexto.setText("Instrumentos con la utilidad: " + utilidad + ":\n\n" + resultado);
            }
        }
    }

    // Metodo que permite consultar por condición y mostrarlo en la interfaz.
    public static void consultarPorCondicion() {
        Object[] opcionCondicion = {"Ansiedad", "Estrés", "Ambos"};
        int condicionInt = JOptionPane.showOptionDialog(
                null,
                "Seleccione la condición a buscar", "Condición",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionCondicion,
                opcionCondicion[0]
        );

        if (condicionInt != JOptionPane.CLOSED_OPTION) {
            String condicion = (String) opcionCondicion[condicionInt];
            Control control = new Control();
            String resultado = control.consultaPorCondicion(condicion);

            if (resultado.isEmpty()) {
                areaTexto.setText("No se encontraron instrumentos de la condición: " + condicion);
            } else {
                areaTexto.setText("Instrumentos para la condición: " + condicion + ":\n\n" + resultado);
            }
        }
    }

    // Metodo que permite consultar por evaluación y mostrarlo en la interfaz.
    public static void consultarPorEvaluacion() {
        Object[] opciones = {"Sí Evaluados", "No evaluados"};
        int opcion = JOptionPane.showOptionDialog(
                null,
                "¿Buscar instrumentos evaluados o no evaluados?",
                "Tipo de evaluación",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (opcion != JOptionPane.CLOSED_OPTION) {
            boolean buscarEvaluados = (opcion == 0);
            Control control = new Control();
            String resultado = control.constultaPorEvaluacion(buscarEvaluados);

            String tipoBusqueda = buscarEvaluados ? "evaluados" : "no evaluados";

            if (resultado.isEmpty()) {
                areaTexto.setText("No se encontraron instrumentos " + tipoBusqueda);
            } else {
                areaTexto.setText("Instrumentos " + tipoBusqueda + ":\n\n" + resultado);
            }
        }
    }

    // Metodo que permite mostrar una ventana emergente con la información del autor.
    public static void mostrarCreditos() {
        JPanel panelCreditos = new JPanel(new BorderLayout(10, 10));
        panelCreditos.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Créditos", SwingConstants.CENTER);
        titulo.setFont(new Font("Noto Sans", Font.BOLD, 24));
        panelCreditos.add(titulo, BorderLayout.NORTH);

        JTextArea contenido = new JTextArea(
                "Desarrollado por:\n" +
                        "• Angel Gabriel Manjarrez Moreno (1197503)\n\n" +
                        "Versión: 22/08/2025\n" +
                        "© Todos los derechos reservados"
        );
        contenido.setFont(new Font("Noto Sans", Font.PLAIN, 14));
        contenido.setEditable(false);
        contenido.setBackground(new Color(240, 240, 240));
        contenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCreditos.add(new JScrollPane(contenido), BorderLayout.CENTER);

        Font fuenteBotones = new Font("Noto Sans", Font.BOLD, 14);
        Color colorBoton = new Color(200, 220, 255);

        JButton cerrar = new JButton("Cerrar");
        cerrar.addActionListener(e -> ((Window) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose());
        cerrar.setFont(fuenteBotones);
        cerrar.setBackground(colorBoton);
        cerrar.setFocusPainted(false);

        JPanel panelBoton = new JPanel();
        panelBoton.add(cerrar);
        panelCreditos.add(panelBoton, BorderLayout.SOUTH);

        JDialog creditos = new JDialog();
        creditos.setTitle("Créditos");
        creditos.setModal(true);
        creditos.setResizable(true);
        creditos.setContentPane(panelCreditos);
        creditos.pack();
        creditos.setLocationRelativeTo(null);
        creditos.setVisible(true);
    }

}

