/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Lexeme;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Daryl Ospina
 */
public class LexemeController {

    public ArrayList<Lexeme> lexemes;

    public LexemeController() {
        this.lexemes = new ArrayList<>();
    }

    public DefaultTableModel updateLexemes() throws IOException, InterruptedException, ParseException, Exception {
        this.lexemes = new ArrayList<>();
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Token");
        model.addColumn("Type");
        model.addColumn("Positions");

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/analizador_lexico/lexemes"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        JSONArray array = (JSONArray) (new JSONParser().parse(response.body()));

        Gson gson = new Gson();
        
        array.stream().map((obj) -> (JSONObject) obj).map((jsonLexeme) -> gson.fromJson(jsonLexeme.toString(), Lexeme.class)).forEachOrdered((lexeme) -> {
            this.lexemes.add((Lexeme) lexeme);
        });

        Logger.getLogger(LexemeController.class.getName()).log(Level.INFO, "Response body: {0}", this.lexemes.toString());
        
        this.lexemes.forEach((lexeme) -> {
            model.addRow(new Object[]{
                lexeme.getWord(),
                lexeme.getType(),
                "row: "+lexeme.getRow()+" - columns: "+lexeme.getColumn()
            });
        });

        return model;
    }
}
