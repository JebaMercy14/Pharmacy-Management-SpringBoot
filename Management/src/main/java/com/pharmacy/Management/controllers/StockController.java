package com.pharmacy.Management.controllers;



import java.util.List;
import java.util.Map;
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

import com.pharmacy.Management.DTO.StockDTO;
import com.pharmacy.Management.models.Stock;
import com.pharmacy.Management.repository.StockRepository;
import com.pharmacy.Management.services.StockService;

import jakarta.validation.Valid;

@Controller
public class StockController {
	
	private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stock/dashboard")
    public String showDashboard(Model model) {
        // Fetch data from your service/repository
        int totalDrugs = stockService.getTotalStocks();
        int expiredDrugs = stockService.getExpiredDrugs();
        int outOfStockDrugs = stockService.getOutOfStockDrugs();
     // Retrieve analysis data from the service
        
        Map<String, Long> drugCategoryDistribution = stockService.getDrugCategoryDistribution();
        Map<String, Long> topManufacturers = stockService.getTopManufacturers();
        
        double averageStockQuantity = stockService.getAverageStockQuantity();
        int minStockQuantity = stockService.getMinStockQuantity();
        int maxStockQuantity = stockService.getMaxStockQuantity();
        double averagePrice = stockService.getAveragePrice();
        int minPrice = stockService.getMinPrice();
        int maxPrice = stockService.getMaxPrice();

        // Add data to the model
        model.addAttribute("totalDrugs", totalDrugs);
        model.addAttribute("expiredDrugs", expiredDrugs);
        model.addAttribute("outOfStockDrugs", outOfStockDrugs);
        
     // Add analysis data to the model
        model.addAttribute("totalDrugs", totalDrugs);
        model.addAttribute("drugCategoryDistribution", drugCategoryDistribution);
        model.addAttribute("topManufacturers", topManufacturers);
        
        model.addAttribute("averageStockQuantity", averageStockQuantity);
        model.addAttribute("minStockQuantity", minStockQuantity);
        model.addAttribute("maxStockQuantity", maxStockQuantity);
        model.addAttribute("averagePrice", averagePrice);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);

        return "/stock/StockDashboard"; // Return the HTML template name
    }
	
	@Autowired
    private  StockRepository repository;
	
	

    @GetMapping("/stock")
    public String showStockList(Model model) {
        model.addAttribute("stocks", repository.findAll());
        return "stock/stock";
    }
    @PostMapping("/stock")
    public String showstockList(Model model) {
        model.addAttribute("stocks", repository.findAll());
        return "stock/stock";
    }

    @GetMapping("/stock/create")
    public String showCreateStockPage(Model model) {
        StockDTO stockDTO = new StockDTO();
        model.addAttribute("stockDTO", stockDTO);
        return "stock/CreateStock";
    }

    @PostMapping("/stock/create")
    public String createStock(
            @Valid @ModelAttribute("stockDTO") StockDTO stockDTO,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "stock/CreateStock";
        }
        Stock stock = new Stock();
        
        stock.setDrugName(stockDTO.getDrugName());
        stock.setDrugCategory(stockDTO.getDrugCategory());
        stock.setManufacturer(stockDTO.getManufacturer());
        stock.setManufacturedDate(stockDTO.getManufacturedDate());
        stock.setExpiredDate(stockDTO.getExpiredDate());
        stock.setQuantity(stockDTO.getQuantity());
        stock.setPrice(stockDTO.getPrice());
        stock.setDrugUse(stockDTO.getDrugUse());
        stock.setSideEffect(stockDTO.getSideEffect());
        repository.save(stock);
        return "redirect:/stock";
    }  
    
    

    
    @GetMapping("/stock/delete/{drugID}")
    public String deleteStock(@PathVariable("drugID") int id) {
        // Assuming the repository has a deleteById method
        repository.deleteById(id);
        return "redirect:/stock";
    }
    
    
    @GetMapping("/stock/edit/{drugID}")
    public String showUpdateStock(@PathVariable("drugID") int id, Model model) {        

        Optional<Stock> stockDTO = repository.findById(id);
        model.addAttribute("stockDTO", stockDTO);
        return "stock/EditStock";
    }    
    
    
    @PostMapping("/stock/edit")
    public String editStock(Stock stock) {
        
        repository.save(stock);
        return "redirect:/stock";
    }
    
    @GetMapping("/stock/view/{drugID}")
    public String showViewStock(@PathVariable("drugID") int id, Model model) {        

        Optional<Stock> stockDTO = repository.findById(id);
        model.addAttribute("stockDTO", stockDTO);
        return "stock/ViewStock";
    }    
    
    
    @PostMapping("/stock/view")
    public String viewStock(Stock stock) {
                
        return "stock/ViewStock";
    }
    
    @GetMapping("/searchDrug")
    public String searchDrug(@RequestParam String keyword, Model model) {
        List<Stock> filteredStocks = stockService.filterStocksByDrugName(keyword);
        model.addAttribute("stocks", filteredStocks);
        return "stock/stock"; // return the name of the view
    }
    
    
}

