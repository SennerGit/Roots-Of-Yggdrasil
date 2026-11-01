package net.sen.roy.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class RootsOfYggdrasilConfig {
    public static class Common {
        public Common(ModConfigSpec.Builder builder) {}
    }
    public static class Server {
        public Server(ModConfigSpec.Builder builder) {}
    }
    public static class Client {
        public Client(ModConfigSpec.Builder builder) {}
    }

    public static final Common COMMON;
    public static final ModConfigSpec COMMON_SPEC;

    public static final Server SERVER;
    public static final ModConfigSpec SERVER_SPEC;

    public static final Client CLIENT;
    public static final ModConfigSpec CLIENT_SPEC;

    static {
        final Pair<Common, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(RootsOfYggdrasilConfig.Common::new);
        COMMON = commonSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();

        final Pair<Server, ModConfigSpec> serverSpecPair = new ModConfigSpec.Builder().configure(RootsOfYggdrasilConfig.Server::new);
        SERVER = serverSpecPair.getLeft();
        SERVER_SPEC = serverSpecPair.getRight();

        final Pair<Client, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(RootsOfYggdrasilConfig.Client::new);
        CLIENT = clientSpecPair.getLeft();
        CLIENT_SPEC = clientSpecPair.getRight();
    }
}
