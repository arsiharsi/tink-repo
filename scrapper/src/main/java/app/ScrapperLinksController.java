package app;

import configuration.JdbcAccessConfiguration;
import dbController.DataBaseJDBCChatController;
import dbController.DataBaseJDBCLinkController;
import dto_classes.*;
import entity.Link;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("/links")

@RestController
public class ScrapperLinksController {
    DataSource dataSource = new JdbcAccessConfiguration().dataSource();
    JdbcTemplate template = new JdbcTemplate(dataSource);
    DataBaseJDBCLinkController dataBaseJDBCLinkController = new DataBaseJDBCLinkController();
    @ApiResponse(responseCode = "200", description = "Чат зарегистрирован")
    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")
    @ApiResponse(responseCode = "404", description = "Ссылка не найдена")
    @GetMapping("/{chat_id}")
    List<Link> getLinks(@PathVariable long chat_id){
        return dataBaseJDBCLinkController.getCertainLinks(template,chat_id);
    }
    @PostMapping("/{chat_id}/{url}")
    AddLinkRequest addLinkRequest(@PathVariable int chat_id, @PathVariable String url) throws URISyntaxException {
        dataBaseJDBCLinkController.addLink(chat_id,template,url);
        return new AddLinkRequest(new URI(url));
    }
    @DeleteMapping("/{chat_id}/{url}")
    RemoveLinkRequest deleteLink(@PathVariable int chat_id, @PathVariable String url) throws URISyntaxException {
        dataBaseJDBCLinkController.deleteLink(chat_id, url, template);
        return new RemoveLinkRequest(new URI(url));
    }
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleException(MethodArgumentNotValidException e){
        return new ApiErrorResponse("Некорректные параметры запроса",
                e.getStatusCode().toString(),
                e.getObjectName(),
                e.getLocalizedMessage(),
                new String[]{String.valueOf(e.getStackTrace())});
    }
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse linkException(MethodArgumentNotValidException e){
        return new ApiErrorResponse("Ссылка не найдена",
                e.getStatusCode().toString(),
                e.getObjectName(),
                e.getLocalizedMessage(),
                new String[]{String.valueOf(e.getStackTrace())});
    }

}
