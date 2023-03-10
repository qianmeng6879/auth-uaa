package top.mxzero.uaa.exception;

/**
 * @author Zero
 * @email qianmeng6879@163.com
 * @since 2023/3/5
 */
public class ServiceException extends RuntimeException{
    public ServiceException(String message){
        super(message);
    }
}
