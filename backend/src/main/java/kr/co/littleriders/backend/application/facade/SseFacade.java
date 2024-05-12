package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseFacade {

    SseEmitter createSmsUserSseConnectionByUuid(String uuid);

    SseEmitter createAcademySseConnectionByAcademyId(long academyId);

    void broadcastShuttleLocationByShuttleId(long shuttleId, ShuttleLocationRequest shuttleLocationRequest);

    void broadcastBoardByAcademyIdAndViewerId(long academyId, String viewerUuid, AcademyChild academyChild, double latitude, double longitude);

    void broadcastDropByAcademyIdAndViewerId(long academyId, String viewerUuid, AcademyChild academyChild, double latitude, double longitude);)
}