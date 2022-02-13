package com.anmarcu.repository;

import com.anmarcu.domain.AudioContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AudioContractRepository extends JpaRepository<AudioContract, Long> {

//    AudioContract save(AudioContract audioContract);
//
//    Optional<AudioContract> findById(@Param("id") Long id);

    List<AudioContract> findAll();

//    void deleteByIdIn(List<Long> ids);
}
