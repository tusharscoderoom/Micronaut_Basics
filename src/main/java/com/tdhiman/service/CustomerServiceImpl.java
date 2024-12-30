package com.tdhiman.service;

import java.util.List;

import com.tdhiman.models.Customer;
import com.tdhiman.repository.CustomerRepository;

import jakarta.inject.Singleton;

@Singleton
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public Customer getCustomer(Long id) {
    return customerRepository.findById(id).orElse(null);
  }

  @Override
  public List<Customer> getAllCustomer() {
    return customerRepository.findAll();
  }

  @Override
  public Customer createCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  @Override
  public Customer updateCustomer(Customer customer) {
    Customer existingCustomer = customerRepository.findById(customer.getId()).orElse(null);

    if (existingCustomer != null) {
      existingCustomer.setName(existingCustomer.getName());
      existingCustomer.setEmail(existingCustomer.getEmail());
    }

    return customerRepository.update(existingCustomer);
  }

  @Override
  public void deleteCustomer(Long id) {
    customerRepository.deleteById(id);
  }

}
