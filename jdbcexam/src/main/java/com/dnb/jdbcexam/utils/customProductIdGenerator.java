package com.dnb.jdbcexam.utils;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.hibernate.type.spi.TypeConfiguration;

	 
//Custom product ID generator extending SequenceStyleGenerator
	public class customProductIdGenerator extends SequenceStyleGenerator{

	    // Parameter name for the value prefix
		public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
		public static final String VALUE_PREFIX_DEFAULT = "";
		private String valuePrefix;
		
	    // Parameter name for the number format
		public static final String NUMBER_FORMAT_PARAMETER = "numberPrefix";
		public static final String NUMBER_FORMAT_DEFAULT = "%d";
		private String numberFormat;


		@Override
		public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
			// TODO Auto-generated method stub
	        // Generate the custom product ID by combining the value prefix and the generated number
			return valuePrefix + String.format(numberFormat, super.generate(session, object));

		}

		@Override
		public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) throws MappingException {
			// TODO Auto-generated method stub
	        // Configure the custom product ID generator
			super.configure(new TypeConfiguration().getBasicTypeRegistry().getRegisteredType(Long.class),parameters,serviceRegistry);
			valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, parameters, VALUE_PREFIX_DEFAULT);
			numberFormat= ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, parameters, NUMBER_FORMAT_DEFAULT);
		}
}
