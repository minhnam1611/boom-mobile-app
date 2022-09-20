package com.demo.mobileapp.modal.changepasword;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {
    private String token;
    private String oldPass;
    private String newPass;
}
