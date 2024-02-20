package com.codecool.healnaturalisapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private long id;

@Email (message = "Invalid email format")
@NotBlank (message = "Email cannot be blank")
    private String email;

@NotBlank (message = "Password cannot be blank")
    private String password;

private List<Long> cartIds;

}
