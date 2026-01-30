package cn.stylefeng.guns.modular.tuoguan.student.mapper;

import cn.stylefeng.guns.modular.tuoguan.student.entity.BusContact;

public interface BusContactMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusContact record);

    int insertSelective(BusContact record);

    BusContact selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusContact record);

    int updateByPrimaryKey(BusContact record);
}