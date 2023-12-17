package org.codenotknock.secy.mapper;

import org.codenotknock.secy.vo.domain.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author xiaofu
 * @since 2023-12-18
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> selectPermsByUserId(Long userId);
}
