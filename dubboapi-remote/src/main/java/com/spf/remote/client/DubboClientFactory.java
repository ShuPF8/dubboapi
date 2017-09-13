/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-13 15:10 创建
 *
 */
package com.spf.remote.client;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author turalyon@jinvovo.com
 */
@Component
public class DubboClientFactory {

	/**
	 * 缓存ID
	 */
	public static final long CACHE_INDEX = 1;
	public static final String DUBBO_DEFUALT_VERSION = "1.0";
	public static final String DUBBO_DEFUALT_GROUP = null;

	@Autowired
	private DubboRemoteProxyFacotry dubboRemoteProxyFacotry;

	// 用于存放已初始化的client
	private volatile static Map<Class<?>, Object> clients = Maps.newConcurrentMap();

	/**
	 * 取得代理对象
	 *
	 * @param clazz 类
	 * @return client对象，需强转
	 */
	@SuppressWarnings("unchecked")
	public <T> T getClient(Class<T> clazz, String group, String version ) {
		T client = null;
		if ((client = (T) clients.get(clazz)) == null) {
			client = dubboRemoteProxyFacotry.getProxy(clazz, group, version);
		}

		return client;
	}
	
	public <T> T getClient(Class<T> clazz, String group, String version, int timeout ) {
		T client = null;
		if ((client = (T) clients.get(clazz)) == null) {
			client = dubboRemoteProxyFacotry.getProxy(clazz, group, version,timeout);
		}
		
		return client;
	}

	public static <T> T getDubbleClient(Class<T> clazz, String url, String group, String version) {
		ReferenceConfig<T> reference = null;// cache.get(key);
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("interchange");

		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setAddress(url);
		registryConfig.setProtocol("zookeeper");

		reference = new ReferenceConfig<T>();
		reference.setRegistry(registryConfig);
		reference.setApplication(applicationConfig);
		reference.setInterface(clazz);
		reference.setVersion(version);
		reference.setGroup(group);
		return reference.get();
	}

}
