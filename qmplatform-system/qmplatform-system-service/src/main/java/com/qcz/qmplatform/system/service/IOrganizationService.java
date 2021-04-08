package com.qcz.qmplatform.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qcz.qmplatform.system.domain.Organization;
import com.qcz.qmplatform.system.po.OrgTree;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quchangzhong
 * @since 2020-09-03
 */
public interface IOrganizationService extends IService<Organization> {

    /**
     * 获取组织机构列表
     *
     * @param org 查询参数
     * @return
     */
    List<OrgTree> getOrgList(Organization org);

    /**
     * 添加组织机构
     *
     * @param org
     * @return
     */
    boolean addOrgOne(Organization org);

    /**
     * 修改组织机构
     *
     * @param org
     * @return
     */
    boolean updateOrgOne(Organization org);

    /**
     * 删除组织机构
     *
     * @param orgIds
     * @return
     */
    boolean deleteOrg(List<String> orgIds);

    boolean saveOrgOne(Organization org);
}
