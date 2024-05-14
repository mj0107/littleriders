package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyShuttleResponse;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Map;

public interface AcademyShuttleFacade {

	Long insertShuttle(ShuttleRegistRequest shuttleRegistRequest, Long academyId);

	List<AcademyShuttleResponse> readShuttleList(Long academyId);

}
