package com.anmarcu.controllers;

import com.anmarcu.domain.AudioContract;
import com.anmarcu.repository.AudioContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/audio-contracts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AudioContractController {

    private final AudioContractRepository audioContractRepository;

    @GetMapping("/{id}")
    public AudioContract findOne(@PathVariable Long id) {
        log.info("Call 1");
        return audioContractRepository.findById(id).orElseThrow();
    }

    @GetMapping
    public List<AudioContract> findAll() {
        return audioContractRepository.findAll();
    }

    @PutMapping
    public AudioContract addAudioContract(AudioContract audioContract){
        return audioContractRepository.save(audioContract);
    }
}
