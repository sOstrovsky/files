package com.ostrovsky;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        StringBuilder log = new StringBuilder();
        int idx = 0;
        File[] dirs = {
                new File("/Users/ostrovsky/Games/src"),
                new File("/Users/ostrovsky/Games/res"),
                new File("/Users/ostrovsky/Games/savegames"),
                new File("/Users/ostrovsky/Games/temp"),
        };
        for (File dir: dirs) {
            if (dir.mkdir()) {
                idx++;
                log.append(idx).append(". Каталог ").append(dir.getName()).append(" создан").append('\n');
            }
        }

        File[] srcDirs = {
                new File("/Users/ostrovsky/Games/src/main"),
                new File("/Users/ostrovsky/Games/src/test"),
        };
        if (dirs[0].exists()) {
            for (File dir: srcDirs) {
                if (dir.mkdir()) {
                    idx++;
                    log.append(idx).append(". Каталог ").append(dir.getName()).append(" создан").append('\n');
                }
            }
        }


        File[] mainFiles = {
                new File("/Users/ostrovsky/Games/src/main/Main.java"),
                new File("/Users/ostrovsky/Games/src/main/Utils.java"),
        };

        if (srcDirs[0].exists()) {
            for (File file: mainFiles) {
                try {
                    if (file.createNewFile()) {
                        idx++;
                        log.append(idx).append(". Файл ").append(file.getName()).append(" создан").append('\n');
                    };
                } catch (IOException io) {
                    System.out.println(io.getMessage());
                }
            }
        }

        File[] resDirs = {
                new File("/Users/ostrovsky/Games/res/drawables"),
                new File("/Users/ostrovsky/Games/res/vectors"),
                new File("/Users/ostrovsky/Games/res/icons"),
        };

        if (dirs[1].exists()) {
            for (File dir: resDirs) {
                if (dir.mkdir()) {
                    idx++;
                    log.append(idx).append(". Каталог ").append(dir.getName()).append(" создан").append('\n');
                }
            }
        }

        File tempFile = new File("/Users/ostrovsky/Games/temp/temp.txt");
        try {
            if (tempFile.createNewFile()) {
                idx++;
                log.append(idx).append(". Файл ").append(tempFile.getName()).append(" создан").append('\n');
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }

        String completeLog = log.toString();

        if (tempFile.exists()) {
            System.out.println(completeLog);
            try (FileWriter fw = new FileWriter(tempFile)) {
                fw.write(completeLog);
                fw.flush();
            } catch (IOException io) {
                System.out.println(io.getMessage());
            }
        }
    }
}
