package com.erp.controller.material;

import com.erp.annotation.UpdateMethod;
import com.erp.bean.QueryVO;
import com.erp.bean.device.Info;
import com.erp.bean.material.Material;
import com.erp.bean.material.Material_consume;
import com.erp.service.material.MaterialConsumeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Qiu
 * @Date: 2019/5/17 21:35
 */

@Controller
@RequestMapping("materialConsume")
public class MaterialConsumeController {

    @Autowired
    MaterialConsumeService materialConsumeService;

    @RequestMapping("find")
    public String returnMaterialConsumePage() {
        return "materialConsume_list";
    }

    @RequestMapping(value = "list", method = {RequestMethod.GET})
    @ResponseBody
    public QueryVO<Material_consume> materialConsumePageList(@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows) {
        PageHelper.startPage(page,rows);


        QueryVO<Material_consume> materialConsumeList = materialConsumeService.getMaterialConsumeList(page,rows);

        return materialConsumeList;
    }


    @RequestMapping("search_materialConsume_by_consumeId")
    @ResponseBody
    public QueryVO<Material_consume> queryReceiveByConsumeId(String searchValue,@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows){

        QueryVO<Material_consume> materialReceiveList =  materialConsumeService.queryConsumeByConsumeId(searchValue,page,rows);


        return materialReceiveList;
    }
    @RequestMapping("search_materialConsume_by_workId")
    @ResponseBody
    public QueryVO<Material_consume> queryReceiveByWorkId(String searchValue,@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows){


        QueryVO<Material_consume> materialReceiveList =  materialConsumeService.queryConsumeByWorkId(searchValue,page,rows);


        return materialReceiveList;
    }
    @RequestMapping("search_materialConsume_by_materialId")
    @ResponseBody
    public QueryVO<Material_consume> queryReceiveByMaterialId(String searchValue,@RequestParam(defaultValue = "1",
            value = "page") Integer page, Integer rows){


        QueryVO<Material_consume> materialReceiveList =  materialConsumeService.queryConsumeByMaterialId(searchValue,page,rows);


        return materialReceiveList;
    }

    @RequestMapping("add_judge")
    public String  materialConsumeAdd_judge(){
        return "materialConsume_add";
    }

    @RequestMapping("add")
    public String  materialConsumeAdd(){
        return "materialConsume_add";
    }

    @RequestMapping("insert")
    @ResponseBody
    public Info insertMaterialConsume(@ModelAttribute("materialConsume")Material_consume material_consume){

        Info info = new Info();

        Material_consume queryMaterialConsume = materialConsumeService.getConsumeByConsumeId(material_consume.getConsumeId());

        if (queryMaterialConsume!= null){
            info.setStatus(0);
            info.setMsg("该消耗编号已存在");
        }else {
            boolean insert = materialConsumeService.insertMaterialConsume(material_consume);
            info = returnMsg(info,insert,"新增成功","新增失败");
        }


        return info;
    }


    @RequestMapping("delete_judge")
    @ResponseBody
    public String  deleteDudge(){
        return "";
    }

    @RequestMapping("edit_judge")
    @ResponseBody
    public  String  editDudge(){
        return "";
    }

    @UpdateMethod("materialConsume")
    @RequestMapping("update_note")
    @ResponseBody
    public Info update_note(Material_consume material_consume){

        Info info = new Info();

        boolean update = materialConsumeService.update_note(material_consume);

        info = returnMsg(info, update, "更新成功", "更新失败");


        return info;
    }

    private Info returnMsg(Info info, boolean update, String success, String fail) {
        if (update) {
            info.setStatus(200);
            info.setMsg(success);
        } else {
            info.setStatus(0);
            info.setMsg(fail);
        }

        return info;
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    public Info delete_batch(List<String> ids){

        Info info = new Info();

        boolean isDele = materialConsumeService.delete_batch(ids);
        info = returnMsg(info, isDele, "删除成功", "删除失败");

        return info;
    }

    @RequestMapping("edit")
    public String  materialEdit(){
        return "materialConsume_edit";
    }

    @UpdateMethod("materialConsume")
    @RequestMapping("update_all")
    @ResponseBody
    public Info update_all(Material_consume material_consume){
        Info info = new Info();

        boolean isUpdate = materialConsumeService.update_all(material_consume);

        info = returnMsg(info, isUpdate, "编辑成功", "编辑失败");

        return info;
    }


}
