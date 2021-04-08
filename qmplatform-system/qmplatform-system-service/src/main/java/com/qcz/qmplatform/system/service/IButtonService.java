package com.qcz.qmplatform.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qcz.qmplatform.system.domain.Button;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quchangzhong
 * @since 2020-09-03
 */
public interface IButtonService extends IService<Button> {

    /**
     * 获取按钮列表
     *
     * @param button 查询参数
     * @return
     */
    List<Button> getButtonList(Button button);

    /**
     * 添加按钮
     *
     * @param button
     * @return
     */
    boolean addButtonOne(Button button);

    /**
     * 修改按钮
     *
     * @param button
     * @return
     */
    boolean updateButtonOne(Button button);

    /**
     * 保存按钮
     *
     * @param button
     * @return
     */
    boolean saveButtonOne(Button button);

    /**
     * 删除按钮
     *
     * @param buttonIds
     * @return
     */
    boolean deleteButton(List<String> buttonIds);
}
