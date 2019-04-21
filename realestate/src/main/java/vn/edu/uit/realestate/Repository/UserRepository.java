package vn.edu.uit.realestate.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import vn.edu.uit.realestate.Model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>{

    Page<User> findByUserKindId(Long userKindId, Pageable pageable);
    Optional<User> findByIdAndUserKindId(Long id, Long postId);
}
