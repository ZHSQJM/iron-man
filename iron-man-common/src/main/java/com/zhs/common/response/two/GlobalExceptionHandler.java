//package com.zhs.common.response.two;
//
///**
// * @project: iron-man
// * @author: zhs
// * @date: 2020/7/23 14:05
// * @package: com.zhs.common.response.two
// * @description:
// */
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    /**-------- 通用异常处理方法 --------**/
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public R error(Exception e) {
//        e.printStackTrace();
//        return R.error();    // 通用异常结果
//    }
//
//    /**-------- 指定异常处理方法 --------**/
//    @ExceptionHandler(NullPointerException.class)
//    @ResponseBody
//    public R error(NullPointerException e) {
//        e.printStackTrace();
//        return R.setResult(ResultCodeEnum.NULL_POINT);
//    }
//
//    @ExceptionHandler(HttpClientErrorException.class)
//    @ResponseBody
//    public R error(IndexOutOfBoundsException e) {
//        e.printStackTrace();
//        return R.setResult(ResultCodeEnum.HTTP_CLIENT_ERROR);
//    }
//
//    /**-------- 自定义定异常处理方法 --------**/
//    @ExceptionHandler(CMSException.class)
//    @ResponseBody
//    public R error(CMSException e) {
//        e.printStackTrace();
//        return R.error().message(e.getMessage()).code(e.getCode());
//    }
//}
