package com.github.sigrist.spotify.impl.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.reflect.MethodUtils;

import com.github.sigrist.spotify.SpotifyAPIException;

public class LazyLoadProxy<T> implements InvocationHandler {

	private final T object;
	private boolean called = false;

	public LazyLoadProxy(final T lazyLoad) {
		this.object = lazyLoad;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		final boolean hasAnnotation = isLazy(method);

		if (hasAnnotation && !called) {
			call();
		}
		return method.invoke(object, args);
	}

	private void call() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		final List<Method> methods = MethodUtils.getMethodsListWithAnnotation(object.getClass(), LazyMethod.class);
		if (methods.size() != 1) {
			throw new SpotifyAPIException("Only one @LazyMethos is allowed. Found: " + methods.size());
		}
		final Method lazyMethod = methods.get(0);

		lazyMethod.invoke(object);
		called = true;
	}

	private Boolean isLazy(Method method) throws NoSuchMethodException, SecurityException {

		final Method realMethod = object.getClass().getMethod(method.getName(), method.getParameterTypes());

		return realMethod.isAnnotationPresent(Lazy.class);
	}

}
