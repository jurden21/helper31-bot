package ru.jurden.helper31bot.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

import java.nio.charset.StandardCharsets;

@UtilityClass
public class ResourceUtils {

    public String readToString(String path) {
        try {
            return IOUtils.resourceToString(path, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalStateException("Error during reading file from resources: " + path, e);
        }
    }

}
