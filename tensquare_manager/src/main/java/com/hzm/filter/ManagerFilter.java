package com.hzm.filter;

import com.hzm.util.JwtUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ManagerFilter extends ZuulFilter {
    @Autowired
    private JwtUtil jwtUtil;
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

    @Override
    public Object run() throws ZuulException {
        System.out.println("manager的Zuul过滤器");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        if(request.getMethod().equals("OPTIONS")){
            return null;
        }
        //登录不用验证
        String url = request.getRequestURL().toString();
        if(url.indexOf("/admin/login")>0){
            System.out.println("登陆页面"+url);
            return null;
        }
        //验证token
        String authHeader =(String) request.getHeader("Authorization");//获取头信息
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);
            Claims claims = jwtUtil.parseJwt(token);
            if(claims!=null){
                //管理员角色
                if("admin".equals(claims.get("roles"))){
                    requestContext.addZuulRequestHeader("Authorization",authHeader);
                    System.out.println("token 验证通过，添加了头信息"+authHeader);
                    return null;
                }
            }
        }
        //验证不通过
        requestContext.setSendZuulResponse(false);//终止运行
        requestContext.setResponseStatusCode(401);//http状态码
        requestContext.setResponseBody("无权访问");
        requestContext.getResponse().setContentType("text/html;charset=UTF‐8");
        return null;
    }
}
