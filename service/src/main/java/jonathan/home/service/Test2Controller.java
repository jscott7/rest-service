package jonathan.home.service;

import jonathan.home.api.Test2Api;
import jonathan.home.model.SimpleResult;
import jonathan.home.model.Test2PostRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test2Controller implements Test2Api {
    @Override
    public ResponseEntity<SimpleResult> test2Post(Test2PostRequest test2PostRequest) {
        SimpleResult result = new SimpleResult();
        result.setValueKey("Test");
        result.setData("Data");
        return ResponseEntity.ok(result);
    }
}