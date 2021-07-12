package avaliacaoMapas.teste;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CorConnect {

	public static void main(String[] args) throws SQLException {
		System.out.println("Iniciando...");
		DriverManager.registerDriver(new org.postgresql.Driver());
		System.out.println("Registrado...");
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cores", "postgres", "ra1984"); 
		System.out.println("Conectado...");
		Statement stmt = conn.createStatement();
		ResultSet rSet = stmt.executeQuery("SELECT DISTINCT simbolo FROM CORES");
		System.out.println("EXECUTOU CONSULTA...");
		while(rSet.next()) {
			//String id = rSet.getString("id");
			//String sNome = rSet.getString("nome");
			String simbolo = rSet.getString("simbolo");		
			//int red = rSet.getInt("red");
			//int green = rSet.getInt("green");
			//int blue = rSet.getInt("blue");
			//int cyan = rSet.getInt("cyan");
			//int magenta = rSet.getInt("magenta");
			//int yellow = rSet.getInt("yellow");
			//int black = rSet.getInt("black");
			//int tipo = rSet.getInt("tipo");
			//System.out.println(id + ": " + sNome + ": " + simbolo + ": " + red + ": " + green + ": " + blue + ": " + cyan + ": " + magenta + ": " + yellow + ": " + black + ": " + tipo);
			System.out.println(simbolo);
		}
		rSet.close();
		stmt.close();
		conn.close();		
	}	
}
