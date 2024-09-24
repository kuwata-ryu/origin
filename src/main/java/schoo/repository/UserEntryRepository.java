package schoo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import schoo.entity.UserEntry;

/**
 * event_entryテーブルを操作するためのRepositoryクラスです
 */
public interface UserEntryRepository extends JpaRepository<UserEntry, Integer> {
	/**
	 * 指定したイベントIDからイベント参加者の情報を検索します.
	 * @param eventId イベントID
	 * @return	イベント参加者一覧
	 */
	public List<UserEntry> findByUserId(Integer userId);
}