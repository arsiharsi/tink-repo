package app;
import dto_classes.ApiErrorResponse;
import dto_classes.DataClass;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.codec.StringDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/updates")
@RestController
public class BotController {

    @ApiResponse(responseCode = "200", description = "Обработано")
    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")


    @PostMapping(consumes = "application/json", produces = "application/json")
    public DataClass update(@Valid @RequestBody DataClass dataClass) {
        return new DataClass(dataClass.getId(), dataClass.getUrl(), dataClass.getDescription(), dataClass.getTgChatIds());
    }


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    //@ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleException(MethodArgumentNotValidException e){
        return new ApiErrorResponse("Некорректные параметры запроса",
                e.getStatusCode().toString(),
                e.getObjectName(),
                e.getLocalizedMessage(),
                new String[]{String.valueOf(e.getStackTrace())});
    }

}
