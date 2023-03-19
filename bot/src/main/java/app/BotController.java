package app;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class BotController {
    @RequestMapping("/")
    String hello() {
        return "<html>Arsi</html>";
    }
}
