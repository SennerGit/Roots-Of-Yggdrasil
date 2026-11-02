package net.sen.roy.data.language;

import net.minecraft.Util;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class RootsOfYggdrasilLang {
    private static final Map<String, Map<String, String>> DATA = new HashMap<>();

    private RootsOfYggdrasilLang() {}

    public static Entry blockLang(DeferredBlock<?> block) {
        return new Entry(Util.makeDescriptionId("block", block.getId()));
    }

    public static Map<String, String> getLang(String locale) {
        return DATA.getOrDefault(locale, Map.of());
    }

    public static final class Entry {
        private final String key;

        private Entry(String key) {
            this.key = key;
        }

        public Entry name(String locale, String text) {
            Map<String, String> map = DATA.computeIfAbsent(locale, k -> new LinkedHashMap<>());
            if (map.put(key, text) != null) {
                throw new IllegalStateException("Duplicate entry for locale " + locale + ": " + key);
            }
            return this;
        }

        public Entry en_us(String text) { return name("en_us", text); }
    }
}
