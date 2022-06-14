package com.deliverMe.tqs;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.deliverMe.tqs.model.Address;
import com.deliverMe.tqs.model.Manager;
import com.deliverMe.tqs.model.Purchase;
import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.model.Store;
import com.deliverMe.tqs.repository.AddressRepository;
import com.deliverMe.tqs.services.AddressService;
import com.deliverMe.tqs.services.ManagerService;
import com.deliverMe.tqs.services.PurchaseService;
import com.deliverMe.tqs.services.RiderService;
import com.deliverMe.tqs.services.StoreService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration
public class TqsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TqsApplication.class, args);
	}

	@Transactional
	@Bean
	public CommandLineRunner demo(ManagerService managerService, PurchaseService purchaseService, RiderService riderService, StoreService storeService, AddressService addressService){
		return(args) -> {
			

			if (riderService.getRiders().size()==0){
				Rider r1 = new Rider("Quim", "2001-02-29", "quim@ua.pt");
				Rider r2 = new Rider("Maria", "1999-12-19", "maria@ua.pt");
				riderService.saveRider(r1);
				riderService.saveRider(r2);
			}

			if (managerService.getManagers().size()==0){
				Manager m = new Manager("admin", "1987-12-25", "admin@ua.pt");
				managerService.saveManager(m);
			}

			if (addressService.getAddresses().size()==0 && storeService.getStores().size()==0 && purchaseService.getAllPurchases().size()==0){
				Address a = new Address("Rua da Estia", "Campia", "Portugal", "3670");
				Address a2 = new Address("Rua da Velhice", "Ovar", "Portugal", "3800");
	
				addressService.saveAddress(a);
				addressService.saveAddress(a2);
				Store s = new Store("BookShelf", a, 90.0, 89.0);
				storeService.saveStore(s);
				Purchase p1 = new Purchase(s, "Maria",a2);
				purchaseService.addPurchase(p1);
			}		

		};
	}

}
