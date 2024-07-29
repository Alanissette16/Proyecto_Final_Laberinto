package Vista;
import java.awt.*;
import java.awt.event.*;
//import java.util.concurrent.TimeUnit;
import javax.swing.*;

import Modelo.Celda;

import java.util.List;



public class LaberintoGui extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtAltura;
    private JTextField txtAnchura;
    private JTextField txtInicioX;
    private JTextField txtInicioY;
    private JTextField txtFinX;
    private JTextField txtFinY;
    private JTextArea txtResultado;
    private JTextField txtTiempo;
    private JTextArea txtTiempoAnterior;
    private JPanel laberintoPanel;
    private JButton[][] botones;
    private int altura;
    private int anchura;
    private boolean[][] grid;
    private JButton btnGenerar;
    private JButton btnRecursivo;
    private JButton btnCache;
    private JButton btnBFS;
    private JButton btnDFS;
    private JButton btnLimpiarRecorrido; 
    private JCheckBox cbDelay;

    public LaberintoGui() {
        setTitle("Laberinto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        // Título
        JLabel lblTitulo = new JLabel("Laberinto");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // Panel que recibe los inputs
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblAltura = new JLabel("Alto:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(lblAltura, gbc);

        txtAltura = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        topPanel.add(txtAltura, gbc);
        txtAltura.setColumns(5);

        JLabel lblAnchura = new JLabel("Ancho:");
        gbc.gridx = 2;
        gbc.gridy = 0;
        topPanel.add(lblAnchura, gbc);

        txtAnchura = new JTextField();
        gbc.gridx = 3;
        gbc.gridy = 0;
        topPanel.add(txtAnchura, gbc);
        txtAnchura.setColumns(5);

        btnGenerar = new JButton("Generar Laberinto");
        gbc.gridx = 4;
        gbc.gridy = 0;
        topPanel.add(btnGenerar, gbc);

        btnLimpiarRecorrido = new JButton("Limpiar Recorrido"); 
        gbc.gridx = 5;
        gbc.gridy = 0;
        topPanel.add(btnLimpiarRecorrido, gbc);

        cbDelay = new JCheckBox("Delay ");
        gbc.gridx = 5;
        gbc.gridy = 1;
        topPanel.add(cbDelay, gbc);


        JLabel lblInicioX = new JLabel("Inicio X:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        topPanel.add(lblInicioX, gbc);

        txtInicioX = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        topPanel.add(txtInicioX, gbc);
        txtInicioX.setColumns(5);

        JLabel lblInicioY = new JLabel("Inicio Y:");
        gbc.gridx = 2;
        gbc.gridy = 1;
        topPanel.add(lblInicioY, gbc);

        txtInicioY = new JTextField();
        gbc.gridx = 3;
        gbc.gridy = 1;
        topPanel.add(txtInicioY, gbc);
        txtInicioY.setColumns(5);

        JLabel lblFinX = new JLabel("Fin X:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        topPanel.add(lblFinX, gbc);

        txtFinX = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        topPanel.add(txtFinX, gbc);
        txtFinX.setColumns(5);

        JLabel lblFinY = new JLabel("Fin Y:");
        gbc.gridx = 2;
        gbc.gridy = 2;
        topPanel.add(lblFinY, gbc);

        txtFinY = new JTextField();
        gbc.gridx = 3;
        gbc.gridy = 2;
        topPanel.add(txtFinY, gbc);
        txtFinY.setColumns(5);

        contentPane.add(topPanel, BorderLayout.NORTH);

        // Panel donde se genera el laberinto
        laberintoPanel = new JPanel();
        laberintoPanel.setLayout(new GridLayout());
        contentPane.add(laberintoPanel, BorderLayout.CENTER);

        // Panel para botones de resolución y resultados
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        btnRecursivo = new JButton("Recursivo Simple");
        gbc.gridx = 0;
        gbc.gridy = 0;
        bottomPanel.add(btnRecursivo, gbc);

        btnCache = new JButton("Método Caché");
        gbc.gridx = 1;
        gbc.gridy = 0;
        bottomPanel.add(btnCache, gbc);

        btnBFS = new JButton("BFS");
        gbc.gridx = 2;
        gbc.gridy = 0;
        bottomPanel.add(btnBFS, gbc);

        btnDFS = new JButton("DFS");
        gbc.gridx = 3;
        gbc.gridy = 0;
        bottomPanel.add(btnDFS, gbc);

        JLabel lblResultado = new JLabel("Resultado:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        bottomPanel.add(lblResultado, gbc);

        txtResultado = new JTextArea();
        txtResultado.setColumns(20);
        txtResultado.setRows(2);
        txtResultado.setLineWrap(true); // Permite el ajuste de línea
        txtResultado.setWrapStyleWord(true); // Ajuste de palabra
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        bottomPanel.add(txtResultado, gbc);

        JLabel lblTiempoAnterior = new JLabel("Tiempos:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        bottomPanel.add(lblTiempoAnterior, gbc);

        txtTiempoAnterior = new JTextArea();
        txtTiempoAnterior.setColumns(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        bottomPanel.add(txtTiempoAnterior, gbc);

        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        // Listener para el botón "Limpiar Recorrido"
        btnLimpiarRecorrido.addActionListener(e -> limpiarRecorrido());
    }
    

    // Añadir este método para obtener el estado del checkbox
    public boolean isDelayEnabled() {
        return cbDelay.isSelected();
    }
    

    public void addGenerarLaberintoListener(ActionListener listener) {
        btnGenerar.addActionListener(listener);
    }

    public void addRecursivoListener(ActionListener listener) {
        btnRecursivo.addActionListener(listener);
    }

    public void addCacheListener(ActionListener listener) {
        btnCache.addActionListener(listener);
    }

    public void addBFSListener(ActionListener listener) {
        btnBFS.addActionListener(listener);
    }

    public void addDFSListener(ActionListener listener) {
        btnDFS.addActionListener(listener);
    }

    public int getAltura() throws NumberFormatException {
        return Integer.parseInt(txtAltura.getText());
    }

    public int getAnchura() throws NumberFormatException {
        return Integer.parseInt(txtAnchura.getText());
    }

    public int getInicioX() throws NumberFormatException {
        return Integer.parseInt(txtInicioX.getText());
    }

    public int getInicioY() throws NumberFormatException {
        return Integer.parseInt(txtInicioY.getText());
    }

    public int getFinX() throws NumberFormatException {
        return Integer.parseInt(txtFinX.getText());
    }

    public int getFinY() throws NumberFormatException {
        return Integer.parseInt(txtFinY.getText());
    }

    public JButton[][] getBotones() {
        return botones;
    }


    public void generarLaberinto() {
        try {
            altura = getAltura();
            anchura = getAnchura();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese números válidos para el alto y ancho.");
            return;
        }

        laberintoPanel.removeAll();
        laberintoPanel.setLayout(new GridLayout(altura, anchura));
        botones = new JButton[altura][anchura];
        grid = new boolean[altura][anchura];

        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < anchura; j++) {
                botones[i][j] = new JButton();
                botones[i][j].setBackground(Color.WHITE);
                botones[i][j].addActionListener(e -> {
                    JButton boton = (JButton) e.getSource();
                    if (boton.getBackground() == Color.WHITE) {
                        boton.setBackground(Color.RED);
                    } else {
                        boton.setBackground(Color.WHITE);
                    }
                });
                laberintoPanel.add(botones[i][j]);
                grid[i][j] = true;
            }
        }

        laberintoPanel.revalidate();
        laberintoPanel.repaint();
    }

    public void mostrarResultado(String resultado) {
        txtResultado.setText(resultado);
    }

    public void colorearCelda(int row, int col, Color color) {
        botones[row][col].setBackground(color);
        laberintoPanel.revalidate();
        laberintoPanel.repaint();

    }
    public void pintarRecorridoPasoAPaso(List<Celda> recorrido, Color color, Runnable callback) {
        new Thread(() -> {
            for (Celda celda : recorrido) {
                SwingUtilities.invokeLater(() -> colorearCelda(celda.getRow(), celda.getCol(), color));
                try {
                    Thread.sleep(200); // Delay of 200 milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (callback != null) {
                SwingUtilities.invokeLater(callback);
            }
        }).start();
    }

    public void mostrarTiempo(String tiempo) {
        txtTiempo.setText(tiempo);
    }

    public void mostrarTiemposAnterior(String tiempo) {
        txtTiempoAnterior.setText(tiempo);
    }


    public void limpiarRecorrido() {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < anchura; j++) {
                if (botones[i][j].getBackground() != Color.RED) {
                    botones[i][j].setBackground(Color.WHITE);
                }
            }
        }
        txtResultado.setText("");
        txtTiempoAnterior.setText("");       
    }
}
