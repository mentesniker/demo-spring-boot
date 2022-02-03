package com.example.demo.api.exceptions;
import com.example.demo.api.utils.HttpEstado;

public class ServiceException extends ControllerException {


    /**
     * <p>Constructor for ServiceException.</p>
     *
     * @param shortMessage a {@link java.lang.String} object.
     * @param detailedMessage a {@link java.lang.String} object.
     * @param localExceptionNumber a int.
     * @param localExceptionKey a {@link java.lang.String} object.
     * @param httpStatus a {@link io.kebblar.petstore.api.model.enumerations.HttpStatus} object.
     */
    public ServiceException(
            String shortMessage,
            String detailedMessage,
            int localExceptionNumber,
            String localExceptionKey,
            HttpEstado httpStatus) {
        super(shortMessage, detailedMessage, localExceptionNumber, localExceptionKey, httpStatus);
    }


    public ServiceException(
            Throwable throwable,
            String shortMessage,
            String detailedMessage,
            int exceptionNumber,
            String message,
            HttpEstado httpStatus) {
        super(throwable,shortMessage,detailedMessage,exceptionNumber,message,httpStatus);
    }

}