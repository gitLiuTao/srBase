package com.sunrise.base.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.TypeReference;

public class JsonUtil {

	private static final Logger logger = Logger.getLogger(JsonUtil.class);

	private static final ObjectMapper jacksonObjectMapper = new ObjectMapper();

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String writeValueAsString(Object value) {
		if (value == null) {
			return null;
		}
		String result = null;
		try {
			result = jacksonObjectMapper.writeValueAsString(value);
		} catch (JsonGenerationException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Could't generation Object " + value.getClass(), e);
			}
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Generated value " + value + " result is " + result);
		}
		return result;
	}

	public static <T> T readValueWithString(String content, Class<T> valueType) {
		if (content == null) {
			return null;
		}
		T result = null;
		try {
			result = jacksonObjectMapper.readValue(content, valueType);
		} catch (JsonParseException e) {
			if (logger.isInfoEnabled()) {
				logger.info("Could't parse Object " + content, e);
			}
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static <T> T readValueWithString(String content, TypeReference valueTypeRef) {
		if (content == null) {
			return null;
		}
		T result = null;
		try {
			result = jacksonObjectMapper.readValue(content, valueTypeRef);
		} catch (JsonParseException e) {
			if (logger.isInfoEnabled()) {
				logger.info("Could't parse Object " + content, e);
			}
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param parameter
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String writeWithoutAnnoatioin(Object value) {
		if (value == null) {
			return null;
		}
		String result = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationConfig.Feature.USE_ANNOTATIONS, false);
			objectMapper.configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, false);
			objectMapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
			objectMapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
			objectMapper.setDateFormat(DATE_FORMAT);
			result = objectMapper.writeValueAsString(value);
		} catch (JsonGenerationException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Could't generation Object " + value.getClass(), e);
			}
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Generated value " + value + " result is " + result);
		}
		return result;
	}

	public static void writeJsonToResponse(Object value, ServletResponse response) {
		String result = null;
		try {
			result = jacksonObjectMapper.writeValueAsString(value);
		} catch (JsonGenerationException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Could't generation Object " + value.getClass(), e);
			}
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Generated value " + value + " result is " + result);
		}
		try {
			response.setContentType("application/json" + ";charset=UTF-8");
			response.getWriter().write(result);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
