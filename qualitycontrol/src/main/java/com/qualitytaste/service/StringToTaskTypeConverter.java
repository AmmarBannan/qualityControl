package com.qualitytaste.service;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.qualitytaste.model.TaskType;

// Since the @Component annotation is used, Spring Boot will automatically detect and 
// register this converter as part of its application context. This ensures that any TaskType
//  mapping in your request handlers (like @RequestMapping or @ModelAttribute) will utilize
//   this converter.
@Component
public class StringToTaskTypeConverter implements Converter<String, TaskType> {

    @Override
    public TaskType convert(String source) {
        return TaskType.valueOf(source.toUpperCase()); // Convert input to uppercase
    }
}

