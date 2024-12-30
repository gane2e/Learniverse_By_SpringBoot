package com.lms.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lms.dto.CourseFormDto;
import com.lms.dto.CourseVideoDto;
import com.lms.dto.VideoFormDto;
import com.lms.dto.VideoListDto;
import com.lms.entity.Category;
import com.lms.entity.SubCategory;
import com.lms.service.CategoryService;
import com.lms.service.CourseService;
import com.lms.service.VideoService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private VideoService videoService;

    /* 교육과정 등록페이지 */
    @GetMapping(value = "/newCourse")
    public String newCourse(Model model) {

        // 1차카테고리 얻어서 모델로 보내기
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("CourseFormDto", new CourseFormDto());

        return "admin/newCourse";
    }


    /* 영상 등록페이지 */
    @GetMapping(value = "/newVideo")
    public String newVideo(Model model) {

        // 1차카테고리 얻어서 모델로 보내기
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("videoFormDto", new VideoFormDto());


        
        return "admin/newVideo";
    }


    /* 영상 등록 요청 */
    @PostMapping(value = "/newVideo")
    public String newVideoReg(@Valid VideoFormDto videoFormDto, BindingResult bindingResult,
                              Model model, @RequestParam("videoFile") MultipartFile videoFile ) {

        System.out.println("영상 등록 요청");
        
        if (bindingResult.hasErrors()) {
            System.out.println("bindingResult : 에러발생");
            return "error/404";
        }

        if (videoFile.isEmpty()) {
            System.out.println("videoFile.isEmpty() : 필수");
            model.addAttribute("errorMsg", "영상은 필수 첨부입니다.");
        }

        try {
            courseService.saveVideo(videoFormDto, videoFile);
        } catch (Exception e) {
            model.addAttribute("errorMsg", "영상 등록중 에러가 발생하였습니다.");
            System.out.println("catch : 에러발생");
            System.out.println(e.getMessage());
            return "error/404";
        }

        log.info("영상등록 성공!");

        return "redirect:/admin/newVideo";
    }


    // 관리자 영상 목록
    @GetMapping(value = "/videoList")
    public String videoList(Model model) {

        List<VideoListDto> videoList  =videoService.findAllVideosByCategoryInfo();
        model.addAttribute("videoList", videoList);

        for (VideoListDto video : videoList) {
            System.out.println(video.getTitle());
            System.out.println("--------------------");
        }

        return "admin/video/videoList";
    }

    /*// 관리자 영상 상세페이지
    @GetMapping(value = "/video/${videoId}")
    public String videoDtl(@PathVariable("videoId") Long videoId, Model model) {

      *//*  // 비디오 정보 반환
        List<VideoListDto> videoList  =videoService.findAllVideosByCategoryInfo();
        model.addAttribute("videoList", videoList);

        for (VideoListDto video : videoList) {
            System.out.println(video.getTitle());
            System.out.println("--------------------");
        }*//*

        return "admin/video/videoDtl";
    }*/


    // 교육과정 등록 시 선택한 카테고리의 영상목록 반환하기
    @GetMapping("/getVideosBySubCategory")
    @ResponseBody
    public List<VideoFormDto> getVideosBySubCategory(@RequestParam("subCategoryId") Long subCategoryId
            , Model model) {

        // 위에서 선택한 카테고리별 교육영상 불러오기
        List<VideoFormDto> scByVideoList = videoService.findVideosBySubCategoryId(subCategoryId);

        model.addAttribute("videoList", scByVideoList);

        for (VideoFormDto video : scByVideoList) {
            System.out.println("video ID : " + video.getVideoId());
            System.out.println("video Title : " + video.getTitle());
            System.out.println("video fileName : " + video.getOriVideoName());
            System.out.println("--------------------");
        }

        return scByVideoList;
    }



    // 교육과정 등록 요청
    @PostMapping(value = "/newCourse")
    public String newCourse(@Valid CourseFormDto courseFormDto, BindingResult bindingResult,
                            Model model,
                            @RequestParam("imgFile") MultipartFile imgFile,
                            @RequestParam(value = "videoData", required = false) String videoDataJson) {

        System.out.println("교육과정 등록 요청");


        if (videoDataJson != null && !videoDataJson.isEmpty()) {
            System.out.println("videoDataJson 널 아님");
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                List<Map<String, Integer>> videoData = objectMapper.readValue(videoDataJson, new TypeReference<List<Map<String, Integer>>>() {});
                System.out.println("videoDataJson 매핑중임");
                for (Map<String, Integer> video : videoData) {
                    int courseVideoIndex = video.get("courseVideoIndex");
                    int videoId = video.get("videoId");
                    System.out.println("순서: " + courseVideoIndex + ", 비디오 ID: " + videoId);
                    // courseVideoIndex와 videoId를 사용하여 필요한 로직 처리
                }
            } catch (Exception e) {
                e.printStackTrace();

                // JSON 파싱 오류 처리
            }
        }  else
            System.out.println("videoDataJson 널임");

        if (bindingResult.hasErrors()) {
            System.out.println("bindingResult : 에러발생");

            bindingResult.getAllErrors().forEach(error -> {
                log.warn("유효성 검사 에러 발생: {} - {}", error.getDefaultMessage(), error.getObjectName() + "." + error.getCode());
            });
            return "error/404";
        }

        if (imgFile.isEmpty()) {
            System.out.println("imgFile.isEmpty() : 필수");
            model.addAttribute("errorMsg", "썸네일은 필수 첨부입니다.");
        }

        try {
            courseService.saveCourse(courseFormDto, imgFile/*, courseVideoDtos*/);
        } catch (Exception e) {
            model.addAttribute("errorMsg", "교육과정 등록중 에러가 발생하였습니다.");
            System.out.println("catch : 에러발생");
            System.out.println(e.getMessage());
            return "error/404";
        }

        log.info("교육과정 등록 성공!");

        return "redirect:/admin/newCourse";

    }




}
