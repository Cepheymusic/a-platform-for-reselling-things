package ru.skypro.homework.service.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdService {
    private AdRepository adRepository;
    private UserRepository userRepository;

    public AdService(AdRepository adRepository, UserRepository userRepository) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
    }

    public AdsList getAdsList() {
        List<Ad> ads = adRepository.findAll().stream().map(AdMapper.INSTANCE::adToAdDTO).collect(Collectors.toList());
        return new AdsList(ads);
    }

    public Ad createAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, UserDetails userDetails) {
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException());
        AdEntity adEntity = AdMapper.INSTANCE.adEntityToCreateOrUpdateAd(createOrUpdateAd);
        adEntity.setAuthor(userEntity);
        //написать обработку фото
        adEntity.setImage("123");
        adRepository.save(adEntity);
        return AdMapper.INSTANCE.adToAdDTO(adEntity);
    }

    public ExtendedAd getAd(int id) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return AdMapper.INSTANCE.adEntityToExtendedAdDTO(adEntity);
    }

    public void deleteAd(int id, UserDetails userDetails) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new RuntimeException());
        accessVerification(userDetails, adEntity);
        adRepository.deleteById(id);
    }

    public Ad updateAd(int id, CreateOrUpdateAd createOrUpdateAd, UserDetails userDetails) {
        AdEntity adEntity = adRepository.findById(id).orElseThrow(() -> new RuntimeException());
        accessVerification(userDetails, adEntity);
        adEntity.setTitle(createOrUpdateAd.getTitle());
        adEntity.setPrice(createOrUpdateAd.getPrice());
        adEntity.setDescription(createOrUpdateAd.getDescription());
        adRepository.save(adEntity);
        return AdMapper.INSTANCE.adToAdDTO(adEntity);
    }
    public AdsList getMyAds(UserData userData) {
        List<AdEntity> adEntities = adRepository.findByAuthorId(userData.getId());
        List<Ad> adList = adEntities.stream().map(AdMapper.INSTANCE::adToAdDTO).collect(Collectors.toList());
        return new AdsList(adList);
    }

    public void updateImage() {

    }
    public void accessVerification(UserDetails userDetails, AdEntity adEntity) {
        if (!userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
                && !userDetails.getUsername().equals(adEntity.getAuthor().getEmail())) {
            throw new RuntimeException();
        }
    }
}
