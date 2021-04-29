package com.example.demo.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CustomeException;
import com.example.demo.service.StatementService;
import com.example.demo.utils.CustomUtils;
import com.example.demo.view.StatementView;

@Service
public class StatementServiceImpl implements StatementService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatementServiceImpl.class);

	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	private static final String ROLE_USER = "ROLE_USER";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<StatementView> getStatements(String accountId, String fromDate, String toDate, String fromAmount,
			String toAmount) {
		LOGGER.info("Fetching statements started");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			LOGGER.debug("Access denied");
			throw new CustomeException(HttpStatus.FORBIDDEN, "Access denied");
		}
		validateParameter(accountId, fromAmount, toAmount);

		String userRoll = auth.getAuthorities().iterator().next().toString();

		String query = queryBuilder(userRoll,accountId, fromDate, toDate, fromAmount, toAmount);

		LOGGER.info("query :{}", query);

		return selectStatements(query);
	}

	private void validateParameter(String accountId, String fromAmount, String toAmount) {
		if (StringUtils.isEmpty(accountId)) {
			LOGGER.debug("Invalid accountID :{}", accountId);
			throw new CustomeException(HttpStatus.BAD_REQUEST, "Invalid accountID");
		}
		if (!CustomUtils.isNumber(accountId)) {
			LOGGER.debug("Invalid accountID :{}", accountId);
			throw new CustomeException(HttpStatus.BAD_REQUEST, "Invalid accountID");
		}
		if (!CustomUtils.isNumeric(fromAmount)) {
			LOGGER.debug("Invalid fromAmount :{}", fromAmount);
			throw new CustomeException(HttpStatus.BAD_REQUEST, "Invalid fromAmount");
		}
		if (!CustomUtils.isNumeric(toAmount)) {
			LOGGER.debug("Invalid toAmount :{}", toAmount);
			throw new CustomeException(HttpStatus.BAD_REQUEST, "Invalid toAmount");
		}
	}

	private String queryBuilder(String userRoll,String accountId,  String fromDate, String toDate, String fromAmount,
			String toAmount) {
		StringBuilder query = new StringBuilder(
				"select acc.ID as ID,acc.account_type as accountType,acc.account_number as accountNumber,stmt.ID as statementId ,stmt.account_id as account_id,stmt.datefield as datefield,stmt.amount as amount FROM Account acc inner join Statement stmt on(acc.ID=stmt.account_id) ");
		query.append(" WHERE acc.ID=").append(accountId);
		
		Calendar calendar = Calendar.getInstance();
		if (ROLE_ADMIN.equals(userRoll)) {
			if (StringUtils.isNotEmpty(fromDate) && StringUtils.isNotEmpty(toDate)) {
				query.append(" and stmt.datefield >='").append(CustomUtils.fomateDateWithdot(fromDate))
						.append("' and stmt.datefield <='").append(CustomUtils.fomateDateWithdot(toDate)).append("'");
			}
			if (StringUtils.isNotEmpty(fromAmount) && StringUtils.isNotEmpty(toAmount)) {
				query.append(" and stmt.amount between '").append(fromAmount).append("' and '").append(toAmount)
						.append("'");
			}
			if (StringUtils.isEmpty(fromDate) && StringUtils.isEmpty(toDate) && StringUtils.isEmpty(fromAmount) && StringUtils.isEmpty(toAmount)) {
				query.append(" and stmt.datefield <='").append(CustomUtils.formateDate(calendar)).append("' and stmt.datefield >='")
				.append(CustomUtils.minusThreeMonth(calendar)).append("'");
			}
			
		} else if (ROLE_USER.equals(userRoll)) {

			if (StringUtils.isNotEmpty(fromDate) || StringUtils.isNotEmpty(toDate) || StringUtils.isNotEmpty(fromAmount)
					|| StringUtils.isNotEmpty(toAmount)) {
				LOGGER.debug("unauthorized access :test user with request parameters");
				throw new CustomeException(HttpStatus.UNAUTHORIZED, "unauthorized access");
			}

			
			query.append(" and stmt.datefield <='").append(CustomUtils.formateDate(calendar)).append("' and stmt.datefield >='")
					.append(CustomUtils.minusThreeMonth(calendar)).append("'");
		}
		return query.toString();
	}

	private List<StatementView> selectStatements(String query) {
		List<StatementView> statementViews = null;
		try {
			statementViews = jdbcTemplate.query(query, new RowMapper<StatementView>() {
				@Override
				public StatementView mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new StatementView(rs.getString("ID"), rs.getString("accountType"),
							rs.getString("accountNumber"), rs.getString("statementId"), rs.getString("datefield"),
							rs.getString("amount"));
				}
			});

		} catch (Exception e) {
			throw new CustomeException(HttpStatus.UNPROCESSABLE_ENTITY, "something wentwrong try again later");
		}
		return statementViews;
	}
}
