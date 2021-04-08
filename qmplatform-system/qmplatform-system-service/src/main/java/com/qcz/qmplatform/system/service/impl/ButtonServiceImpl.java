package com.qcz.qmplatform.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcz.qmplatform.common.utils.StringUtils;
import com.qcz.qmplatform.system.domain.Button;
import com.qcz.qmplatform.system.mapper.ButtonMapper;
import com.qcz.qmplatform.system.service.IButtonService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author quchangzhong
 * @since 2020-09-03
 */
@Service
public class ButtonServiceImpl extends ServiceImpl<ButtonMapper, Button> implements IButtonService {

    @Override
    public List<Button> getButtonList(Button button) {
        QueryWrapper<Button> wrapper = new QueryWrapper<>();
        String buttonName = button.getButtonName();
        wrapper.like(StringUtils.isNotBlank(buttonName), "button_name", buttonName);
        return list(wrapper);
    }

    @Override
    public boolean addButtonOne(Button button) {
        button.setButtonId(StringUtils.uuid());
        return save(button);
    }

    @Override
    public boolean updateButtonOne(Button button) {
        return updateById(button);
    }

    @Override
    public boolean saveButtonOne(Button button) {
        if (StringUtils.isBlank(button.getButtonId())) {
            return addButtonOne(button);
        }
        return updateButtonOne(button);
    }

    @Override
    public boolean deleteButton(List<String> buttonIds) {
        return removeByIds(buttonIds);
    }
}
