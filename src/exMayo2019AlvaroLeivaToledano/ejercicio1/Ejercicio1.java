package exMayo2019AlvaroLeivaToledano.ejercicio1;

/**
 * Escribe un programa que usando la interfaz gráfica de Java escoja un fichero del sistema de archivos y
 * lo almacene en otro de manera que se sustituyan todas las ocurrencias de los siguientes caracteres
 * (mayúscula o minúscula) del fichero original por sus correspondientes números
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

    frame.setVisible(true);
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
          BufferedReader br2 = new BufferedReader(new FileReader(ficheroDestino));
          BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroDestino));

          String linea = "";

          // Recorremos el fichero origen sustituyendo caracteres y a su vez imprime en el
          // fichero destino
          while (linea != null) {
            bw.write(linea.replace('A', '4').replace('a', '4').replace('B', '8').replace('b', '8').replace('E', '3')
                .replace('e', '3').replace('I', '1').replace('i', '1').replace('O', '0').replace('o', '0')
                .replace('S', '5').replace('s', '5').replace('T', '7').replace('t', '7') + "\n");
            linea = br1.readLine();
          }
          bw.close();
          br1.close();

          // Recorre el fichero destino y va añadiendo cada linea de este a
          // contenidoFichero, para luego mostrarlo en textArea
          String contenidoFichero = "";
          linea = "";
          while (linea != null) {
            linea = br2.readLine();
            if (linea != null) {
              contenidoFichero += linea + "\n";
            }
          }
          textArea.append(contenidoFichero);
          br2.close();

        } catch (Exception e1) {
          JOptionPane.showMessageDialog(null, "ERROR");
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
