package com.github.sigrist.spotify.impl.proxy;

import java.lang.reflect.Proxy;

public class SpotifyProxy<T> {

	private final T instance;

	public SpotifyProxy(T instance) {
		this.instance = instance;
	}

	@SuppressWarnings("unchecked")
	public T proxy() {
		return (T) Proxy.newProxyInstance(instance.getClass().getClassLoader(), instance.getClass().getInterfaces(),
				new LazyLoadProxy<T>(instance));
	}
}
