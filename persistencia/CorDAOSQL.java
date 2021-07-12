package avaliacaoMapas.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import avaliacaoMapas.model.entity.Cor;
import avaliacaoMapas.model.entity.CorRGB;
import avaliacaoMapas.model.entity.CorCMYK;
import avaliacaoMapas.model.TipoCor;
//import avaliacaoMapas.model.entity.ImagemRGB;
//import avaliacaoMapas.model.entity.ImagemCMYK;
import avaliacaoMapas.model.exception.CorInexistenteException;
import avaliacaoMapas.persistencia.CorDAOSQL;

public class CorDAOSQL implements CorDAOIF{

	private static final String URI = "jdbc:postgresql://localhost:5432/cores";
	private static final String USER = "postgres";
	private static final String PWD = "ra1984";
	
	private static final String COR_INSERT = "INSERT INTO CORES(id, nome, simbolo, red, green, blue, cyan, magenta, yellow, black, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String COR_UPDATE = "UPDATE CORES SET nome = ?, simbolo = ? WHERE id = ?";
	private static final String COR_SELECT_BYNAME = "SELECT id, nome, simbolo, red, green, blue, cyan, magenta, yellow, black, tipo FROM CORES WHERE nome = ?";
	private static final String COR_SELECT_ALL = "SELECT id, nome, simbolo, red, green, blue, cyan, magenta, yellow, black, tipo FROM CORES";
	private static final String COR_SELECT_BYSIMBOL = "SELECT id, nome, simbolo, red, green, blue, cyan, magenta, yellow, black, tipo FROM CORES WHERE simbolo = ?";
	
	public CorDAOSQL() throws SQLException {
		DriverManager.registerDriver(new org.postgresql.Driver());
	}	
	
	public Connection getConn() throws SQLException {
		return  DriverManager.getConnection(CorDAOSQL.URI, CorDAOSQL.USER, CorDAOSQL.PWD); 
	}
	
	@Override
	public void salvar(Cor c) throws Exception {
		PreparedStatement pStmt = this.getConn().prepareStatement(COR_INSERT);
		pStmt.setString(1, c.getId());
		pStmt.setString(2, c.getNome());
		pStmt.setString(3, c.getSimbolo());
		
		if(c instanceof CorRGB) {
			CorRGB rgb = (CorRGB)c;
			pStmt.setInt(4, rgb.getRed());
			pStmt.setInt(5, rgb.getGreen());
			pStmt.setInt(6, rgb.getBlue());
			pStmt.setInt(7, 0);
			pStmt.setInt(8, 0);
			pStmt.setInt(9, 0);
			pStmt.setInt(10, 0);
		}else if(c instanceof CorCMYK) {
			CorCMYK cmyk = (CorCMYK)c;
			pStmt.setInt(4, 0);
			pStmt.setInt(5, 0);
			pStmt.setInt(6, 0);
			pStmt.setInt(7, cmyk.getCyan());
			pStmt.setInt(8, cmyk.getMagenta());
			pStmt.setInt(9, cmyk.getYellow());
			pStmt.setInt(10, cmyk.getBlack());			
		}
		pStmt.setInt(11, this.getTipoCor(c));
		pStmt.executeUpdate();
	}

	private int getTipoCor(Cor c) {
		if(c instanceof CorRGB)
			return TipoCor.CorRGB.value();
		else if (c instanceof CorCMYK)
			return TipoCor.CorCMYK.value();
		else
			return -1;
	}

	@Override
	public void atualizar(Cor c) throws Exception {
		PreparedStatement pStmt = this.getConn().prepareStatement(COR_UPDATE);
		pStmt.setString(1, c.getId());
		pStmt.setString(2, c.getNome());
		pStmt.setString(3, c.getSimbolo());
		
		if(c instanceof CorRGB) {
			CorRGB rgb = (CorRGB)c;
			pStmt.setInt(4, rgb.getRed());
			pStmt.setInt(5, rgb.getGreen());
			pStmt.setInt(6, rgb.getBlue());
			pStmt.setInt(7, 0);
			pStmt.setInt(8, 0);
			pStmt.setInt(9, 0);
			pStmt.setInt(10, 0);
		}else if(c instanceof CorCMYK) {
			CorCMYK cmyk = (CorCMYK)c;
			pStmt.setInt(4, 0);
			pStmt.setInt(5, 0);
			pStmt.setInt(6, 0);
			pStmt.setInt(7, cmyk.getCyan());
			pStmt.setInt(8, cmyk.getMagenta());
			pStmt.setInt(9, cmyk.getYellow());
			pStmt.setInt(10, cmyk.getBlack());			
		}
		pStmt.executeUpdate();
		
	}

