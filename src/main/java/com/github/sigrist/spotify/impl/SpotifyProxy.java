package com.github.sigrist.spotify.impl;

import java.lang.reflect.Proxy;

import com.github.sigrist.spotify.impl.proxy.LazyLoadProxy;

public class SpotifyProxy<T> {

	private final T instance;

	public SpotifyProxy(T instance) {
		this.instance = instance;
	}

	@SuppressWarnings("unchecked")
	T proxy() {
		return (T) Proxy.newProxyInstance(instance.getClass().getClassLoader(), instance.getClass().getInterfaces(),
				new LazyLoadProxy<T>(instance));
	}
}
