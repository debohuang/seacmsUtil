package com.gb.common.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonJsonUtil {

	private static Logger log = LoggerFactory.getLogger( JacksonJsonUtil.class );
	
	private final static ObjectMapper objectMapper = new ObjectMapper();
	
	 static {
	        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
	        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//	        objectMapper.configure(JsonParser.Feature.INTERN_FIELD_NAMES, true);
//	        objectMapper.configure(JsonParser.Feature.CANONICALIZE_FIELD_NAMES, true);
//	        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	 }
	
	public static String objectToJsonString(Object obj){
//		ObjectMapper objectMapper = new ObjectMapper();
		String requestJSON = "";
		try {
			requestJSON = objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return requestJSON;
	}
	
	public static HashMap jsonStringToMap(String dataString){
		HashMap resultMap = new HashMap();
//		ObjectMapper objectMapper = new ObjectMapper();
		try {
			resultMap = objectMapper.readValue(dataString, HashMap.class);
		} catch (JsonParseException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * 将JSON字符串转换为对象
	 * 
	 * @param json
	 *            JSON字符串
	 * @param valueType
	 *            对象类型
	 * @return 对象
	 */
	public static <T> T toObject(String json, Class<T> valueType)
	{
		try
		{
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return objectMapper.readValue(json, valueType);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	 /**
     * 将json string反序列化成对象
     *
     * @param json
     * @param valueType
     * @return
     */
    public static <T> T decode(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (JsonParseException e) {
            log.error("decode(String, Class<T>)", e);
        } catch (JsonMappingException e) {
            log.error("decode(String, Class<T>)", e);
        } catch (IOException e) {
            log.error("decode(String, Class<T>)", e);
        }
        return null;
    }

    /**
     * 将json array反序列化为对象
     *
     * @param json
     * @param jsonTypeReference
     * @return
     */
    public static <T> T decode(String json, TypeReference<T> jsonTypeReference) {
        try {
            return (T) objectMapper.readValue(json, jsonTypeReference);
        } catch (JsonParseException e) {
            log.error("decode(String, JsonTypeReference<T>)", e);
        } catch (JsonMappingException e) {
            log.error("decode(String, JsonTypeReference<T>)", e);
        } catch (IOException e) {
            log.error("decode(String, JsonTypeReference<T>)", e);
        }
        return null;
    }
    
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
    	
    	//复杂json处理
    	String str="{\"count\":20,\"start\":0,\"total\":38,\"subjects\":[{\"rating\":{\"max\":10,\"average\":6.7,\"stars\":\"35\",\"min\":0},\"genres\":[\"动作\",\"科幻\",\"冒险\"],\"title\":\"狂暴巨兽\",\"casts\":[{\"alt\":\"https:\\/\\/movie.douban.com\\/celebrity\\/1044707\\/\",\"avatars\":{\"small\":\"https://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p196.jpg\",\"large\":\"https://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p196.jpg\",\"medium\":\"https://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p196.jpg\"},\"name\":\"道恩·强森\",\"id\":\"1044707\"},{\"alt\":\"https:\\/\\/movie.douban.com\\/celebrity\\/1049542\\/\",\"avatars\":{\"small\":\"https://img1.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1486878030.07.jpg\",\"large\":\"https://img1.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1486878030.07.jpg\",\"medium\":\"https://img1.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1486878030.07.jpg\"},\"name\":\"娜奥米·哈里斯\",\"id\":\"1049542\"},{\"alt\":\"https:\\/\\/movie.douban.com\\/celebrity\\/1044708\\/\",\"avatars\":{\"small\":\"https://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p6531.jpg\",\"large\":\"https://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p6531.jpg\",\"medium\":\"https://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p6531.jpg\"},\"name\":\"杰弗里·迪恩·摩根\",\"id\":\"1044708\"}],\"collect_count\":43214,\"original_title\":\"Rampage\",\"subtype\":\"movie\",\"directors\":[{\"alt\":\"https:\\/\\/movie.douban.com\\/celebrity\\/1317388\\/\",\"avatars\":{\"small\":\"https://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1432840050.06.jpg\",\"large\":\"https://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1432840050.06.jpg\",\"medium\":\"https://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1432840050.06.jpg\"},\"name\":\"布拉德·佩顿\",\"id\":\"1317388\"}],\"year\":\"2018\",\"images\":{\"small\":\"https://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p2516079193.jpg\",\"large\":\"https://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p2516079193.jpg\",\"medium\":\"https://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p2516079193.jpg\"},\"alt\":\"https:\\/\\/movie.douban.com\\/subject\\/26430636\\/\",\"id\":\"26430636\"}],\"title\":\"正在上映的电影-北京\"}";
    	
    	ObjectMapper objectMapper = new ObjectMapper();
		Map<String,Object> returnMap = objectMapper.readValue(str, Map.class);
		
		List<Object> subjects=(List<Object>) returnMap.get("subjects");
		
		for (int i = 0; i < subjects.size(); i++) {
			Object object=subjects.get(i);
			Map<String, Object> subObject=(Map<String, Object>) object;
			String title=subObject.get("title").toString();
			System.out.println(title);
		}
    	
	}
    

}
