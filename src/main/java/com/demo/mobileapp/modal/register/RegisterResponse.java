package com.demo.mobileapp.modal.register;

import com.demo.mobileapp.modal.BaseResponse;
import com.demo.mobileapp.modal.ResultResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse extends BaseResponse {
    private Long custId;
    private Long accountId;
}
