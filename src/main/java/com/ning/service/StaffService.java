package com.ning.service;

import com.ning.entity.Staff;

import java.util.List;

public interface StaffService {

    Staff queryStaffByJobId(String jobId);

    //对sex，rank，password不可修改
    int updateStaff(Staff staff);
}
