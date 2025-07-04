package in.saurabhsawant.invoicegeneratorapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import in.saurabhsawant.invoicegeneratorapi.entity.Invoice;
import in.saurabhsawant.invoicegeneratorapi.service.InvoiceService;
import in.saurabhsawant.invoicegeneratorapi.service.MailService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/invoices")
public class InvoiceController {

	private final InvoiceService invoiceService;
	private final MailService mailService;

	@PostMapping
	public ResponseEntity<Invoice> saveInvoice(@RequestBody Invoice invoice) {
		return new ResponseEntity<Invoice>(invoiceService.saveInvoice(invoice), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Invoice>> fetchInvoices(Authentication authentication) {
		return ResponseEntity.ok(invoiceService.fetchInvoices(authentication.getName()));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInvoice(@PathVariable String id, Authentication authentication) {
		if (authentication.getName() != null) {
			invoiceService.deleteInvoice(id, authentication.getName());
			return ResponseEntity.noContent().build();
		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,
					"User does not have permission to access this resource");
		}
	}

	@PostMapping("/sendinvoice")
	public ResponseEntity<?> sendInvoice(@RequestPart("file") MultipartFile file,
			@RequestPart("email") String customerEmail) {
		try {
			mailService.sendInvoiceMail(customerEmail, file);
			return ResponseEntity.ok().body("Invoice send successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Faild to send invoice");
		}
	}
}
