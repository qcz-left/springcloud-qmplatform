package com.qcz.qmplatform.oauth2.mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author quchangzhong
 * @since 2019-11-25
 */
public interface AuthorizationMapper {

	Map<String, String> queryUserByLoginname(String username);
	
	List<String> queryAuthoritiesByUserId(String userId);
}
