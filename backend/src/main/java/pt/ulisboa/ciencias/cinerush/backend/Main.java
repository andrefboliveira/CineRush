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

/*
* Classe para guardar os dados no firebase. Iria ser semelhante a classe fartaz mas guardaria directamente no FirebaseDatabase.
* Ao aceder FirebaseDatabase da null pointer exception
*/
public class Main {

    public static void main(String[] args) throws FileNotFoundException {


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream("./backend/src/main/webapp/WEB-INF/CineRush-2b403b949f3b.json"))
                .setDatabaseUrl("https://fir-cinerush.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReferenceFromUrl("https://fir-cinerush.firebaseio.com/Cartaz");


    
    }

}