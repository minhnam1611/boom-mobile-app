package com.demo.mobileapp.modal.getUserByToken;

import com.demo.mobileapp.entity.Account;
import com.demo.mobileapp.modal.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserByTokenResponse  extends BaseResponse {
    private Account account;
}
