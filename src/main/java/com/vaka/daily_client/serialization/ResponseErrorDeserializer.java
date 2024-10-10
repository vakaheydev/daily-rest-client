package com.vaka.daily_client.serialization;

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
import java.util.regex.Pattern;

public class ResponseErrorDeserializer extends StdDeserializer<ResponseError> {
    private static final Pattern pattern = Pattern.compile(".*[iI][dD].*");
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
                String key = entry.getKey();
                String value = entry.getValue().asText();

                if (isId(key)) {
                    detailsMap.put(entry.getKey(), (isNull(value) ? null : Integer.parseInt(value)));
                } else {
                    detailsMap.put(entry.getKey(), entry.getValue());
                }
            });

            details = new ResponseError.ResponseDetails(detailsMap);
        }

        return new ResponseError(error, message, status, Optional.ofNullable(details));
    }

    private boolean isId(String string) {
        return pattern.matcher(string).matches();
    }

    private boolean isNull(String string) {
        return string.equals("null");
    }
}
