package com.yjh.study.generator.mapper;

import com.yjh.study.generator.domain.Employee;
import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long empId);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long empId);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);
}