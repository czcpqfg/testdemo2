package com.erp.controller.device;

import com.erp.bean.QueryVO;
import com.erp.bean.device.Device_check;
import com.erp.bean.device.Info;
import com.erp.service.device.DeviceCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: yyc
 * @Date: 2019/5/18 17:47
 */
@Controller
@RequestMapping("deviceCheck")
public class DeviceCheckController {
    @Autowired
    DeviceCheckService deviceCheckService;

    @RequestMapping("list")
    public @ResponseBody QueryVO<Device_check> list(int page,int rows){
        return deviceCheckService.getDeviceCheckInPage(page, rows);
    }

    @RequestMapping("add_judge")
    public @ResponseBody String  addDudge(){
        //Todo 判断权限
        return null;
    }
    @RequestMapping("add")
    public String toAdd(){
        return "deviceCheck_add";
    }
    @RequestMapping(value = "insert")
    public @ResponseBody
    Info insert(Device_check device_check){
        int res = deviceCheckService.addNew(device_check);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"该设备号已经存在，请更换设备号！",null);
        }
    }
    @RequestMapping("edit_judge")
    public @ResponseBody String  editDudge(){
        //Todo 判断权限
        return "";
    }
    @RequestMapping("edit")
    public String toEdit(){
        return "deviceCheck_edit";
    }
    @RequestMapping("update")
    public @ResponseBody Info update(Device_check device_check){
        int res = deviceCheckService.update(device_check);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"该设备号已经存在，请更换设备号！",null);
        }
    }

    @RequestMapping("delete_judge")
    public @ResponseBody String  deleteDudge(){
        //Todo 判断权限
        return "";
    }

    @RequestMapping("delete_batch")
    public @ResponseBody Info deleteByIDs(String[] ids){
        int res = deviceCheckService.deleteByIDs(ids);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"该设备号已经存在，请更换设备号！",null);
        }
    }
    @RequestMapping("search_deviceCheck_by_deviceCheckId")
    public @ResponseBody QueryVO<Device_check> searchDeviceCheckByDeviceCheckId(String searchValue,int page,int rows){
        return deviceCheckService.searchDeviceCheckByDeviceCheckId(searchValue, page, rows);

    }
    @RequestMapping("search_deviceCheck_by_deviceName")
    public @ResponseBody QueryVO<Device_check> searchDeviceCheckByDeviceName(String searchValue,int page,int rows){
        return deviceCheckService.searchDeviceCheckByDeviceName(searchValue, page, rows);

    }
    @RequestMapping("update_note")
    public @ResponseBody Info updateNoteById(String deviceCheckId,String deviceCheckResult){
        int res = deviceCheckService.updateNoteById(deviceCheckId,deviceCheckResult);
        if (res==1){
            return new Info(200,"更新成功",null);
        }else{
            return new Info(res,"该设备号已经存在，请更换设备号！",null);
        }
    }




}
