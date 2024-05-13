package kr.co.littleriders.backend.domain.shuttle.service;


import kr.co.littleriders.backend.domain.shuttle.ShuttleBoardService;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ShuttleBoardServiceImpl implements ShuttleBoardService {

    private final ShuttleBoardRepository shuttleBoardRepository;
    @Override
    public ShuttleBoard findByAcademyChildId(long academyChildId) {
        return shuttleBoardRepository.findById(academyChildId).orElseThrow();
    }

    @Override
    public List<ShuttleBoard> findByShuttleId(long shuttleId) {
        return shuttleBoardRepository.findByShuttleId(shuttleId);
    }

    @Override
    public List<ShuttleBoard> findByAcademyId(long academyId) {
        return shuttleBoardRepository.findByAcademyId(academyId);
    }

    @Override
    public void deleteAllByShuttleId(long shuttleId) {
        List<ShuttleBoard> ShuttleBoardList = shuttleBoardRepository.findByShuttleId(shuttleId);
        shuttleBoardRepository.deleteAll(ShuttleBoardList);
    }

    @Override
    public void deleteAllByAcademyId(long academyId) {
        List<ShuttleBoard> ShuttleBoardList  =shuttleBoardRepository.findByAcademyId(academyId);
        shuttleBoardRepository.deleteAll(ShuttleBoardList);
    }

    @Override
    public void delete(ShuttleBoard shuttleBoard) {
        shuttleBoardRepository.delete(shuttleBoard);
    }

    @Override
    public void save(ShuttleBoard shuttleBoard) {
        shuttleBoardRepository.save(shuttleBoard);
    }

    @Override
    public boolean notExistsByAcademyChildId(long academyChildId) {
        return !shuttleBoardRepository.existsById(academyChildId);
    }

    @Override
    public boolean existsByAcademyChildId(long academyChildId) {
        return shuttleBoardRepository.existsById(academyChildId);
    }
}