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
 * @author：LT(286269159@qq.com)
 * @create：2018-03-02 15:35:39
 * @version：2018 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public class ValidateUtils {
	private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

	public static void validate(Object object, Class<?>... groups) {
		Set<ConstraintViolation<Object>> result = VALIDATOR.validate(object);
		if (!CollectionUtils.isEmpty(result)) {
			StringBuilder errorMsg = new StringBuilder();
			for (ConstraintViolation<Object> objectConstraintViolation : result) {
				errorMsg.append(objectConstraintViolation.getMessage()).append(";");
			}
			throw new ValidationException(errorMsg.toString());
		}
	}
}
