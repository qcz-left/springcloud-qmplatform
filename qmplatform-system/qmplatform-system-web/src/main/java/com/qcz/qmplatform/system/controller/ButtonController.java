package com.qcz.qmplatform.system.controller;


import com.qcz.qmplatform.common.bean.PageRequest;
import com.qcz.qmplatform.common.bean.PageResult;
import com.qcz.qmplatform.common.bean.PageResultHelper;
import com.qcz.qmplatform.common.bean.ResponseResult;
import com.qcz.qmplatform.system.domain.Button;
import com.qcz.qmplatform.system.service.IButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quchangzhong
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/button")
public class ButtonController {

    @Autowired
    private IButtonService buttonService;

    /**
     * 获取按钮列表
     *
     * @param pageRequest 分页请求
     * @param button      请求参数
     * @return
     */
    @GetMapping("/getButtonList")
    public ResponseResult<PageResult> getButtonList(PageRequest pageRequest, Button button) {
        PageResultHelper.startPage(pageRequest.getPage(), pageRequest.getLimit());
        List<Button> buttonList = buttonService.getButtonList(button);
        return ResponseResult.ok(PageResultHelper.parseResult(buttonList));
    }

    /**
     * 获取按钮信息
     *
     * @param buttonId 按钮id
     * @return
     */
    @GetMapping("/getButtonOne/{menuId}")
    public ResponseResult<Button> getButtonOne(@PathVariable String buttonId) {
        return ResponseResult.ok(buttonService.getById(buttonId));
    }

    /**
     * 添加按钮信息
     *
     * @param button 按钮表单信息
     * @return
     */
    @PostMapping("/addButtonOne")
    public ResponseResult addButtonOne(@RequestBody Button button) {
        if (buttonService.addButtonOne(button)) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 修改按钮信息
     *
     * @param button 按钮表单信息
     * @return
     */
    @PutMapping("/updateButtonOne")
    public ResponseResult updateButtonOne(@RequestBody Button button) {
        if (buttonService.updateButtonOne(button)) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 删除按钮信息
     *
     * @param permissionIds 按钮id数组
     * @return
     */
    @DeleteMapping("/deleteButton")
    public ResponseResult deleteButton(String permissionIds) {
        if (buttonService.deleteButton(Arrays.asList(permissionIds.split(",")))) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }
}

