package org.techmate.techmate_be.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.techmate.techmate_be.model.enumeration.OAuthProvider;

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    private String code;
    @NotNull
    private OAuthProvider provider;
}