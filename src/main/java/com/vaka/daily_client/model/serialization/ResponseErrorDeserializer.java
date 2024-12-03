package com.vaka.daily_client.model.serialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.vaka.daily_client.model.ResponseError;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ResponseErrorDeserializer extends StdDeserializer<ResponseError> {
    public ResponseErrorDeserializer() {
        super(ResponseError.class);
    }

    @Override
    public ResponseError deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);

        String error = node.get("error").asText();
        String message = node.get("message").asText();
        Integer status = node.get("status").asInt();

        ResponseError.ResponseDetails details = null;
        if (node.has("details") && node.get("details").isObject()) {
            JsonNode detailsNode = node.get("details");

            Map<String, Object> detailsMap = new HashMap<>();
            detailsNode.fields().forEachRemaining(entry -> {
                detailsMap.put(entry.getKey(), getSimpleValue(entry.getValue().asText()));
            });

            details = new ResponseError.ResponseDetails(detailsMap);
        }

        return new ResponseError(error, message, status, Optional.ofNullable(details));
    }

    public String getSimpleValue(String val) {
        return val.replace("\"", "");
    }
}
