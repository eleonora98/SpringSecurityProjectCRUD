package com.uni.fmi.task.bakery.exception;

import java.io.Serializable;

public class NoSuchEntityException extends ApplicationException {

	private static final long serialVersionUID = -2897660826146933231L;

	public NoSuchEntityException(Class<?> entityType, Serializable id) {
		super("Entity " + entityType.getSimpleName() + " with " + id + " not found");
	}
}
