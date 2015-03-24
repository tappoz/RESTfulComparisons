package org.tappoz.rest.remote.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonValidator {

    private final static Logger log = LoggerFactory.getLogger(JsonValidator.class);

    ObjectMapper objectMapper;

    @Inject
    public JsonValidator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public boolean isValidJSON(final String json) {

        try {

            final JsonParser parser = objectMapper.getFactory().createParser(json);
            int tokensNum = 0;
            while (parser.nextToken() != null) {
                tokensNum++;
            }

            log.debug("Just parsed {} tokens", tokensNum);
            return true;

        } catch (Exception e) {

            log.error("The JSON seems to be not valid! The {} message was: {}", e.getClass().getCanonicalName(), e.getMessage());
            return false;
        }
    }
}
