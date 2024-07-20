package pet.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.dao.CustomerDao;
import pet.store.dao.PetStoreDao;
import pet.store.dao.EmployeeDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {
	@SuppressWarnings("unused")
	@Autowired
	private PetStoreDao petStoreDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private CustomerDao customerDao;
	
	
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
		copyPetStoreFields(petStore, petStoreData);
		PetStore dbPetStore = petStoreDao.save(petStore);
		
		return new PetStoreData(dbPetStore);
	}
	
	private void copyPetStoreFields (PetStore petStore, PetStoreData petStoreData) {
		petStore.setPetStoreId(petStoreData.getPetStoreId());
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
		petStore.setPetStoreCityStateZip(petStoreData.getPetStoreCityStateZip());
		petStore.setPetStorePhoneNumber(petStoreData.getPetStorePhoneNumber());
	}
	
	private PetStore findOrCreatePetStore (Long petStoreId) {
//		PetStore petStore = null; // not need when we return for each part of "If/Else" statements
		if(Objects.isNull(petStoreId)) {
			return new PetStore();
		} else {
			return findPetStoreById(petStoreId);
		}
	}
	
	private PetStore findPetStoreById(Long petStoreId) {
		return petStoreDao.findById(petStoreId).orElseThrow(
				() -> new NoSuchElementException("Pet Store with ID=" + petStoreId + " does not exist"));
	}

// ABOVE IS WEEK 14
// BELOW IS WEEK 15
	@Transactional
	public List<PetStoreData> retrieveAllPetStores() {
		List<PetStore> petStores = petStoreDao.findAll();
		
	List<PetStoreData> result = new LinkedList<>();

	for(PetStore petStore : petStores) {
	  PetStoreData psd = new PetStoreData(petStore);

	  psd.getEmployees().clear();
	  psd.getCustomers().clear();

	  result.add(psd);
	  }
	
	return result;
	} // end of retrieveAllPetStores
	
	@Transactional(readOnly = false)
	public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee petStoreEmployee) {
		PetStore petStore = findPetStoreById(petStoreId);
		Employee employee = findOrCreatePetStoreEmployee(petStoreId, petStoreEmployee.getEmployeeId());
		copyEmployeeFields(employee, petStoreEmployee);
		employee.setPetStore(petStore);
		petStore.getEmployees().add(employee);
		Employee dbEmployee = employeeDao.save(employee);
	    return new PetStoreEmployee(dbEmployee); 
	}

	private Employee findOrCreatePetStoreEmployee(Long petStoreId, Long employeeId) {
		if(Objects.isNull(employeeId)) {
			return new Employee();
		} else {
			return findEmployeeById(petStoreId, employeeId);
		}
	}

	private Employee findEmployeeById(Long petStoreId, Long employeeId) {
		Employee employee = employeeDao.findById(employeeId).orElseThrow(
				() -> new NoSuchElementException("Employee with ID=" + employeeId + " does not exist"));

		if(employee.getPetStore().getPetStoreId() != petStoreId) {
			throw new IllegalArgumentException("Employee with ID=" + employeeId + " does not work at "
					+ "Pet Store with ID=" + petStoreId);
		}
		return employee;
	}

	// TO-DO: If an employee pet store ID equals a petStoreId, then return employee, 
	// or else throw an IllegalArgumentException(msg) response

	private void copyEmployeeFields (Employee employee, PetStoreEmployee petStoreEmployee) {
		employee.setEmployeeId(petStoreEmployee.getEmployeeId());
		employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
		employee.setEmployeeSurname(petStoreEmployee.getEmployeeSurname());
		employee.setEmployeePhoneNumber(petStoreEmployee.getEmployeePhoneNumber());
		employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());
	}

	public PetStoreCustomer saveCustomer(Long petStoreId, PetStoreCustomer petStoreCustomer) {
		PetStore petStore = findPetStoreById(petStoreId);
		Customer customer = findOrCreatePetStoreCustomer(petStoreId, petStoreCustomer.getCustomerId());
		copyCustomerFields(customer, petStoreCustomer);
		customer.getPetStores().add(petStore);
		petStore.getCustomers().add(customer);
		Customer dbCustomer = customerDao.save(customer);
	    return new PetStoreCustomer(dbCustomer); 
	}

	private void copyCustomerFields(Customer customer, PetStoreCustomer petStoreCustomer) {
		customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
		customer.setCustomerSurname(petStoreCustomer.getCustomerSurname());
		customer.setCustomerId(petStoreCustomer.getCustomerId());
		customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());
		customer.setCustomerPhoneNumber(petStoreCustomer.getCustomerPhoneNumber());
	}

	private Customer findOrCreatePetStoreCustomer(Long petStoreId, Long customerId) {
		if(Objects.isNull(customerId)) {
			return new Customer();
		} else {
			return findCustomerById(petStoreId, customerId);
		}
	}

	private Customer findCustomerById(Long petStoreId, Long customerId) {
		Customer customer = customerDao.findById(customerId).orElseThrow(
				() -> new NoSuchElementException("Customer with ID=" + customerId + " shops elsewhere"));
		
		boolean found = false;
		
		for (PetStore petStore : customer.getPetStores()) {
			if(petStore.getPetStoreId() == petStoreId) {
				found = true;
				break;
			}
		}
		
		if(!found) {
			throw new IllegalArgumentException("Customer with ID=" + customerId + " does not shop at "
					+ "Pet Store with ID=" + petStoreId); 
		}
		return customer;
		} // end of findCustomerById

	
	@Transactional(readOnly = true)
	public PetStoreData retrievePetStoreById(Long petStoreId) {
		PetStore petStore = findPetStoreById(petStoreId);
		return new PetStoreData(petStore);
	}

	
	@SuppressWarnings("unused")
	@Transactional(readOnly = false)
	public void deletePetStoreById(Long petStoreId) {
		PetStore petStore = findPetStoreById(petStoreId);
		petStoreDao.deleteById(petStoreId);
	}

} // end of class