package net.sen.roy.data.language;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.sen.roy.RootsOfYggdrasil;

public abstract class LanguageProviderHelper extends LanguageProvider {
    public LanguageProviderHelper(PackOutput output, String locale) {
        super(output, RootsOfYggdrasil.MODID, locale);
    }

    @Override
    protected void addTranslations() {
    }
}
