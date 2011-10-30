package br.ufpe.cin.rgms.base;

public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9215011216043082566L;
	
	private Class<?> className;
	private String fieldName;
	private String fieldValue;
	private IValidator validator;

	public ValidationException(Class<?> className, String fieldName, String fieldValue, IValidator validator) {
		this.className = className;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.validator = validator;
	}

	public Class<?> getClassName() {
		return className;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public IValidator getValidator() {
		return validator;
	}

	public String toString() {
		return String.format("%s %s=%s failed %s", this.className.getName(), this.fieldName, this.fieldValue, this.validator.getClass().getName());
	}
}
