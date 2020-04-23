package com.as.publichealth.service;

import com.as.publichealth.domain.person.HealthPerson;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

@Service
//批量新增接口
public interface HealthPersonIService extends IService<HealthPerson> {
}
