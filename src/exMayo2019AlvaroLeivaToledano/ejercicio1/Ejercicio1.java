package exMayo2019AlvaroLeivaToledano.ejercicio1;

/**
 * Interfaz gráfica de Java que escoje un fichero del sistema de archivos y
 * lo almacene en otro de manera que se sustituyan todas las ocurrencias de los 
 * siguientes caracteres (mayúscula o minúscula) del fichero original por 
 * sus correspondientes números
 * 
 * A - 4, B - 8, E - 3, I - 1, O - 0, S - 5, T - 7
 * 
 * @author Alvaro Leiva Toledano
 * @version 1.0
 */
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Ejercicio1 {

  private JFrame frame;
  private JTextField textFieldFicheroOrigen;
  private JTextField textFieldFicheroDestino;
  File ficheroOrigen;
  File ficheroDestino;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Ejercicio1 window = new Ejercicio1();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public Ejercicio1() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setTitle("Ejercicio 1 - exMayo2019");

    // frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Toolkit pantalla = Toolkit.getDefaultToolkit();

    // Obtiene el tamaño de la pantalla que ejecuta el programa
    Dimension tamanoPantalla = pantalla.getScreenSize();

    int altoPantalla = tamanoPantalla.height;
    int anchoPantalla = tamanoPantalla.width;

    // Defino el tamaño y posición del marco según el alto y el ancho de la pantalla
    frame.setSize(680, 400);
    frame.setLocation(anchoPantalla / 4, altoPantalla / 4);

    frame.setResizable(false);
    frame.getContentPane().setLayout(null);

    // Botón que selecciona el fichero origen (fichero donde se encuentra el texto a
    // sustituir los caracteres)
    JButton btnFicheroOrigen = new JButton("Seleccionar fichero origen");
    btnFicheroOrigen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {

        JFileChooser fc = new JFileChooser();

        int seleccionado = fc.showOpenDialog(frame.getContentPane());
        if (seleccionado == JFileChooser.APPROVE_OPTION) {
          ficheroOrigen = fc.getSelectedFile();

          // Ruta donde se encuentra el fichero seleccionado
          textFieldFicheroOrigen.setText(ficheroOrigen.getAbsolutePath());
        }
      }
    });
    btnFicheroOrigen.setFont(new Font("Sitka Text", Font.PLAIN, 14));
    btnFicheroOrigen.setBounds(23, 21, 236, 52);
    frame.getContentPane().add(btnFicheroOrigen);

    // Botón que selecciona el fichero destino (fichero donde se almacenará el texto
    // del fichero origen modificado)
    JButton btnFicheroDestino = new JButton("Seleccionar fichero destino");
    btnFicheroDestino.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        JFileChooser fc = new JFileChooser();

        int seleccionado = fc.showOpenDialog(frame.getContentPane());
        if (seleccionado == JFileChooser.APPROVE_OPTION) {
          ficheroDestino = fc.getSelectedFile();

          // Ruta donde se encuentra el fichero seleccionado
          textFieldFicheroDestino.setText(ficheroDestino.getAbsolutePath());
        }
      }
    });
    btnFicheroDestino.setFont(new Font("Sitka Text", Font.PLAIN, 14));
    btnFicheroDestino.setBounds(292, 21, 236, 52);
    frame.getContentPane().add(btnFicheroDestino);

    // ScrollPane y TextArea, para visualizar el contenido del fichero destino
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setBounds(23, 182, 628, 162);
    frame.getContentPane().add(scrollPane);

    JTextArea textArea = new JTextArea();
    textArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
    textArea.setEditable(false);
    scrollPane.setViewportView(textArea);

    // Botón que ejecuta la acción de sustituir los caracteres y que muestra el
    // contenido del fichero destino en el textArea
    JButton btnSustituir = new JButton("Sustituir caracteres");
    btnSustituir.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        try {
          BufferedReader br1 = new BufferedReader(new FileReader(ficheroOrigen));
          BufferedWriter bw2 = new BufferedWriter(new FileWriter(ficheroDestino));
          Vector<String> escritura = new Vector<String>();

          String linea = "";
          String salida = "";

          while (linea != null) {
            linea = br1.readLine();
            if (linea != null)
              escritura.addElement(linea);
          }

          for (String t : escritura) {
            t = t.replace('a', '4').replace('A', '4').replace('b', '8').replace('B', '8').replace('e', '3')
                .replace('E', '3').replace('i', '1').replace('I', '1').replace('o', '0').replace('O', '0')
                .replace('s', '5').replace('S', '5').replace('t', '7').replace('T', '7');
            bw2.write(t + "\n");
            salida = salida + t + ("\n");
          }

          br1.close();
          bw2.close();
          textArea.append(salida);

        } catch (Exception e1) {
          JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    btnSustituir.setFont(new Font("Sitka Text", Font.PLAIN, 14));
    btnSustituir.setBounds(458, 103, 183, 52);
    frame.getContentPane().add(btnSustituir);

    JLabel lblFicheroOrigen = new JLabel("Ruta fichero origen");
    lblFicheroOrigen.setBounds(23, 84, 133, 14);
    frame.getContentPane().add(lblFicheroOrigen);

    textFieldFicheroOrigen = new JTextField();
    textFieldFicheroOrigen.setEditable(false);
    textFieldFicheroOrigen.setBounds(23, 97, 402, 20);
    frame.getContentPane().add(textFieldFicheroOrigen);
    textFieldFicheroOrigen.setColumns(10);

    JLabel lblFicheroDestino = new JLabel("Ruta fichero destino");
    lblFicheroDestino.setBounds(23, 128, 133, 14);
    frame.getContentPane().add(lblFicheroDestino);

    textFieldFicheroDestino = new JTextField();
    textFieldFicheroDestino.setEditable(false);
    textFieldFicheroDestino.setColumns(10);
    textFieldFicheroDestino.setBounds(23, 141, 402, 20);
    frame.getContentPane().add(textFieldFicheroDestino);
  }

}