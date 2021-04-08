package com.qcz.qmplatform.system.controller;


import com.qcz.qmplatform.common.bean.ResponseResult;
import com.qcz.qmplatform.system.domain.Organization;
import com.qcz.qmplatform.system.po.OrgTree;
import com.qcz.qmplatform.system.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 组织机构前端控制器
 * </p>
 *
 * @author quchangzhong
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private IOrganizationService organizationService;

    /**
     * 获取组织机构列表
     *
     * @param organization 请求参数
     * @return
     */
    @GetMapping("/getOrgList")
    public ResponseResult<List<OrgTree>> getOrgList(Organization organization) {
        return ResponseResult.ok(organizationService.getOrgList(organization));
    }

    /**
     * 获取组织机构信息
     *
     * @param orgId 组织机构id
     * @return
     */
    @GetMapping("/getOrgOne/{orgId}")
    public ResponseResult<Organization> getOrgOne(@PathVariable String orgId) {
        return ResponseResult.ok(organizationService.getById(orgId));
    }

    /**
     * 添加组织机构信息
     *
     * @param org 组织机构信息
     * @return
     */
    @PostMapping("/addOrgOne")
    public ResponseResult addOrgOne(@RequestBody Organization org) {
        if (organizationService.addOrgOne(org)) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 保存组织机构信息（新增或修改）
     *
     * @param org 组织机构信息
     * @return
     */
    @PostMapping("/saveOrgOne")
    public ResponseResult saveOrgOne(@RequestBody Organization org) {
        if (organizationService.saveOrgOne(org)) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 修改组织机构信息
     *
     * @param org 组织机构信息
     * @return
     */
    @PutMapping("/updateOrgOne")
    public ResponseResult updateOrgOne(@RequestBody Organization org) {
        if (organizationService.updateOrgOne(org)) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 删除组织机构信息
     *
     * @param orgIds 组织机构id数组
     * @return
     */
    @DeleteMapping("/deleteOrg")
    public ResponseResult deleteOrg(List<String> orgIds) {
        if (organizationService.deleteOrg(orgIds)) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

}

