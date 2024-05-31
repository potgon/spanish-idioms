package dev.potgon.spanishIdioms.api.gpt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class GPTLoggerService {

    private static final Logger logger = LoggerFactory.getLogger(GPTLoggerService.class);

    public void logRequest(String prompt) {
        logger.info("Sending request to OpenAI with prompt: {}", prompt);
    }

    public void logResponse(String response) {
        logger.info("Receiving response from OpenAI: {}", response);
    }

    public void logError(Exception e) {
        logger.error("Error occurred during OpenAI interaction: {}", e.getMessage());
    }

}
