# Тренировочный модуль   
для отработки межаппликационного взаимодействия по REST   

## Что дополнительно сделано
Реализовано логирование с вставкой значения заголовка x-trace-id посредством MDC в консоль и в файл лога.  
Реализован автотест модуля RmaTrainingRestAppTests

## Дополнение
Для отправки REST запроса использовать Git Bash   
curl -X POST -H "Content-Type: application/json" -d '{"name": "lallora", "email": "lallora@mail.ru"}' http://localhost:8090/createGreeting   
curl -X POST -H "Content-Type: application/json" -H "x-trace-id: myTraceId" -H "Connection: close" -v --data  '{"name": "lallora", "email": "lallora@mail.ru"}' http://localhost:8090/createGreeting