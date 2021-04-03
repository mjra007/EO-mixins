package io.github.crucible.grimoire.mixins.cooldown;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class Cooldown {

  private static final Map<String, Cooldown> cooldowns = new HashMap<>();
  private final int timeInSeconds;
  private final String cooldownName;
  private long start;

  private Cooldown(String cooldownName, int timeInSeconds, long start) {
    this.start=  start;
    this.cooldownName = cooldownName;
    this.timeInSeconds = timeInSeconds;
  }

  public static boolean isInCooldown(String cooldownName) {
    if (getTimeLeft(cooldownName) >= 1) {
      return true;
    } else {
      stop(cooldownName);
      return false;
    }
  }

  public static void stop(String cooldownName) {
    Cooldown.cooldowns.remove(cooldownName);
  }

  public static Cooldown getCooldown(String cooldownName) {
    return cooldowns.getOrDefault(cooldownName, null);
  }

  public static int getTimeLeft(String cooldownName) {
    Cooldown cooldown = getCooldown(cooldownName);
    int f = -1;
    if (cooldown != null) {
      long now = System.currentTimeMillis();
      long cooldownTime = cooldown.start;
      int r = (int) (now - cooldownTime) / 1000;
      f = (r - cooldown.timeInSeconds) * (-1);
    }
    return f;
  }

  public static void start(String id, int timeInSeconds){
    cooldowns.put(id , new Cooldown(id, timeInSeconds, System.currentTimeMillis()));
  }



}
