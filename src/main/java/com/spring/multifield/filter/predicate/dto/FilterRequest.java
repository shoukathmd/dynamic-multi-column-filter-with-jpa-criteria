package com.spring.multifield.filter.predicate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FilterRequest {
   
   private String firstName;
   private String lastName;
   private String parentName;
   private String grade;
   private String section;
   private String rollNo;

}
