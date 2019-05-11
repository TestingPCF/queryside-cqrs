package com.cqrs.product.queryside.bean;

import org.springframework.http.HttpStatus;

/**
 * This is an entity class, which will represent the http response status in
 * json format.
 *
 * @author shikhar.a || ankit-kumar
 */
public class Status {

 /**
  * Http Status.
  */
 private HttpStatus status;

 /**
  * Status Message.
  */
 private String message;

 /**
  * Default Constructor.
  */
 public Status() {
  // TODO Auto-generated method stub
 }

 /**
  * fully parameterized constructor.
  *
  * @param statusObj Status object
  * @param messageObj Message String
  */
 public Status(HttpStatus statusObj, String messageObj) {
  super();
  this.status = statusObj;
  this.message = messageObj;
 }

 /**
  * @return Status code..
  */
 public final int getCode() {
  return status().value();
 }

 /**
  * Getter of status.
  * @return the status
  */
 public final HttpStatus status() {
  return status;
 }

 /**
  * Setter of status.
  * @param statusObj
  *            the status to set
  */
 public final void setStatus(final HttpStatus statusObj) {
  this.status = statusObj;
 }

 /**
  * Getter of message.
  * @return the message
  */
 public final String getMessage() {
  return message;
 }

 /**
  * @param messageObj
  *            the message to set
  */
 public final void setMessage(final String messageObj) {
  this.message = messageObj;
 }

}
