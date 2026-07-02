package org.example.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private String name ;
    private String pwd;
    private String email;
    private int limit;    // 5 sa zyade url short nhi karega ek user , aws nhi hai mongodb hai backend mai.
}
