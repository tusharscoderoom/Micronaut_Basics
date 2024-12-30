package com.tdhiman.contoller;

import java.util.List;
import com.tdhiman.models.Customer;
import com.tdhiman.service.CustomerService;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get All Customers", description = "Get All Customers from the Database")
    @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "Customer")))
    @ApiResponse(responseCode = "200", description = "List of Customers Retrieved Successfully")
    @ApiResponse(responseCode = "404", description = "No Customers Found")
    @ApiResponse(responseCode = "500", description = "Unable to Process Your Request, Internal Server Error. Please Try Again Later!")
    @Tag(name = "Get All Customers")
    public HttpResponse<List<Customer>> getAllCustomers() {
        return HttpResponse.ok(customerService.getAllCustomer());
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create New Customer", description = "Creates a New Customer Entry into the Database")
    @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "Customer")))
    @ApiResponse(responseCode = "200", description = "Customer Record Saved Successfully")
    @ApiResponse(responseCode = "404", description = "No Customers Found")
    @ApiResponse(responseCode = "500", description = "Unable to Process Your Request, Internal Server Error. Please Try Again Later!")
    @Tag(name = "Create New Customer Record")
    public HttpResponse<Customer> createCustomer(@Body Customer customer) {
        return HttpResponse.created(customerService.createCustomer(customer));
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Customer Details By Id", description = "Retrieves existing Customer Record By Customer Id from the Database")
    @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "Customer")))
    @ApiResponse(responseCode = "200", description = "Customer Record Retrieved Successfully")
    @ApiResponse(responseCode = "404", description = "No Customer Record Found")
    @ApiResponse(responseCode = "500", description = "Unable to Process Your Request, Internal Server Error. Please Try Again Later!")
    @Tag(name = "Get Customer Details by Id")
    public HttpResponse<Customer> getCustomerById(@PathVariable Long id) {
        return HttpResponse.ok(customerService.getCustomer(id));
    }

    @Post("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update Existing Customer Details", description = "Updates Existing Customer Record into the Database")
    @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "Customer")))
    @ApiResponse(responseCode = "200", description = "Customer Record Updated Successfully")
    @ApiResponse(responseCode = "404", description = "No Customer Record Found")
    @ApiResponse(responseCode = "500", description = "Unable to Process Your Request, Internal Server Error. Please Try Again Later!")
    @Tag(name = "Update Customer Details")
    public HttpResponse<Customer> updateCustomer(@Body Customer existingCustomer) {
        Customer updatedCustomer = customerService.updateCustomer(existingCustomer);

        if (updatedCustomer != null) {
            return HttpResponse.ok(updatedCustomer);
        } else {
            return HttpResponse.notFound();
        }
    }

    @Delete("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete Customer Details By Id", description = "Deletes existing Customer Record By Customer Id from the Database")
    @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "Customer")))
    @ApiResponse(responseCode = "200", description = "Customer Record Deleted Successfully")
    @ApiResponse(responseCode = "404", description = "No Customer Record Found")
    @ApiResponse(responseCode = "500", description = "Unable to Process Your Request, Internal Server Error. Please Try Again Later!")
    @Tag(name = "Delete Customer Details by Id")
    public HttpResponse<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);

        return HttpResponse.ok("Customer Record Deleted");
    }
}