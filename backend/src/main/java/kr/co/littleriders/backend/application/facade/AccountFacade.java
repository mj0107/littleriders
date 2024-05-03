package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.global.auth.dto.AuthDTO;
import kr.co.littleriders.backend.global.jwt.JwtToken;

public interface AccountFacade {

    JwtToken tokenReIssue(String token);

    void signOut(String requestRefreshToken);

    JwtToken signIn(String email,String password);

    JwtToken signInByTerminalNumber(String terminalNumber);

    void sendChangePasswordEmail(String email);

    JwtToken signInByEmailAndVerificationCode(String email, String code);

    void changePassword(AuthDTO authDTO, String password);

}
