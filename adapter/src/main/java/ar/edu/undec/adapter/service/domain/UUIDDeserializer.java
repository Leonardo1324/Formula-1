package ar.edu.undec.adapter.service.domain;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.UUID;

public class UUIDDeserializer extends JsonDeserializer<UUID> {
    @Override
    public UUID deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String uuid = jsonParser.getText();
        if (uuid != null) {
            return UUID.randomUUID();
        }
        return null;
    }
}
