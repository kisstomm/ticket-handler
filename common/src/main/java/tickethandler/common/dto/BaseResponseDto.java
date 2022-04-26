package tickethandler.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tickethandler.common.enums.ErrorType;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseDto {

    private boolean success;
    private ErrorType errorType;
    private int errorCode;
    private String errorMessage;

    // SETTERS

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
        this.errorCode = errorType.getCode();
        this.errorMessage = errorType.getMessage();
    }
}
