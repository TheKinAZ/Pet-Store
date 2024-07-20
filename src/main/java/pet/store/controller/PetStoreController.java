package pet.store.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.service.PetStoreService;

@RestController 				// per Wk14 assignment, "This tells Spring that this class is a REST controller. As such it expects and returns JSON in the request/response bodies. The default response status code is 200 (OK) if you don't specify something different. And finally, this annotation tells Spring to map HTTP requests to class methods. The annotation is in the org.springframework.web.bind.annotation package."
@RequestMapping("/pet_store") 	// "This instructs Spring that the URI for every HTTP request that is mapped to a method in this controller class must start with "/pet_store". This annotation is in the org.springframework.web.bind.annotation package."
@Slf4j 							// "This is a Lombok annotation that creates an SLF4J logger. It adds the logger as an instance variable named log. Use it like this: log.info("This is a log line"): This annotation is in the lombok.extern.slf4j package."
public class PetStoreController {
	@Autowired
	private PetStoreService petStoreService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData createPetStore (@RequestBody PetStoreData petStoreData) {
		log.info("Creating Pet Store {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PutMapping("/pet_store/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId,
			@RequestBody PetStoreData petStoreData) {
		petStoreData.setPetStoreId(petStoreId);
		log.info("Updating Pet Store {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PostMapping("/{petStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreEmployee addPetStoreEmployee (@PathVariable Long petStoreId, 
			@RequestBody PetStoreEmployee petStoreEmployee) {
		log.info("Add Employee () to store with ID=()", petStoreEmployee, petStoreId);
		return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
	}
	
	@PutMapping("/{petStoreId}/employee/{employeeId}")
	public PetStoreEmployee updateEmployee (@PathVariable Long petStoreId, @PathVariable Long employeeId, 
			@RequestBody PetStoreEmployee petStoreEmployee) {
		petStoreEmployee.setEmployeeId(employeeId); 
		log.info("Update Employee {}", petStoreEmployee, petStoreId);
		return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
	}

	@PostMapping("/{petStoreId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreCustomer addPetStoreCustomer (@PathVariable Long petStoreId, 
			@RequestBody PetStoreCustomer petStoreCustomer) {
		log.info("Add Customer () to store with ID=()", petStoreCustomer, petStoreId);
		return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
	}

	@PutMapping("/{petStoreId}/customer/{customerId}")
	public PetStoreCustomer updateCustomer (@PathVariable Long petStoreId, @PathVariable Long customerId, 
			@RequestBody PetStoreCustomer petStoreCustomer) {
		petStoreCustomer.setCustomerId(customerId); 
		log.info("Update Customer {}", petStoreCustomer, petStoreId);
		return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
	}
	
	@GetMapping
	public List<PetStoreData> retrieveAllPetStores() {
	  log.info("List all pet stores.");
	  return petStoreService.retrieveAllPetStores();
	}

	@GetMapping("/pet_store/{petStoreId}")
	public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
		log.info("Retrieve pet store with ID={}", petStoreId);
		return petStoreService.retrievePetStoreById(petStoreId);
	}

	@DeleteMapping("/pet_store")
	public void deleteAllPetStores() {
		log.info("Attempting to delete all pet stores");
		throw new UnsupportedOperationException("Deleting all pet stores is not allowed!");
	}
	
	@DeleteMapping("/pet_store/{petStoreId}")
	public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
		log.info("Deleting pet store with ID={}", petStoreId);
		
		petStoreService.deletePetStoreById(petStoreId);
		
		return Map.of("message", "Deletion of Pet Store with ID=" + petStoreId + " was successful");
	}
} // end of class