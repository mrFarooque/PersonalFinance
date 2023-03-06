package com.mdfa.personalFinance.controllers;

import com.mdfa.personalFinance.enums.Category;
import com.mdfa.personalFinance.enums.Type;
import com.mdfa.personalFinance.models.Finance;
import com.mdfa.personalFinance.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/finance")
public class FinanceController {
    @Autowired
    FinanceService financeService;

    @PostMapping("/")
    public ResponseEntity<Finance> newFinance(@RequestBody Finance finance) {
        return new ResponseEntity<>(financeService.newFinance(finance), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinanceById(@PathVariable("id") int id) {
        financeService.deleteFinanceById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/balance/{month}")
    public ResponseEntity<Integer> balanceByMonth(@PathVariable("month") int month) {
        return new ResponseEntity<>(financeService.balanceByMonth(month), HttpStatus.OK);
    }

    @GetMapping("/balance/current")
    public ResponseEntity<Integer> balanceByMonth() {
        int month = LocalDate.now().getMonth().getValue();
        return new ResponseEntity<>(financeService.balanceByMonth(month), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Finance>> listAllFinance() {
        List<Finance> finances = financeService.listAll();
        return new ResponseEntity<>(finances, HttpStatus.OK);
    }

    @GetMapping("/list/{type}")
    public ResponseEntity<List<Finance>> listAllFinanceByType(@PathVariable("type") Type type) {
        List<Finance> finances = financeService.listAllByType(type);
        return new ResponseEntity<>(finances, HttpStatus.OK);
    }

    @GetMapping("/list/{month}")
    public ResponseEntity<List<Finance>> listAllFinanceByMonth(@PathVariable(value = "month") int month) {
        return new ResponseEntity<>(financeService.listAllByMonth(month), HttpStatus.OK);
    }

    @GetMapping("/list/current")
    public ResponseEntity<List<Finance>> listAllFinanceByCurrentMonth() {
        int month = LocalDate.now().getMonth().getValue();
        return new ResponseEntity<>(financeService.listAllByMonth(month), HttpStatus.OK);
    }

    @GetMapping("/list/{type}/{month}")
    public ResponseEntity<List<Finance>> listAllFinanceByTypeAndMonth(@PathVariable("type") Type type, @PathVariable("month") int month) {
        System.out.println("entered");
        if (type.equals("ALL")) {
            return new ResponseEntity<>(financeService.listAllByMonth(month), HttpStatus.OK);
        }
        return new ResponseEntity<>(financeService.listTypeByMonth(type, month), HttpStatus.OK);
    }

    @GetMapping("/list/{type}/current")
    public ResponseEntity<List<Finance>> listAllFinanceByTypeAndCurrentMonth(@PathVariable("type") Type type) {
        int month = LocalDate.now().getMonth().getValue();
        return new ResponseEntity<>(financeService.listTypeByMonth(type, month), HttpStatus.OK);
    }

    @GetMapping("/total/{type}")
    public ResponseEntity<Integer> totalFinanceByType(@PathVariable("type") Type type) {
        return new ResponseEntity<>(financeService.totalByType(type), HttpStatus.OK);
    }

    @GetMapping("/total/{type}/{month}")
    public ResponseEntity<Integer> totalFinanceByTypeAndMonth(@PathVariable("type") Type type, @PathVariable("month") int month) {
        return new ResponseEntity<>(financeService.totalByTypeAndMonth(type, month), HttpStatus.OK);
    }

    @GetMapping("/total/{type}/current")
    public ResponseEntity<Integer> totalFinanceByTypeAndCurrentMonth(@PathVariable("type") Type type) {
        int month = LocalDate.now().getMonth().getValue();
        return new ResponseEntity<>(financeService.totalByTypeAndMonth(type, month), HttpStatus.OK);
    }

    @GetMapping("/total/{type}/{category}/{month}")
    public ResponseEntity<Integer> totalFinanceByTypeAndCategoryAndMonth(@PathVariable("type") Type type,
                                                                         @PathVariable("category") Category category,
                                                                         @PathVariable("month") int month) {
        return new ResponseEntity<>(financeService.totalByTypeAndCategoryAndMonth(type, category, month), HttpStatus.OK);
    }

    @GetMapping("/total/{type}/{category}/current")
    public ResponseEntity<Integer> totalFinanceByTypeAndCategoryAndCurrentMonth(@PathVariable("type") Type type,
                                                                                @PathVariable("category") Category category) {
        int month = LocalDate.now().getMonth().getValue();
        return new ResponseEntity<>(financeService.totalByTypeAndCategoryAndMonth(type, category, month), HttpStatus.OK);
    }

}
