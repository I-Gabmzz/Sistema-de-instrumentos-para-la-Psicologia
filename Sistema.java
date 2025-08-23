import javax.swing.*;
import java.awt.*;

public class Sistema {

    public static void main(String[] args) {
        mostrarMenuConsultas();
    }

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
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 70, 100, 0)); // 70px margen izquierdo

        JButton botonAgregar = new JButton("Agregar Instrumento");
        JButton botonEliminar = new JButton("Eliminar Instrumento");
        JButton botonConsultar = new JButton("Consultar Instrumento");
        JButton botonCreditos = new JButton("Créditos");
        JButton botonSalir = new JButton("Salir");

        Font fuenteBotones = new Font("Noto Sans", Font.BOLD, 36); // 36px enorme
        Color colorBoton = new Color(255, 255, 255);

        for (JButton boton : new JButton[]{botonAgregar, botonEliminar, botonConsultar, botonCreditos, botonSalir}) {
            boton.setFont(fuenteBotones);
            boton.setBackground(colorBoton);
            boton.setFocusPainted(false);
            boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            boton.setPreferredSize(new Dimension(450, 90)); // Más grandes para la fuente de 36px
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
        });

        botonEliminar.addActionListener(e -> {
        });

        botonConsultar.addActionListener(e -> {
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

    public static void mostrarMenuConsultas() {
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

        JTextArea areaTexto = new JTextArea();
        areaTexto.setFont(new Font("Noto Sans", Font.PLAIN, 14));
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setBackground(new Color(255, 255, 255, 220));
        areaTexto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        areaTexto.setCursor(Cursor.getDefaultCursor());

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

        });
        botonPorTipo.addActionListener(e -> {

        });
        botonPorUtilidad.addActionListener(e -> {

        });
        botonPorCondicion.addActionListener(e -> {

        });
        botonPorEvaluacion.addActionListener(e -> {

        });
        botonOrdenadosClave.addActionListener(e -> {

        });
        botonOrdenadosAutor.addActionListener(e -> {

        });

        botonRegresar.addActionListener(e -> {
            ventana.dispose();
            menuPrincipal();
        });

        ventana.add(panelPrincipal);
        ventana.setVisible(true);
    }

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

