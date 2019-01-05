package ua.hpopov.parking.datasource;

public class Strings {
	public static String concat(String...strings) {
		StringBuilder sb = new StringBuilder();
		for(String s: strings) {
			sb.append(s);
		}
		return sb.toString();
	}
}
