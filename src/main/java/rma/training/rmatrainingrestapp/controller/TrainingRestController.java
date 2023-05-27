package rma.training.rmatrainingrestapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import static rma.training.rmatrainingrestapp.config.AppConstants.ENDPOINT_GET_TEST;
import static rma.training.rmatrainingrestapp.config.AppConstants.MSG_GREETINGS;


@Slf4j
@RestController
public class TrainingRestController {

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return MSG_GREETINGS;
    }

    @GetMapping(value = ENDPOINT_GET_TEST)
    public ResponseEntity<String> test() {
        log.info("{} started", Thread.currentThread().getStackTrace()[1].getMethodName());
        return new ResponseEntity<>("test Ok", HttpStatus.OK);
    }

    @PostMapping(value = "/createGreeting")
    public ResponseEntity<String> createGreeting(HttpServletRequest httpServletRequest, @RequestBody String json) {
        // Получение карты с заголовками запроса
        Map<String, String> requestHeadersMap = Collections.list(httpServletRequest.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(h -> h, httpServletRequest :: getHeader));
        // Получение значения заголовка по его ключу
        String strXTraceId = requestHeadersMap.get("x-trace-id");
        // Пример использования Mapping Diagnostic Context
        MDC.put("TraceIdInMdcKey", strXTraceId);
        log.info("Server got traceId: [{}] body: {}", MDC.get("TraceIdInMdcKey"), json);
        MDC.clear();
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }
}
