package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.AdsList;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

import java.util.List;


@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdController {
    @GetMapping
    public AdsList getAdsList() {
        return new AdsList(1, List.of(new Ad(1, "", 1, 200, "package")));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Ad createAd(@RequestPart("properties") CreateOrUpdateAd createOrUpdateAd, @RequestPart("image") MultipartFile image) {
        return new Ad(1, "", 1, 200, "package");
    }

    @GetMapping("{id}")
    public ExtendedAd getAd(@PathVariable("id") int id) {
        return new ExtendedAd(1, "Serzh",
                "Sergeev",
                "not", "eeee@gmail.com",
                "", "+798798", 200,"package");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAd(@PathVariable("id") int id) {
        return ResponseEntity.ok("OK");
    }

    @PatchMapping("{id}")
    public Ad updateAd(@PathVariable("id") int id, CreateOrUpdateAd createOrUpdateAd) {
        return new Ad(1, "", 1, 200, "package");
    }

    @GetMapping("/me")
    public AdsList getMyAds() {
        return new AdsList(1, List.of(new Ad(1, "", 1, 200, "package")));
    }

    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable("id") int id, @RequestPart("image") MultipartFile newImage) {
        return ResponseEntity.ok().build();
    }
}
