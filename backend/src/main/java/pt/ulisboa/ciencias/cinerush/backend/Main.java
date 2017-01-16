package pt.ulisboa.ciencias.cinerush.backend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sun.jersey.api.client.*;
import com.sun.jersey.core.util.MultivaluedMapImpl;


import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream("./backend/src/main/webapp/WEB-INF/CineRush-2b403b949f3b.json"))
                .setDatabaseUrl("https://fir-cinerush.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReferenceFromUrl("https://fir-cinerush.firebaseio.com/Cartaz");


    /*
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

        //System.out.println(response.getEntity(String.class));


        String xmlToParse = response.getEntity(String.class);

        System.out.println(xmlToParse);

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder  builder = factory.newDocumentBuilder();
            Document doc = builder.parse( new InputSource( new StringReader( xmlToParse ) ));

            // optional, but recommended
            doc.getDocumentElement().normalize();

            // Vamos imprimir o nome do nó raiz do XML => Cinema
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            // Vamos agora obter os nós Row que tem efetivamente informaçao sobre o cinema
            NodeList nList = doc.getFirstChild().getChildNodes();


            // Para poderes aceder aos nós que criaste anteriormente tens de aceder aos nós que estão na NodeList
            for (int index = 0; index < nList.getLength(); index++) {

                // Obtens o próximo nó da lista
                Node nNode = nList.item(index);

                // Impriimes o nome do nó que tens. Neste momento, tens um nó Row
                System.out.println("\nCurrent Element :" + nNode.getNodeName() + " at index: " + index);

                // Existem vários tipos de nó. Estás a garantir que é um Elemento e que tem outros Elementos lá dentro.
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    // getElementsByTagName, devolve-te a lista de elementos com a Tag "Numero" dentro do elemento Row.
                    //Aquilo devolve sempre uma lista, como só tens um, garantidamente o elemento que queres aceder esta
                    // na posiçao 0 e portanto fazes item(0)
                    String numero = eElement.getElementsByTagName("Numero").item(0).getTextContent();
                    String creationDate = eElement.getElementsByTagName("DataCriacao").item(0).getTextContent();
                    System.out.println("Numero id : " + numero );
                    System.out.println("DataCriacao : " + creationDate);

                    // Senao quiseres estar a alocar string para guardar o conteudo dos elementos podes simplesmente invocar diretamente (:
                    System.out.println("DataEdicao : " + eElement.getElementsByTagName("DataEdicao").item(0).getTextContent());
                    System.out.println("Filme : " + eElement.getElementsByTagName("Filme").item(0).getTextContent());
                    System.out.println("TituloOriginal : " + eElement.getElementsByTagName("TituloOriginal").item(0).getTextContent());

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        } */
    }

}