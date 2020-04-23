package com.as.publichealth.service;

import com.as.publichealth.dao.HealthPersonMapper;
import com.as.publichealth.domain.person.HealthPerson;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
//批量新增接口实现
public class HealthPersonIServiceImpl extends ServiceImpl<HealthPersonMapper, HealthPerson> implements HealthPersonIService {
}
