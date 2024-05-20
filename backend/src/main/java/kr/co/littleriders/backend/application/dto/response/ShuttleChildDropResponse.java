package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ShuttleChildDropResponse {
    private long academyChildId;
    private String name;
    private String phoneNumber;
    private String image;

    public static ShuttleChildDropResponse from(AcademyChild academyChild) {
        long academyChildId = academyChild.getId();
        String name = academyChild.getName();
        String phoneNumber = academyChild.getPhoneNumber();
        String image = academyChild.getImagePath();

        return new ShuttleChildDropResponse(academyChildId, name, phoneNumber,image);
    }

}
