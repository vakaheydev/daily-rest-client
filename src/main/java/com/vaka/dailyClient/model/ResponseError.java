package com.vaka.dailyClient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vaka.dailyClient.serialization.ResponseErrorDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {
    public static class ResponseDetails {
        private Map<String, Object> details;

        public ResponseDetails() {
        }

        public ResponseDetails(Map<String, Object> details) {
            this.details = details;
        }

        public boolean contains(String name) {
            return details.containsKey(name);
        }

        public Optional<String> find(String name) {
            return Optional.ofNullable(String.valueOf(details.getOrDefault(name, "null")));
        }

        public Map<String, Object> getDetails() {
            return details;
        }

        public void setDetails(Map<String, Object> details) {
            this.details = details;
        }

        @Override
        public String toString() {
            return "ResponseDetails{" +
                    "details=" + details +
                    '}';
        }
    }

    private String error;
    private String message;
    private Integer status;
    private Optional<ResponseDetails> details;

    @Override
    public String toString() {
        return "ResponseError{" +
                "error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", details=" + details.orElse(new ResponseDetails()) +
                '}';
    }
}
