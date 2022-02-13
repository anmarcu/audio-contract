package com.anmarcu.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MergeAudioFilesService {

    public String merge(List<AudioInputStream> audioInputStreams, String fileType, AudioFileFormat.Type audioType) {
        UUID randomUUID = UUID.randomUUID();
        String response = randomUUID.toString().concat(fileType);
        AudioInputStream appendedFiles = null;
        if (audioInputStreams.size() == 0) {
            return null;
        }
        if (audioInputStreams.size() == 1) {
            return null;
        }
        for (int i = 0; i < audioInputStreams.size() - 1; i++) {
            if (i == 0) {
                appendedFiles = new AudioInputStream(
                        new SequenceInputStream(audioInputStreams.get(i), audioInputStreams.get(i + 1)),
                        audioInputStreams.get(i).getFormat(),
                        audioInputStreams.get(i).getFrameLength() + audioInputStreams.get(i + 1).getFrameLength());
                continue;
            }
            appendedFiles =
                    new AudioInputStream(
                            new SequenceInputStream(appendedFiles, audioInputStreams.get(i + 1)),
                            appendedFiles.getFormat(),
                            appendedFiles.getFrameLength() + audioInputStreams.get(i + 1).getFrameLength());
        }
        try {
            AudioSystem.write(appendedFiles, audioType, new File(response));
        } catch (Exception ex) {
            log.info("Exception Occurred");
        }
        return response;
    }

    public List<AudioInputStream> convertFilesToAudioInputStream(List<File> inputFiles) {
        return inputFiles.stream().
                map(this::getAudioInputStream)
                .collect(Collectors.toList());
    }

    private AudioInputStream getAudioInputStream(File inputFile) {
        try {
            return AudioSystem.getAudioInputStream(inputFile);
        } catch (UnsupportedAudioFileException | IOException e) {
            log.error("Input file cannot be converted to audio file.");
            e.printStackTrace();
            return null;
        }
    }
}
