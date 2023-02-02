package dk.serik.recipes.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperationResultDTO {
    private String result = "ok";

    public OperationResultDTO() {}

    public OperationResultDTO(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public OperationResultDTO setResult(String result) {
        this.result = result;
        return this;
    }
}
