package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.ShuttleChildRideRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleStartRequest;
import kr.co.littleriders.backend.application.dto.response.RouteResponse;
import kr.co.littleriders.backend.application.dto.response.RouteDetailResponse;
import kr.co.littleriders.backend.application.dto.response.ShuttleChildRideResponse;
import kr.co.littleriders.backend.global.auth.dto.AuthTerminal;

import java.util.List;

public interface ShuttleFacade {
    List<RouteResponse> getRouteList(AuthTerminal authTerminal);

    RouteDetailResponse getRoute(AuthTerminal authTerminal, long routeId);

    void startDrive(AuthTerminal authTerminal, ShuttleStartRequest startRequest);

    void endDrive(long shuttleId);

    ShuttleChildRideResponse recordChildRiding(AuthTerminal authTerminal, ShuttleChildRideRequest rideRequest);

    void uploadLocation(AuthTerminal authTerminal, ShuttleLocationRequest locationRequest);

}

