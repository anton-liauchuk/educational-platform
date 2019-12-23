package com.user.management.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {

    private String errorMessage;

    private String detailException;

    /**
     * Generate ErrorResponseDto object by message.
     *
     * @param message message
     * @return dto
     */
    public static ErrorResponseDTO aResponseDTO(final String message) {
        final ErrorResponseDTO dto = new ErrorResponseDTO();
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
    public static ErrorResponseDTO aResponseDTO(final String message, final String details) {
        final ErrorResponseDTO dto = aResponseDTO(message);
        dto.setDetailException(details);
        return dto;
    }

}
