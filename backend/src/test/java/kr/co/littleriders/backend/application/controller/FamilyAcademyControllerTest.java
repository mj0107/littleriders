package kr.co.littleriders.backend.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.littleriders.backend.application.dto.request.ChildRegistRequest;
import kr.co.littleriders.backend.application.dto.request.FamilyAcademyRegistRequest;
import kr.co.littleriders.backend.application.facade.FamilyAcademyFacade;
import kr.co.littleriders.backend.common.fixture.AcademyFixture;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.child.ChildService;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.domain.pending.PendingService;
import kr.co.littleriders.backend.domain.pending.entity.Pending;
import kr.co.littleriders.backend.domain.pending.entity.PendingStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FamilyAcademyControllerTest {

    @Autowired
    private FamilyAcademyFacade familyAcademyFacade;

    @Autowired
    private ChildService childService;

    @Autowired
    private AcademyService academyService;

    @Autowired
    private FamilyService familyService;

    @Autowired
    private PendingService pendingService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Nested
    @DisplayName("학원 검색 기능")
    class searchAcademy {

        @Test
        @DisplayName("성공")
        void whenSuccess() throws Exception {
            Academy academy = AcademyFixture.BOXING.toAcademy();
			academyService.save(academy);
            Academy academy1 = AcademyFixture.BASEBALL.toAcademy();
            academyService.save(academy1);
            Academy academy2 = AcademyFixture.SOCCER.toAcademy();
            academyService.save(academy2);

            mockMvc.perform(
					get("/family/academy")
                            .param("name", "테스트")
                            .param("page", String.valueOf(0))
                            .contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andDo(print());
        }
    }

    @Nested
    @DisplayName("학원 신청 기능")
    class joinAcademy {

        @Test
        @DisplayName("성공")
        void whenSuccess() throws Exception {
            Academy academy = Academy.of("test@com", "password", "테스트학원", "테스트시 테스트동", "010-1111",3,4);
            academyService.save(academy);
            Family family = Family.of("test1@com", "password", "테스트부모", "테스트시 테스트동", "010-1111");
            familyService.save(family);
            ChildRegistRequest childRegist = new ChildRegistRequest("테스트", LocalDate.of(2024, 4,26), "MALE", null);
            Child child = childRegist.toEntity(family);
            Long childId = childService.save(child);

            FamilyAcademyRegistRequest regist = new FamilyAcademyRegistRequest(1L, childId);
            mockMvc.perform(
                            post("/family/academy")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(regist))
                    )
                    .andExpect(status().isOk())
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("학원 신청 내역 조회 기능")
    class getAcademyStatus {

        @Test
        @DisplayName("성공")
        void whenSuccess() throws Exception {
            Academy academy = Academy.of("test@com", "password", "테스트학원", "테스트시 테스트동", "010-1111",3,4);
            academyService.save(academy);
            Family family = Family.of("test@com", "password", "테스트부모", "테스트시 테스트동", "010-1111");
            familyService.save(family);
            ChildRegistRequest childRegist = new ChildRegistRequest("테스트", LocalDate.of(2024, 4, 26), "MALE", null);
            Child child = childRegist.toEntity(family);
            Long childId = childService.save(child);

            Pending pending = Pending.of(academy, child, PendingStatus.PENDING);
            pendingService.save(pending);

            mockMvc.perform(
                            get("/family/academy/status"))
                    .andExpect(status().isOk())
                    .andDo(print());
        }
    }
}