	@Override
	public Cor findByNome(String nome) throws Exception {
		Cor c = null;
		PreparedStatement pStmt = this.getConn().prepareStatement(COR_SELECT_BYNAME);
		pStmt.setString(1, nome);
		ResultSet rSet = pStmt.executeQuery();
		
		if(!rSet.next())
			throw new CorInexistenteException(nome);
		
		String id = rSet.getString("id");
		String sNome = rSet.getString("nome");
		String simbolo = rSet.getString("simbolo");		
		int red = rSet.getInt("red");
		int green = rSet.getInt("green");
		int blue = rSet.getInt("blue");
		int cyan = rSet.getInt("cyan");
		int magenta = rSet.getInt("magenta");
		int yellow = rSet.getInt("yellow");
		int black = rSet.getInt("black");
		int tipo = rSet.getInt("tipo");


		if(tipo == TipoCor.CorRGB.value()) {
			c = new CorRGB(id, sNome, simbolo, red, green, blue);
		}
		else if(tipo == TipoCor.CorCMYK.value()) {
			c = new CorCMYK(id, sNome, simbolo, cyan, magenta, yellow, black);
		}
		return c;
	}

	public Collection<Cor> findBySimbolo(String simbolo) throws Exception {
		Set<Cor> cores = new HashSet<Cor>();
		PreparedStatement pStmt = this.getConn().prepareStatement(COR_SELECT_BYSIMBOL);
		pStmt.setString(1, simbolo);
		ResultSet rSet = pStmt.executeQuery();
		while(rSet.next()) {
			Cor c = null;
			String id = rSet.getString("id");
			String sNome = rSet.getString("nome");
			String simbol = rSet.getString("simbolo");		
			int red = rSet.getInt("red");
			int green = rSet.getInt("green");
			int blue = rSet.getInt("blue");
			int cyan = rSet.getInt("cyan");
			int magenta = rSet.getInt("magenta");
			int yellow = rSet.getInt("yellow");
			int black = rSet.getInt("black");
			int tipo = rSet.getInt("tipo");
			if(tipo == TipoCor.CorRGB.value()) {
				c = new CorRGB(id, sNome, simbol, red, green, blue);
			}
			else if(tipo == TipoCor.CorCMYK.value()) {
				c = new CorCMYK(id, sNome, simbol, cyan, magenta, yellow, black);
			}
			cores.add(c);
		}
		return cores;
	}
	
	@Override
	public Collection<Cor> findAll() throws Exception {
		Set<Cor> cores = new HashSet<Cor>();
		PreparedStatement pStmt = this.getConn().prepareStatement(COR_SELECT_ALL);
		ResultSet rSet = pStmt.executeQuery();
		while(rSet.next()) {
			Cor c = null;
			String id = rSet.getString("id");
			String sNome = rSet.getString("nome");
			String simbolo = rSet.getString("simbolo");		
			int red = rSet.getInt("red");
			int green = rSet.getInt("green");
			int blue = rSet.getInt("blue");
			int cyan = rSet.getInt("cyan");
			int magenta = rSet.getInt("magenta");
			int yellow = rSet.getInt("yellow");
			int black = rSet.getInt("black");
			int tipo = rSet.getInt("tipo");
			if(tipo == TipoCor.CorRGB.value()) {
				c = new CorRGB(id, sNome, simbolo, red, green, blue);
			}
			else if(tipo == TipoCor.CorCMYK.value()) {
				c = new CorCMYK(id, sNome, simbolo, cyan, magenta, yellow, black);
			}
			cores.add(c);
		}
		return cores;
	}
	
	//@Override
	public Collection<String> listarSimbolos() throws Exception {
		Collection<String> corSimbol = new HashSet<String>();
		PreparedStatement pStmt = this.getConn().prepareStatement("SELECT DISTINCT simbolo FROM CORES");
		ResultSet rSet = pStmt.executeQuery();
		while(rSet.next()) {
			String simbolo = rSet.getString("simbolo");					
			corSimbol.add(simbolo);
		}
		return corSimbol;
	}

	public static void main(String[] args) throws Exception {
		//Cor c = new CorCMYK("B01", "PRETO", "ESGOTO", 0, 0, 0, 100);
		
		CorDAOSQL dao = new CorDAOSQL();
		//dao.salvar(c);
		//dao.findAll();
		//dao.findByNome("PRETO");
		
		System.out.println(dao.findBySimbolo("VEGETACAO"));
		//System.out.println(dao.findAll());
	}


}
