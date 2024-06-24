package com.example.luckybank.pojo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmailRequest  implements Serializable {
    private String to;
    private String subject;
    private String text;
}

