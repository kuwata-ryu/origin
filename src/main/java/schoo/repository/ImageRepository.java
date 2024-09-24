package schoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import schoo.entity.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}