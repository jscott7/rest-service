package jonathan.home.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.JsonNode;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name="StreamndPost", description = "Stream ND Json input")
public class StreamndJsonController  {

    private static final Logger log = LoggerFactory.getLogger(StreamndJsonController.class);
    private final ObjectMapper objectMapper;

    public StreamndJsonController(){
        objectMapper = new ObjectMapper();
       // PrettyPrinter pp;
    }
    @Operation(
        operationId = "streamndJsonPost",
        responses =  {
                @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/streamndJson",
        produces = "application/json",
        consumes = "application/x-ndjson"
    )
    public ResponseEntity<Object> streamndJsonPost(InputStream body) throws IOException {
        var bif = new BufferedInputStream(body, 64 * 1024);
        int b;
        List<JsonNode> batch = new ArrayList<>(1000);
        long line = 0, ok = 0, failed = 0;
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        while(true){
            b = bif.read();
            if (b == -1 || b == '\n'){
                line++;
                if (buf.size() > 0){
                    try{
                        log.info(objectMapper.readTree(buf.toByteArray()).toPrettyString());
                    } catch(JsonProcessingException e){
                        log.error(e.getMessage());
                    }
                }
                buf.reset();
                if (b == -1) break;
            } else {
                buf.write(b);
            }
        }

        return ResponseEntity.ok("{\"Success\":\"true\"}");
    }


}
