package org.codenotknock.secy.vo.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaofu
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {

    private SysUser user;

    private List<String> permissions;

    @Override
    @JSONField(serialize = false)   // 不对改属性进行序列化，忽略
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 把 permissions 中的信息封装成 SimpleGrantedAuthority 类型
        /*
        public SimpleGrantedAuthority(String role) {
            Assert.hasText(role, "A granted authority textual representation is required");
            this.role = role;
        }
         */
        List<SimpleGrantedAuthority> authlist = permissions.stream()
                .map(s -> new SimpleGrantedAuthority(s))
                .collect(Collectors.toList());

        return authlist;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
