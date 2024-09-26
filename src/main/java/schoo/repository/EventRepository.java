package schoo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import schoo.entity.Event;

/**
 * eventテーブルを操作するためのRepositoryクラスです
 */
public interface EventRepository extends JpaRepository<Event, Integer> {
	/**
	 * 指定したイベントIDからイベントの情報を検索します.
	 * @param eventId イベントID
	 * @return	イベント一覧
	 */
	public List<Event> findByEventId(Integer eventId);
	public List<Event> findByUserId(Integer UserId);
}