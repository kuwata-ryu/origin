package schoo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import schoo.entity.UserList;

/**
 * user_listテーブルを操作するためのRepositoryクラスです
 */
@Repository
public interface UserListRepository extends JpaRepository<UserList, Integer> {
	Optional<UserList> findByName(String name);
	List<UserList> findByUserId(Integer userId);
}