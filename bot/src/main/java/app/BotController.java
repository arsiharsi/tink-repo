package app;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController

public class BotController {
    @RequestMapping("/api_testing")
    String hello() {
        return "<html>Bot</html>";
    }
    @GetMapping("/api_testing/{id}")
    public String findById(@PathVariable long id) {
        return id+"";
    }


    @PutMapping("/api_testing/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateBook(
            @PathVariable("id") final String id, @RequestBody final String ID) {
        return ID;
    }


}
