package com.ethanco.mvvm_bridge_anno;

public interface AbstractInjector<T>
{
	void inject(Finder finder, T target, Object source);
}
