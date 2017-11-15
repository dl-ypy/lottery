package com.lottery.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 封装返回的提示信息并序列化，返回为json格式
 * @author Administrator
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)   //表示序列化时，如果有值为null的字段，就不对该字段进行序列化
public class ServerResponse<T> implements Serializable {
	private int status;
	private String msg;
	private T data;
	
	public ServerResponse(int status) {
		this.status = status;
	}
	
	public ServerResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	public ServerResponse(int status, T data) {
		this.status = status;
		this.data = data;
	}
	
	public ServerResponse(int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}

	public T getData() {
		return data;
	}
	
	/**
	 * 判断是否返回成功
	 * 在登录成功后，返回status为0，此时就可通过isLogin判断是否登录
	 * @return
	 */
	@JsonIgnore    //表示序列化后，此字段不会显示到json中
	public boolean isLogin() {
		return this.status == 0;
	}
	
	/**
	 * 只返回状态的成功信息
	 * @return
	 */
	public static <T> ServerResponse<T> createBySuccess() {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
	}
	
	/**
	 * 只返回状态和一个泛型数据的成功信息
	 * @return
	 */
	public static <T> ServerResponse<T> createBySuccess(T data) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
	}
	
	/**
	 * 只返回状态和信息的成功信息
	 * @return
	 */
	public static <T> ServerResponse<T> createBySuccessMessage(String msg) {   //为了区分传的参数，将方法名设为不同的
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
	}
	
	/**
	 * 返回所有信息的成功信息
	 * @return
	 */
	public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
	}
	
	/**
	 * 返回错误码和错误信息
	 * @return
	 */
    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    /**
     * 返回错误码和自定义错误信息
     * @param errorMessage
     * @return
     */
    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    /**
     * 返回自定义错误码和自定义错误信息
     * @param errorCode
     * @param errorMessage
     * @return
     */
    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }
}
