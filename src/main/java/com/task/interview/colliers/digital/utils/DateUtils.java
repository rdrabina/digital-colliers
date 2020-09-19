package com.task.interview.colliers.digital.utils;

import com.task.interview.colliers.digital.exception.InvalidDateParametersException;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateUtils {

    public static Date createDateWithFormat(String date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            log.error("Cannot parse date: " + date + " with format: " + format);
            throw new InvalidDateParametersException();
        }
    }

}
