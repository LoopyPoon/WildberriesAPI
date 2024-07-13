package com.example.wildberriesapi.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SupplyController {

    SupplierService supplierService;

    @Autowired
    private SupplyController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/supplies")
    public String getSupply(Model model) {
        List<Supply> supplies = supplierService.getSuppliers();
        model.addAttribute("supplies", supplies);
        return "supplies";
    }

}
