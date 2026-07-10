package com.cognizant.ems.projection; import org.springframework.beans.factory.annotation.*;
public interface EmployeeSummary {String getName();String getEmail();@Value("#{target.department.name}") String getDepartmentName();}
