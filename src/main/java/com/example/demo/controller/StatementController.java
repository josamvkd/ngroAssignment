package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.StatementService;
import com.example.demo.view.StatementView;

@RestController
public class StatementController {
	
	@Autowired
	private StatementService statementService;
		
	@GetMapping("/statements")
	public List<StatementView> statments(@RequestParam(required=true,name="accountId") String accountId,@RequestParam(required=false,name="fromDate") String fromDate,@RequestParam(required=false,name="toDate") String toDate,@RequestParam(required=false,name="fromAmount") String fromAmount,@RequestParam(required=false,name="toAmount") String toAmount) {
		return statementService.getStatements(accountId,fromDate,toDate,fromAmount,toAmount);
	}
	
}
