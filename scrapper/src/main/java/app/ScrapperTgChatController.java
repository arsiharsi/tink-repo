package app;

import configuration.JdbcAccessConfiguration;
import dbController.DataBaseJDBCChatController;
import dbController.DataBaseJDBCLinkController;
import dto_classes.ApiErrorResponse;
import entity.Chat;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;

@RequestMapping("/tg-chat")
@RestController
public class ScrapperTgChatController {
    DataSource dataSource = new JdbcAccessConfiguration().dataSource();
    JdbcTemplate template = new JdbcTemplate(dataSource);
    DataBaseJDBCChatController dataBaseJDBCChatController = new DataBaseJDBCChatController();
    @ApiResponse(responseCode = "200", description = "Чат зарегистрирован")
    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")
    @ApiResponse(responseCode = "404", description = "Ссылка не найдена")
    @PostMapping("/{id}")
    String registrateChat(@PathVariable long id){
        dataBaseJDBCChatController.addChat(id, template);
        return "Chat Id: " + id;
    }
    @DeleteMapping("/{id}")
    String deleteChat(@PathVariable long id){
        dataBaseJDBCChatController.deleteChat(id, template);
        return "Chat Id: " + id;
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
