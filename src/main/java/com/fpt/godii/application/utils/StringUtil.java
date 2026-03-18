package com.fpt.godii.application.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtil {
    public static String formatInput(String input){
        return  '%' + input.replaceAll("%", Matcher.quoteReplacement("\\%")) + '%';
    }

    public static String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        //Special case for "đ" and "Đ"
        temp = StringUtils.replace(temp, "đ", "d");
        temp = StringUtils.replace(temp, "Đ", "D");
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
}
