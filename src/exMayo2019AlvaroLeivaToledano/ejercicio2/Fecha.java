package exMayo2019AlvaroLeivaToledano.ejercicio2;

import java.util.Date;

public class Fecha {

  final static int[] DIAS_MES = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // constante, sin bisiestos

  public static boolean esFechaValida(String f) {

    // comprobar que es un formato dd/mm/aaaa
    if (f.length() != 10 || !Character.isDigit(f.charAt(0)) || !Character.isDigit(f.charAt(1))
        || !Character.isDigit(f.charAt(3)) || !Character.isDigit(f.charAt(4)) || !Character.isDigit(f.charAt(6))
        || !Character.isDigit(f.charAt(7)) || !Character.isDigit(f.charAt(8)) || !Character.isDigit(f.charAt(9))
        || f.charAt(2) != '/' || f.charAt(5) != '/') {
      return false;
    }
    // comprobar si mes es correcto
    int mes = Integer.parseInt(f.substring(3, 5));
    if (mes < 1 || mes > 12) {
      return false;
    }
    // comprobar si día es correcto
    int dia = Integer.parseInt(f.substring(0, 2));
    int anyo = Integer.parseInt(f.substring(6));
    int diasmes = DIAS_MES[mes - 1]; // restamos 1 al mes para que esté entre 0 y 11
    // ¿febrero y año bisisesto?
    if (mes == 2 && anyo % 4 == 0 && (anyo % 100 != 0 || anyo % 400 == 0)) {
      diasmes++;
    }
    return (dia > 0 && dia <= diasmes);
  }

  public static String sumaDiaFecha(String f) {

    int dia = Integer.parseInt(f.substring(0, 2));
    int mes = Integer.parseInt(f.substring(3, 5));
    int anno = Integer.parseInt(f.substring(6));

    int diasmes = DIAS_MES[mes - 1];
    // ¿febrero y año bisisesto?
    if (mes == 2 && anno % 4 == 0 && (anno % 100 != 0 || anno % 400 == 0)) {
      diasmes++;
    }
    dia++;
    if (dia > diasmes) {
      dia = 1;
      mes++;
      if (mes == 13) {
        mes = 1;
        anno++;
      }
    }
    f = fecha(dia, mes, anno);
    return f;
  }

  public static String restaDiaFecha(String f) {
    int dia = Integer.parseInt(f.substring(0, 2));
    int mes = Integer.parseInt(f.substring(3, 5));
    int anno = Integer.parseInt(f.substring(6));

    dia--;
    if (dia == 0) { // mes anterior
      mes--;
      if (mes == 0) { // año anterior
        mes = 12;
        anno--;
      }
      dia = DIAS_MES[mes - 1];
      // ¿febrero y año bisisesto?
      if (mes == 2 && anno % 4 == 0 && (anno % 100 != 0 || anno % 400 == 0)) {
        dia++;
      }
    }
    f = fecha(dia, mes, anno);
    return f;
  }

  public static int diasHastaHoy(String f) throws FechaMayorQueHoyException {
    int dia = Integer.parseInt(f.substring(0, 2));
    int mes = Integer.parseInt(f.substring(3, 5));

    String fechaHoy;

    if (dia < 10 && mes < 10) {
      java.util.Date hoy = new Date();
      fechaHoy = "0" + hoy.getDate() + "0/" + (1 + hoy.getMonth()) + "/" + (hoy.getYear() + 1900);
    } else if (dia < 10) {
      java.util.Date hoy = new Date();
      fechaHoy = "0" + hoy.getDate() + "/" + (1 + hoy.getMonth()) + "/" + (hoy.getYear() + 1900);
    } else if (mes < 10) {
      java.util.Date hoy = new Date();
      fechaHoy = hoy.getDate() + "/0" + (1 + hoy.getMonth()) + "/" + (hoy.getYear() + 1900);
    } else {
      java.util.Date hoy = new Date();
      fechaHoy = hoy.getDate() + "/" + (1 + hoy.getMonth()) + "/" + (hoy.getYear() + 1900);
    }

    if (comparaFechas(f, fechaHoy) > 0) {
      throw new FechaMayorQueHoyException("Fecha mayor que hoy " + fechaHoy);
    }

    int cont = 0;
    do {

      f = sumaDiaFecha(f);
      cont++;
    } while (comparaFechas(f, fechaHoy) < 0);

    return cont;
  }

  public static int comparaFechas(String fecha1, String fecha2) {
    int dia1 = Integer.parseInt(fecha1.substring(0, 2));
    int dia2 = Integer.parseInt(fecha2.substring(0, 2));
    int mes1 = Integer.parseInt(fecha1.substring(3, 5));
    int mes2 = Integer.parseInt(fecha2.substring(3, 5));
    int anyo1 = Integer.parseInt(fecha1.substring(6));
    int anyo2 = Integer.parseInt(fecha2.substring(6));

    if (anyo1 != anyo2) {
      return anyo1 - anyo2;
    } else if (mes1 != mes2) {
      return mes1 - mes2;
    } else {
      return dia1 - dia2;
    }
  }

  public static String fecha(int d, int m, int a) {
    String dia = Integer.toString(d).trim();
    String mes = Integer.toString(m).trim();
    String anyo = Integer.toString(a).trim();
    // día
    if (dia.length() < 2) {
      dia = "0" + dia;
    }
    // mes
    if (mes.length() < 2) {
      mes = "0" + mes;
    }
    // año
    for (int i = anyo.length(); i < 4; i++) {
      anyo = "0" + anyo;
    }
    return dia + "/" + mes + "/" + anyo;
  }
}