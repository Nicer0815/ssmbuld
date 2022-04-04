package com.ning.dao;

import com.ning.entity.Staff;

import java.util.List;

public interface StaffMapper {

    Staff queryStaffByJobId(String jobId);

    //对sex，rank，password不可修改 留到到C/S 架构实现
    int updateStaff(Staff staff);
}
