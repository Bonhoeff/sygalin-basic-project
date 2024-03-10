package {{.PackageName}}.configurations;

import {{.PackageName}}.globalmodels.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import java.io.IOException;
import java.util.Objects;

@ControllerAdvice
@AllArgsConstructor
public class AppExceptionHandler {
    private  Environment env;
    private static final Logger logger = LoggerFactory.getLogger(AppException.class);
    @ExceptionHandler(value = {AppException.class})
    @ResponseBody
    public ResponseMessage appExceptionRestResponseHandle(AppException e, HttpServletResponse response){
        response.setStatus(e.getStatus());
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(e.getStatus());
        responseMessage.setCode(e.getStatus());
        responseMessage.setMessage(e.getMessage());
        responseMessage.setError(e.getLocalizedMessage());
        String appEnvironment = env.getProperty("application.environment");
        if (Objects.equals(appEnvironment, "DEV")){
            logger.error(e.getMessage());
           // e.printStackTrace();
        }
        return responseMessage;
    }

    @ExceptionHandler(value = {MultipartException.class, IOException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage handleIOException(HttpServletRequest request){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setCode(HttpStatus.BAD_REQUEST.value());
        responseMessage.setMessage("You have Bad Request from "+request.getRequestURI());
        responseMessage.setError("");
        return responseMessage;
    }

    @ExceptionHandler(value = {JDBCException.class})
    public String jdbcHandleException(Exception e){

        return e.getLocalizedMessage();
    }
}
