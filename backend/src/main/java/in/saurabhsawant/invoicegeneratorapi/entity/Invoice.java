package in.saurabhsawant.invoicegeneratorapi.entity;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "invoices")
public class Invoice {

	@Id
	private String id;
	
	private String clerkId;
	private Company company;
	private Billing billing;
	private Shipping shipping;
	private InvoiceDetails invoice;
	private List<Item> items;
	private String notes;
	private String logo;
	private double tax;
	@CreatedDate
	private Instant createdAt;

	@LastModifiedBy
	private Instant lastUpdatedAt;
	private String thumbnailUrl;
	private String template;
	private String title;

	@Data
	public static class Company {
		private String name;
		private String phone;
		private String address;
	}

	@Data
	public static class Billing {
		private String name;
		private String phone;
		private String address;
	}

	@Data
	public static class Shipping {
		private String name;
		private String phone;
		private String address;
	}

	@Data
	public static class InvoiceDetails {
		private String number;
		private String date;
		private String duedate;
	}

	@Data
	public static class Item {
		private String name;
		private String qty;
		private double amount;
		private String description;
		private double total;
	}
}
