package com.jaqstoot.leagueutilities.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LcuAuthUtil {
    public String[] getLcuCredentials() throws IOException {
        //todo: add null file validation, needs better data security
        String lockfilePath = System.getenv("LOCALAPPDATA") + "/Riot Games/League of Legends/lockfile";
        String content = Files.readString(Path.of(lockfilePath));
        String[] parts = content.split(":");

        return new String[]{parts[2], parts[3]};
    }
}
