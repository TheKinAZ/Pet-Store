// CTRL+A = all; CTRL+/ = comment out highlights 
//
//package dnu.pet.store.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import lombok.extern.slf4j.Slf4j;
//import pet.store.controller.model.Contributor;
//import pet.store.service.PetStoreService;
//
//@RestController 				// per Wk14 assignment, "This tells Spring that this class is a REST controller. As such it expects and returns JSON in the request/response bodies. The default response status code is 200 (OK) if you don't specify something different. And finally, this annotation tells Spring to map HTTP requests to class methods. The annotation is in the org.springframework.web.bind.annotation package."
//@RequestMapping("/pet_store") 	// "This instructs Spring that the URI for every HTTP request that is mapped to a method in this controller class must start with "/pet_store". This annotation is in the org.springframework.web.bind.annotation package."
//@Slf4j 							// "This is a Lombok annotation that creates an SLF4J logger. It adds the logger as an instance variable named log. Use it like this: log.info("This is a log line"): This annotation is in the lombok.extern.slf4j package."
//public class PetStoreController {
//	
//	@Autowired
//	private PetStoreService petStoreService;
//
//	@PostMapping("/contributor") // This contributor name will vary if it is for "pet store" or "pet park" or "album"
//	@ResponseStatus(code = HttpStatus.CREATED)
//	public Contributor insertContributor(@RequestBody Contributor contributorData) { // REMEMBER: A contributor will be named accordingly if it is for "pet store" or "pet park" or "album"
//		log.info("Creating contributor {}", contributorData);
////		return petStoreService.saveContributor(contributorData);
//	}
//	
////	@PutMapping("/contributor/{contributorId}")
////	public Contributor updateContributor(@PathVariable Long contributorId,
////			@RequestBody Contributor contributorData) {
////		contributorData.setContributorId(contributorId);
////		log.info("Updating Contributor {}", contributorData);
////		return petStoreService.saveContributor(contributorData);
//	}
//
//}
