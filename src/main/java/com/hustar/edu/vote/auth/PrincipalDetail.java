package com.hustar.edu.vote.auth;

import com.hustar.edu.vote.dto.tb_user;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
@Getter
public class PrincipalDetail implements UserDetails {

    private tb_user user; // 컴포지션

    public PrincipalDetail(tb_user user) {
        System.out.println(user.getUsername() + " 사용자 입니다.");
        System.out.println(user.getVote() + " 에 투표 했습니다.");
        this.user = user;
    }

    // 계정이 갖고있는 권한 목록을 리턴한다.
    // {권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 우리는 한개만}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();

        // 람다식 표현
        collectors.add(()->{return"ROLE_"+user.getRole();});
        return collectors;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public Integer getIdx() {
        return user.getIdx();
    }

    public Integer getVote() {
        return user.getVote();
    }

    public String getRole() {
        return user.getRole();
    }

    public String getProfile_img() {
        return user.getProfile_img();
    }

    // 계정이 만료되지 않았는지 리턴한다(true : 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있지 않았는지 리턴한다(true : 잠기지않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되지 않았는지 리턴한다(true : 만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화(사용가능)인지 리턴한다 (true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
