package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.GrjbxxSxMapper;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_sx;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxSxResult;
import com.as.occupationaldseases.domain.user.responce.UserCode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 输血
 */
@Service
public class GrjbxxSxService {
    @Resource
    private GrjbxxSxMapper grjbxxSxMapper;

    /**
     * 新增
     * @param grjbxxSx
     * @return
     */
    public GrjbxxSxResult add(Grjbxx_sx grjbxxSx){
        if(grjbxxSx==null){
            return new GrjbxxSxResult(CommonCode.INVALID_PARAM,null);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("personalid",grjbxxSx.getPersonalid());
        grjbxxSxMapper.deleteByMap(map);
        if (grjbxxSx.getSfsx().equals("1")) {
            grjbxxSx.setId(null);
            grjbxxSxMapper.insert(grjbxxSx);
            return  new GrjbxxSxResult(CommonCode.SUCCESS,grjbxxSx);
        }
        String[] sxsj = grjbxxSx.getSxsj().split(",");
        String[] sxyy = grjbxxSx.getSxyy().split(",");
        for (int i = 0; i < sxsj.length; i++) {
            Grjbxx_sx grjbxx_sx = new Grjbxx_sx();
            grjbxx_sx.setId(null);
            grjbxx_sx.setPersonalid(grjbxxSx.getPersonalid());
            grjbxx_sx.setSxsj(sxsj[i]);
            grjbxx_sx.setSxyy(sxyy[i]);
            grjbxx_sx.setSfsx("2");
            grjbxxSxMapper.insert(grjbxx_sx);
        }

        return  new GrjbxxSxResult(CommonCode.SUCCESS,grjbxxSx);

    }

    /**
     * 修改
     * @param id
     * @param grjbxxSx
     * @return
     */
    public GrjbxxSxResult update(Long id,Grjbxx_sx grjbxxSx){
        Grjbxx_sx grjbxx_sx = grjbxxSxMapper.selectById(id);
        if(grjbxx_sx!=null){
            //提交修改
            grjbxxSxMapper.updateById(grjbxxSx);
            return new GrjbxxSxResult(CommonCode.SUCCESS,grjbxxSx);
        }
        //修改失败
        return new GrjbxxSxResult(UserCode.USER_NOTEXISTS,null);

    }

    /**
     * 删除
     * @param grjbxxSx
     * @return
     */
    public GrjbxxSxResult delete(Grjbxx_sx grjbxxSx){
        QueryWrapper<Grjbxx_sx> wrapper = new QueryWrapper<>();
        wrapper.setEntity(grjbxxSx);
        Grjbxx_sx grjbxx_sx = grjbxxSxMapper.selectOne(wrapper);
        if(grjbxx_sx!=null){
            wrapper.setEntity(grjbxx_sx);
            grjbxxSxMapper.delete(wrapper);
            return new GrjbxxSxResult(CommonCode.SUCCESS,grjbxx_sx);
        }
        return new GrjbxxSxResult(UserCode.USER_NOTEXISTS,null);
    }

    /**
     * 查询
     * @param grjbxxSx
     * @return
     */
    public QueryResponseResult select(Grjbxx_sx grjbxxSx){
        QueryWrapper<Grjbxx_sx> wrapper = new QueryWrapper<>();
        wrapper.setEntity(grjbxxSx);
        List<Grjbxx_sx> grjbxxSxList = grjbxxSxMapper.selectList(wrapper);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(grjbxxSxList);//数据列表
        queryResult.setTotal(grjbxxSxList.size());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;

    }

}
