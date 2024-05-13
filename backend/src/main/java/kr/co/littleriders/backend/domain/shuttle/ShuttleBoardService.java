package kr.co.littleriders.backend.domain.shuttle;

import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleBoard;

import java.util.List;

public interface ShuttleBoardService {

	ShuttleBoard findByAcademyChildId(long academyChildId);

	List<ShuttleBoard> findByShuttleId(long shuttleId);

	List<ShuttleBoard> findByAcademyId(long academyId);

	void deleteAllByShuttleId(long shuttleId);

	void deleteAllByAcademyId(long academyId);

	void delete(ShuttleBoard shuttleBoard);

	void save(ShuttleBoard shuttleBoard);

	boolean notExistsByAcademyChildId(long academyChildId);
	boolean existsByAcademyChildId(long academyChildId);
}
