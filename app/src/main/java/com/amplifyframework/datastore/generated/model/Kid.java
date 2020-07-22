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

/** This is an auto generated class representing the Kid type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Kids")
public final class Kid implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField KID_NAME = field("kidName");
  public static final QueryField MONSTER_IMAGE_RESOURCE_NAME = field("monsterImageResourceName");
  public static final QueryField DATE_CREATED = field("dateCreated");
  public static final QueryField KID_UUID = field("kidUUID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String kidName;
  private final @ModelField(targetType="String", isRequired = true) String monsterImageResourceName;
  private final @ModelField(targetType="String", isRequired = true) String dateCreated;
  private final @ModelField(targetType="String", isRequired = true) String kidUUID;
  public String getId() {
      return id;
  }
  
  public String getKidName() {
      return kidName;
  }
  
  public String getMonsterImageResourceName() {
      return monsterImageResourceName;
  }
  
  public String getDateCreated() {
      return dateCreated;
  }
  
  public String getKidUuid() {
      return kidUUID;
  }
  
  private Kid(String id, String kidName, String monsterImageResourceName, String dateCreated, String kidUUID) {
    this.id = id;
    this.kidName = kidName;
    this.monsterImageResourceName = monsterImageResourceName;
    this.dateCreated = dateCreated;
    this.kidUUID = kidUUID;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Kid kid = (Kid) obj;
      return ObjectsCompat.equals(getId(), kid.getId()) &&
              ObjectsCompat.equals(getKidName(), kid.getKidName()) &&
              ObjectsCompat.equals(getMonsterImageResourceName(), kid.getMonsterImageResourceName()) &&
              ObjectsCompat.equals(getDateCreated(), kid.getDateCreated()) &&
              ObjectsCompat.equals(getKidUuid(), kid.getKidUuid());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getKidName())
      .append(getMonsterImageResourceName())
      .append(getDateCreated())
      .append(getKidUuid())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Kid {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("kidName=" + String.valueOf(getKidName()) + ", ")
      .append("monsterImageResourceName=" + String.valueOf(getMonsterImageResourceName()) + ", ")
      .append("dateCreated=" + String.valueOf(getDateCreated()) + ", ")
      .append("kidUUID=" + String.valueOf(getKidUuid()))
      .append("}")
      .toString();
  }
  
  public static KidNameStep builder() {
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
  public static Kid justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Kid(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      kidName,
      monsterImageResourceName,
      dateCreated,
      kidUUID);
  }
  public interface KidNameStep {
    MonsterImageResourceNameStep kidName(String kidName);
  }
  

  public interface MonsterImageResourceNameStep {
    DateCreatedStep monsterImageResourceName(String monsterImageResourceName);
  }
  

  public interface DateCreatedStep {
    KidUuidStep dateCreated(String dateCreated);
  }
  

  public interface KidUuidStep {
    BuildStep kidUuid(String kidUuid);
  }
  

  public interface BuildStep {
    Kid build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements KidNameStep, MonsterImageResourceNameStep, DateCreatedStep, KidUuidStep, BuildStep {
    private String id;
    private String kidName;
    private String monsterImageResourceName;
    private String dateCreated;
    private String kidUUID;
    @Override
     public Kid build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Kid(
          id,
          kidName,
          monsterImageResourceName,
          dateCreated,
          kidUUID);
    }
    
    @Override
     public MonsterImageResourceNameStep kidName(String kidName) {
        Objects.requireNonNull(kidName);
        this.kidName = kidName;
        return this;
    }
    
    @Override
     public DateCreatedStep monsterImageResourceName(String monsterImageResourceName) {
        Objects.requireNonNull(monsterImageResourceName);
        this.monsterImageResourceName = monsterImageResourceName;
        return this;
    }
    
    @Override
     public KidUuidStep dateCreated(String dateCreated) {
        Objects.requireNonNull(dateCreated);
        this.dateCreated = dateCreated;
        return this;
    }
    
    @Override
     public BuildStep kidUuid(String kidUuid) {
        Objects.requireNonNull(kidUuid);
        this.kidUUID = kidUuid;
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
    private CopyOfBuilder(String id, String kidName, String monsterImageResourceName, String dateCreated, String kidUuid) {
      super.id(id);
      super.kidName(kidName)
        .monsterImageResourceName(monsterImageResourceName)
        .dateCreated(dateCreated)
        .kidUuid(kidUuid);
    }
    
    @Override
     public CopyOfBuilder kidName(String kidName) {
      return (CopyOfBuilder) super.kidName(kidName);
    }
    
    @Override
     public CopyOfBuilder monsterImageResourceName(String monsterImageResourceName) {
      return (CopyOfBuilder) super.monsterImageResourceName(monsterImageResourceName);
    }
    
    @Override
     public CopyOfBuilder dateCreated(String dateCreated) {
      return (CopyOfBuilder) super.dateCreated(dateCreated);
    }
    
    @Override
     public CopyOfBuilder kidUuid(String kidUuid) {
      return (CopyOfBuilder) super.kidUuid(kidUuid);
    }
  }
  
}
