package com.lms.service;

import com.lms.dto.MemberFormDto;
import com.lms.entity.Member;
import com.lms.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class MemberService implements UserDetailsService {
    
    private final MemberRepository memberRepository;

    /* 회원가입 service */
    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    } 
    
    /* 아이디 중복체크 */
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByLoginId(member.getLoginId());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public MemberFormDto modifyMember(String loginId) {
        Member member = memberRepository.findByLoginId(loginId);
        MemberFormDto memberFormDto = MemberFormDto.of(member);
        return memberFormDto;
    }

    @Override //로그인로직
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        log.info("------------------loadUserByUsername-------------------");

        Member member = memberRepository.findByLoginId(loginId);

        //email 해당하는 member 없으면
        if(member == null) {
            throw new UsernameNotFoundException(loginId);
        }


        return User.builder()
                .username(member.getLoginId()) //id
                .password(member.getPassword()) //pw
                .roles(member.getRole().toString()) //권한정보
                .build();
    }

    public Member kakaoUserCheck(String kakaoKey) {
       return  memberRepository.findByKakaoKey(kakaoKey);
    }

    public Member create(String loginId, String password, String email) {
        Member member = new Member();
        member.setLoginId(loginId);
        member.setPassword(password);
        member.setEmail(email);
        return null;
    }

    
}
