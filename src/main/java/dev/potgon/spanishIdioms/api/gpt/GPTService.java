package dev.potgon.spanishIdioms.api.gpt;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GPTService {

    private final ChatClient chatClient;
    private final GPTLoggerService loggerService;

    public String generate(String idiom, String guess) {
        String promptMessage = "Te voy a enviar una frase hecha en español y una suposición de lo que puede significar" +
                ", me gustaria que le dieras una nota, del 1 al 10, y que tu mensaje de respuesta sea solamente el numero de la " +
                "nota. Pon la nota en base a lo precisa que es la suposicion a su verdadero significado. " +
                "Por ejemplo, notas del 0 al 5 serian suposiciones que no tienen mucho que ver con la explicacion," +
                "mientras que notas del 5 al 10 son suposiciones que se van acercando gradualmente" +
                "al verdadero significado, siendo un 10 el significado perfectamente explicado" +
                ". Para la nota, usa un numero entero sin decimales. " +
                "Esta es la frase hecha: " + idiom +
                ". Y esta es la suposicion de su significado: " + guess;

        try {
            loggerService.logRequest(promptMessage);
            String response = chatClient.call(promptMessage);
            loggerService.logResponse(response);
            return response;
        } catch (Exception e) {
            loggerService.logError(e);
            throw e;
        }
    }
}
