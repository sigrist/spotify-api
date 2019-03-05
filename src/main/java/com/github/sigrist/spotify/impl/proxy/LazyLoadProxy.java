package com.github.sigrist.spotify.impl.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.reflect.MethodUtils;

import lombok.NonNull;

public class LazyLoadProxy<T> implements InvocationHandler {

	private final T lazyLoad;
	private boolean called = false;

	public LazyLoadProxy(@NonNull final T lazyLoad) {
		this.lazyLoad = lazyLoad;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result;
		boolean hasAnnotation = isLazy(method);

		System.out.println("Before " + method.getName());

		if (hasAnnotation && !called) {
			call();
		}
		result = method.invoke(lazyLoad, args);
		System.out.println("AFTER  " + method.getName());
		return result;
	}

	private void call() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		List<Method> methods = MethodUtils.getMethodsListWithAnnotation(lazyLoad.getClass(), LazyMethod.class);
		if (methods.size() != 1) {
			throw new RuntimeException("Only one @LazyMethos is allowed. Found: " + methods.size());
		}
		Method lazyMethod = methods.get(0);
		
		lazyMethod.invoke(lazyLoad);
		called = true;
	}

	private Boolean isLazy(Method method) throws NoSuchMethodException, SecurityException {

		Method realMethod = lazyLoad.getClass().getMethod(method.getName(), method.getParameterTypes());

		return realMethod.isAnnotationPresent(Lazy.class);
	}

}
