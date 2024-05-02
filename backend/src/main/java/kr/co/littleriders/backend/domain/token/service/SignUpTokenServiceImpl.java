package kr.co.littleriders.backend.domain.token.service;

import kr.co.littleriders.backend.domain.token.SignUpTokenService;
import kr.co.littleriders.backend.domain.token.entity.SignUpToken;
import kr.co.littleriders.backend.domain.token.entity.SignUpTokenType;
import kr.co.littleriders.backend.domain.token.error.code.SignUpTokenErrorCode;
import kr.co.littleriders.backend.domain.token.error.exception.SignUpTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SignUpTokenServiceImpl implements SignUpTokenService { //write and check 만 한다.

    private final SignUpTokenRepository signUpTokenRepository;


    private SignUpToken findByEmailAndTokenAndType(final String email, final String token, final SignUpTokenType signUpTokenType) {


        SignUpToken signUpToken = signUpTokenRepository.findByEmail(email).orElseThrow(
                () -> SignUpTokenException.from(SignUpTokenErrorCode.NOT_FOUND)
        );
        if (signUpToken.notEqualsToken(token) || signUpToken.notEqualsType(signUpTokenType)) {
            throw SignUpTokenException.from(SignUpTokenErrorCode.NOT_FOUND);
        }
        return signUpToken;
    }


    @Override
    public SignUpToken findFamilySignUpTokenByEmailAndToken(String email, String token) {
        return findByEmailAndTokenAndType(email, token, SignUpTokenType.FAMILY);
    }

    @Override
    public void save(SignUpToken signUpToken) {
        signUpTokenRepository.save(signUpToken);
    }

    @Override
    public void delete(SignUpToken signUpToken) {
        signUpTokenRepository.delete(signUpToken);
    }

    @Override
    public SignUpToken findAcademySignUpTokenByEmailAndToken(String email, String token) {
        return findByEmailAndTokenAndType(email,token,SignUpTokenType.ACADEMY);
    }


}
