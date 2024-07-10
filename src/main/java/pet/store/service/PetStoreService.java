package pet.store.service;

import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.store.controller.model.PetStoreData;
// import pet.store.dao.ContributorDao; // not in Step 3(a) - 3(d) but appeared in PetPark
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {

	@SuppressWarnings("unused")
	@Autowired
	private PetStoreDao petStoreDao;
	
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

} // end of class