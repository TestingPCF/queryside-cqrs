package com.cqrs.product.queryside.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;

/**
 * This is an entity class, which will represent the complete http response
 * along with data in a json format.
 * @param <E> Order or List
 * @author shikhar.a || ankit-kumar
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class Response<E> {

 /**
  * status containing message and Httpstatus code.
  */
 private final Status status;

 /**
  * Entity containing data.
  */
 private final E data;

 /**
  * Collection having resource data.
  */
 private final Collection<E> dataSet;

 /**
  * setting builder values.
  * @param builder
  *            Builder
  */
 private Response(Builder<E> builder) {
  this.data = builder.data;
  this.status = builder.status;
  this.dataSet = builder.dataSet;
 }

 /**
  * Getter of status.
  * @return status status.
  */
 public Status getStatus() {
  return status;
 }

 /**
  * Gets the data.
  * @return data.
  */
 public E getData() {
  return data;
 }

 /**
  * Returns data set.
  * @return Collection data set
  */
 public Collection<E> getDataSet() {
  return dataSet;
 }

 /**
  * Builder class.
  * @param <E>
  * @author shikhar.a || ankit-kumar
  */
 public static class Builder<E> {
  /**
   * data.
   */
  private E data;

  /**
   * status.
   */
  private Status status;

  /**
   * dataSet.
   */
  private Collection<E> dataSet;

  /**
   * Object instantiation with mandatory fields.
   *
   * @param statusObj Status Object
   */
  public Builder(final Status statusObj) {
   this.status = statusObj;
  }

  /**
   * Builder<E>.
   *
   * @param dataObj
   *            Data
   * @return builder Builder.
   */
  public final Builder<E> setEntity(final E dataObj) {
   this.data = dataObj;
   return this;
  }

  /**
   * Builder<E>.
   *
   * @param dataSetObj
   *            dataset
   * @return builder Builder.
   */
  public final Builder<E> setCollection(
    final Collection<E> dataSetObj) {
   this.dataSet = dataSetObj;
   return this;
  }

  /**
   * /** ResponseStatus<E>.
   *
   * @return responseStatus status.
   */
  public final Response<E> build() {
   return new Response<E>(this);
  }
 }

}
