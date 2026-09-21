package jonathan.home.service;

import jonathan.home.api.TestApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TestController implements TestApi {
    @Override
    public ResponseEntity<List<String>> testGet() {
        return ResponseEntity.ok(Arrays.asList("A", "B", "C"));
    }

}
