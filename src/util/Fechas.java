package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fechas {

	private static final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	private static final SimpleDateFormat oracleFormatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// metodo para coger la fecha
	public static String dateToString(Date date) {
		return formato.format(date);
	}

	// metodo de conversion de string a date
	public static java.util.Date stringToDate(String fecha) {
		Date FechaE = null;
		try {
			FechaE = formato.parse(fecha);
			return FechaE;
		} catch (ParseException pe) {
			return null;
		}
	}
	
	public static String toOracleFormat(Date d) {
		return oracleFormatter.format(d);
	}
}
