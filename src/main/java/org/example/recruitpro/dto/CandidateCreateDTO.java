package org.example.recruitpro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CandidateCreateDTO {

    @NotBlank(message = "Full Name cannot be empty")
    @Size(
            min = 5,
            max = 50,
            message = "Full Name must contain 5 to 50 characters"
    )
    private String fullName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is invalid")
    private String email;

    @NotNull(message = "Age cannot be empty")
    @Min(
            value = 18,
            message = "Age must be at least 18"
    )
    private Integer age;

    @NotNull(message = "Years of experience cannot be empty")
    @Min(
            value = 0,
            message = "Years of experience cannot be negative"
    )
    private Integer yearsOfExperience;

    public CandidateCreateDTO() {
    }

    public CandidateCreateDTO(
            String fullName,
            String email,
            Integer age,
            Integer yearsOfExperience
    ) {
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(
            Integer yearsOfExperience
    ) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
