package jonathan.home.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import jonathan.home.api.JsonTestApi;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JsonTestController implements JsonTestApi {

    private ObjectMapper objectMapper;

    public JsonTestController(){
        objectMapper = new ObjectMapper();
        PrettyPrinter pp;
    }

    @Override
    public ResponseEntity<Object> jsonTestGet(){
       // var body = createListBody();
        var body = createListBodyAsType();
        return ResponseEntity.ok(body);
    }

    public List<String> createListBody(){

        List<String> body = new ArrayList<>();
        var dataType1 = new DataType("A", "B", 1);
        var dataType2 = new DataType("B", "C", 1);

        try {
            body.add(objectMapper.writeValueAsString(dataType1));
            body.add(objectMapper.writeValueAsString(dataType2));
        }
        catch (JsonProcessingException ex){
            // do nothing
        }

        return body;
    }

    public List<DataType> createListBodyAsType(){
        List<DataType> body = new ArrayList<>();
        var dataType1 = new DataType("A", "B", 1);
        var dataType2 = new DataType("B", "C", 1);
        body.add(dataType1);
        body.add(dataType2);
        return body;
    }
}
