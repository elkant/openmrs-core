/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.validator;

import org.junit.Assert;
import org.junit.Test;
import org.openmrs.ConceptDatatype;
import org.openmrs.test.Verifies;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * Tests methods on the {@link ConceptDatatypeValidator} class.
 */
public class ConceptDatatypeValidatorTest {
	
	/**
	 * @see {@link ConceptDatatypeValidator#validate(Object,Errors)}
	 */
	@Test
	@Verifies(value = "should fail validation if name is null or empty or whitespace", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfNameIsNullOrEmptyOrWhitespace() throws Exception {
		ConceptDatatype cd = new ConceptDatatype();
		cd.setName(null);
		cd.setDescription("some text");
		
		Errors errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		cd.setName("");
		errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		cd.setName(" ");
		errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
	}
	
	/**
	 * @see {@link ConceptDatatypeValidator#validate(Object,Errors)}
	 */
	@Test
	@Verifies(value = "should pass validation if description is null or empty or whitespace", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfDescriptionIsNullOrEmptyOrWhitespace() throws Exception {
		ConceptDatatype cd = new ConceptDatatype();
		cd.setName("name");
		cd.setDescription(null);
		
		Errors errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		Assert.assertFalse(errors.hasFieldErrors("description"));
		
		cd.setDescription("");
		errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		Assert.assertFalse(errors.hasFieldErrors("description"));
		
		cd.setDescription(" ");
		errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		Assert.assertFalse(errors.hasFieldErrors("description"));
	}
	
	/**
	 * @see {@link ConceptDatatypeValidator#validate(Object,Errors)}
	 */
	@Test
	@Verifies(value = "should pass validation if all required fields have proper values", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfAllRequiredFieldsHaveProperValues() throws Exception {
		ConceptDatatype cd = new ConceptDatatype();
		cd.setName("name");
		cd.setDescription("some text");
		
		Errors errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		
		Assert.assertFalse(errors.hasErrors());
	}
}
