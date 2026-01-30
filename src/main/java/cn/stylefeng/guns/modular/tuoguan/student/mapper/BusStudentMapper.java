package cn.stylefeng.guns.modular.tuoguan.student.mapper;

import cn.stylefeng.guns.modular.tuoguan.student.entity.BusStudent;

public interface BusStudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusStudent record);

    int insertSelective(BusStudent record);

    BusStudent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusStudent record);

    int updateByPrimaryKey(BusStudent record);
}