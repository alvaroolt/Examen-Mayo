package exMayo2019AlvaroLeivaToledano.ejercicio2v2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import exMayo2019AlvaroLeivaToledano.ejercicio2.FechaInvalidaException;

public class Fecha {

  @SuppressWarnings("unused")
  private static String fecha;

  // getInstance() obtiene un calendario utilizando la zona horaria y la
  // configuración regional predeterminadas. El calendario devuelto se basa en la
  // hora actual en la zona horaria
  private static Calendar calendario = Calendar.getInstance();

  // SimpleDateFormat es una clase concreta para formatear y analizar fechas de
  // una manera sensible al entorno local. Permite el formateo (fecha -> texto),
  // el análisis (texto -> fecha) y la normalización.
  private static SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

  public static Calendar comprobarFecha(String f) throws FechaInvalidaException {

    String arrayFecha[];

    if (f.contains("/") && f.length() == 10) {

      // split() divide la cadena alrededor de coincidencias de la expresión regular
      // dada. En este caso: /
      arrayFecha = f.split("/");
      int dia = Integer.parseInt(arrayFecha[0]);
      int mes = Integer.parseInt(arrayFecha[1]);
      int anno = Integer.parseInt(arrayFecha[2]);

      switch (mes) {
      case 1:
        calendario.set(anno, Calendar.JANUARY, dia);
        break;
      case 2:
        calendario.set(anno, Calendar.FEBRUARY, dia);
        break;
      case 3:
        calendario.set(anno, Calendar.MARCH, dia);
        break;
      case 4:
        calendario.set(anno, Calendar.APRIL, dia);
        break;
      case 5:
        calendario.set(anno, Calendar.MAY, dia);
        break;
      case 6:
        calendario.set(anno, Calendar.JUNE, dia);
        break;
      case 7:
        calendario.set(anno, Calendar.JULY, dia);
        break;
      case 8:
        calendario.set(anno, Calendar.AUGUST, dia);
        break;
      case 9:
        calendario.set(anno, Calendar.SEPTEMBER, dia);
        break;
      case 10:
        calendario.set(anno, Calendar.OCTOBER, dia);
        break;
      case 11:
        calendario.set(anno, Calendar.NOVEMBER, dia);
        break;
      case 12:
        calendario.set(anno, Calendar.DECEMBER, dia);
        break;
      default:
        calendario.set(0, 0, 0);
        break;
      }
    } else {
      calendario.set(0, 0, 0);
    }

    // si la fecha introducida no coincide con el formato correcto, se lanza la
    // excepción FechaInvalidaException. getTime() devuelve un objeto de fecha que
    // representa el valor de tiempo del calendario
    if (!f.equals(formatoFecha.format(calendario.getTime()))) {
      throw new FechaInvalidaException("Fecha incorrecta");
    }

    return calendario;

  }

  public static String sumarDia(String f) {

    // add() agrega o resta la cantidad de tiempo especificada al campo del
    // calendario dado
    calendario.add(Calendar.DATE, 1);

    // getTime() devuelve un objeto de fecha que representa el valor de tiempo del
    // calendario
    return (formatoFecha.format(calendario.getTime()));

  }

  public static String restarDia(String f) {

    // add() agrega o resta la cantidad de tiempo especificada al campo del
    // calendario dado
    calendario.add(Calendar.DATE, -1);

    // getTime() devuelve un objeto de fecha que representa el valor de tiempo del
    // calendario
    return (formatoFecha.format(calendario.getTime()));

  }

  public static int diasHastaHoy(String f) throws ParseException {

    Calendar calendario1 = Calendar.getInstance();

    // Calendar.MONTH +1 es debido a que enero empieza en el índice 0
    String fecha = calendario1.get(Calendar.DATE) + "/" + (calendario1.get(Calendar.MONTH) + 1) + "/"
        + calendario1.get(Calendar.YEAR);

    // La clase ParseException señala que se ha alcanzado un error inesperado
    // durante el análisis.
    // parse() analiza el texto de una cadena para producir una fecha.
    Date fechaHoy = formatoFecha.parse(fecha);
    Date fechaTextField = formatoFecha.parse(f);

    // getTime() devuelve un objeto de fecha que representa el valor de tiempo del
    // calendario
    long fHoy = fechaHoy.getTime();
    long fTextField = fechaTextField.getTime();

    // si la fecha introducida acontece antes que la fecha de hoy, a los días de hoy
    // restamos los de la fecha introducida. Y si no (la fecha de hoy es posterior a
    // la introducida), a ls días de la fecha introducida se les resta los de hoy
    if (fechaTextField.before(fechaHoy)) {
      return (int) (((fHoy - fTextField) / (3600 * 24 * 1000)));
    } else
      return (int) (((fTextField - fHoy) / (3600 * 24 * 1000)));
  }
}
