package org.codenotknock.mybatisPlus.service.impl;

import org.codenotknock.mybatisPlus.mapper.AddressMapper;
import org.codenotknock.mybatisPlus.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.codenotknock.mybatisPlus.vo.domain.Address;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaofu
 * @since 2023-12-14
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
