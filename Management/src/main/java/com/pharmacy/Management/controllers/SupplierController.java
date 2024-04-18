package com.pharmacy.Management.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pharmacy.Management.DTO.SupplierDTO;
import com.pharmacy.Management.models.Supplier;
import com.pharmacy.Management.repository.SupplierRepository;
import com.pharmacy.Management.services.SupplierService;

import jakarta.validation.Valid;

@Controller
public class SupplierController {
	
	 @Autowired
	    private SupplierRepository repository;

	    @GetMapping("/supplier")
	    public String showSupplierList(Model model) {
	        model.addAttribute("suppliers", repository.findAll());
	        return "supplier/supplier";
	    }

	    @GetMapping("/supplier/create")
	    public String showCreateSupplierPage(Model model) {
	        SupplierDTO supplierDTO = new SupplierDTO();
	        model.addAttribute("supplierDTO", supplierDTO);
	        return "supplier/CreateSupplier";
	    }

	    @PostMapping("/supplier/create")
	    public String createSupplier(
	            @Valid @ModelAttribute("supplierDTO") SupplierDTO supplierDTO,
	            BindingResult result
	    ) {
	        if (result.hasErrors()) {
	            return "supplier/CreateSupplier";
	        }
	        Supplier supplier = new Supplier();
	        supplier.setSupplierName(supplierDTO.getSupplierName());
	        supplier.setSupplierEmail(supplierDTO.getSupplierEmail());
	        supplier.setSupplierContact(supplierDTO.getSupplierContact());
	        supplier.setSupplierAddress(supplierDTO.getSupplierAddress());
	        
	        supplier.setCompanyName(supplierDTO.getCompanyName());
	        supplier.setContactPerson(supplierDTO.getContactPerson());
	        supplier.setDrugsSupplied(supplierDTO.getDrugsSupplied());	        
	        
	        repository.save(supplier);
	        return "redirect:/supplier";
	    }
	    
	    @GetMapping("/supplier/delete/{supplierID}")
	    public String deleteSupplier(@PathVariable("supplierID") int id) {
	        // Assuming the repository has a deleteById method
	        repository.deleteById(id);
	        return "redirect:/supplier";
	    }
	    
	    @GetMapping("/supplier/edit/{supplierID}")
		public String showUpdateSupplier(@PathVariable ("supplierID")int id,Model model) {
			Optional<Supplier> supplierDTO = repository.findById(id);
			model.addAttribute("supplierDTO",supplierDTO);
			return "supplier/EditSupplier";
		}
		
		@PostMapping("/supplier/edit")
		public String editSupplier(Supplier supplier) {
			repository.save(supplier);
			return "redirect:/supplier";
			
		}
		
		@GetMapping("/supplier/view/{supplierID}")
		public String showSupplier(@PathVariable ("supplierID")int id,Model model) {
			Optional<Supplier> supplierDTO = repository.findById(id);
			model.addAttribute("supplierDTO",supplierDTO);
			return "supplier/ViewSupplier";
		}
		
		@PostMapping("/supplier/view")
		public String viewSupplier(Supplier supplier) {
			
			return "redirect:/supplier";
			
		}
		
		
		
		 @Autowired
		    private SupplierService supplierService;

		    @GetMapping("/searchSupplier")
		    public String searchSupplier(@RequestParam String keyword, Model model) {
		        List<Supplier> filteredSuppliers = supplierService.filterSuppliersBySupplierName(keyword);
		        model.addAttribute("suppliers", filteredSuppliers);
		        return "supplier/supplier"; // return the name of the view
		    }
		    
		    
	    @GetMapping("/supplier/mail/{supplierID}")
		public String mailSupplier(@PathVariable ("supplierID")int id,Model model) {
			Optional<Supplier> supplierDTO = repository.findById(id);
			model.addAttribute("supplierDTO",supplierDTO);
			return "supplier/MailSupplier";
		}
		
	    @PostMapping("/supplier/mail")
	    public String mailSupplier(@ModelAttribute("supplier") Supplier supplier, 
	    		@RequestParam("drugsReq") String drugsReq) {
	        supplierService.sendSimpleEmail(supplier, drugsReq);
	        return "redirect:/supplier";
	    }

			


}
