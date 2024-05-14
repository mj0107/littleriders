package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.TeacherRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyTeacherResponse;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Map;

public interface AcademyTeacherFacade {

	Long insertTeacher(TeacherRegistRequest teacherRegistRequest, Long academyId);

	List<AcademyTeacherResponse> readTeacherList(Long academyId);

}
