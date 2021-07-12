package avaliacaoMapas.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import avaliacaoMapas.model.AppAvMapas;
import avaliacaoMapas.model.AvMapasLogicaIF;
import avaliacaoMapas.persistencia.CorDAOSQL;
import avaliacaoMapas.persistencia.ImagemDAOFile;

public class AvaliacaoMapasConsole {
	private AvMapasLogicaIF logica;
	private BufferedReader in;
	
	public AvaliacaoMapasConsole() throws Exception {
		this.logica = new AppAvMapas();
		this.in = new BufferedReader(new InputStreamReader(System.in));
	}	
	
	public void run() throws Exception{
		char option;
		do {
			System.out.println("****************************************");
			System.out.println("*****Sistema de Avaliacao  de Mapas*****");
			System.out.println("Relacao de simbolos:");
			System.out.println("[1] - Analisar Imagem");
			System.out.println("[0] - Sair");
			System.out.println("==>");
			option = in.readLine().charAt(0);
			switch(option) {
				case '1' : this.analisarImagem(); break;				
			}
		}while(option != '0');
		System.out.print("\nO sistema foi encerrado!");
	}

	private void analisarImagem() throws Exception {
		CorDAOSQL aux = new CorDAOSQL();
		String uri;  
		String simbolo;
		
		System.out.println("****************************************");
		System.out.print("Informe o caminho do arquivo: \n");
		uri = this.in.readLine();
		
		System.out.print("Digite um dos simbolos abaixo: \n");		
		System.out.println(aux.listarSimbolos());	
		simbolo = this.in.readLine();
		
		try {
			
			System.out.println("Analisando a imagem...");
			String[] result = this.logica.analisarImagens(uri, simbolo);
			for(int i = 0; i < result.length; i++) {
				System.out.println(result[i]);
			}
			
		} catch (Exception e) {
			System.out.println("Erro no caminho do arquivo ou no simbolo: " + e.getMessage());
		}			
	}
	
	public static void main(String[] args) throws Exception {
		(new AvaliacaoMapasConsole()).run();
	}
}
