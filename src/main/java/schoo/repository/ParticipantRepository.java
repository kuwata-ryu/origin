package schoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import schoo.entity.Participant;

/**
 * participantテーブルを操作するためのRepositoryクラスです
 */
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
}