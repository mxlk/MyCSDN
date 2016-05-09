package com.eric.imitate.icsdn.Bean;

/**
 * Created by Administrator on 2015/11/16.
 */
public class CommonException extends Exception {
    public CommonException(){
        super();
    }
    public CommonException(String message){
        super(message);
    }
    public CommonException(String message, Throwable cause){
        super(message, cause);
    }
    public CommonException(Throwable cause){
        super(cause);
    }
}
