package main.java.cn.com.sinosoft.app.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
/**
 * Jackson �򵥷�װ��
 * @ClassName: JacksonBinder
 * @Description:Jackson �򵥷�װ��
 * @author lihengjun
 * �޸�ʱ�䣺 2013��11��8�� ����9:56:18
 * �޸����ݣ��½�
 */
public class JacksonBinder {

	private static Logger logger = Logger.getLogger(JacksonBinder.class);

	private ObjectMapper mapper;
//	private XmlMapper xmlMapper;

	public JacksonBinder(Inclusion inclusion) {
		this.mapper = new ObjectMapper();
		// ��������������
		this.mapper.getSerializationConfig().setSerializationInclusion(inclusion);
		// ��������ʱ����JSON�ַ��д��ڶ�Java����ʵ��û�е�����
		this.mapper.getDeserializationConfig()
				.set(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
						false);

//		xmlMapper = new XmlMapper();
//		xmlMapper.getSerializationConfig().setSerializationInclusion(inclusion);
//		xmlMapper.getDeserializationConfig().set(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
//						false);
		
	}

	/**
	 * �������ȫ�����Ե�Json�ַ��Binder.
	 */
	public static JacksonBinder buildNormalBinder() {
		return new JacksonBinder(Inclusion.ALWAYS);
	}

	/**
	 * ����ֻ����ǿ����Ե�Json�ַ��Binder.
	 */
	public static JacksonBinder buildNonNullBinder() {
		return new JacksonBinder(Inclusion.NON_NULL);
	}

	/**
	 * ����ֻ�����ʼֵ���ı�����Ե�Json�ַ��Binder.
	 */
	public static JacksonBinder buildNonDefaultBinder() {
		return new JacksonBinder(Inclusion.NON_DEFAULT);
	}

	/**
	 * ���JSON�ַ�ΪNull��"null"�ַ�,����Null. ���JSON�ַ�Ϊ"[]",���ؿռ���.
	 * 
	 * �����ȡ������List/Map,�Ҳ���List<String>���ּ�����ʱʹ���������: List<MyBean> beanList =
	 * binder.getMapper().readValue(listString, new
	 * TypeReference<List<MyBean>>() {});
	 */
	public <T> T fromJson(String jsonString, Class<T> clazz) {
		if (ObjectUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return this.mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			logger.error("parse json string error:" + jsonString, e);
			return null;
		}
	}


	/**
	 * Դ��ݶ��� ת�� ��Ŀ�����
	 * @Title: convertValue
	 * @Description: 
	 * @param fromValue Դ��ݶ���
	 * @param toValueType Ŀ���������
	 * @return
	 * @author lihengjun
	 * �޸�ʱ�䣺 2013��11��5�� ����11:10:00
	 * �޸����ݣ��½�
	 */
	public <T> T convertValue(Object fromValue, Class<T> toValueType) {
		if (ObjectUtils.isEmpty(fromValue)) {
			return null;
		}
		return this.mapper.convertValue(fromValue, toValueType);
	}
	/**
	 * 
	 * @Title: fromJsonFile
	 * @Description: ���json�ļ�ת���ɶ���
	 * @param file
	 *            json �ļ�
	 * @param clazz
	 * @return
	 * @author lihengjun �޸�ʱ�䣺 2013��11��7�� ����11:22:01 �޸����ݣ��½�
	 */
	public <T> T fromJsonFile(File file, Class<T> clazz) {
		if (ObjectUtils.isEmpty(file)) {
			return null;
		}

		try {
			return this.mapper.readValue(file, clazz);
		} catch (IOException e) {
			logger.error("parse json string error:" + file, e);
			return null;
		}
	}

	/**
	 * ������ΪNull,����"null". ����Ϊ�ռ���,����"[]".
	 */
	public String toJson(Object object) {

		try {
			return this.mapper.writeValueAsString(object);
		} catch (IOException e) {
			logger.error("write to json string error:" + object, e);
			return null;
		}
	}

	/**
	 * ����ת���������͵�format pattern,�������Ĭ�ϴ�ӡTimestamp������.
	 */
	public void setDateFormat(String pattern) {
		if (ObjectUtils.isNotBlank(pattern)) {
			DateFormat df = new SimpleDateFormat(pattern);
			this.mapper.getSerializationConfig().setDateFormat(df);
			this.mapper.getDeserializationConfig().setDateFormat(df);
		}
	}

	/**
	 * ȡ��Mapper����һ�������û�ʹ���������л�API.
	 */
	public ObjectMapper getMapper() {
		return this.mapper;
	}

//	/**
//	 * Object to XML, default root name is 'root'
//	 * 
//	 * @param nameValuePair
//	 * @return
//	 */
//	public String toXML(Object object) {
//		if (ObjectUtils.isEmpty(object)) {
//			return null;
//		}
//		try {
//			return getXmlMapper().writeValueAsString(object);
//		} catch (JsonGenerationException e) {
//			logger.error("write to xml string error:" + object, e);
//		} catch (JsonMappingException e) {
//			logger.error("write to xml string error:" + object, e);
//		} catch (IOException e) {
//			logger.error("write to xml string error:" + object, e);
//		}
//		return null;
//	}
//	
//	/**
//	 * 
//	 * @Title: fromXML
//	 * @Description: 
//	 * @param xml
//	 * @param clazz
//	 * @return
//	 * @author lihengjun
//	 * �޸�ʱ�䣺 2013��11��7�� ����12:51:10
//	 * �޸����ݣ��½�
//	 */
//	public <T> T fromXML(String xml, Class<T> clazz) {
//		if (ObjectUtils.isEmpty(xml)) {
//			return null;
//		}
//
//		try {
//			return getXmlMapper().readValue(xml, clazz);
//		} catch (IOException e) {
//			logger.error("parse json string error:" + xml, e);
//			return null;
//		}
//	}
//	public XmlMapper getXmlMapper() {
//		return xmlMapper;
//	}

}