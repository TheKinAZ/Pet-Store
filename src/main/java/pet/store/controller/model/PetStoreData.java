package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

// @Entity // will change for Wk14
@Data
@NoArgsConstructor // part of Wk14
public class PetStoreData {
	private Long petStoreId;
	private String petStoreName;
	private String petStoreAddress;
	private String petStoreCityStateZip;
	private String petStorePhoneNumber;

	private Set<PetStoreCustomer> customers = new HashSet<>();

	private Set<PetStoreEmployee> employees = new HashSet<>();

//	public static class PetStoreContributor {
//		@SuppressWarnings("unused") // QUESTION: why does this not carry through the other privates?
//		private Long contributorId;
//		@SuppressWarnings("unused")
//		private String contributorName;
//		@SuppressWarnings("unused")
//		private String contributorEmail;

//		THE FOLLOWING WILL MODEL WHAT YOU ARE LOGGING, e.g. Pet Parks, Pet Stores, albums (SEE FURTHER BELOW)	
//		public Contributor(Contributor contributor) { 
//			contributorId = contributor.getContributorId();
//			contributorName = contributor.getContributorName();
//			contributorEmail = contributor.getContributorEmail();
//		}
//	}

	public PetStoreData(PetStore petStore) {
		petStoreId = petStore.getPetStoreId();
		petStoreName = petStore.getPetStoreName();
		petStoreAddress = petStore.getPetStoreAddress();
		petStoreCityStateZip = petStore.getPetStoreCityStateZip();
		petStorePhoneNumber = petStore.getPetStorePhoneNumber();
		
		for (Customer customer : petStore.getCustomers()) {
			customers.add(new PetStoreCustomer(customer));
		}
		
		for (Employee employee : petStore.getEmployees()) {
//			PetStoreEmployee petStoreEmployee = new PetStoreEmployee(employee);
//			employees.add(petStoreEmployee); // fixed in group chat (24-0706)
			employees.add(new PetStoreEmployee(employee)); // same as above; completes in 1 line
		}
	}
	
	@Data
	@NoArgsConstructor
	static class PetStoreCustomer {
		private Long customerId;
		private String customerFirstName;
		private String customerSurname;
		private String customerPhoneNumber;
		private String customerEmail;
		
		public PetStoreCustomer(Customer customer) {
			customerId = customer.getCustomerId();
			customerFirstName = customer.getCustomerFirstName();
			customerSurname = customer.getCustomerSurname();
			customerPhoneNumber = customer.getCustomerPhoneNumber();
			customerEmail = customer.getCustomerEmail();
		}
	}

	@Data
	@NoArgsConstructor
	static class PetStoreEmployee {
		private Long employeeId;
		private String employeeFirstName;
		private String employeeSurname;
		private String employeePhoneNumber;
		private String employeeJobTitle;
		
		public PetStoreEmployee(Employee employee) {
			employeeId = employee.getEmployeeId();
			employeeFirstName = employee.getEmployeeFirstName();
			employeeSurname = employee.getEmployeeSurname();
			employeePhoneNumber = employee.getEmployeePhoneNumber();
			employeeJobTitle = employee.getEmployeeJobTitle();
		}
	}
} // end of class