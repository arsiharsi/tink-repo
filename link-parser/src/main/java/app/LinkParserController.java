package app;

import arsi.backend.BackendLinkParser;
import dto_classes.IncomingLink;
import dto_classes.LinkData;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController

public class LinkParserController {
    @GetMapping("/")
    @ResponseBody
    String hello() {
        return "<html>Go to http://localhost:8080/url:URL with correct URL so it starts working</html>";
    }

    @GetMapping("/url:{url}")
    @ResponseBody
    public LinkData getUrl(@PathVariable String url) throws URISyntaxException {
        BackendLinkParser linkParser = new BackendLinkParser(url);
        return linkParser.getData();
    }
    @PostMapping("/url")
    public LinkData getUrl(@RequestBody IncomingLink url) throws URISyntaxException {
        BackendLinkParser linkParser = new BackendLinkParser(url.url());
        return linkParser.getData();
    }
}
