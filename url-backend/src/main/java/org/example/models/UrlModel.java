package org.example.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("Url_db")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlModel {
    @Id
    private String id;

    private String url;
    @Indexed(unique = true)
    private String smallHashCode;

    @Indexed(expireAfter = "5h")
    private Date expireAt;

    public UrlModel(String url, String smallHashCode) {
        this.url = url;
        this.smallHashCode = smallHashCode;
        this.expireAt = new Date();
    }
}
