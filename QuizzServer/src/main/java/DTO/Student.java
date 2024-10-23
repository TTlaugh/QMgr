package DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Student {
    private int uid;
    private int groupId;
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
