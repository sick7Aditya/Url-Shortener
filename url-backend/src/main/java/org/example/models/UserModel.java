package org.example.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document("Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    @Id
    private String id;

    private String name ;
    private String pwd;
    private String email;
    private int limit;    // 5 sa zyade url short nhi karega ek user , aws nhi hai mongodb hai backend mai.

    private Set<String> userUrls = new HashSet<>();
}
