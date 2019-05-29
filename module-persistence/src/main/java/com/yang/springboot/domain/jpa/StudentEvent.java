package com.yang.springboot.domain.jpa;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author yanghao
 * @date 2019-05-28 15:31
 */
@Data
@Document(collection = "student_info")
public class StudentEvent {
    private String id;
    private String userName;
    private String passWord;
}
