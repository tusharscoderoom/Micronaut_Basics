package com.tdhiman.service;

import java.util.List;

import com.tdhiman.models.Customer;

public interface CustomerService {
  Customer getCustomer(Long id);
  List<Customer> getAllCustomer();
  Customer createCustomer(Customer customer);
  Customer updateCustomer(Customer customer);
  void deleteCustomer(Long id);
}
