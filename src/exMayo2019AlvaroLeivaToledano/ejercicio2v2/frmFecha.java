package exMayo2019AlvaroLeivaToledano.ejercicio2v2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmFecha {

  private JFrame subframe;
  private JTextField textFieldIntroducirFecha;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          frmFecha window = new frmFecha();
          window.subframe.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public frmFecha() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    subframe = new JFrame();
    subframe.setResizable(false);
    subframe.setBounds(100, 100, 295, 200);
    subframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    subframe.getContentPane().setLayout(null);

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
      }
    });
    btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
    btnAceptar.setBounds(93, 119, 89, 23);
    subframe.getContentPane().add(btnAceptar);
  }
}
