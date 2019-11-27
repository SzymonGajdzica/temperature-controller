package pl.polsl.temperature.base;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.temperature.exception.WrongBodyException;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PUBLIC)
    protected Long id;

    public abstract void checkPostModel() throws WrongBodyException;

}
