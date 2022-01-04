package com.spring.multifield.filter.predicate.services;

import com.spring.multifield.filter.predicate.dto.FilterRequest;

import java.util.List;

public interface StudentService <StudentDAO, Long>
{
    List<StudentDAO> applyFilter(FilterRequest filterRequest);
}


