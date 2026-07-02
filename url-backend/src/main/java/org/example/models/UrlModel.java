package org.example.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Url_db")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlModel {
    private String url;
    private String smallHashCode;
}
