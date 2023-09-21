package com.dnb.productdemo.Utils;


import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.hibernate.type.spi.TypeConfiguration;

//The CustomIdGenerator class is responsible for generating custom unique IDs for entities.
//It provides a method for generating custom IDs based on specific rules or criteria.


public class CustomProductIdGenerator extends SequenceStyleGenerator {
	public static final String VALUE_PREFIX_PARAMETER = "VALUEPREFIX";
	public static final String VALUE_PREFIX_DEFAULT = "";
	private String valueprefix;

	public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
	public static final String NUMBER_FORMAT_DEFAULT = "%05d";
	private String numberFormat;

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		return valueprefix + String.format(numberFormat, super.generate(session, object));

	}

	@Override
	public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) throws MappingException {

		super.configure(new TypeConfiguration().getBasicTypeRegistry().getRegisteredType(Long.class), parameters,
				serviceRegistry);
		valueprefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, parameters, VALUE_PREFIX_DEFAULT);
		numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, parameters, NUMBER_FORMAT_DEFAULT);

	}

}
