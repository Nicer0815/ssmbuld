package com.ning.service.impl;

import com.ning.dao.StaffMapper;
import com.ning.entity.Staff;
import com.ning.service.StaffService;

import java.util.List;

public class StaffServiceImpl implements StaffService {
    StaffMapper staffMapper;

    @Override
    public Staff queryStaffByJobId(String jobId) {
        return staffMapper.queryStaffByJobId(jobId);
    }

    @Override
    public int updateStaff(Staff staff) {
        return staffMapper.updateStaff(staff);
    }

    public void setStaffMapper(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }
}
