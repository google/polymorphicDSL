package com.google.pdsl.gherkin.models;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GherkinString {
    private Set<String> parameterSubstitutionKeys;
    private String rawString;
    private static final Pattern parameterPattern;

    static {
        String regex = "(<[^>]*>)";
        Pattern pattern = Pattern.compile(regex);
        parameterPattern = pattern;
    };

    public GherkinString(String data) {
        Matcher matcher = parameterPattern.matcher(data);
        List<String> p = new LinkedList<>();
        if (matcher.find()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                p.add(matcher.group(i));
            }
        }
        this.parameterSubstitutionKeys = Set.copyOf(p);
        this.rawString = data;
    }

    public boolean hasSubstitutions() {
        return !parameterSubstitutionKeys.isEmpty();
    }

    public String getRawString() {
        return rawString;
    }

    public String getStringWithSubstitutions(Map<String, String> substitutions) {
        if (!hasSubstitutions()) { return rawString; }
        String substitutedString = rawString;
        for (Map.Entry<String, String> entry : substitutions.entrySet()) {
            substitutedString = substitutedString.replaceAll(entry.getKey(), entry.getValue()).trim();
        }
        // If we didn't substitute everything in the string it was half formed
        if (parameterPattern.matcher(substitutedString).find()) {
            throw new MissingSubstitutionValuesException("Did not have all needed substitutions for gherkin string:\n\t" + rawString);
        }
        return substitutedString;
    }
}