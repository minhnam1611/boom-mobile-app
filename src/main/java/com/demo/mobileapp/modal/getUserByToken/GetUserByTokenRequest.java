package com.demo.mobileapp.modal.getUserByToken;

import com.demo.mobileapp.modal.base.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserByTokenRequest extends BaseRequest {
    private String token;
}
