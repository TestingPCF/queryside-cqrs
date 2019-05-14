package com.cqrs.product.queryside.util;

import com.cqrs.product.queryside.bean.TokenInfo;
import com.cqrs.product.queryside.client.RestClient;
import com.cqrs.product.queryside.constant.CartConstant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

/**
 * EntityTransformerUtility - Utility class to convert java object to Json and vice-versa.
 * @author kumar_sanjay
 */
public class EntityTransformerUtility {

	private static final Logger LOG = LoggerFactory.getLogger(EntityTransformerUtility.class);
	
	/**
	 * Convert Java object to Json.
	 * @param object
	 * @return String
	 */
	public static String convertJavaToJsonString(final Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = objectMapper.writeValueAsString(object);
		} catch (JsonParseException ex) {
			LOG.error(ex.getMessage());
		} catch (JsonMappingException ex) {
			LOG.error(ex.getMessage());
		} catch (IOException ex) {
			LOG.error(ex.getMessage());
		}
		return jsonString;
	}

	/**
	 * @param authToken
	 * @return
	 * @throws IOException
	 */
	public static TokenInfo getTokenInfo(final String authToken) throws IOException {
		ResponseEntity<Object> response = RestClient
				.getResponseFromMS(CartConstant.TOKEN,
						null, authToken, null);
		JsonNode jsonNode = new ObjectMapper().valueToTree(response.getBody());
		String json = new ObjectMapper().writeValueAsString(jsonNode);
		return new ObjectMapper().readValue(json, TokenInfo.class);
	}

}
