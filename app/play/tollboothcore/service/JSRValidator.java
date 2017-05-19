package play.tollboothcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.*;

import play.tollboothcore.exception.GatewayServiceValidationException;


/**
 * Validator service uses a JSR validator implementation and returns a 
 * Spring Errors object.
 * 
 */
@Service
public class JSRValidator {
	
	
	private Validator validator;
	

	
	protected Errors getErrorsObject(Object target, String objectName){
		return new BeanPropertyBindingResult(target, objectName);
	}

	/**
	 * Validate a bean class that has JSR validation annotations on its fields.
	 * @param bean
	 * @return
	 */
	public Errors validate(Object bean){
		return validate(bean, bean.getClass().getSimpleName());
	}
	
	/**
	 * Validate a bean class that has JSR validation annotations on its fields.
	 * @param bean
	 * @param beanName
	 * @return
	 */
	public Errors validate(Object bean, String beanName){
		Errors errors = getErrorsObject(bean, beanName);
		ValidationUtils.invokeValidator(validator, bean, errors);
		return errors;
	}
	
	/**
	 * Inspects the errors object and throws a PaymentServiceValidationException
	 * if there are any errors.
	 * @param message The message in the exception to throw
	 * @param errors The Errors object (returned by one of the validate methods)
	 * @throws PaymentServiceValidationException 
	 */
	public void throwExceptionIfErrorsExist(String message, Errors errors){
		if(errors.hasErrors()){
			throw new GatewayServiceValidationException(message, errors);
		}
	}
}
