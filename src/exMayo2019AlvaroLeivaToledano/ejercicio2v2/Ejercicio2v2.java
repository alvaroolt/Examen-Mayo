package exMayo2019AlvaroLeivaToledano.ejercicio2v2;

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
 * En esta versión del programa, modificaré el código de manera que trabajemos con un menú desplegable
 * y una nueva ventana emergente para introducir la fecha
 * 
 * @author Alvaro Leiva Toledano
 * @version 2.0
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import exMayo2019AlvaroLeivaToledano.ejercicio2v2.Fecha;
import exMayo2019AlvaroLeivaToledano.ejercicio2v2.FechaInvalidaException;

public class Ejercicio2v2 {

  private JFrame frmEjercicio;
  private JFrame subframe;
  private JTextField textFieldFecha;
  private JTextField textFieldIntroducirFecha;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Ejercicio2v2 window = new Ejercicio2v2();
          window.frmEjercicio.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public Ejercicio2v2() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmEjercicio = new JFrame();
    frmEjercicio.setTitle("Ejercicio 2 - Examen de Mayo - v2.0");
    frmEjercicio.setBounds(425, 150, 450, 300);
    frmEjercicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JMenuBar menuBar = new JMenuBar();
    menuBar.setBackground(Color.LIGHT_GRAY);
    menuBar.setMargin(new Insets(2, 2, 2, 2));
    frmEjercicio.setJMenuBar(menuBar);

    JButton btnIntroducirFecha = new JButton("Introducir fecha");
    btnIntroducirFecha.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        subframe = new JFrame();
        subframe.setResizable(false);
        subframe.setBounds(100, 100, 295, 200);
        subframe.getContentPane().setLayout(null);
        subframe.setVisible(true);

        JLabel lblIntroduceUnaFecha = new JLabel("Introduce una fecha");
        lblIntroduceUnaFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblIntroduceUnaFecha.setBounds(69, 34, 139, 22);
        subframe.getContentPane().add(lblIntroduceUnaFecha);

        textFieldIntroducirFecha = new JTextField();
        textFieldIntroducirFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldIntroducirFecha.setBounds(69, 57, 139, 29);
        subframe.getContentPane().add(textFieldIntroducirFecha);
        textFieldIntroducirFecha.setColumns(10);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {

            try {

              // si comprobarFecha no devuelve null, la fecha es correcta
              if (Fecha.comprobarFecha(textFieldIntroducirFecha.getText()) != null) {
                textFieldFecha.setText(textFieldIntroducirFecha.getText());
                subframe.setVisible(false);
                subframe.dispose();
              }

            } catch (FechaInvalidaException ee) {
              JOptionPane.showMessageDialog(null, ee.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
          }
        });
        btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnAceptar.setBounds(93, 119, 89, 23);
        subframe.getContentPane().add(btnAceptar);
      }
    });
    btnIntroducirFecha.setHorizontalAlignment(SwingConstants.LEADING);
    btnIntroducirFecha.setBackground(Color.LIGHT_GRAY);
    btnIntroducirFecha.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    menuBar.add(btnIntroducirFecha);

    JMenu mnOperaciones = new JMenu("Operaciones");
    mnOperaciones.setBackground(Color.LIGHT_GRAY);
    mnOperaciones.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    menuBar.add(mnOperaciones);

    JMenuItem mnSumarDia = new JMenuItem("Sumar dia");
    mnSumarDia.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        try {
          // antes de sumar, comprueba que la fecha sea correcta
          Fecha.comprobarFecha(textFieldFecha.getText());
          textFieldFecha.setText(Fecha.sumarDia(textFieldFecha.getText()));
        } catch (FechaInvalidaException e1) {
          JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    mnOperaciones.add(mnSumarDia);

    JMenuItem mnRestarDia = new JMenuItem("Restar dia");
    mnRestarDia.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {

        try {
          // antes de restar, comprueba que la fecha sea correcta
          Fecha.comprobarFecha(textFieldFecha.getText());
          textFieldFecha.setText(Fecha.restarDia(textFieldFecha.getText()));
        } catch (FechaInvalidaException e1) {
          JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    mnOperaciones.add(mnRestarDia);

    JMenuItem mnDiasHastaHoy = new JMenuItem("Dias hasta hoy");
    mnDiasHastaHoy.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        try {
          // antes de calcular los días hasta hoy, comprueba que la fecha sea correcta
          Fecha.comprobarFecha(textFieldFecha.getText());
          JOptionPane.showMessageDialog(null, "Días hasta hoy: " + Fecha.diasHastaHoy(textFieldFecha.getText()));

        } catch (FechaInvalidaException | ParseException e1) {
          JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    mnOperaciones.add(mnDiasHastaHoy);

    JButton btnSalir = new JButton("Salir");
    btnSalir.setHorizontalAlignment(SwingConstants.LEADING);
    btnSalir.setBackground(Color.LIGHT_GRAY);
    btnSalir.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        System.exit(0);
      }
    });
    btnSalir.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    menuBar.add(btnSalir);
    frmEjercicio.getContentPane().setLayout(null);

    JLabel lblFecha = new JLabel("Fecha:");
    lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 25));
    lblFecha.setBounds(32, 87, 74, 28);
    frmEjercicio.getContentPane().add(lblFecha);

    textFieldFecha = new JTextField();
    textFieldFecha.setEditable(false);
    textFieldFecha.setFont(new Font("Tahoma", Font.PLAIN, 45));
    textFieldFecha.setBounds(124, 70, 258, 69);
    frmEjercicio.getContentPane().add(textFieldFecha);
    textFieldFecha.setColumns(10);
  }
}
