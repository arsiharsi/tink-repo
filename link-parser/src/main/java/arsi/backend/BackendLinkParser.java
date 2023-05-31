package arsi.backend;

import dto_classes.LinkData;
import reactor.core.publisher.Mono;

import java.net.URISyntaxException;

public record BackendLinkParser(String URL) {
    public LinkData getData() throws URISyntaxException {
        if (!((URL.contains("https://github.com/") && countSymbols(URL,"/")>=4) || URL.contains("https://stackoverflow.com/questions/"))){
            return new LinkData("null", null);
        }
        else {
            Links[] links = {new StackOverflowURL(), new GitHubURL()};
            int link_type = URL.contains("https://github.com/") ? 1:0;
            return new LinkData(links[link_type].getData(URL), links[link_type].getAllLinks(URL));
        }
    }

    private int countSymbols(String string, String substring){
        int i = string.length() - string.replace(substring, "").length();
        return i;
    }
}
