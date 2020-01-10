package com.hzm.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class WebFilter extends ZuulFilter{
    /**
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型
     * 具体如下：
     * pre ：可以在请求被路由之前调用
     * route ：在路由请求时候被调用
     * post ：在route和error过滤器之后被调用
     * error ：处理请求时发生错误时被调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";// 前置过滤器
    }

    /**
     * filterOrder ：通过返回一个int值来定义过滤器的执行顺序，优先级
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;// 优先级为0，数字越大，优先级越低
    }

    /**
     * shouldFilter ：返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可
     * 实现过滤器的开关。我们直接返回true，所以该过滤器总是生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;// 是否执行该过滤器，此处为true，说明需要过滤
    }

    /**
     * run ：过滤器的具体逻
     * 这里是在过滤器中接收header，转发给微服务
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("web的zuul过滤器...");
        //向header中添加鉴权令牌
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获取header
        HttpServletRequest request = requestContext.getRequest();
        String authorization = request.getHeader("Authorization");
        //路由请求中添加令牌
        if(authorization!=null){
            requestContext.addZuulRequestHeader("Authorization",authorization);
        }
        return null;
    }
}
