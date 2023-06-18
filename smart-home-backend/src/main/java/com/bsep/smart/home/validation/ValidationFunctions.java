package com.bsep.smart.home.validation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidationFunctions {
    public static boolean validateCommonlyUsedPasswords(String value) throws IOException {
        ClassLoader classLoader = ValidationFunctions.class.getClassLoader();
        URL resourceUrl = classLoader.getResource("commonpasswords.txt");
        File resourceFile = new File(resourceUrl.getFile());
        List<String> lines = Files.readAllLines(resourceFile.toPath(), StandardCharsets.UTF_8);
        Set<String> commonPasswords = new HashSet<>(lines);
        return !commonPasswords.contains(value);
    }
}
