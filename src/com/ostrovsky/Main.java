package com.ostrovsky;

import java.io.*;

public class Main {
    private final static String ROOT = "/Users/ostrovsky/Games/";

    public static void main(String[] args) {
        StringBuilder log = new StringBuilder();
        int idx = 0;
        File[] dirs = {
                new File(ROOT + "src"),
                new File(ROOT + "res"),
                new File(ROOT + "savegames"),
                new File(ROOT + "temp"),
        };
        for (File dir: dirs) {
            if (dir.mkdir()) {
                idx++;
                log.append(idx).append(". Каталог ").append(dir.getName()).append(" создан").append('\n');
            }
        }

        File[] srcDirs = {
                new File(ROOT + "src/main"),
                new File(ROOT + "src/test"),
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
                new File(ROOT + "src/main/Main.java"),
                new File(ROOT + "src/main/Utils.java"),
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
                new File(ROOT + "res/drawables"),
                new File(ROOT + "res/vectors"),
                new File(ROOT + "res/icons"),
        };

        if (dirs[1].exists()) {
            for (File dir: resDirs) {
                if (dir.mkdir()) {
                    idx++;
                    log.append(idx).append(". Каталог ").append(dir.getName()).append(" создан").append('\n');
                }
            }
        }

        File tempFile = new File(ROOT + "temp/temp.txt");
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
            try (FileWriter fw = new FileWriter(tempFile)) {
                fw.write(completeLog);
                fw.flush();
            } catch (IOException io) {
                System.out.println(io.getMessage());
            }
        }

        /*
            task 2
        */

        GameProgress gameProgress1 = new GameProgress(100, 2, 2, 1.2);
        GameProgress gameProgress2 = new GameProgress(97, 4, 3, 2.1);
        GameProgress gameProgress3 = new GameProgress(98, 4, 3, 2.2);

        gameProgress1.saveGame(ROOT + "savegames/save1.dat", gameProgress1);
        gameProgress1.zipFiles(ROOT + "savegames/zip1.zip", ROOT + "savegames/save1.dat");

        gameProgress2.saveGame(ROOT + "savegames/save2.dat", gameProgress2);
        gameProgress1.zipFiles(ROOT + "savegames/zip2.zip", ROOT + "savegames/save2.dat");

        gameProgress3.saveGame(ROOT + "savegames/save3.dat", gameProgress3);
        gameProgress1.zipFiles(ROOT + "savegames/zip3.zip", ROOT + "savegames/save3.dat");

        // remove all non-zipped files
        for (File save: dirs[2].listFiles()) {
            if (save.getName().contains(".dat")) {
                if (save.delete()) {
                    continue;
                };
            }
        }
    }
}
