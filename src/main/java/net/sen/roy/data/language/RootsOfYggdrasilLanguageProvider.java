package net.sen.roy.data.language;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.sen.roy.RootsOfYggdrasil;
import net.sen.roy.common.registries.RootsOfYggdrasilBlocks;
import static net.sen.roy.data.language.RootsOfYggdrasilLang.*;

public class RootsOfYggdrasilLanguageProvider extends LanguageProvider {
    private final String locale;

    public RootsOfYggdrasilLanguageProvider(PackOutput output, String locale) {
        super(output, RootsOfYggdrasil.MODID, locale);
        this.locale = locale;
    }

    public static void define() {
        blockLang(RootsOfYggdrasilBlocks.RIFT_BLOCK).en_us("Rift Block");
    }

    @Override
    protected void addTranslations() {
        getLang(locale).forEach(this::add);
    }
}
