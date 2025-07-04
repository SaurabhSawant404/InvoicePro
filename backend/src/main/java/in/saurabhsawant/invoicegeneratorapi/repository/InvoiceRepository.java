package in.saurabhsawant.invoicegeneratorapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import in.saurabhsawant.invoicegeneratorapi.entity.Invoice;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, String> {
	List<Invoice> findByClerkId(String id);

	Optional<Invoice> findByClerkIdAndId(String clerkId, String id);
}
