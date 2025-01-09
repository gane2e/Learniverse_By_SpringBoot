package com.lms.oauth2;

import com.lms.constant.Role;
import com.lms.entity.Member;
import com.lms.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MemberRepository memberRepository;

    // 구글로부터 받은 userRequest 데이터에 대한 후처리되는 함수
    // 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration = " + userRequest.getClientRegistration()); // registrationId로 어떤 OAuth로 로그인 했는지 확인 가능
        System.out.println("getAccessToken = " + userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        // 구글로그인 버튼 클릭 -> 구글로그인창 -> 로그인을 완료 -> code를 리턴(OAuth2-Client 라이브러리) -> AccessToken 요청
        // userRequest 정보 -> 회원 프로필 받아야함(loadUser함수 호출) -> 구글로부터 회원프로필 받아준다.
        System.out.println("getAttributes = " + oAuth2User.getAttributes());

        String provider = userRequest.getClientRegistration().getRegistrationId(); // google
        String providerId = oAuth2User.getAttribute("sub");
        String username = provider + "_" + providerId; // google_10021320120
        String password = passwordEncoder.encode("겟인데어");
        String email = oAuth2User.getAttribute("email");
        Role role = Role.USER;

        Member memberEntity = memberRepository.findByLoginId(username);

        if(memberEntity == null) {
            System.out.println("구글 로그인이 최초입니다.");
            memberEntity.setLoginId(username);
            memberEntity.setPassword(password);
            memberEntity.setEmail(email);
            memberEntity.setRole(role);
            memberEntity.setProvider(provider);
            memberEntity.setProviderId(providerId);
            memberRepository.save(memberEntity);
        } else {
            System.out.println("구글로그인을 이미 한적이 있습니다. 당신은 자동회원가입이 되어 있습니다.");
        }

        //회원가입 강제로 진행
        return new PrincipalDetails(memberEntity, oAuth2User.getAttributes());
    }
}
