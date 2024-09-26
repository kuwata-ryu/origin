package schoo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import schoo.entity.UserEntry;
import schoo.entity.Event;
import schoo.form.UserEntryForm;
import schoo.repository.UserEntryRepository;
import schoo.repository.EventRepository;

/**
 * イベント参加者のService実装クラスです.
 */
@Service
public class UserEntryServiceImpl implements UserEntryService {

	@Autowired
	UserEntryRepository userEntryRepository;
	@Autowired
	EventRepository eventRepository;

	/**
	 * 指定したイベントIDに紐づく参加者の一覧を取得します.
	 * @param userId イベントID
	 * @return 参加者の一覧
	 */
	public List<Event> findByUserId(Integer userId) {
		// 指定したイベントIDからイベント参加者の情報を取得
		List<UserEntry> userEntryList =
			userEntryRepository.findByUserId(userId);

		// イベント参加者のIDを取得
		// イベント参加者は複数いる可能性がある
		List<Integer> ids = new ArrayList<Integer>();
		for(UserEntry userEntry : userEntryList) {
			ids.add(userEntry.getUserId());
		}

		// イベント参加者の情報を検索して返却
		List<Event> eventList =
			eventRepository.findAllById(ids);
		return eventList;
	}

	/**
	 * イベント参加者の情報をDBに登録します.
	 * @param userEntryForm イベント参加者情報
	 */
	public void save(UserEntryForm userEntryForm) {
		// イベント参加者の情報を登録
		Event event = new Event();
		event.setEventName(userEntryForm.getName());
		eventRepository.save(event);

		// イベント参加情報を登録
		UserEntry userEntry = new UserEntry();
		userEntry.setUserId(userEntryForm.getUserId());
		userEntry.setUserId(event.getEventId());
		userEntryRepository.save(userEntry);
	}
}