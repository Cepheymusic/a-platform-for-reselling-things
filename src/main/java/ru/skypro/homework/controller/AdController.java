package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.impl.AdService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdController {
    private AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping
    public AdsList getAdsList() {
        return adService.getAdsList();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Ad createAd(@RequestPart("properties") CreateOrUpdateAd createOrUpdateAd,
                       @RequestPart("image") MultipartFile image,
                       @AuthenticationPrincipal UserDetails userDetails) {
        return adService.createAd(createOrUpdateAd,image, userDetails);
    }

    @GetMapping("/{id}")
    public ExtendedAd getAd(@PathVariable("id") int id) {
        return adService.getAd(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAd(@PathVariable("id") int id,
                                           @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        adService.deleteAd(id, userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public Ad updateAd(@PathVariable("id") int id,@RequestBody CreateOrUpdateAd createOrUpdateAd,
                       @AuthenticationPrincipal UserDetails userDetails) {
        return adService.updateAd(id, createOrUpdateAd, userDetails);
    }

    @GetMapping("/me")
    public AdsList getMyAds(@AuthenticationPrincipal UserDetails userDetails) {
        return adService.getMyAds(userDetails);
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable("id") int id, @RequestPart("image") MultipartFile newImage,
                                              @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        adService.updateImage(id, newImage, userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
