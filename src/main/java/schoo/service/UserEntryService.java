package schoo.service;

import java.util.List;

import schoo.entity.Event;
import schoo.form.UserEntryForm;

/**
 * イベント参加者のServiceインターフェースです.
 */
public interface UserEntryService {
	/**
	 * 指定したイベントIDに紐づく参加者の一覧を取得します.
	 * @param eventId イベントID
	 * @return 参加者の一覧
	 */
	public List<Event> findByUserId(Integer userId);
	/**
	 * イベント参加者の情報をDBに登録します.
	 * @param eventEntryForm イベント参加者情報
	 */
	public void save(UserEntryForm eventEntryForm);
}