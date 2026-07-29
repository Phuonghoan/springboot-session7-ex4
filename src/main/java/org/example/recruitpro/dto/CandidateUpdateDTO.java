package org.example.recruitpro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CandidateUpdateDTO {

    @NotBlank(message = "Address cannot be empty")
    private String address;

    @NotBlank(message = "Bio cannot be empty")
    @Size(
            max = 200,
            message = "Bio must not exceed 200 characters"
    )
    private String bio;

    public CandidateUpdateDTO() {
    }

    public CandidateUpdateDTO(
            String address,
            String bio
    ) {
        this.address = address;
        this.bio = bio;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
