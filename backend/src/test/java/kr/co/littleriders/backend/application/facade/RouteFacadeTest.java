package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.RouteRequest;
import kr.co.littleriders.backend.common.fixture.AcademyFixture;
import kr.co.littleriders.backend.common.fixture.RouteFixture;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.route.RouteService;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.route.error.code.RouteErrorCode;
import kr.co.littleriders.backend.domain.route.error.exception.RouteException;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RouteFacadeTest {
    @Autowired
    private RouteFacade routeFacade;

    @Autowired
    private AcademyService academyService;

    @Autowired
    private RouteService routeService;

    private Academy academy;
    private AuthAcademy authAcademy;

    @BeforeEach
    void setUp() {
        academy = AcademyFixture.BOXING.toAcademy();
        academyService.save(academy);
        authAcademy = AuthAcademy.from(academy);
    }

    @Nested
    @DisplayName("노선 등록 테스트")
    class createRouteTest {

        @Test
        @DisplayName("성공")
        void whenSuccess() throws Exception {
            //RouteRequest routeRequest = new RouteRequest("등원A", "board");
            RouteRequest routeRequest = RouteFixture.F.toRouteRequest("board");
            academyService.save(academy);
            assertDoesNotThrow(() -> {
                routeFacade.createRoute(authAcademy, routeRequest);
            });
        }

        @Test
        @DisplayName("실패, 중복된 정류장명")
        void whenFailDuplicateName() {

            //given
            RouteFixture routeFixture = RouteFixture.R;
            RouteRequest routeRequest = routeFixture.toRouteRequest("board");
            academyService.save(academy);
            Route route = routeFixture.toRoute(academy,"board");
            routeService.save(route);


            RouteException exception = assertThrows(RouteException.class,() -> { //when and then;
                routeFacade.createRoute(authAcademy, routeRequest);
            });
            assertEquals(exception.getErrorCode(), RouteErrorCode.DUPLICATE_NAME);
        }
    }

    @Nested
    @DisplayName("노선 수정 테스트")
    class updateRouteTest {
        @Test
        @DisplayName("성공")
        void whenSuccess() throws Exception {

            RouteRequest routeRequest = new RouteRequest("하원B", "drop");

            Route route = Route.of(academy, "등원B", "board");
            long routeId = routeService.save(route);

            routeFacade.updateRoute(academy.getId(), routeId, routeRequest);
        }
    }

    @Nested
    @DisplayName("노선 삭제 테스트")
    class deleteRouteTest {
        @Test
        @DisplayName("성공")
        void whenSuccess() throws Exception {
            Route route = Route.of(academy, "등원B", "board");
            long routeId = routeService.save(route);

            routeFacade.deleteRoute(academy.getId(), routeId);
        }
    }

}
