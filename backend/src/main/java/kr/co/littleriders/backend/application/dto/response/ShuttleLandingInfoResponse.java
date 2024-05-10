package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocation;
import kr.co.littleriders.backend.global.entity.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ShuttleLandingInfoResponse {


    private final long shuttleId;
    private final long driverId;
    private final long teacherId;
    private final long routeId;
    private final List<Location> locationList;

    private final List<BoardDropInfo> boardList;
    private final List<BoardDropInfo> dropList;


    public static ShuttleLandingInfoResponse of(long shuttleId, long driverId, long teacherId, long routeId, List<ShuttleLocation> shuttleLocationList,
                                                List<BoardDropInfo> boardList, List<BoardDropInfo> dropList) {

        List<Location> locationList = shuttleLocationList.stream().map(Location::from).toList();
        return new ShuttleLandingInfoResponse(shuttleId, driverId, teacherId, routeId, locationList,boardList,dropList);
    }


    public record Location(double latitude, double longitude, int speed) {
        public static Location from(ShuttleLocation shuttleLocation) {
            return new Location(shuttleLocation.getLatitude(), shuttleLocation.getLongitude(), shuttleLocation.getSpeed());
        }
    }

    public record BoardDropInfo(Child child, double latitude, double longitude, LocalDateTime time){

        public static BoardDropInfo of(Child child, double latitude, double longitude, LocalDateTime time){
            return new BoardDropInfo(child,latitude,longitude,time);
        }
    }

    public record Child (long academyChildId, String name, Gender gender,String imagePath){
        public static Child from(AcademyChild academyChild){
            return new Child(academyChild.getId(), academyChild.getName(), academyChild.getGender(), academyChild.getImagePath());
        }
    }
}

