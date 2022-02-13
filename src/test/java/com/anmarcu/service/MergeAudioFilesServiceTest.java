package com.anmarcu.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
public class MergeAudioFilesServiceTest {

    @Test
    public void testMerge() throws UnsupportedAudioFileException, IOException {
        MergeAudioFilesService mergeAudioFilesService = new MergeAudioFilesService();
        List<File> inputFiles = new ArrayList<>();
        inputFiles.add(new File("src/test/resources/audioInput/test1.wav"));
        inputFiles.add(new File("src/test/resources/audioInput/test2.wav"));
        List<AudioInputStream> audioInputStreams = mergeAudioFilesService.convertFilesToAudioInputStream(inputFiles);
        AudioFileFormat audioFileFormat = AudioSystem.getAudioFileFormat(inputFiles.get(0));
        String outputType = "." + audioFileFormat.getType().getExtension();

        String expected = mergeAudioFilesService.merge(audioInputStreams, outputType, audioFileFormat.getType());
        assertFalse(Strings.isBlank(expected));

    }


}
