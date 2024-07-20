// <<COMMENTED OUT BECAUSE AVAILABLE ON package pet.store.controller.model/PetStoreData.java>>
//package pet.store.controller.model;
//
//import java.util.HashSet;
//import java.util.Set;
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToMany;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.ToString;
//import pet.store.entity.PetStore;
//
//@Entity
//@Data
//public class PetStoreCustomer {
//	@Id
//	@GeneratedValue (strategy = GenerationType.IDENTITY)
//	private Long customerId;
//	
//	private String customerFirstName;
//	private String customerSurname;
//	private String customerPhoneNumber;
//	private String customerEmail;
//	
//
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
//	@ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST)
//	private Set<PetStore> petStores = new HashSet<>();
//
//} // end of class