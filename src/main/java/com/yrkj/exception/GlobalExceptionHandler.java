package com.yrkj.exception;

import com.yrkj.model.core.ActionResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xuenianxiang on 2017/4/11.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LogManager.getLogger(getClass());

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ActionResult defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        logger.error(e.getMessage());

        ActionResult result = new ActionResult(false,null,e.getMessage());

        return result;
    }
}
