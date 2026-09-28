package jonathan.home.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jonathan.home.api.TestStreamApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class TestStreamController implements TestStreamApi {
    private static final Logger log = LoggerFactory.getLogger(StreamndJsonController.class);
    private final ObjectMapper objectMapper;

    public TestStreamController(){
        objectMapper = new ObjectMapper();
    }

    /**
     * Initial implementation taken from StreamndJsonController
     * @param body  (required)
     * @return Response JSON indicating success or failure
     */
    @Override
    public ResponseEntity<Object> testStreamNdJsonPost(InputStream body) {
        // Currently failing because client sends Content-Type 'application/x-ndjson;charset=UTF-8'
        try {
            var bif = new BufferedInputStream(body, 64 * 1024);
            int b;
            long line = 0;
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            while (true) {
                b = bif.read();
                if (b == -1 || b == '\n') {
                    line++;
                    if (buf.size() > 0) {
                        try {
                            log.info(objectMapper.readTree(buf.toByteArray()).toPrettyString());
                        } catch (JsonProcessingException e) {
                            log.error(e.getMessage());
                        }
                    }
                    buf.reset();
                    if (b == -1) break;
                } else {
                    buf.write(b);
                }

                log.info("Read {} lines", line);
            }

            return ResponseEntity.ok("{\"Success\":\"true\"}");
        }
        catch(IOException e){
            log.error(e.getMessage());
            return ResponseEntity.ok("{\"Success\":\"false\"}");
        }
    }
}
