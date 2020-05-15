package com.kafonin.employee.repository;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.kafonin.employee.model.People;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PeopleRepository {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleRepository.class);

	@Autowired
	private DynamoDBMapper mapper;

	public void insertIntoDynamoDB(People people) {
		mapper.save(people);
	}

	public People getOnePeopleDetails(String peopleId, String lastName) {
		return mapper.load(People.class, peopleId, lastName);
	}

	public void updatePeopleDetails(People people) {
		try {
			mapper.save(people, buildDynamoDBSaveExpression(people));
		} catch (ConditionalCheckFailedException exception) {
			LOGGER.error("invalid data - " + exception.getMessage());
		}
	}

	public void deletePeopleDetails(People people) {
		mapper.delete(people);
	}

	public DynamoDBSaveExpression buildDynamoDBSaveExpression(People people) {
		DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
		Map<String, ExpectedAttributeValue> expected = new HashMap<>();
		expected.put("peopleId", new ExpectedAttributeValue(new AttributeValue(people.getPeopleId()))
				.withComparisonOperator(ComparisonOperator.EQ));
		saveExpression.setExpected(expected);
		return saveExpression;
	}
}