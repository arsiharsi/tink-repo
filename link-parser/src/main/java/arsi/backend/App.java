package arsi.backend;

import java.net.URISyntaxException;
import java.util.Scanner;

/**
 * Так же можно затестить в LinkParserApplication
 *
 */
public class App 
{
    public static void main( String[] args ) throws URISyntaxException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите ссылку: ");
        String URL = sc.nextLine();
        BackendLinkParser linkParser = new BackendLinkParser(URL);
        System.out.println(linkParser.getData().toString());
    }
}
