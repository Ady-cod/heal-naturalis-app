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

@Email (message = "Invalid User email format")
@NotBlank (message = "User email cannot be blank")
    private String email;

@NotBlank (message = "User password cannot be blank")
    private String password;

private List<Long> cartIds;

}
