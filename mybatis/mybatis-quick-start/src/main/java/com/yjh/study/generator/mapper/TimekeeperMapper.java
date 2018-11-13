package com.yjh.study.generator.mapper;

import com.yjh.study.generator.domain.Timekeeper;
import java.util.List;

public interface TimekeeperMapper {
    int deleteByPrimaryKey(String timekeeperId);

    int insert(Timekeeper record);

    Timekeeper selectByPrimaryKey(String timekeeperId);

    List<Timekeeper> selectAll();

    int updateByPrimaryKey(Timekeeper record);
}