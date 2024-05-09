package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.entity.DriveUniqueKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface DriveUniqueKeyRepository extends CrudRepository<DriveUniqueKey, String> {

    List<DriveUniqueKey> findByShuttleId(long shuttleId);
}
