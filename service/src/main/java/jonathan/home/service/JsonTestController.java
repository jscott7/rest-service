package jonathan.home.service;

import jonathan.home.api.JsonTestApi;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JsonTestController implements JsonTestApi {

    public JsonTestController(){
    }

    @Override
    public ResponseEntity<Object> jsonTestGet(){
        var body = createListOfDataType();
        return ResponseEntity.ok(body);
    }

    /**
     * Creates a list of {@link DataType}
     * @return Fixed list, created in function implementation
     */
    public List<DataType> createListOfDataType(){
        List<DataType> body = new ArrayList<>();
        var dataType1 = new DataType("A", "B", 1);
        var dataType2 = new DataType("B", "C", 1);
        body.add(dataType1);
        body.add(dataType2);
        return body;
    }
}
