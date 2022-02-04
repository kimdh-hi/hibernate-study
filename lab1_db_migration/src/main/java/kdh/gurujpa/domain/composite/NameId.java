package kdh.gurujpa.domain.composite;

import java.io.Serializable;

// 복합키로 사용되는 클래스의 경우 직렬화를 반드시 필요로 한다.
public class NameId implements Serializable {

    private String firstName;
    private String lastName;


    public NameId() {
    }

    public NameId(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NameId nameId = (NameId) o;

        if (firstName != null ? !firstName.equals(nameId.firstName) : nameId.firstName != null) return false;
        return lastName != null ? lastName.equals(nameId.lastName) : nameId.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
