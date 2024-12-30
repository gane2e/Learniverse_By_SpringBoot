package com.lms.controller;

import com.lms.dto.VideoFormDto;
import com.lms.dto.VideoListDto;
import com.lms.entity.Category;
import com.lms.entity.SubCategory;
import com.lms.service.CategoryService;
import com.lms.service.CourseService;
import com.lms.service.VideoService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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




}
