package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleErrorCode;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class ShuttleServiceImpl implements ShuttleService {
    private final ShuttleRepository shuttleRepository;

    @Override
    public Shuttle findById(long id) {
        return shuttleRepository.findById(id).orElseThrow(
                () -> ShuttleException.from(ShuttleErrorCode.NOT_FOUND)
        );
    }
}
