package com.scheideggergroup.students.controller;

import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("avatar")
public class AvatarController {

  @RequestMapping(value = "/random", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<InputStreamResource> getRandomImage() throws IOException {

    int randomValue = (int)getRandomNumber(1,18);

    ClassPathResource imgFile = new ClassPathResource("image/"+randomValue+".jpg");

      return ResponseEntity
              .ok()
              .contentType(MediaType.IMAGE_JPEG)
              .body(new InputStreamResource(imgFile.getInputStream()));
  }

  public static double getRandomNumber(double min, double max){
    double x = (Math.random() * ((max - min) + 1)) + min;
    return x;
  }
}