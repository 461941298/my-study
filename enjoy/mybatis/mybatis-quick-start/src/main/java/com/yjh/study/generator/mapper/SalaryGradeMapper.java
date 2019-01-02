package com.yjh.study.generator.mapper;

import com.yjh.study.generator.domain.SalaryGrade;
import java.util.List;

public interface SalaryGradeMapper {
    int deleteByPrimaryKey(Integer grade);

    int insert(SalaryGrade record);

    SalaryGrade selectByPrimaryKey(Integer grade);

    List<SalaryGrade> selectAll();

    int updateByPrimaryKey(SalaryGrade record);
}