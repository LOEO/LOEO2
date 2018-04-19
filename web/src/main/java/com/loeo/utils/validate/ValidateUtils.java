package com.loeo.utils.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.springframework.util.CollectionUtils;

/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2018-03-02 15:35:39
 * @version ：2018 Version：1.0

 */
public class ValidateUtils {
	private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

	public static void validate(Object object, Class<?>... groups) {
		Set<ConstraintViolation<Object>> result = VALIDATOR.validate(object,groups);
		if (!CollectionUtils.isEmpty(result)) {
			StringBuilder errorMsg = new StringBuilder();
			for (ConstraintViolation<Object> objectConstraintViolation : result) {
				errorMsg.append("[")
						.append(objectConstraintViolation.getPropertyPath())
						.append("]")
						.append(objectConstraintViolation.getMessage()).append(";");
			}
			throw new ValidationException(errorMsg.toString());
		}
	}
}
