package com.qcz.qmplatform.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcz.qmplatform.common.utils.DateUtils;
import com.qcz.qmplatform.common.utils.StringUtils;
import com.qcz.qmplatform.common.utils.TreeUtils;
import com.qcz.qmplatform.system.domain.Organization;
import com.qcz.qmplatform.system.mapper.OrganizationMapper;
import com.qcz.qmplatform.system.po.OrgTree;
import com.qcz.qmplatform.system.service.IOrganizationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 组织机构服务实现类
 * </p>
 *
 * @author quchangzhong
 * @since 2020-09-03
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements IOrganizationService {

    @Override
    public List<OrgTree> getOrgList(Organization organization) {
        return TreeUtils.buildTree(baseMapper.selectOrgTree(organization));
    }

    @Override
    public boolean addOrgOne(Organization org) {
        org.setOrganizationId(StringUtils.uuid());
        org.setCreateTime(DateUtils.getCurrTimestamp());
        return save(org);
    }

    @Override
    public boolean updateOrgOne(Organization org) {
        return updateById(org);
    }

    @Override
    public boolean deleteOrg(List<String> orgIds) {
        return removeByIds(orgIds);
    }

    @Override
    public boolean saveOrgOne(Organization org) {
        if (StringUtils.isBlank(org.getOrganizationId())) {
            return addOrgOne(org);
        }
        return updateOrgOne(org);
    }
}
