package com.pdsl.component.gherkin.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GherkinString {
    private static final Pattern parameterPattern;

    static {
        String regex = "(<[^>]*>)";
        Pattern pattern = Pattern.compile(regex);
        parameterPattern = pattern;
    }

    private final Set<String> parameterSubstitutionKeys;
    private final String rawString;

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
        if (!hasSubstitutions()) {
            return rawString;
        }
        String substitutedString = rawString;
        try {
            for (Map.Entry<String, String> entry : substitutions.entrySet()) {
                substitutedString = substitutedString.replaceAll(entry.getKey(), Matcher.quoteReplacement(entry.getValue()).trim());
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("There was a problem when doing gherkin string substitutions!\n\tRaw string: <BEGIN>" + rawString + "<END>\n\tSubstitutions: " + substitutions.toString());
        }
        // If we didn't substitute everything in the string it was half formed
        if (parameterPattern.matcher(substitutedString).find()) {
            throw new MissingSubstitutionValuesException("Did not have all needed substitutions for gherkin string:\n\t" + rawString);
        }
        return substitutedString;
    }
}