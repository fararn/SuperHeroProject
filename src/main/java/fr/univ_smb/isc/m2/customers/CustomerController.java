package fr.univ_smb.isc.m2.customers;

import fr.univ_smb.isc.m2.config.rest.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired(required = true)
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Customer> customer() {
        return customerService.all();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Customer customer(@PathVariable String id) {

        List<Customer> collect = customerService.all().stream().filter(u -> u.id == parseInt(id)).collect(toList());

        if (collect.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return collect.get(0);

    }

}