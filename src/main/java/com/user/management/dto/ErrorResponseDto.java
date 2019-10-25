package com.user.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {

    private String errorMessage;

    private String detailException;

    /**
     * Generate ErrorResponseDto object by message.
     *
     * @param message message
     * @return dto
     */
    public static ErrorResponseDto aResponseDTO(final String message) {
        final ErrorResponseDto dto = new ErrorResponseDto();
        dto.setErrorMessage(message);
        return dto;
    }

    /**
     * Generate ErrorResponseDto object by message and details.
     *
     * @param message message of response
     * @param details details  of response
     * @return dto
     */
    public static ErrorResponseDto aResponseDTO(final String message, final String details) {
        final ErrorResponseDto dto = aResponseDTO(message);
        dto.setDetailException(details);
        return dto;
    }

}
