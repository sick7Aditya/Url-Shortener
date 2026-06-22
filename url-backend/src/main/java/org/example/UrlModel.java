package org.example;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Url_db")
@Data
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class UrlModel {


    private String url;
    private String smallHashCode;

//    public UrlModel(String url,String SmallHashCode)
//    {
//        this.url = url;
//        this.SmallHashCode=SmallHashCode;
//    }

}
