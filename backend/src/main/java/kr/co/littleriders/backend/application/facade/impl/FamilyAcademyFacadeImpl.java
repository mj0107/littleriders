package kr.co.littleriders.backend.application.facade.impl;


import kr.co.littleriders.backend.application.dto.request.FamilyAcademyRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyList;
import kr.co.littleriders.backend.application.dto.response.AcademyListResponse;
import kr.co.littleriders.backend.application.dto.response.AcademyRegistStatusResponse;
import kr.co.littleriders.backend.application.facade.FamilyAcademyFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.child.ChildService;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.child.error.code.ChildErrorCode;
import kr.co.littleriders.backend.domain.child.error.exception.ChildException;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.domain.pending.PendingService;
import kr.co.littleriders.backend.domain.pending.entity.Pending;
import kr.co.littleriders.backend.domain.pending.entity.PendingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class FamilyAcademyFacadeImpl implements FamilyAcademyFacade {

    private final AcademyService academyService;
    private final ChildService childService;
    private final FamilyService familyService;
    private final PendingService pendingService;

    @Override
    public AcademyListResponse readAcademyList(String name, Pageable pageable) {

        //TODO - 김도현 - 이윤지 정상수행 안됨 수정 필요

        Slice<Academy> academyPage = academyService.findByName(name, pageable);

        List<AcademyList> academyList = academyPage.getContent()
                                                   .stream()
                                                   .map(AcademyList::from)
                                                   .collect(Collectors.toList());

        AcademyListResponse academyListResponse = AcademyListResponse.of(academyList, academyPage.getNumber(), academyPage.hasNext());

        return academyListResponse;
    }

    @Override
    public Long insertAcademyJoin(Long familyId
            , FamilyAcademyRegistRequest familyAcademyRegistRequest) {

        Family family = familyService.findById(familyId);
        Academy academy = academyService.findById(familyAcademyRegistRequest.getAcademyId());
        Child child = childService.findById(familyAcademyRegistRequest.getChildId());

        if (child.equalsFamily(family)){
            throw ChildException.from(ChildErrorCode.ILLEGAL_ACCESS);
        }

        // TODO-이윤지-이전에 신청한 내역이 있는지 확인 후 생성
        Pending pending = Pending.of(academy, child, PendingStatus.PENDING);
        return pendingService.save(pending);
    }

    @Override
    public List<AcademyRegistStatusResponse> readAcademyRegistStatusList(Long familyId) {

        List<Child> childList = familyService.findById(familyId).getChild();
        List<AcademyRegistStatusResponse> academyList = pendingService.searchByChild(childList)
                                                                      .stream()
                                                                      .map(AcademyRegistStatusResponse::from)
                                                                      .collect(Collectors.toList());

        return academyList;
    }
}
