package com.example.demo1.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xmlResponse")
public class CommonReturnType {

    // 表明对应请求的返回处理结果为: "success" or "failed"
    @XmlElement
    private String status;

    // 若status=success, 则data内返回前端需要的json数据
    // 若status=failed, 则data内使用通用的错误码格式
    @XmlElement
    private Object data;

    // 通用静态工厂方法
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result, "success");
    }

    // 封装返回数据data
    public static CommonReturnType create(Object result, String status){
        CommonReturnType returnType = new CommonReturnType();

        returnType.setStatus(status);
        returnType.setData(result);
        return returnType;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
