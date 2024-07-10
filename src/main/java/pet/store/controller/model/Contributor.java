//package pet.store.controller.model;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.ToString;
//// import pet.store.entity.Contributor;
//import pet.store.entity.PetStore;
//
//// reference ContributorData.java in "package pet.park.controller.model" for guide;
//@Entity
//@Data
//public class Contributor {
//		@Id
//		@GeneratedValue(strategy = GenerationType.IDENTITY)
//		private Long contributorId;
//		private String contributorName;
//		
//		@Column(unique = true)
//		private String contributorEmail;
//		
//		@EqualsAndHashCode.Exclude
//		@ToString.Exclude
//		@OneToMany(mappedBy = "contributor", cascade = CascadeType.ALL)
//		private Set<PetStore> petStore = new HashSet<>();
//		
//} //end of class
