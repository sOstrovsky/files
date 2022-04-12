package com.ostrovsky;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final int health;
    private final int weapons;
    private final int lvl;
    private final double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    public void saveGame(String input, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(input)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gameProgress);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void zipFiles(String output, String input) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(output)); FileInputStream fis = new FileInputStream(input)) {
            String name = input.substring(input.lastIndexOf("/") + 1);
            ZipEntry entry = new ZipEntry("packed_" + name);
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void openZip(String input, String output) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(input))) {
            ZipEntry entry;
            String name;
            while ((entry = zis.getNextEntry()) != null) {
                name = entry.getName();
                name = name.substring(name.lastIndexOf("_") + 1);
                FileOutputStream fos = new FileOutputStream(output + name);
                for (int i = zis.read(); i != -1; i = zis.read()) {
                    fos.write(i);
                }
                fos.flush();
                zis.closeEntry();
                fos.close();
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public GameProgress openProgress(String input) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(input); ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return gameProgress;
    }

    @Override
    public String toString() {
        return "GameProgress{" + "health=" + health + ", weapons=" + weapons + ", lvl=" + lvl + ", distance=" + distance + '}';
    }
}