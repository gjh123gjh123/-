package com.shopping.miaosha.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import com.shopping.miaosha.service.miaoshauserservice;
import com.shopping.miaosha.dao.miaoshauser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class userArgumentResolvers  implements HandlerMethodArgumentResolver {


    @Autowired
    miaoshauserservice userService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz=methodParameter.getParameterType();
        return clazz== miaoshauser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request=nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response=nativeWebRequest.getNativeResponse(HttpServletResponse.class);

        String paramToken=request.getParameter(miaoshauserservice.COOKIE_NAME_TOKEN);
        String cookieToken=getCookieValue(request,miaoshauserservice.COOKIE_NAME_TOKEN);
        if (org.apache.commons.lang3.StringUtils.isEmpty(cookieToken)&& org.apache.commons.lang3.StringUtils.isEmpty(paramToken))
            return null;
        String token= StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        miaoshauser user=userService.getbytoken(response,token);
        return user;
    }

    private String getCookieValue(HttpServletRequest request, String cookiName) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null ||cookies.length<=0){

            return null;

        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookiName))
                return cookie.getValue();
        }
        return null;


//    @Autowired
//    miaoshauserservice miaoshauserservice;
//
//    @Override
//    public boolean supportsParameter(MethodParameter methodParameter) {
//       Class<?> claz=methodParameter.getParameterType();
//        return claz==miaoshauser.class;
//    }

//    @Override
//    public Object resolveArgument(MethodParameter methodParameter,
//                                  ModelAndViewContainer modelAndViewContainer,
//                                  NativeWebRequest nativeWebRequest,
//                                  WebDataBinderFactory webDataBinderFactory
//                                    ) throws Exception {
//        HttpServletRequest request=nativeWebRequest.getNativeRequest(HttpServletRequest.class);
//        HttpServletResponse response=nativeWebRequest.getNativeResponse(HttpServletResponse.class);
//        String paramToken=request.getParameter(miaoshauserservice.COOKIE_NAME_TOKEN);
//        String cookieToken=getCookieValue(request,miaoshauserservice.COOKIE_NAME_TOKEN);
//        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)){
//            return null;
//        }
//        String token=StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
//        return miaoshauserservice.getbytoken(response,token);
//    }
//
//    private String getCookieValue(HttpServletRequest request, String cookieNameToken) {
//        Cookie[] cookies=request.getCookies();
//        for(Cookie cookie:cookies){
//            if(cookie.getName().equals(cookieNameToken)){
//                return cookie.getValue();
//            }
//        }
//        return null;
//    }

    }}
