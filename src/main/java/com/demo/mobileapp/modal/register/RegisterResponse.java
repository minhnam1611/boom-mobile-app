package com.demo.mobileapp.modal.register;

import com.demo.mobileapp.modal.base.BaseResponse;
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
