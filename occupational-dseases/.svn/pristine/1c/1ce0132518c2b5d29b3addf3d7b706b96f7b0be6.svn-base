package com.as.occupationaldseases.domain.template.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.template.Template;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TemplateResult extends ResponseResult {
    Template template;
    public TemplateResult(ResultCode resultCode, Template template) {
        super(resultCode);
        this.template = template;
    }
}
