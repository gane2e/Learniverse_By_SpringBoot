package com.lms.service;

import com.lms.dto.VideoFormDto;
import com.lms.entity.Videos;
import com.lms.repository.VideoRepository;
import groovy.util.logging.Log4j2;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Log4j2
class VideoServiceTest {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoRepository videoRepository;

    @Test
    void VideoByCategoryTest() {

        // When: 서비스 메서드 호출
       /* List<VideoFormDto> result = videoService.findVideosBySubCategoryId(102L);*/

        System.out.println("Test VideoByCategory");

       /* result.forEach(videoFormDto -> {
            System.out.println("Video ID: " + videoFormDto.getVideoId());
            System.out.println("Video Title: " + videoFormDto.getTitle());
            System.out.println("SubCategory ID: " + videoFormDto.getSubCategoryId());
            System.out.println("---------------------------");
        });*/
    }



}