package com.example.wildberriesapi.statistics.incomes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SupplyController {

    SupplierIncomesService supplierIncomesService;

    @Autowired
    private SupplyController(SupplierIncomesService supplierIncomesService) {
        this.supplierIncomesService = supplierIncomesService;
    }

    @GetMapping("/supplies")
    public String getSupply(Model model) {
        List<SupplyIncomes> supplies = supplierIncomesService.getSuppliers();
        model.addAttribute("supplies", supplies);
        return "supplies";
    }

}
