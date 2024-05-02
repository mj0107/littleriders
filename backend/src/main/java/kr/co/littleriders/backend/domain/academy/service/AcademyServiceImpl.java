package kr.co.littleriders.backend.domain.academy.service;

import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.error.code.AcademyErrorCode;
import kr.co.littleriders.backend.domain.academy.error.exception.AcademyException;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
class AcademyServiceImpl implements AcademyService {

    private final AcademyRepository academyRepository;

    @Override
    public Academy findById(final long id) {
        return academyRepository.findById(id).orElseThrow(
                () -> AcademyException.from(AcademyErrorCode.NOT_FOUND)
        );
    }

    @Override
    public Academy findByEmail(final String email) {
        return academyRepository.findByEmail(email).orElseThrow(
                () -> AcademyException.from(AcademyErrorCode.NOT_FOUND)
        );
    }

    @Override
    public boolean existsById(final long id) {
        return academyRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(final long id) {
        return !academyRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(final String email) {
        return academyRepository.existsByEmail(email);
    }

    @Override
    public boolean notExistsByEmail(String email) {
        return !academyRepository.existsByEmail(email);
    }

    @Override
    public Slice<Academy> findByName(String name, Pageable pageable) {

        Slice<Academy> academyList = academyRepository.findByName(name, pageable);
        if (academyList.getContent().isEmpty()) {
            throw AcademyException.from(AcademyErrorCode.NOT_FOUND);
        }

        return academyList;
    }

    @Override
    public long save(Academy academy) {
        return academyRepository.save(academy).getId();
    }

}
