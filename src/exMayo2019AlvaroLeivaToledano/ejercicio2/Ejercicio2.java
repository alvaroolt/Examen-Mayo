package exMayo2019AlvaroLeivaToledano.ejercicio2;

/**
 * Escribe un programa que usando la interfaz gráfica de Java permita al usuario introducir una fecha en
 * formato dd/mm/aaaa y que tenga los siguientes botones:
 * • Validar fecha: muestra un mensaje diciendo si la fecha es o no válida.
 * • Día posterior: Modifica la fecha sumándole un día. Debe validar la fecha antes de hacer la
 * operación.
 * • Día anterior: Modifica la fecha restándole un día. Debe validar la fecha antes de hacer la
 * operación.
 * • Días hasta hoy: Muestra el número de días que hay entre la fecha introducida y la fecha de hoy.
 * Debe validar la fecha antes de hacer la operación.
 * • Terminar.
 * La fecha debe ser manejada mediante un objeto de una clase que o bien construyáis con sus métodos
 * correspondientes o de las que ya existen en la API de Java.
 * 
 * @author Alvaro Leiva Toledano
 * @version 2.0
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Ejercicio2 {

  private JFrame frame;
  private JTextField textFieldFechaIntroducida;
  private JTextField textFieldDiasHastaHoy;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Ejercicio2 window = new Ejercicio2();
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
  public Ejercicio2() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setTitle("Ejercicio 2 - Examen Tercer Trimestre");
    frame.setSize(594, 406);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);
    Toolkit pantalla = Toolkit.getDefaultToolkit();

    // Obtiene el tamaño de la pantalla que ejecuta el programa
    Dimension tamanoPantalla = pantalla.getScreenSize();

    int altoPantalla = tamanoPantalla.height;
    int anchoPantalla = tamanoPantalla.width;

    // defino el tamaño y posición del marco según el alto y el ancho de la pantalla
    frame.setSize(anchoPantalla / 2, altoPantalla / 2);
    frame.setLocation(anchoPantalla / 4, altoPantalla / 4);

    frame.setResizable(false);

    JLabel labelFecha = new JLabel("Introduce una fecha");
    labelFecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
    labelFecha.setBounds(63, 33, 173, 22);
    frame.getContentPane().add(labelFecha);

    textFieldFechaIntroducida = new JTextField();
    textFieldFechaIntroducida.setFont(new Font("Tahoma", Font.PLAIN, 15));
    textFieldFechaIntroducida.setBounds(246, 29, 162, 32);
    frame.getContentPane().add(textFieldFechaIntroducida);
    textFieldFechaIntroducida.setColumns(10);

    JButton btnValidarFecha = new JButton("Validar fecha");
    btnValidarFecha.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {

        try {

          // si comprobarFecha no devuelve null, la fecha es correcta
          if (Fecha.comprobarFecha(textFieldFechaIntroducida.getText()) != null) {
            JOptionPane.showMessageDialog(null, "Fecha correcta");
          }

        } catch (FechaInvalidaException e) {
          JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

      }
    });
    btnValidarFecha.setBackground(Color.CYAN);
    btnValidarFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
    btnValidarFecha.setBounds(468, 21, 138, 47);
    frame.getContentPane().add(btnValidarFecha);

    JButton btnSumar = new JButton("Sumar día");
    btnSumar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        try {
          // antes de sumar, comprueba que la fecha sea correcta
          Fecha.comprobarFecha(textFieldFechaIntroducida.getText());
          textFieldFechaIntroducida.setText(Fecha.sumarDia(textFieldFechaIntroducida.getText()));
        } catch (FechaInvalidaException e1) {
          JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

      }
    });
    btnSumar.setFont(new Font("Tahoma", Font.PLAIN, 14));
    btnSumar.setBounds(127, 111, 138, 47);
    frame.getContentPane().add(btnSumar);

    JButton btnRestar = new JButton("Restar día");
    btnRestar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        try {
          // antes de restar, comprueba que la fecha sea correcta
          Fecha.comprobarFecha(textFieldFechaIntroducida.getText());
          textFieldFechaIntroducida.setText(Fecha.restarDia(textFieldFechaIntroducida.getText()));
        } catch (FechaInvalidaException e1) {
          JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    btnRestar.setFont(new Font("Tahoma", Font.PLAIN, 14));
    btnRestar.setBounds(382, 111, 138, 47);
    frame.getContentPane().add(btnRestar);

    JButton btnDiasHastaHoy = new JButton("Días hasta hoy");
    btnDiasHastaHoy.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        try {
          // antes de calcular los días hasta hoy, comprueba que la fecha sea correcta
          Fecha.comprobarFecha(textFieldFechaIntroducida.getText());
          textFieldDiasHastaHoy.setText("" + Fecha.diasHastaHoy(textFieldFechaIntroducida.getText()));

        } catch (FechaInvalidaException | ParseException e1) {
          JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    btnDiasHastaHoy.setFont(new Font("Tahoma", Font.PLAIN, 12));
    btnDiasHastaHoy.setBounds(92, 236, 118, 47);
    frame.getContentPane().add(btnDiasHastaHoy);

    textFieldDiasHastaHoy = new JTextField();
    textFieldDiasHastaHoy.setFont(new Font("Tahoma", Font.PLAIN, 15));
    textFieldDiasHastaHoy.setEditable(false);
    textFieldDiasHastaHoy.setColumns(10);
    textFieldDiasHastaHoy.setBounds(232, 243, 118, 32);
    frame.getContentPane().add(textFieldDiasHastaHoy);

    JButton btnTerminar = new JButton("Terminar");
    btnTerminar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    btnTerminar.setBackground(Color.ORANGE);
    btnTerminar.setFont(new Font("Tahoma", Font.PLAIN, 15));
    btnTerminar.setBounds(507, 230, 99, 57);
    frame.getContentPane().add(btnTerminar);
  }

}