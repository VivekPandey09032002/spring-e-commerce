package com.vivek.commerce;

import java.time.LocalDate;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vivek.commerce.product.Product;
import com.vivek.commerce.product.ProductRepository;
import com.vivek.commerce.product.category.Category;
import com.vivek.commerce.product.category.CategoryRepository;
import com.vivek.commerce.user.Address;
import com.vivek.commerce.user.CommerceUser;
import com.vivek.commerce.user.CommerceUserRepository;
import com.vivek.commerce.user.PaymentInformation;

@SpringBootApplication
public class CommerceApplication implements CommandLineRunner {

	@Autowired
	CommerceUserRepository jpaUserRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(CommerceApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Address address = new Address();
		address.setCity("delhi");
		address.setState("delhi");
		address.setCountry("india");
		address.setZipCode("121004");
		address.setStreet("punk street");

		PaymentInformation paymentInformation = new PaymentInformation();
		paymentInformation.setCvv(2002);
		paymentInformation.setCardHolderName("vivek pandey");
		paymentInformation.setExpirationDate(LocalDate.of(2080,12,10));
		paymentInformation.setCreditCardNumber("1214-1232-1232");
		CommerceUser user = new CommerceUser();
		user.setFirstName("vivek");
		user.setEmail("1@gmail.com");
		user.setMobile("123456890");
		user.setPassword(encoder.encode("root"));
		user.setAddressList(Collections.singletonList(address));
		user.setPaymentInformationList(Collections.singletonList(paymentInformation));

		address.setUser(user);

		if(jpaUserRepository.count() == 0 )
			jpaUserRepository.save(user);
		
	}
	
	
	public Category getCategory() {
		Category category = new Category();
		Category parentCategory = new Category();
		parentCategory.setName("Technology");
		category.setName("Electronics");
		category.setParentCategory(parentCategory);
		return category;
	}
	
	public Product getProduct() {
		Product product = new Product();
		product.setTitle("test product");
		product.setPrice(20.00);		
		product.setBrand("new-brand");		
		product.setCategory(getCategory());
		return product;
	}
	
	public void saveProduct() {
		Category category = getCategory();
		categoryRepository.save(category);
		Product product = getProduct();
		productRepository.save(product);
	}
}
