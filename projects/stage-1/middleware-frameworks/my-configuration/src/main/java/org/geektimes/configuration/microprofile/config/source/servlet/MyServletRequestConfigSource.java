package org.geektimes.configuration.microprofile.config.source.servlet;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Ken
 * @className MyServletRequestConfigSource
 * @description ，实现 ServletRequest 请求参数的 ConfigSource（MicroProfile Config）
 * @date 2021/7/12 8:45 下午
 */
public class MyServletRequestConfigSource implements ConfigSource {

    protected final ServletRequest request;

    private final String name;

    public MyServletRequestConfigSource(ServletRequest request, String name) {
        this.request = request;
        this.name = name;
    }

    @Override
    public Map<String, String> getProperties() {

        String value = request.getParameter(name);

        Map<String, String> reqMap = new HashMap<>();

        if (!StringUtils.hasText(value)) {
            return null;
        } else {
            reqMap.put(name, value);
        }

        return reqMap;
    }

    @Override
    public Set<String> getPropertyNames() {

        return request.getParameterMap().keySet();
    }

    @Override
    public String getValue(String name) {
        return request.getParameter(name);
    }

    @Override
    public String getName() {
        return name;
    }
}
