package in.saurabhsawant.invoicegeneratorapi.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import in.saurabhsawant.invoicegeneratorapi.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByClerkId(String clerkId);
	boolean existsByClerkId(String clerkId);

}
