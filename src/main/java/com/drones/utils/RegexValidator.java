package com.drones.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RegexValidator {

    public static final String MEDICATION_NAME_REGEX = "^[a-zA-Z0-9\\_\\-]*$";

    public static final String MEDICATION_CODE_REGEX = "^[A-Z0-9\\_]*$";

    private Pattern namePattern;

    private Pattern codePattern;

    public RegexValidator() {

        namePattern = Pattern.compile(MEDICATION_NAME_REGEX);

        codePattern = Pattern.compile(MEDICATION_CODE_REGEX);
    }

    public boolean isValidMedicationName(final String name) {

        return namePattern.matcher(name).matches();
    }

    public boolean isValidMedicationCode(final String code) {

        return codePattern.matcher(code).matches();
    }
}
