package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.request.StationRequest;
import kr.co.littleriders.backend.application.dto.response.StationResponse;
import kr.co.littleriders.backend.application.facade.StationFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/station")
@RequiredArgsConstructor
public class StationController {
    private final StationFacade stationFacade;

    // 정류장 등록
    @PostMapping
    public ResponseEntity<Void> createStation(@Auth AuthAcademy authAcademy, @RequestBody StationRequest createRequest) {
        long academyId = authAcademy.getId();
        stationFacade.createStation(academyId, createRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 정류장 목록 조회
    @GetMapping
    public ResponseEntity<List<StationResponse>> searchStationByName(@Auth AuthAcademy authAcademy, @RequestParam(required = false, defaultValue = "") String name) {
        long academyId = authAcademy.getId();
        return ResponseEntity.ok().body(stationFacade.searchByName(name, academyId));
    }
}
