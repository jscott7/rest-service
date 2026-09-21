package jonathan.home.service;

import com.github.victools.jsonschema.generator.*;
import com.google.common.reflect.ClassPath;
import jonathan.home.api.ActionsApi;
import jonathan.home.model.Action;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ActionsController implements ActionsApi {

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ActionsApi.super.getRequest();
    }

    @Override
    public ResponseEntity<List<String>> actionsGet() {
        List<String> actions = new ArrayList<>();
        try {
            Set<Class<?>> classes = findAllClassesUsingGoogleGuice( "jonathan.home.model");
            for (Class<?> clazz : classes)
            {
                if (Action.class.isAssignableFrom(clazz)) {
                    actions.add(clazz.getName());
                }
            }
        }
        catch(Exception ex){
            actions.add(ex.getMessage());
        }
        return ResponseEntity.ok(actions);
    }

    @Override
    public ResponseEntity<String> actionsIdGet(String id) {
        String response = "";
        try {

            Class<?> clazz = Class.forName(id);
            var configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12, OptionPreset.PLAIN_JSON);
            var config = configBuilder.build();
            var generator = new SchemaGenerator(config);
            var jsonSchema = generator.generateSchema(clazz);
            response = jsonSchema.toPrettyString();

        }
        catch(Exception ex)
        {
            response = ex.getMessage();
        }
        return ResponseEntity.ok(response);
    }

    public Set<Class<?>> findAllClassesUsingGoogleGuice(String packageName) throws IOException {
        return ClassPath.from(ClassLoader.getSystemClassLoader())
                .getAllClasses()
                .stream()
                .filter(clazz -> clazz.getPackageName()
                        .equalsIgnoreCase(packageName))
                .map(ClassPath.ClassInfo::load)
                .collect(Collectors.toSet());
    }
}
