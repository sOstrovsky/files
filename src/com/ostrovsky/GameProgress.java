package com.ostrovsky;

import java.io.*;
import java.util.zip.ZipEntry;
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

    public void saveGame(String address, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(address)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gameProgress);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void zipFiles(String output, String input) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(output)); FileInputStream fis = new FileInputStream(input)) {
            ZipEntry entry = new ZipEntry("packed.txt");
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public String toString() {
        return "GameProgress{" + "health=" + health + ", weapons=" + weapons + ", lvl=" + lvl + ", distance=" + distance + '}';
    }
}