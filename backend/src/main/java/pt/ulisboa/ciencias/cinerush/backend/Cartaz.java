package pt.ulisboa.ciencias.cinerush.backend;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/*
* Classe para fazer os pedidos a Sercultur, relativamente aos Filmes em Cartaz e estreias ate 5 semanas (valor indicado por nos)
* Gera ficheiros .Json que sao injectados no firebase
*/

public class Cartaz {
	
	
	static Map<Integer, Cinema> listaCinemas = new HashMap<>();
	static Map<String, Map<String, List<Integer>>> listaCinemasPorLocal = new HashMap<>();
	
	static Map<Integer, Interprete> listaInterpretes = new HashMap<>();
	
	static Map<Integer, Filme> listaFilmes = new HashMap<>();
	static Map<Integer, Map<Integer, Sessao>> listaSessoesPorFilmes = new HashMap<>();
	
	static Map<Integer, Estreia> listaEstreias = new HashMap<>();




	
	public static void getSessoesFromXML(String XML) {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(XML)));

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getFirstChild().getChildNodes();

			
		
			for (int i_nList = 0; i_nList < nList.getLength(); i_nList++) {

				Node node = nList.item(i_nList);
				

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					int numeroFilme = Integer.parseInt(element.getElementsByTagName("Numero").item(0).getTextContent());

					Filme filme = new Filme(numeroFilme, element);
					
					

					NodeList interpreteList = element.getLastChild().getPreviousSibling().getChildNodes();
					
					
					for (int i_Interprete = 0; i_Interprete < interpreteList.getLength(); i_Interprete++) {
						Node nInterprete = interpreteList.item(i_Interprete);
						if (nInterprete.getNodeType() == Node.ELEMENT_NODE && nInterprete.getNodeName() == "Row" &&  nInterprete.getParentNode().getNodeName() == "Interpretes") {
							Element eInterprete = (Element) nInterprete;
							
							int numeroInterprete = Integer.parseInt(eInterprete.getElementsByTagName("NumeroInterprete").item(0).getTextContent());
							Interprete interprete;
							if (listaInterpretes.containsKey(numeroInterprete)) {
								interprete = listaInterpretes.get(numeroInterprete);
							} else {
								interprete = new Interprete(numeroInterprete, eInterprete);
								listaInterpretes.put(numeroInterprete, interprete);
							}
							filme.addInterpretes(interprete);
						}
						
					}
					

					
					NodeList sessaoList = element.getLastChild().getPreviousSibling().getPreviousSibling().getPreviousSibling().getChildNodes();
					Map<Integer, Sessao> listaSessoes = new HashMap<>();

					for (int i_Sessao = 0; i_Sessao < sessaoList.getLength(); i_Sessao++) {						
						Node nSessao = sessaoList.item(i_Sessao);
						if (nSessao.getNodeType() == Node.ELEMENT_NODE && nSessao.getNodeName() == "Row" &&  nSessao.getParentNode().getNodeName() == "EmExibicao") {
							Element eSessao = (Element) nSessao;
							
							int numeroCinema = Integer.parseInt(eSessao.getElementsByTagName("NumeroCinema").item(0).getTextContent());
							Cinema cinema;
							if (listaCinemas.containsKey(numeroCinema)) {
								cinema = listaCinemas.get(numeroCinema);
							} else {
								cinema = new Cinema(numeroCinema, eSessao);
								listaCinemas.put(numeroCinema, cinema);
							}


							Sessao sessao = new Sessao(eSessao, filme, cinema);
							listaSessoes.put(sessao.getNumeroSessao(), sessao);
							
						}

						
					}
					listaFilmes.put(numeroFilme, filme);
					listaSessoesPorFilmes.put(numeroFilme, listaSessoes);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void getFilmesCartaz() {
		Client client = Client.create();
		WebResource resource = client.resource("http://ws.sercultur.pt/ws.asmx/CartazCinemaFilmesExt"); 
		MultivaluedMap queryParams = new MultivaluedMapImpl();
		queryParams.add("Pais", "");
		queryParams.add("Genero", "");
		queryParams.add("Classificacao", "");
		queryParams.add("Distrito", "");
		queryParams.add("Concelho", "");
		queryParams.add("CicloFestival", "");
		queryParams.add("DiasProgramacao", "");
		queryParams.add("Destaques", "");
		queryParams.add("Opcionais", "");
		queryParams.add("EspacoAcademico", "");
		
		ClientResponse response = resource.post(ClientResponse.class, queryParams);	
		
		String xmlToParse = response.getEntity(String.class);
		getSessoesFromXML(xmlToParse);
	}
	
	private static void getEstreiasFromXML(String XML) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(XML)));

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getFirstChild().getChildNodes();
			
			for (int i_nList = 0; i_nList < nList.getLength(); i_nList++) {

				Node node = nList.item(i_nList);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					int numeroEstreia = Integer.parseInt(element.getElementsByTagName("Numero").item(0).getTextContent());

					Estreia estreia = new Estreia(numeroEstreia, element);
					
					
					
					NodeList interpreteList = element.getLastChild().getPreviousSibling().getChildNodes();
					
					for (int i_Interprete = 0; i_Interprete < interpreteList.getLength(); i_Interprete++) {
						Node nInterprete = interpreteList.item(i_Interprete);
						if (nInterprete.getNodeType() == Node.ELEMENT_NODE && nInterprete.getNodeName() == "Row" &&  nInterprete.getParentNode().getNodeName() == "Interpretes") {
							Element eInterprete = (Element) nInterprete;
							
							int numeroInterprete = Integer.parseInt(element.getElementsByTagName("NumeroInterprete").item(0).getTextContent());
							Interprete interprete;
							if (listaInterpretes.containsKey(numeroInterprete)) {
								interprete = listaInterpretes.get(numeroInterprete);
							} else {
								interprete = new Interprete(numeroInterprete, eInterprete);
								listaInterpretes.put(numeroInterprete, interprete);
							}
							estreia.addInterpretes(interprete);
						}
						
					}
					
					
					listaEstreias.put(numeroEstreia, estreia);				

				
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	
	public static void getEstreiasCartaz(int numeroSemanas) {
		Client client = Client.create();
		WebResource resource = client.resource("http://ws.sercultur.pt/ws.asmx/EstreiasCinema"); 
		MultivaluedMap queryParams = new MultivaluedMapImpl();
		queryParams.add("Semana", "");
		queryParams.add("NumeroSemanas", String.valueOf(numeroSemanas));
		
		
		ClientResponse response = resource.post(ClientResponse.class, queryParams);

		
		
		String xmlToParse = response.getEntity(String.class);
		getEstreiasFromXML(xmlToParse);
	}
	
	public static void setCinemasPorLocal(){
		Map<String,Map<String, List<Integer>>> listaCinemasPorDistrito = new HashMap<>();
		
		for (Cinema cinema : listaCinemas.values()) {
			Integer cinemaID = cinema.getNumeroCinema();
			String distrito = cinema.getDistrito();
			String concelho = cinema.getConcelho();
			
			Map<String, List<Integer>> listaCinemasPorConcelho = new HashMap<>();
			List<Integer> cinemasExistentesNoConcelho = new ArrayList<>();
			
			listaCinemasPorConcelho = listaCinemasPorDistrito.get(distrito);
			if (listaCinemasPorConcelho == null) {
				listaCinemasPorConcelho = new HashMap<>();
			}

			cinemasExistentesNoConcelho = listaCinemasPorConcelho.get(concelho);
			
			if (cinemasExistentesNoConcelho == null) {
				cinemasExistentesNoConcelho = new ArrayList<>();
			}
			cinemasExistentesNoConcelho.add(cinemaID);
			listaCinemasPorConcelho.put(concelho, cinemasExistentesNoConcelho);
			
			
			listaCinemasPorDistrito.put(distrito, listaCinemasPorConcelho);
			
		}
		
		listaCinemasPorLocal = listaCinemasPorDistrito;
		
	}
	
	

	public static void main(String[] args) throws IOException {
		
		getFilmesCartaz();
		getEstreiasCartaz(5);
		setCinemasPorLocal();
		
		Gson gson = new GsonBuilder().create();
		BufferedWriter listaCinemasFile = null;
		BufferedWriter listaCinemasPorLocalFile = null;
		BufferedWriter listaSessoesPorFilmesFile = null;
		BufferedWriter listaFilmesFile = null;
		BufferedWriter listaInterpretesFile = null;
		BufferedWriter listaEstreiasFile = null;


		
		try {
			listaCinemasFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./Json/listaCinemas.json"), "UTF-8"));
			listaCinemasPorLocalFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./Json/listaCinemasPorLocal.json"), "UTF-8"));
			listaSessoesPorFilmesFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./Json/listaSessoesPorFilmes.json"), "UTF-8"));
			listaFilmesFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./Json/listaFilmes.json"), "UTF-8"));
			listaInterpretesFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./Json/listaInterpretes.json"), "UTF-8"));
			listaEstreiasFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./Json/listaEstreias.json"), "UTF-8"));

			gson.toJson(listaCinemas, listaCinemasFile);
			gson.toJson(listaCinemasPorLocal, listaCinemasPorLocalFile);
			gson.toJson(listaSessoesPorFilmes, listaSessoesPorFilmesFile);
			gson.toJson(listaFilmes, listaFilmesFile);
			gson.toJson(listaInterpretes, listaInterpretesFile);
			gson.toJson(listaEstreias, listaEstreiasFile);
			

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			listaCinemasFile.close();
			listaCinemasPorLocalFile.close();
			listaSessoesPorFilmesFile.close();
			listaFilmesFile.close();
			listaInterpretesFile.close();
			listaEstreiasFile.close();


		}
		
			
		
	}

}
