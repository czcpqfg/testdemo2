package com.erp.service.device.impl;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Device_maintain;
import com.erp.bean.device.Device_maintainExample;
import com.erp.mapper.device.Device_maintainMapper;
import com.erp.mapper.employee.EmployeeMapper;
import com.erp.service.device.DeviceMaintainService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Author: yyc
 * @Date: 2019/5/18 20:50
 */
@Service
public class DeviceMaintainServiceImpl implements DeviceMaintainService {
    @Autowired
    private Device_maintainMapper device_maintainMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public QueryVO getDeviceMaintainInPage(int page, int rows) {
        Device_maintainExample device_maintainExample = new Device_maintainExample();
        device_maintainExample.or();
        long l = device_maintainMapper.countByExample(device_maintainExample);
        PageHelper.startPage(page, rows);
        List<Device_maintain> devicemaintains = device_maintainMapper.selectByExample(device_maintainExample);
        //todo 添加emp信息
        devicemaintains = fillEmpName(devicemaintains);
        return new QueryVO( (int)l,devicemaintains);
    }

    private List<Device_maintain> fillEmpName(List<Device_maintain> devicemaintains) {
        for (Device_maintain devicemaintain : devicemaintains) {
            String EmpId = devicemaintain.getDeviceMaintainEmpId();
            String EmpName = employeeMapper.selectEmpNameByID(EmpId);
            devicemaintain.setDeviceMaintainEmp(EmpName);
        }
        return devicemaintains;
    }

    @Override
    public int addNew(Device_maintain device_maintain) {
        Device_maintain device_maintain1 = device_maintainMapper.selectByPrimaryKey(device_maintain.getDeviceMaintainId());
        if (device_maintain1!=null)
            return 0;
        int insert = device_maintainMapper.insert(device_maintain);
        return insert;
    }

    @Override
    public int update(Device_maintain device_maintain) {
        int update = device_maintainMapper.updateByPrimaryKeySelective(device_maintain);
        return update;
    }

    @Override
    public int deleteByIDs(String[] ids) {
        for (String id : ids) {
            int i = device_maintainMapper.deleteByPrimaryKey(id);
            if (i!=1)
                return 0;
        }
        return 1;
    }

    @Override
    public QueryVO<Device_maintain> searchDeviceMaintainByDeviceMaintainId(String searchValue, int page, int rows) {
        Device_maintainExample device_maintainExample = new Device_maintainExample();
        device_maintainExample.or().andDeviceMaintainIdLike("%"+searchValue+"%");
        long l = device_maintainMapper.countByExample(device_maintainExample);
        PageHelper.startPage(page, rows);
        List<Device_maintain> devices = device_maintainMapper.selectByExample(device_maintainExample);
        devices=fillEmpName(devices);
        return new QueryVO( (int)l,devices);
    }

    @Override
    public QueryVO<Device_maintain> searchDeviceMaintainByDeviceFaultId(String searchValue, int page, int rows) {
        Device_maintainExample device_maintainExample = new Device_maintainExample();
        device_maintainExample.or().andDeviceFaultIdLike("%"+searchValue+"%");
        long l = device_maintainMapper.countByExample(device_maintainExample);
        PageHelper.startPage(page, rows);
        List<Device_maintain> devices = device_maintainMapper.selectByExample(device_maintainExample);
        devices = fillEmpName(devices);
        return new QueryVO( (int)l,devices);
    }

    @Override
    public int updateNoteById(String deviceMaintainId, String note) {
        Device_maintain device_maintain = new Device_maintain();
        device_maintain.setDeviceMaintainId(deviceMaintainId);
        device_maintain.setNote(note);
        return device_maintainMapper.updateByPrimaryKeySelective(device_maintain);
    }
}
