package kr.co.littleriders.backend.application.facade.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import kr.co.littleriders.backend.application.dto.response.AcademyShuttleResponse;
import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleErrorCode;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleException;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.application.facade.AcademyShuttleFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleStatus;
import kr.co.littleriders.backend.global.utils.ImageUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AcademyShuttleFacadeImpl implements AcademyShuttleFacade {

	private final ShuttleService shuttleService;
	private final AcademyService academyService;
	private final ImageUtil imageUtil;

	@Override
	public Long insertShuttle(ShuttleRegistRequest shuttleRegistRequest, Long academyId) {

		Academy academy = academyService.findById(academyId);
		Shuttle shuttle = shuttleRegistRequest.toEntity(academy);

		MultipartFile image = shuttleRegistRequest.getImage();
		if(image != null){
			String imagePath = imageUtil.saveImage(image);
			shuttle.setImagePath(imagePath);
		}

		return shuttleService.save(shuttle);
	}

	@Override
	public List<AcademyShuttleResponse> readShuttleList(Long academyId) {

		Academy academy = academyService.findById(academyId);

		List<AcademyShuttleResponse> shuttleList = shuttleService.findByAcademy(academy)
																 .stream()
																 .sorted(Comparator.comparing(shuttle -> shuttle.getStatus() == ShuttleStatus.USE ? 0 : 1))
																 .map(AcademyShuttleResponse::from)
																 .collect(Collectors.toList());



		return shuttleList;
	}

	@Override
	public Resource readShuttleImage(Long academyId, Long shuttleId) {

		Academy academy = academyService.findById(academyId);
		Shuttle shuttle = shuttleService.findById(shuttleId);
		if (!shuttle.equalsAcademy(academy)) {
			throw ShuttleException.from(ShuttleErrorCode.FORBIDDEN);
		}

		String imagePath = shuttle.getImagePath();
		Resource resource = imageUtil.getImage(imagePath);

		return resource;
	}
}
