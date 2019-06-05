package exMayo2019AlvaroLeivaToledano.ejercicio2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Ejercicio2 {

  private JFrame frmEj2;
  private JTextField textFieldFechaIntroducida;
  private JTextField textFieldDiasHastaHoy;
  private boolean FechaCorrecta = false;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Ejercicio2 window = new Ejercicio2();
          window.frmEj2.setVisible(true);
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
    frmEj2 = new JFrame();
    frmEj2.setTitle("Ejercicio 2 - Examen Tercer Trimestre");
    frmEj2.setSize(594, 406);
    frmEj2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frmEj2.getContentPane().setLayout(null);
    Toolkit pantalla = Toolkit.getDefaultToolkit();

    // Obtiene el tamaño de la pantalla que ejecuta el programa
    Dimension tamanoPantalla = pantalla.getScreenSize();

    int altoPantalla = tamanoPantalla.height;
    int anchoPantalla = tamanoPantalla.width;

    // defino el tamaño y posición del marco según el alto y el ancho de la pantalla
    frmEj2.setSize(anchoPantalla / 2, altoPantalla / 2);
    frmEj2.setLocation(anchoPantalla / 4, altoPantalla / 4);

    frmEj2.setResizable(false);

    JLabel labelFecha = new JLabel("Introduce una fecha");
    labelFecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
    labelFecha.setBounds(63, 33, 173, 22);
    frmEj2.getContentPane().add(labelFecha);

    textFieldFechaIntroducida = new JTextField();
    textFieldFechaIntroducida.setFont(new Font("Tahoma", Font.PLAIN, 15));
    textFieldFechaIntroducida.setBounds(246, 29, 162, 32);
    frmEj2.getContentPane().add(textFieldFechaIntroducida);
    textFieldFechaIntroducida.setColumns(10);

    JButton btnValidarFecha = new JButton("Validar fecha");
    btnValidarFecha.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {

        if (Fecha.esFechaValida(textFieldFechaIntroducida.getText()) == false) {
          JOptionPane.showMessageDialog(null, "Fecha incorrecta");
        } else {
          JOptionPane.showMessageDialog(null, "Fecha correcta");
          FechaCorrecta = true;
          textFieldFechaIntroducida.setEditable(false);
        }
      }
    });
    btnValidarFecha.setBackground(Color.CYAN);
    btnValidarFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
    btnValidarFecha.setBounds(468, 21, 138, 47);
    frmEj2.getContentPane().add(btnValidarFecha);

    JButton btnSumarDa = new JButton("Sumar día");
    btnSumarDa.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        
      }
    });
    btnSumarDa.setFont(new Font("Tahoma", Font.PLAIN, 14));
    btnSumarDa.setBounds(127, 111, 138, 47);
    frmEj2.getContentPane().add(btnSumarDa);

    JButton btnRestarDa = new JButton("Restar día");
    btnRestarDa.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

      }
    });
    btnRestarDa.setFont(new Font("Tahoma", Font.PLAIN, 14));
    btnRestarDa.setBounds(382, 111, 138, 47);
    frmEj2.getContentPane().add(btnRestarDa);

    JButton btnDiasHastaHoy = new JButton("Días hasta hoy");
    btnDiasHastaHoy.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        if (FechaCorrecta == false) {
          JOptionPane.showMessageDialog(null, "Valida la fecha primero.");
        } else {
          try {

            textFieldDiasHastaHoy.setText("" + Fecha.diasHastaHoy(textFieldFechaIntroducida.getText()));
          } catch (FechaMayorQueHoyException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
          }
        }
      }
    });
    btnDiasHastaHoy.setFont(new Font("Tahoma", Font.PLAIN, 12));
    btnDiasHastaHoy.setBounds(92, 236, 118, 47);
    frmEj2.getContentPane().add(btnDiasHastaHoy);

    textFieldDiasHastaHoy = new JTextField();
    textFieldDiasHastaHoy.setFont(new Font("Tahoma", Font.PLAIN, 15));
    textFieldDiasHastaHoy.setEditable(false);
    textFieldDiasHastaHoy.setColumns(10);
    textFieldDiasHastaHoy.setBounds(232, 243, 118, 32);
    frmEj2.getContentPane().add(textFieldDiasHastaHoy);

    JButton btnTerminar = new JButton("Terminar");
    btnTerminar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    btnTerminar.setBackground(Color.ORANGE);
    btnTerminar.setFont(new Font("Tahoma", Font.PLAIN, 15));
    btnTerminar.setBounds(507, 230, 99, 57);
    frmEj2.getContentPane().add(btnTerminar);
  }

}
