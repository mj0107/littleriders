package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.DriverRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyDriverResponse;

import java.util.List;
import java.util.Map;

public interface AcademyDriverFacade {
	Long insertDriver(DriverRegistRequest driverRegistRequest, Long academyId);

	List<AcademyDriverResponse> readDriverList(Long academyId);

}
