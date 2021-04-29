package com.example.demo.service;

import java.util.List;

import com.example.demo.view.StatementView;

public interface StatementService {
	public List<StatementView> getStatements(String accountId,String fromDate,String toDate,String fromAmount,String toAmount);
	
}
