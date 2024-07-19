package org.codenotknock.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.codenotknock.springboot.controller.Student;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> findAll();
}
