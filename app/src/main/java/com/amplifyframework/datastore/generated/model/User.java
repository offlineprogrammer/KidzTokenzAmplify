package com.amplifyframework.datastore.generated.model;


import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the User type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users")
public final class User implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField DATE_CREATED = field("dateCreated");
  public static final QueryField USER_EMAIL = field("userEmail");
  public static final QueryField USER_ID = field("userId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String dateCreated;
  private final @ModelField(targetType="String", isRequired = true) String userEmail;
  private final @ModelField(targetType="String", isRequired = true) String userId;
  public String getId() {
      return id;
  }
  
  public String getDateCreated() {
      return dateCreated;
  }
  
  public String getUserEmail() {
      return userEmail;
  }
  
  public String getUserId() {
      return userId;
  }
  
  private User(String id, String dateCreated, String userEmail, String userId) {
    this.id = id;
    this.dateCreated = dateCreated;
    this.userEmail = userEmail;
    this.userId = userId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      User user = (User) obj;
      return ObjectsCompat.equals(getId(), user.getId()) &&
              ObjectsCompat.equals(getDateCreated(), user.getDateCreated()) &&
              ObjectsCompat.equals(getUserEmail(), user.getUserEmail()) &&
              ObjectsCompat.equals(getUserId(), user.getUserId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getDateCreated())
      .append(getUserEmail())
      .append(getUserId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("User {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("dateCreated=" + String.valueOf(getDateCreated()) + ", ")
      .append("userEmail=" + String.valueOf(getUserEmail()) + ", ")
      .append("userId=" + String.valueOf(getUserId()))
      .append("}")
      .toString();
  }
  
  public static DateCreatedStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static User justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new User(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      dateCreated,
      userEmail,
      userId);
  }
  public interface DateCreatedStep {
    UserEmailStep dateCreated(String dateCreated);
  }
  

  public interface UserEmailStep {
    UserIdStep userEmail(String userEmail);
  }
  

  public interface UserIdStep {
    BuildStep userId(String userId);
  }
  

  public interface BuildStep {
    User build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements DateCreatedStep, UserEmailStep, UserIdStep, BuildStep {
    private String id;
    private String dateCreated;
    private String userEmail;
    private String userId;
    @Override
     public User build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new User(
          id,
          dateCreated,
          userEmail,
          userId);
    }
    
    @Override
     public UserEmailStep dateCreated(String dateCreated) {
        Objects.requireNonNull(dateCreated);
        this.dateCreated = dateCreated;
        return this;
    }
    
    @Override
     public UserIdStep userEmail(String userEmail) {
        Objects.requireNonNull(userEmail);
        this.userEmail = userEmail;
        return this;
    }
    
    @Override
     public BuildStep userId(String userId) {
        Objects.requireNonNull(userId);
        this.userId = userId;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String dateCreated, String userEmail, String userId) {
      super.id(id);
      super.dateCreated(dateCreated)
        .userEmail(userEmail)
        .userId(userId);
    }
    
    @Override
     public CopyOfBuilder dateCreated(String dateCreated) {
      return (CopyOfBuilder) super.dateCreated(dateCreated);
    }
    
    @Override
     public CopyOfBuilder userEmail(String userEmail) {
      return (CopyOfBuilder) super.userEmail(userEmail);
    }
    
    @Override
     public CopyOfBuilder userId(String userId) {
      return (CopyOfBuilder) super.userId(userId);
    }
  }
  
}
