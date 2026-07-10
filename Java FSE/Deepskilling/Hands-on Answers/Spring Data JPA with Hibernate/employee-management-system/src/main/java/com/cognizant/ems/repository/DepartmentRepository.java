package com.cognizant.ems.repository; import java.util.*; import org.springframework.data.jpa.repository.*; import com.cognizant.ems.model.*;
public interface DepartmentRepository extends JpaRepository<Department,Long> {Optional<Department> findByName(String name);}
