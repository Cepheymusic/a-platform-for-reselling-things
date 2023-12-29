package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.entity.AdEntity;

import java.util.List;

public interface AdRepository extends JpaRepository<AdEntity,Integer> {
    @Query(value = "select ad_entity.* from ad_entity join user_entity on ad_entity.author_id=user_entity.id where email = ?",
            nativeQuery = true)
    List<AdEntity> findAdsByEmail(String email);
}
