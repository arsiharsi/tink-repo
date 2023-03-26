package app;
import dto_classes.DataClass;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.codec.StringDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api_testing", method = RequestMethod.GET)
@RestController
public class BotController {


    @GetMapping("/show/{id}")
    public DataClass findById(@PathVariable int id) {
        DataClass dataClass = new DataClass(id, "Common URL","Description",new int[1]);
        return dataClass;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public DataClass addById(@RequestBody DataClass dataClass) {
        return dataClass;
    }

    @PutMapping(value = "/{id}", consumes = "application/json",produces = "application/json")
    public DataClass setById(@RequestBody DataClass dataClass) {
//        DataClass dClass = new DataClass(dataClass.getId(), dataClass.getUrl(), dataClass.getDescription(), dataClass.getTgChatIds());
        return dataClass;
    }


}
